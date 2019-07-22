package com.fc.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

/**
 * @author fangchi
 * @date 2019/7/22 19:54
 */
public class ConfigFileTest {
    public static void main(String[] args) throws InterruptedException {
        Ignite ignite = Ignition.start("D:\\sources\\java_base\\src\\test\\resources\\ignite-config.xml");
        System.out.println("Ignite start ....");
        Thread.sleep(5000);
        Ignition.stop(true);
    }
}
