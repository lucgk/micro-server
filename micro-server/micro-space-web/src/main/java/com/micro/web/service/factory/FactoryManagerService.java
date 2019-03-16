package com.micro.web.service.factory;

import com.micro.web.common.utils.LocalDateUtil;
import com.micro.web.dao.factory.FactoryManagerDao;
import com.micro.web.entity.factory.FactoryImage;
import com.micro.web.entity.factory.FactoryInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
    private FactoryManagerDao factoryManagerDao ;

    @Value("${applet.upload.base.dir}")
    private String uploadPath;
    @Value("${applet.server.image}")
    private String imageServer;

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
        List<FactoryInfo> factoryInfos = factoryManagerDao.queryAllFactoryInfo();
        for(FactoryInfo factoryInfo:factoryInfos){
            if(StringUtils.isNotBlank(factoryInfo.getImagePath())){
                factoryInfo.setImagePath(imageServer+factoryInfo.getImagePath());
            }
        }
        return factoryInfos;
    }

    public void uploadImage(MultipartFile image, String factoryId, String isCore) throws IOException {
        String filePath = "image"+File.separator+LocalDateUtil.getLocalDate() +File.separator+image.getOriginalFilename();
        File file = new File(uploadPath+File.separator+filePath);
        FileUtils.copyInputStreamToFile(image.getInputStream(),file);
        FactoryImage factoryImage = new FactoryImage(factoryId,filePath,isCore);
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


    public List<FactoryImage> queryFactoryImages(String factoryId, String isCore) {
        FactoryImage para = new FactoryImage();
        para.setFactoryId(factoryId+"");
        if(StringUtils.isNotBlank(isCore)){
            para.setIsCore(isCore);
        }
        return  factoryManagerDao.queryFactoryImages(para);
    }
}
