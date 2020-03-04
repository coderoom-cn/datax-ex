package cn.coderoom;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.core.Engine;

import static org.junit.Assert.*;

public class EngineTest {

    public static void main(String[] args) {
        System.setProperty("datax.home", getCurrentClasspath());
        String[] datxArgs = {"-job", getCurrentClasspath() + "/job/stream2stream.json", "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);
        } catch (Throwable e) {
            e.printStackTrace();
            //LOG.error("采集处理异常！{}", e.getMessage());
            //throw new DataXException("采集处理出现异常，原因：" + e.getMessage(), e);
        }
    }

    public static String getCurrentClasspath() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String currentClasspath = classLoader.getResource("").getPath();
        // 当前操作系统
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            // 删除path中最前面的/
            currentClasspath = currentClasspath.substring(1);
        }
        return currentClasspath;
    }

}