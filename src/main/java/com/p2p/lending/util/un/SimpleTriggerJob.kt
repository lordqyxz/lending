package com.p2p.lending.util.un

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.scheduling.quartz.QuartzJobBean

class SimpleTriggerJob : QuartzJobBean() {


    /**
     * 这个类是每隔多少时间来执行的业务类。把你要执行的业务写在这里
     */
    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
    }

}
