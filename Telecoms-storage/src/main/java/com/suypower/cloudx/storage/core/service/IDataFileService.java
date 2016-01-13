package com.suypower.cloudx.storage.core.service;

import com.suypower.cloudx.storage.core.entity.DataFile;
import com.suypower.cloudx.storage.core.entity.DataToken;
import com.suypower.cloudx.storage.core.entity.FileOption;
import com.suypower.cloudx.storage.core.entity.UserOption;
import com.suypower.cloudx.storage.exception.DataException;

import java.util.List;

/**
 * Created by Bingdor on 2015/12/3.
 */
public interface IDataFileService {
    public String saveDataFile(UserOption userOption, FileOption fileOption) throws DataException;

    public String cloneDataFile(UserOption userOption, String dataID) throws DataException;

    public Boolean deleteDataFile(UserOption userOption,String dataID) throws DataException;

    public Boolean deleteDataFile(UserOption userOption,List<String> dataID) throws DataException;

    public DataFile findDataFile(String token) throws DataException;

    public DataToken generateToken(UserOption userOption,String dataID) throws DataException;

    public Boolean commitTmpFiles(UserOption userOption,List<String> dataIDs) throws DataException;

    public Boolean destoryToken(String token) throws DataException;

    public DataFile findDataFileById(UserOption userOption,String dataID) throws DataException;

}
