package com.suypower.cloudx.storage.client;

import com.suypower.cloudx.storage.core.entity.DataFile;
import com.suypower.cloudx.storage.core.entity.DataToken;
import com.suypower.cloudx.storage.core.entity.FileOption;
import com.suypower.cloudx.storage.core.entity.UserOption;
import com.suypower.cloudx.storage.core.service.IDataFileService;
import com.suypower.cloudx.storage.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bingdor on 2015/12/8.
 */
@Component
public class AppSupportClient {
    @Autowired
    private IDataFileService dataFileService;

    /**
     * 上传文件至非结构化存储
     * @param userOption 用户选项
     * @param fileOption 文件选项
     * @return 文件唯一标识
     * @throws DataException
     */
    public String uploadFile(UserOption userOption, FileOption fileOption) throws DataException {
        return dataFileService.saveDataFile(userOption, fileOption);
    }

    /**
     * 克隆已存在的文件
     * @param userOption
     * @param dataID
     * @return
     * @throws DataException
     */
    public String cloneFile(UserOption userOption,String dataID)throws DataException {
        return dataFileService.cloneDataFile(userOption, dataID);
    }

    public DataFile queryFile(UserOption userOption,String dataID) throws DataException{
        DataFile dataFile = dataFileService.findDataFileById(userOption,dataID);
        if(dataFile != null){
            dataFile.setPath(null);
            dataFile.setUploadUser(null);
            dataFile.setDataApp(null);
            dataFile.setStatus(null);
        }
        return dataFile;
    }

    /**
     * 删除已存在的文件
     * @param userOption 用户选项
     * @param dataID 文件唯一标识
     * @return
     * @throws DataException
     */
    public Boolean deleteDataFile(UserOption userOption, String dataID) throws DataException {
        return dataFileService.deleteDataFile(userOption, dataID);
    }

    /**
     * 批量删除已存在的文件
     * @param userOption 用户选项
     * @param dataIDs 文件唯一标识
     * @return
     * @throws DataException
     */
    public Boolean deleteDataFiles(UserOption userOption, List<String> dataIDs) throws DataException {
        return dataFileService.deleteDataFile(userOption, dataIDs);
    }

    /**
     * 生成文件访问令牌
     * @param userOption 用户选项
     * @param dataID 文件唯一标识
     * @return
     * @throws DataException
     */
    public String generateToken(UserOption userOption, String dataID) throws DataException {
        DataToken dataToken = dataFileService.generateToken(userOption, dataID);
        return dataToken.getToken();
    }

    /**
     * 将临时文件提交至永久存储区
     * @param userOption 用户选项
     * @param dataIDs 文件唯一标识
     * @return
     * @throws DataException
     */
    public Boolean commitTempFiles(UserOption userOption,List<String> dataIDs) throws DataException{
        return dataFileService.commitTmpFiles(userOption,dataIDs);
    }

    /**
     * 获取下载路径URI（需在此URI前添加http://ip:port/appname）
     * @param token 访问令牌
     * @return
     */
    public String getDownloadURI(String token) {
        return "/data/preview/" + token;
    }

}
