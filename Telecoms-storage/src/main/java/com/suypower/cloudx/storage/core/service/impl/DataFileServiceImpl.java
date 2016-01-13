package com.suypower.cloudx.storage.core.service.impl;

import com.suypower.cloudx.storage.core.dao.DataFileMapper;
import com.suypower.cloudx.storage.core.dao.DataTokenMapper;
import com.suypower.cloudx.storage.core.entity.DataFile;
import com.suypower.cloudx.storage.core.entity.DataToken;
import com.suypower.cloudx.storage.core.entity.FileOption;
import com.suypower.cloudx.storage.core.entity.UserOption;
import com.suypower.cloudx.storage.core.service.IDataFileService;
import com.suypower.cloudx.storage.exception.DataException;
import com.suypower.cloudx.storage.identify.entity.DataUser;
import com.suypower.cloudx.storage.identify.service.IDataUserService;
import com.suypower.cloudx.storage.support.StorageHelper;
import com.suypower.cloudx.storage.system.dao.DataConfigMapper;
import com.suypower.cloudx.storage.system.entity.ConfigConst;
import com.suypower.cloudx.storage.system.entity.DataConfig;
import com.suypower.cloudx.support.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Bingdor on 2015/12/3.
 */
@Service
public class DataFileServiceImpl implements IDataFileService {
    @Autowired
    private DataFileMapper dataFileMapper;
    @Autowired
    private IDataUserService dataUserService;
    @Autowired
    private DataConfigMapper dataConfigMapper;
    @Autowired
    private DataTokenMapper dataTokenMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String saveDataFile(UserOption userOption, FileOption fileOption) throws DataException {
        //[1]验证用户
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        //[2]存储文件
        String filePath = null;
        DataConfig dataConfig = null;
        DataFile dataFile = new DataFile();
        dataFile.setDataApp(dataUser.getDataApp());
        dataFile.setFileName(fileOption.getFileName());
        dataFile.setDataGroup(fileOption.getDataGroup());
        dataFile.setFileType(fileOption.getFileType());
        dataFile.setUploadUser(dataUser);
        if (fileOption.isTemp()) {
            dataConfig = dataConfigMapper.queryDataConfig(ConfigConst.TEMP_DIR);
            try {
                filePath = StorageHelper.saveFile(userOption.getAppKey(), dataConfig.getConfigValue(), fileOption);
                dataFile.setFileSize(StorageHelper.getFileSize(filePath));
                dataFile.setContentType(StorageHelper.getContentType(filePath));
                dataFile.setShaCode(StorageHelper.getShaCode(filePath));
                dataFile.setPath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataException("文件写入失败");
            }
            //[3]存储数据表
            dataFileMapper.insertTempDataFile(dataFile);
        } else {
            dataConfig = dataConfigMapper.queryDataConfig(ConfigConst.HOME_DIR);
            try {
                filePath = StorageHelper.saveFile(userOption.getAppKey(), dataConfig.getConfigValue(), fileOption);
                dataFile.setFileSize(StorageHelper.getFileSize(filePath));
                dataFile.setContentType(StorageHelper.getContentType(filePath));
                dataFile.setShaCode(StorageHelper.getShaCode(filePath));
                dataFile.setPath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataException("文件写入失败");
            }
            dataFileMapper.insertDataFile(dataFile);
        }
        if (dataConfig == null) {
            throw new DataException("上下文根目录未定义");
        }
        return dataFile.getDataID();
    }

    public String cloneDataFile(UserOption userOption, String dataID) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        DataFile dataFile = dataFileMapper.queryDataFile(dataID);
        if(dataFile.getUploadUser().getUserID().equals(dataUser.getUserID())){
            try {
                dataFile.setDataID(null);
                dataFile.setPath(StorageHelper.copyFile(dataFile.getPath()));
                dataFileMapper.insertDataFile(dataFile);
                return dataFile.getDataID();
            } catch (IOException e) {
                e.printStackTrace();
                throw new DataException("文件克隆失败");
            }
        }
        throw new DataException("文件操作越权");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteDataFile(UserOption userOption, String dataID) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        if (dataUser != null) {
            DataFile dataFile = dataFileMapper.queryDataFile(dataID);
            if (dataFile != null) {
                dataFileMapper.deleteDataFile(dataID);
                try {
                    StorageHelper.deleteFile(dataFile.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new DataException("文件删除失败");
                }
            }
            return true;
        }else{
            throw new DataException("用户或密码错误");
        }
    }

    public Boolean deleteDataFile(UserOption userOption, List<String> dataID) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        if (dataUser != null) {
            List<DataFile> dataFiles = dataFileMapper.queryDataFiles(dataID);
            List<String> list = new ArrayList<String>();
            for (DataFile dataFile : dataFiles){
                if (dataFile != null) {
                    list.add(dataFile.getPath());
                }
            }
            try {
                dataFileMapper.deleteDataFiles(list);
                StorageHelper.deleteFiles(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }else{
            throw new DataException("用户或密码错误");
        }
    }

    public DataFile findDataFile(String token) throws DataException {
        DataToken dataToken = dataTokenMapper.queryDataToken(token);
        if (dataToken != null) {
            DataFile dataFile = dataFileMapper.queryDataFile(dataToken.getDataFile().getDataID());
            if (dataFile != null) {
                return dataFile;
            }
        }
        throw new DataException("文件不存在");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DataToken generateToken(UserOption userOption, String dataID) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        DataFile dataFile = dataFileMapper.queryDataFile(dataID);
        DataToken dataToken = null;
        if (dataFile == null) {
            throw new DataException("文件不存在");
        }
        if (dataUser != null) {
            dataToken = new DataToken();
            dataToken.setDataFile(dataFile);
            dataToken.setDataUser(dataUser);
            dataToken.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
            try {
                dataTokenMapper.insertDataToken(dataToken);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataException("Token生成失败");
            }
        }
        return dataToken;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean commitTmpFiles(UserOption userOption, List<String> dataIDs) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        List<DataFile> files = dataFileMapper.queryTempFiles(dataIDs);
        if (files == null || files.isEmpty()) {
            throw new DataException("文件不存在");
        }
        Boolean flag = true;
        for (DataFile dataFile : files) {
            if (!dataFile.getUploadUser().getUserID().equals(dataUser.getUserID())) {
                flag = false;
                break;
            }
        }
        DataConfig dataConfig = dataConfigMapper.queryDataConfig(ConfigConst.HOME_DIR);
        if (!flag) {
            throw new DataException("文件操作越权");
        }
        //将临时文件提交至永久存储区
        String oldPath = null;
        String newPath = null;
        for (DataFile dataFile : files) {
            oldPath = dataFile.getPath();
            File file = new File(oldPath);
            newPath = dataConfig.getConfigValue() + "/" +
                    dataFile.getDataApp().getAppKey() + "/" +
                    DateTime.dateToStr(new Date()) + "/" +
                    dataFile.getDataGroup() + "/" + file.getName();
            dataFile.setPath(newPath);
            try {
                StorageHelper.moveFile(oldPath,newPath);
            }catch (IOException e){
                throw new DataException("文件提交失败");
            }
//            dataFileMapper.insertDataFile(dataFile);
        }
        dataFileMapper.insertDataFileWithBatch(files);
        dataFileMapper.deleteTempFiles(dataIDs);
        return true;
    }

    public Boolean destoryToken(String token) throws DataException {
        return dataTokenMapper.updateDataToken(token) == 1;
    }

    public DataFile findDataFileById(UserOption userOption, String dataID) throws DataException {
        DataUser dataUser = dataUserService.validateUser(userOption.getAppKey(), userOption.getUsername(), userOption.getPassword());
        return dataFileMapper.queryDataFile(dataID);
    }
}
