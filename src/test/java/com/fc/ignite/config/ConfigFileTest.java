package com.fc.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

import java.io.InputStream;

/**
 * @author fangchi
 * @date 2019/7/22 19:54
 */
public class ConfigFileTest {
    public static void main(String[] args) throws InterruptedException {
        String s_xmlpath="/ignite-config.xml";
        InputStream in=ConfigFileTest.class.getResourceAsStream(s_xmlpath);
        Ignite ignite = Ignition.start(in);
        System.out.println("Ignite start ....");
        Thread.sleep(5000);
        Ignition.stop(true);
    }
}
