package com.micro.web.controller;

import com.micro.web.common.exception.CommonException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

public abstract class BaseController {

    protected ResponseEntity<InputStreamResource> buildResponseEntity(File file) throws CommonException {
        ResponseEntity<InputStreamResource>  re = null;
        try {
            FileSystemResource fsr = new FileSystemResource(file);
            HttpHeaders header = new HttpHeaders();
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Content-Disposition", String.format("attachment; filename=\"%s\"",fsr.getFilename()));
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            re =  ResponseEntity
                                .ok()
                                .headers(header)
                                .contentLength(fsr.contentLength())
                                .contentType(MediaType.parseMediaType("application/octet-stream"))
                                .body(new InputStreamResource(fsr.getInputStream()));
        } catch (IOException e) {
            throw new CommonException("构建ResponseEntity<InputStreamResource>异常 ",e);
        }
        return re;
    }

}
