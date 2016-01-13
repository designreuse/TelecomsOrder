package com.suypower.cloudx.storage.core.dao;

import com.suypower.cloudx.storage.core.entity.DataFile;

import java.util.List;

/**
 * Created by Bingdor on 2015/12/3.
 */
public interface DataFileMapper {

    public Integer insertDataFile(DataFile dataFile);

    public DataFile queryDataFile(String dataID);

    public List<DataFile> queryDataFiles(List<String> dataID);

    public Integer deleteDataFile(String dataID);

    public Integer deleteDataFiles(List<String> dataIDs);

    public Integer insertTempDataFile(DataFile dataFile);

    public Integer insertFromTempDataFile(List<String> list);

    public List<DataFile> queryTempFiles(List<String> list);

    public Integer deleteTempFiles(List<String> list);

    public Integer insertDataFileWithBatch(List<DataFile> list);
}
