package cn.coderoom;

import cn.coderoom.work.SyncBillReceiveable;
import cn.coderoom.work.SyncGathering;
import cn.coderoom.work.SyncPayment;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/** 
 * 
 * @class Hand 
 * @package cn.coderoom
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/5/27 14:19 
*/ 
public class Hand {

    private static final Log log = LogFactory.get();

    public static void main( String[] args ){
        log.info("===================start sync。。");
        String startTime = args[0];
        String endTime = args[1];
        for (String arg:
             args) {
            log.info("time is ==================="+arg);
        }

        SyncGathering gathering = new SyncGathering();
        gathering.sync(startTime,endTime);
        SyncBillReceiveable syncBillReceiveable = new SyncBillReceiveable();
        syncBillReceiveable.sync(startTime,endTime);
        SyncPayment syncPayment = new SyncPayment();
        syncPayment.sync(startTime,endTime);
        log.info("end===================");

    }

}
