package com.witshare.mars.job;

import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/6/21.
 */
@Component
public class StatisticTxJob {

    //主要是 userTx 表 和userAddress表
    /**
     * 1.根据用户交易流水表的创建时间一次性获取所有
     * 2.根据1中的项目，再一次性获取 地址表中的所有
     * 3.修改统计表，更新redis
     */
}
