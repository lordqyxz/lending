package com.p2p.lending.util

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class SpringContextHolder : ApplicationContextAware, DisposableBean {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        logger.debug("注入ApplicationContext到SpringContextHolder:$applicationContext")
        if (SpringContextHolder.applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext!!)
        }
        SpringContextHolder.applicationContext = applicationContext // NOSONAR
    }

    @Throws(Exception::class)
    override fun destroy() {
        SpringContextHolder.clear()
    }

    companion object {
        private var applicationContext: ApplicationContext? = null
        private val logger = LoggerFactory.getLogger(SpringContextHolder::class.java)

        fun getApplicationContext(): ApplicationContext? {
            assertContextInjected()
            return applicationContext
        }

        fun <T> getBean(name: String): T {
            assertContextInjected()
            return applicationContext!!.getBean(name) as T
        }

        fun <T> getBean(requiredType: Class<T>): T {
            assertContextInjected()
            return applicationContext!!.getBean(requiredType)
        }

        fun clear() {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext!!)
            applicationContext = null
        }

        private fun assertContextInjected() {
            checkNotNull(applicationContext) { "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder" }
        }
    }
}
