package com.p2p.lending

import org.junit.Test

class Test1 {

    fun main1(): Int {
        try {
            println("11111111")
            return 1
        } finally {
            println("33333333")
        }
    }

    companion object {

        @Test
        @JvmStatic
        fun main(args: Array<String>) {
            val a = Test1().main1()
            println("a===  $a")
        }
    }
}
