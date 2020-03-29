package cn.coderoom.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.*;
import java.util.Properties;

/**
 * 
 * @class PropertiesUtil
 * @package cn.coderoom.util
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/3/28 21:53 
*/ 
public class PropertiesUtil {

    private static final Log log = LogFactory.get();

    public static String getProjectConfigPath(){

        Properties properties = new Properties();
        //读取当前 jar 包存放的路径
        String path = System.getProperty("user.dir");
        path = path + File.separator+"config";

        return path;

    }

    public static Properties getProps(String fileName){

        Properties properties = new Properties();
        //读取当前 jar 包存放的路径
        String path = System.getProperty("user.dir");
//        System.out.println(path);
        //判断文件是否存在
        InputStream input = null;
        try {
            //读取当前文件夹下config文件目录下的配置文件
            input = new FileInputStream(new File(path+ File.separator+"config"+File.separator+fileName));
        } catch (FileNotFoundException e) {
            log.info("properties not find {}", e);
        }
        if (input != null) {
            try {
                properties.load(input);
            } catch (IOException e) {
                log.info("fail to load the jdbc.properties," + e);
            }
        }
        return properties;
    }

    public static void main( String[] args ){
        getProps("core.json");
    }

}
