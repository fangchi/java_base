package com.fc.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * @author fangchi
 * @date 2019/7/17 18:04
 */
public class Lifecycle {
    public static void main(String[] args){
        IgniteConfiguration conf = new IgniteConfiguration();
        CacheConfiguration cacheConfiguration = new CacheConfiguration("myCacheName2");
        cacheConfiguration.setCacheMode(CacheMode.LOCAL);
        cacheConfiguration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        //cacheConfiguration.setCachea
        //conf.setCacheConfiguration(cacheConfiguration);
        conf.setCacheConfiguration(cacheConfiguration);
        MyLifecycleBean lifecycleBean = new MyLifecycleBean();
        conf.setLifecycleBeans(lifecycleBean);

        try (Ignite ignite = Ignition.start(conf)) {}
    }
}
