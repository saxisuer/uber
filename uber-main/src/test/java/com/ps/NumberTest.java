package com.ps;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Project uber
 * @Package com.ps
 * @Description //TODO
 * @Date 16/3/5
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class NumberTest {


    @Test
    public void test1() {
        BigDecimal n = new BigDecimal("11");
        BigDecimal nn = new BigDecimal("11");
        long x = 61108;
        long i = 1;
        while (i <= x) {
            n = n.multiply(nn);
            i++;
        }
        System.out.println(n);

        BigDecimal k = new BigDecimal("1");
        BigDecimal kk = new BigDecimal("1");
        BigDecimal xx = new BigDecimal("61108");
        while (x >= 1) {
            k = k.multiply(xx);
            x--;
            xx = xx.subtract(kk);
        }
        System.out.println(k);
        System.out.println(k.subtract(n));
        Assert.assertTrue(k.compareTo(n) > 0);
    }

}
