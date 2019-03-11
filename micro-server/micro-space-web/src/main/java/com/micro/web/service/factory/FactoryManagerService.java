package com.micro.web.service.factory;

import com.micro.web.dao.factory.FactoryManagerDao;
import com.micro.web.entity.factory.FactoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryManagerService {

    @Autowired
    FactoryManagerDao factoryManagerDao ;

    public void saveFactoryInfo(FactoryInfo factoryInfo) {
        if(factoryInfo.getId()>0){
            factoryManagerDao.updateFactoryInfo(factoryInfo);
            return;
        }
        factoryManagerDao.insertFactoryInfo(factoryInfo);
    }

    public void deleteFactoryInfo(int id) {
        factoryManagerDao.deleteFactoryInfoById(id);
    }

    public List<FactoryInfo> queryAllFactoryInfo() {
        return factoryManagerDao.queryAllFactoryInfo();
    }
}
