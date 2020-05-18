package cn.coderoom;

import cn.coderoom.job.BillReceiveableJob;
import cn.coderoom.job.GatheringJob;
import cn.coderoom.job.HttpJob;
import cn.coderoom.job.PaymentJob;
import cn.hutool.http.HttpUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/** 
 * 
 * @class App
 * @package cn.coderoom
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/3/6 11:59 
*/ 
public class App
{

    public static void main( String[] args ) throws SchedulerException, InterruptedException
    {

        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与Job类绑定(Job执行内容)
        JobDetail gatheringJobDetail = JobBuilder.newJob(GatheringJob.class)
                .usingJobData("gatheringJobDetail", "收款单数据同步定时任务")
                .withIdentity("job1", "group").storeDurably() .build();
        JobDetail paymentJobDetail = JobBuilder.newJob(PaymentJob.class)
                .usingJobData("paymentJobDetail", "付款单数据同步定时任务")
                .withIdentity("job2", "group").storeDurably() .build();
        JobDetail billReceiveableJob = JobBuilder.newJob(BillReceiveableJob.class)
                .usingJobData("billReceiveableJob", "应收票据数据同步定时任务")
                .withIdentity("job3", "group").storeDurably().build();

        JobDetail httpJob = JobBuilder.newJob(HttpJob.class)
                .usingJobData("httpJob", "数据请求处理同步定时任务")
                .withIdentity("job4", "group").storeDurably().build();

        // 3、构建Trigger实例
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 1000);

        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 10000);

        CronTrigger cronTriggerGathering = TriggerBuilder.newTrigger().withIdentity("trigger", "triggerGroup")
                .usingJobData("trigger", "这是cronTriggerGathering的trigger")
                .startNow()//立即生效
                /*.startAt(startDate)
                .endAt(endDate)*/
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/12 * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */5 * * * ?"))
                .build();
        CronTrigger cronTriggerPayment = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup")
                .usingJobData("trigger1", "这是cronTriggerPayment的trigger")
                .startNow()//立即生效
                /*.startAt(startDate)
                .endAt(endDate)*/
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/12 * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */5 * * * ?"))
                .build();
        CronTrigger cronTriggerBill = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup")
                .usingJobData("trigger2", "这是cronTriggerBill的trigger")
                .startNow()//立即生效
                /*.startAt(startDate)
                .endAt(endDate)*/
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/12 * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */5 * * * ?"))
                .build();

        CronTrigger cronHttp = TriggerBuilder.newTrigger().withIdentity("trigger3", "triggerGroup")
                .usingJobData("trigger3", "这是cronHttp的trigger")
                .startNow()//立即生效
                /*.startAt(startDate)
                .endAt(endDate)*/
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 1/12 * * ? *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */5 * * * ?"))
                .build();

        //4、执行
        /*scheduler.addJob(gatheringJobDetail,true);
        scheduler.addJob(paymentJobDetail,true);
        scheduler.addJob(billReceiveableJob,true);*/
        //scheduler.addJob(httpJob,true);

        scheduler.scheduleJob(gatheringJobDetail,cronTriggerGathering);
        scheduler.scheduleJob(paymentJobDetail,cronTriggerPayment);
        scheduler.scheduleJob(billReceiveableJob,cronTriggerBill);
        scheduler.scheduleJob(httpJob,cronHttp);

        System.out.println("--------scheduler start ! ------------");
        scheduler.start();
        System.out.println("--------scheduler shutdown ! ------------");

    }

    private void noticeSync(){

        HttpUtil.createGet("http://busos.zmee.com.cn/zmeefund/sync/jd/syncData");

    }
}
