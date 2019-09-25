package com.p2p.lending.util.un

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.scheduling.quartz.QuartzJobBean

class CronTriggerJob : QuartzJobBean() {

    @Throws(JobExecutionException::class)
    override fun executeInternal(arg0: JobExecutionContext) {
    }

}
