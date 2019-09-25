package com.p2p.lending.util

object CreateRandom {
    fun random(): Int {
        val i = ((Math.random() * 9 + 1) * 100000).toInt()
        println(i)
        return i
    }
}
