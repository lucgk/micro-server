package com.micro.web.common.utils.resourceUtil;

import org.springframework.core.io.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @desc 通过Resource 接口获取资源
 */
public class CommonResourceUtils {

    public static void main(String[] args) throws Exception {

        loadClassPathResource("db/sqlite.properties");
        InputStream inputStream = loadFileSystemResource("E:\\GIT\\micro\\micro-server\\micro-space-web\\src\\main\\resources\\db\\sqlite.properties");
        loadFileSystemResource("src/main/resources/db/sqlite.properties");
        loadUrlResource("http://www.lhspace.info/space/swagger-ui.html");
        loadByteArrayResource("hello".getBytes());
        loadInputStreamResource(inputStream);
    }

    /**
     * @desc 通过类路径获取资源文件
     * ClassPathResource  以类路径的方式访问
     * @note  loadClassPathResource("db/sqlite.properties"); 不能带classpath
     * 如果没有指定相对的类名，该类将从类的根路径开始寻找某个resource，如果指定了相对的类名，则根据指定类的相对路径来查找某个resource。
     * Resource rs = new ClassPathResource("onlyfun/caterpillar/beans-config.xml");
     * 或者
     * Resource rs = new ClassPathResource("beans-config.xml",SpringDemo.class);
     * Class.getResource("")获取的是相对于当前类的相对路径
     * Class.getResource("／")获取的是classpath的根路径
     * ClassLoader.getResource("")获取的是classpath的根路径
     * 在创建ClassPathResource对象时，我们可以指定是按Class的相对路径获取文件还是按ClassLoader来获取
     * @param path 不能带classpath
     * @return
     * @throws IOException
     */
    public static InputStream loadClassPathResource(String path) throws IOException {
        Resource  resource = new ClassPathResource(path);
        System.out.println("loadClassPathResource para: db/sqlite.properties 文件的物理路径："+resource.getFile().getAbsolutePath());
        return  resource.getInputStream();
    }

    /**
     * @desc 从文件系统加载，比如说自己指定配置文件的全路
     * @param path  不能使用classpath
     *    如果是相对路径，最终path =  项目路径+ path  eg: path --> db/sqlite.properties  :  E:\GIT\micro\micro-server\micro-space-web\db\sqlite.properties
     *    如果是绝对路径，最终path =  path            eg: path --> E:\db\sqlite.properties
     * @return
     * @throws Exception
     */
    public static InputStream loadFileSystemResource(String path) throws Exception{
        Resource resource = new FileSystemResource(path);
        System.out.println("loadFileSystemResource para : db/sqlite.properties 文件的物理路径："+resource.getFile().getAbsolutePath());
        return resource.getInputStream();
    }

    /**
     * @desc 通过URL地址获取资源  获取网络资源
     * @param path  能够构建URL
     * @return
     * @throws Exception
     */
    public static InputStream loadUrlResource(String path) throws Exception{
        Resource resource = new UrlResource(path);
        System.out.println("loadUrlResource para : db/sqlite.properties getURL："+resource.getURL());
        return resource.getInputStream();
    }


    /**
     * @desc 获取字节数组封装的资源
     * @param data 字节数组
     * @return
     * @throws Exception
     */
    public static InputStream loadByteArrayResource(byte[] data) throws Exception{
        Resource resource = new ByteArrayResource(data);
        System.out.println("loadByteArrayResource ");
        return resource.getInputStream();
    }

    /**
     * @desc 获取输入流封装的资源
     *  对于InputStreamResource而言，其getInputStream()方法只能调用一次，继续调用将抛出异常。
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */
    public static InputStream loadInputStreamResource(InputStream inputStream) throws Exception{
        Resource resource = new InputStreamResource(inputStream);
        System.out.println("loadInputStreamResource");
        return resource.getInputStream();
    }




}
