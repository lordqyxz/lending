package com.p2p.lending.util.un

import org.junit.Test
import sun.misc.BASE64Decoder

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Base64Utils {

    @Test
    @Throws(NoSuchAlgorithmException::class)
    fun test() {
        val string = getMD5("31231231As")

        val q = string.replace("[a-zA-Z]".toRegex(), "")
        println(string)
        println(q)
    }

    companion object {

        @Throws(NoSuchAlgorithmException::class)
        fun getMD5(pwd: String): String {

            val instance = MessageDigest.getInstance("md5")
            val bb = instance.digest(pwd.toByteArray())
            val ss = StringBuilder()
            for (b in bb) {
                if (Integer.toHexString(0xFF and b.toInt()).length == 1) {
                    ss.append("0")
                }
                ss.append(Integer.toHexString(0xFF and b.toInt()))
            }
            val p = ss.toString().toUpperCase()
            println(p)
            return p

        }

        fun getBASE64(b: ByteArray?): String? {
            var s: String? = null
            if (b != null) {
                s = sun.misc.BASE64Encoder().encode(b)
            }
            return s

        }

        fun getFromBASE64(s: String?): ByteArray? {
            var b: ByteArray? = null
            if (s != null) {
                val decoder = BASE64Decoder()
                try {
                    b = decoder.decodeBuffer(s)
                    return b
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return b
        }
    }
}
