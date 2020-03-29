package cn.coderoom.job;

import cn.coderoom.util.PropertiesUtil;
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
 *  付款单数据同步定时任务
 * @class PaymentJob
 * @package cn.coderoom.job
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/3/6 11:44
*/
public class PaymentJob implements Job {

    private static final Log log = LogFactory.get();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.setProperty("datax.home", PropertiesUtil.getProjectConfigPath());
        Date now = new Date();
        DateTime startTime = DateUtil.offsetDay(now, 1);
        System.setProperty("TODAY", startTime.toString());
        log.info("This is {} log", Level.INFO);
        log.info("Start sync time {}", startTime.toString());
        log.info("End sync time {}", DateUtil.formatDateTime(now));
        String[] datxArgs = {"-job", PropertiesUtil.getProjectConfigPath() + "/job/oracle2mysqlpayment.json", "-mode", "standalone", "-jobid", "-1"};
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
