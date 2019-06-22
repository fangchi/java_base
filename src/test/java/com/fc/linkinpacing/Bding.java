package com.fc.linkinpacing;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import java.math.BigDecimal;

/**
 * @author fangchi
 * @date 2019/6/22 15:46
 */
public class Bding {

    public static void main(String[] args){
        Range<Integer> range = Range.closed(0, 9);
        ContiguousSet.create(range, DiscreteDomain.integers()).forEach((s) -> {
            System.out.println((double)s/10);
            System.out.println(ψ((double)s/10));
        });
       // System.out.println(ψ(0));
    }

    public static double ψ(double x){
        double e = 2.718282;
        return  1 - Math.pow(e,x-1);
    }
}
