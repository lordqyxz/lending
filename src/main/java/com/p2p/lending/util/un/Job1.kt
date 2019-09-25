package com.p2p.lending.util.un

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.springframework.scheduling.quartz.QuartzJobBean

class Job1 : QuartzJobBean() {
    private var timeout: Int = 0

    //调度工厂实例化后，经过timeout时间开始执行调度

    fun setTimeout(timeout: Int) {

        this.timeout = timeout

    }

    /**
     * 要调度的具体任务
     */
    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        // TODO Auto-generated method stub
        println("定时任务执行中…")
    }

    companion object {
        private val i = 0
    }

}
