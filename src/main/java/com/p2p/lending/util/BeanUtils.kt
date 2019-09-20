package com.p2p.lending.util

import com.p2p.lending.entity.Log
import org.junit.Test
import java.util.*

/**
 * 这个类是把前台传递过来的参数封装成Map,给xml调用
 *
 * @author Administrator
 */
object BeanUtils {
    fun toMap(obj: Any?): Map<String, Any>? {
        val reMap = HashMap<String, Any>()
        if (obj == null)
            return null
        val fields = obj.javaClass.declaredFields
        try {
            for (i in fields.indices) {
                try {
                    val f = obj.javaClass.getDeclaredField(fields[i].name)
                    f.isAccessible = true
                    val `object` = f.get(obj)
                    reMap[fields[i].name] = `object`
                } catch (e: NoSuchFieldException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: IllegalArgumentException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }

            }
        } catch (e: SecurityException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return reMap
    }

    @Test
    @JvmStatic
    fun main(args: Array<String>) {
        val log = Log()
        log.laccount = "111"

        val map = BeanUtils.toMap(log)
        println(map!!["laccount"])
    }

}
