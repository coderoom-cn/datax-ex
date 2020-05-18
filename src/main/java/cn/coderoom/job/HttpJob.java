package cn.coderoom.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @package：cn.coderoom.job
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/3/30
 */
public class HttpJob implements Job {


    private static final Log log = LogFactory.get();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        syncData();

    }

    private void syncData(){
        log.info(">>>>>>>>>>>>>>>>>start HttpJob time is {}", DateUtil.now());
        HttpRequest httpRequest= HttpUtil.createGet("http://127.0.0.1:8090/zmeefund/sync/jd/syncData");
        try {
            HttpResponse httpResponse = httpRequest.execute();
            int status = httpResponse.getStatus();

            log.info(">>>>>>>>>>>>>>>>>start HttpJob result is {}", httpResponse.body());
        } catch (Exception e) {
            log.info(">>>>>>>>>>>>>>>>>start HttpJob throw Exception is {}", e.getMessage());
            e.printStackTrace();
        }



    }
}
