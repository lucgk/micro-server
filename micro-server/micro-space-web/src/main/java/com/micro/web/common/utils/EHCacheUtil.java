package com.micro.web.common.utils;

import com.micro.web.common.constants.WxAppletConstants;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

/**
 * 对EHCache进行了简单的封装
 * 建议在频繁使用且重负载的函数实现中使用缓存
 * Ehcache会将每个缓存配置的文件路径下创建一个cache_name.data文件，如果使用的磁盘持久化技术，还会生成一个cache name.index文件。
 * @author kouyy
 */
public class EHCacheUtil {

    static CacheManager manager=null;
    static String configfile="classpath:ehcache/ehcache.xml";

    public static void main(String[] args) {
        put(WxAppletConstants.WX_CACHE_SESSION,"zs","aaaaaaaaaaa");
        System.out.println(get(WxAppletConstants.WX_CACHE_SESSION,"zs"));
    }

    //EHCache初始化
    static{
        try {
            File file = ResourceUtils.getFile(configfile);
            manager = CacheManager.create(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据存入Cache
     * @param cachename Cache名称
     * @param key 类似redis的Key
     * @param value 类似redis的value，value可以是任何对象、数据类型，比如person,map,list等
     */
    public static void put(String cachename, Serializable key, Serializable value){
        manager.getCache(cachename).put(new Element(key, value));
    }

    /**
     * 获取缓存cachename中key对应的value
     * @param cachename
     * @param key
     * @return
     */
    public static Serializable get(String cachename,Serializable key){
        try {
            Element e=manager.getCache(cachename).get(key);
            if(e==null){
                return null;
            }
            return e.getValue();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (CacheException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清除缓存名称为cachename的缓存
     * @param cachename
     */
    public static void clearCache(String cachename){
        try {
            manager.getCache(cachename).removeAll();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除缓存cachename中key对应的value
     * @param cachename
     * @param key
     */
    public static void remove(String cachename,Serializable key){
        manager.getCache(cachename).remove(key);
    }
}