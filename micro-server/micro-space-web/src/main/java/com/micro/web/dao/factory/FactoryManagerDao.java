package com.micro.web.dao.factory;

import com.micro.web.dao.MyBatisCrudRepository;
import com.micro.web.entity.factory.FactoryImage;
import com.micro.web.entity.factory.FactoryInfo;

import java.util.List;

public interface FactoryManagerDao extends MyBatisCrudRepository {
    void updateFactoryInfo(FactoryInfo factoryInfo);

    void insertFactoryInfo(FactoryInfo factoryInfo);

    void deleteFactoryInfoById(int id);
    List<FactoryInfo> queryAllFactoryInfo();

    void insertImageInfo(FactoryImage factoryImage);

    void deleteImageInfoById(int id);
}
