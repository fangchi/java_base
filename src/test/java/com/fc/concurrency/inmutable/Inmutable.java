 package com.fc.concurrency.inmutable;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author chi.fang
 * @date 2019/04/05
 */
public class Inmutable {

    private static Map<Integer,Integer> inmutable =   Maps.newHashMap();
    
    static{
        inmutable.put(1, 2);
        inmutable.put(2, 3);
        inmutable = Collections.unmodifiableMap(inmutable);
    }
    
    public static void main(String[] args) {
        System.out.println(inmutable);
    }
}
