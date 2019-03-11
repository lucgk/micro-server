package com.micro.web.service.factory;

import com.micro.web.common.utils.LocalDateUtil;
import com.micro.web.dao.factory.FactoryManagerDao;
import com.micro.web.entity.factory.FactoryImage;
import com.micro.web.entity.factory.FactoryInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FactoryManagerService {

    @Autowired
    FactoryManagerDao factoryManagerDao ;

    @Value("${applet.upload.base.dir}")
    private String uploadPath;

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

    public void uploadImage(MultipartFile image, String factoryId, String isCore) throws IOException {
        File file = new File(uploadPath+File.separator+"image"+File.separator+LocalDateUtil.getLocalDate() +File.separator+image.getOriginalFilename());
        FileUtils.copyInputStreamToFile(image.getInputStream(),file);
        FactoryImage factoryImage = new FactoryImage(factoryId,file.getAbsolutePath(),isCore);
        factoryManagerDao.insertImageInfo(factoryImage);
    }

    public void batchUploadImages(MultipartFile[] images, String factoryId) {
        for(MultipartFile image : images){
            System.out.println(image.getName());
        }

    }

    public void deleteImageInfoById(int id) {
        factoryManagerDao.deleteImageInfoById(id);
    }
}