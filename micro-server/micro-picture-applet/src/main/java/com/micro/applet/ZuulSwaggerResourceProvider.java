package com.micro.applet;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.Arrays;
import java.util.List;

@Component
@Primary
public class ZuulSwaggerResourceProvider implements SwaggerResourcesProvider {

    @Value("${swagger.rest.apiNames}")
    private String[] apiNames;

    @Override
    public List<SwaggerResource> get() {
        List resources = Lists.newArrayList();
        if (null != apiNames) {
            Arrays.stream(apiNames).forEach(apiName -> {
                if(apiName.indexOf("/")>0){
                    apiName = apiName.substring(0,apiName.indexOf("/"));// zuul.routes.dataservice.url=http://localhost:8081/dataservice如不配置上下文路径r(如dataservice)，需注释该行代码
                    resources.add(swaggerResource(apiName,  "/"+apiName + "/v2/api-docs", "2.0"));
                }else {
                    resources.add(swaggerResource(apiName,   "/v2/api-docs", "2.0"));
                }
            });
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String apiName, String location, String version) {
        SwaggerResource sr = new SwaggerResource();
        if(apiName.indexOf("/")>0){
            sr.setName(apiName.substring(apiName.indexOf("/")+1));
        }else {
            sr.setName(apiName);
        }
//        sr.setName(apiName);
        sr.setLocation(location);
        sr.setSwaggerVersion(version);
        return sr;
    }
}