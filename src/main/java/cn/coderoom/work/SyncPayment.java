package cn.coderoom.work;

import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.alibaba.datax.core.Engine;

import java.util.Date;

/**
 * @package：cn.coderoom.work
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/5/27
 */
public class SyncPayment {

    private static final Log log = LogFactory.get();

    public void sync(String startTime,String endTime){

        System.setProperty("datax.home", getPath());
        log.info("datax.home is {} log", getPath());
        Date now = new Date();
        //DateTime startTime = DateUtil.offsetDay(now, -200);
        System.setProperty("start_time", startTime);
        System.setProperty("end_time", endTime);
        log.info("This is {} log", Level.INFO);
        log.info("Start sync time {}", startTime.toString());
        log.info("End sync time {}", DateUtil.formatDateTime(now));
        String[] datxArgs = {"-job", getPath() + "/job/oracle2mysqlpayment.json", "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);

            // TODO 通知业务系统
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e,"This is {} log", Level.ERROR);
            //LOG.error("采集处理异常！{}", e.getMessage());
            //throw new DataXException("采集处理出现异常，原因：" + e.getMessage(), e);
        }

    }


    //获取到该jar包所在目录
    public String getPath() {

        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if(System.getProperty("os.name").contains("dows"))
        {
            path = path.substring(1,path.length());
        }
        if(path.contains("jar"))
        {
            path = path.substring(0,path.lastIndexOf("."));
            return path.substring(0,path.lastIndexOf("/"));
        }
        return path.replace("target/classes/", "");
    }


}
