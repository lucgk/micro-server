package com.micro.web.common.utils.resourceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @desc Spring框架为了更方便的获取资源，尽量弱化程序员对各个Resource接口的实现类的感知，定义了另一个ResourceLoader接口。
 *        该接口的getResource(String location)方法可以用来获取资源。它的DefaultResourceLoader实现类可以适用于所有的环境，
 *        可以返回ClassPathResource、UrlResource等。
 *         ResourceLoader在进行加载资源时需要使用前缀来指定需要加载：“classpath:path”表示返回ClasspathResource，
 *         “http://path”和“file:path”表示返回UrlResource资源，
 *         如果不加前缀则需要根据当前上下文来决定，DefaultResourceLoader默认实现是加载classpath资源
 */
public abstract class ResourceLoaderUtils {

    static Logger logger = LoggerFactory.getLogger(ResourceLoaderUtils.class);
    static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public static void main(String[] args) {
        try {
            readFromClasspath();
            readFromHttp();
            readFromFile();
            readFromFTP();
            readFromNoPreFix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 
     * @Title: readFromClasspath
     * @Description: 读取 classpath: 地址前缀的文件
     * @throws IOException
     * @return: void
     */

    public static void readFromClasspath() throws IOException {
        Resource[] resources = resourcePatternResolver.getResources("classpath*:com/xgj/conf/**/*.xml");
        for (Resource resource : resources) {
            System.out.println(resource.getDescription());
            readContent(resource);
        }

    }

    public static void readFromNoPreFix() throws IOException {
        Resource resource = resourcePatternResolver.getResource("spring-context.xml");
        System.out.println(resource.getDescription());
        readContent(resource);
    }

    /**
     * 
     * 
     * @Title: readFromFile
     * @Description: 使用UrlResource从文件系统目录中装载资源，可以采用绝对路径或者相对路径
     * @throws IOException
     * @return: void
     */
    public static void readFromFile() throws IOException {
        Resource resource = resourcePatternResolver.getResource(
                "file:/D:/workspace/workspace-jee/HelloSpring/hello-spring4/src/main/java/com/xgj/conf/conf2/test2.xml");
        readContent(resource);
    }

    /**
     * 
     * 
     * @Title: readFromHttp
     * 
     * @Description: 使用UrlResource从web服务器中加载资源
     * 
     * @throws IOException
     * 
     * @return: void
     */
    public static void readFromHttp() throws IOException {
        Resource resource = resourcePatternResolver.getResource("http://127.0.0.1:8080/hello-spring4/index.jsp");
        System.out.println(resource.getDescription());
        readContent(resource);
    }

    /**
     * 
     * 
     * @Title: readFromFTP
     * @Description: 这里只演示写法，因为这个服务器要求用户名和密码，其实是无法读取的。
     * @throws IOException
     * @return: void
     */
    public static void readFromFTP() throws IOException {
        Resource resource = resourcePatternResolver
                .getResource("ftp://172.25.243.81/webserver/config/logback.xml");
    }

    /**
     * 
     * 
     * @Title: readContent
     * @Description: 读取获取到的资源文件的内容
     * @param resource
     * @throws IOException
     * @return: void
     */
    public static void readContent(Resource resource) throws IOException {
        InputStream ins = resource.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i;
        while ((i = ins.read()) != -1) {
            bos.write(i);
        }
        logger.debug("读取的文件:" + resource.getFilename() + ",/n内容:/n" + bos.toString());
    }

}