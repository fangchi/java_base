package com.fc.ignite.config;

import org.apache.ignite.configuration.DataPageEvictionMode;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * @author fangchi
 * @date 2019/7/17 19:31
 */
public class RamConfig {
    public static void main(String[] args){
        // Ignite configuration.
        IgniteConfiguration cfg = new IgniteConfiguration();

// Durable Memory configuration.
        DataStorageConfiguration storageCfg = new DataStorageConfiguration();

// Creating a new data region.
        DataRegionConfiguration regionCfg = new DataRegionConfiguration();

// Region name.
        regionCfg.setName("500MB_Region");

// Setting initial RAM size.
        regionCfg.setInitialSize(100L * 1024 * 1024);

// Setting maximum RAM size.
        regionCfg.setMaxSize(500L * 1024 * 1024);

// Enable persistence for the region.
        regionCfg.setPersistenceEnabled(false);

        regionCfg.setPageEvictionMode(DataPageEvictionMode.RANDOM_LRU);

// Setting the data region configuration.
        storageCfg.setDataRegionConfigurations(regionCfg);


// Applying the new configuration.
        cfg.setDataStorageConfiguration(storageCfg);
    }
}
