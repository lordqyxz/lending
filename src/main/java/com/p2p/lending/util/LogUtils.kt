package com.p2p.lending.util

import com.p2p.lending.entity.Log
import com.p2p.lending.service.LoggerService
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import java.util.*
import javax.servlet.http.HttpSession

/**
 * Name: LogUtils Description:日志组件 Create by: LiuXunming Date: 2016-3-29 Time:
 * 10:46
 */
open class LogUtils {
    companion object {
        var session: HttpSession? = null
        var globaluser = session!!.getAttribute("globaluser").toString()
        private val logger = LoggerFactory.getLogger(LogUtils::class.java)
        private val logService = SpringContextHolder.getBean(LoggerService::class.java)

        /**
         * 记录操作日志
         *
         * @param label    标签
         * @param name     操作对象名称
         * @param lremarks 具体信息
         */
        fun log(label: String, name: String, lremarks: Array<String>?) {
            if (lremarks != null) {
                for (lremark in lremarks) {
                    log(label, name, lremark)
                }
            }
        }

        /**
         * 记录操作日志
         *
         * @param label   标签
         * @param name    操作对象类型 如：新增用户，修改用户
         * @param lremark 具体信息
         */
        fun log(label: String, logtype: String, lremark: String) {
            logger.debug("---------------------$label,$logtype:$lremark-----------------------")

            val log = Log()
            log.laccount = globaluser
            log.logtype = logtype
            log.lprocesstime = Date()
            log.lremark = lremark

            log.lremark = if (StringUtils.isEmpty(label)) label else "$lremark:$lremark"

            // logService.create(log);

        }

        /**
         * 记录登录退出日志
         *
         * @param label   标签
         * @param account 操作对象名称
         * @param vp      虚拟路径
         * @param ip      ip
         */
        fun loginLog(account: String, logtype: String, lremark: String) {
            logger.debug("---------------------$logtype,$account-----------------------")

            val log = Log()
            log.laccount = account
            log.logtype = logtype
            log.lremark = lremark
            log.lprocestime = Date()
            logService.create(log)

        }
    }
}