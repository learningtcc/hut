package com.hut.web.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Jared on 2017/2/15.
 */
public class ClearRedisJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 打印触发器名称
        System.out.println("ClearRedisJob 执行了............." + context.getTrigger().getKey().getName());
        // 获取spring容器
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail()
                .getJobDataMap().get("applicationContext");
        // 打印spring容器
        System.out.println("获取到的Spring容器是： " + applicationContext);
    }
}
