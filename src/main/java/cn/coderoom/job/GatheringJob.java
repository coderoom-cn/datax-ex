package cn.coderoom.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.alibaba.datax.core.Engine;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 *  收款单数据同步定时任务
 * @class GatheringJob 
 * @package cn.coderoom.job
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/3/6 11:43 
*/ 
public class GatheringJob implements Job {

    private static final Log log = LogFactory.get();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.setProperty("datax.home",getPath());
        log.info("datax.home is {} log", getPath());
        Date now = new Date();
        DateTime startTime = DateUtil.offsetDay(now, -200);
        System.setProperty("start_time", startTime.toString());
        System.setProperty("end_time", DateUtil.formatDateTime(now));
        log.info("This is {} log", Level.INFO);
        log.info("Start sync time {}", startTime.toString());
        log.info("End sync time {}", DateUtil.formatDateTime(now));
        String[] datxArgs = {"-job", getPath() + "/job/oracle2mysqlgathering.json", "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);

            // TODO 通知业务系统
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e,"This is GatheringJob {} log", Level.ERROR);
            // TODO 异常处理方案
            //JobExecutionException e2 = new JobExecutionException(e);
            // 是否立即重新触发任务？true
            //e2.setRefireImmediately(true);
            //throw e2;
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
