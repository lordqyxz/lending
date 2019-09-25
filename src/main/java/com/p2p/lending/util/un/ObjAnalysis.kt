package com.p2p.lending.util.un

import com.p2p.lending.entity.Log
import java.util.*

object ObjAnalysis {
    fun ConvertObjToMap(obj: Any?): Map<*, *>? {
        val reMap = HashMap<String, Any>()
        if (obj == null)
            return null
        val fields = obj.javaClass.declaredFields
        try {
            for (i in fields.indices) {
                try {
                    val f = obj.javaClass.getDeclaredField(fields[i].name)
                    f.isAccessible = true
                    val o = f.get(obj)
                    reMap[fields[i].name] = o
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

    @JvmStatic
    fun main(args: Array<String>) {
        val a = Log()
        a.laccount = "123456"
        a.id = 1
        val m = ConvertObjToMap(a)
        println(m)
    }
}
