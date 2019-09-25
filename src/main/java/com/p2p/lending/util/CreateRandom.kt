package com.p2p.lending.util;

public class CreateRandom {
    public static int random() {
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        System.out.println(i);
        return i;
    }
}
