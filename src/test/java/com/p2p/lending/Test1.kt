package com.p2p.lending.util;

import org.junit.Test;

public class Test1 {

    @Test
    public static void main(String[] args) {
        int a = new Test1().main1();
        System.out.println("a===  " + a);
    }

    public int main1() {
        try {
            System.out.println("11111111");
            return 1;
        } finally {
            System.out.println("33333333");
        }
    }
}