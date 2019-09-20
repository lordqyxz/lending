package com.p2p.lending.controller

import com.p2p.lending.service.LoggerService
import com.p2p.lending.util.LogUtils
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired

class BaseController : LogUtils() {
    protected var logger = Logger.getLogger(this.javaClass)
    @Autowired
    private val loggerService: LoggerService? = null

    companion object {
        private val serialVersionUID = 6357869213649815390L

        /**
         * 得到分页列表的信息
         */

        fun logBefore(logger: Logger, interfaceName: String) {
            logger.info("")
            logger.info("日志开始------------------------")
            logger.info(interfaceName)
        }

        fun logAfter(logger: Logger) {
            logger.info("日志结束---------------------------")
            logger.info("")
        }
    }

}
