package cn.coderoom.job;

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
        HttpUtil.createGet("http://busos.zmee.com.cn/zmeefund/sync/jd/syncData");
    }

}
