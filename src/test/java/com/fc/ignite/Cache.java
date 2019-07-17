package com.fc.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.transactions.Transaction;

import java.util.concurrent.locks.Lock;

/**
 * @author fangchi
 * @date 2019/7/17 16:44
 */
public class Cache {
    public static void main(String[] args) {
        IgniteConfiguration conf = new IgniteConfiguration();
        CacheConfiguration cacheConfiguration = new CacheConfiguration("myCacheName2");
        cacheConfiguration.setCacheMode(CacheMode.LOCAL);
        cacheConfiguration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        //cacheConfiguration.setCachea
        //conf.setCacheConfiguration(cacheConfiguration);
        conf.setCacheConfiguration(cacheConfiguration);
        try (Ignite ignite = Ignition.start(conf)) {
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");

            // Store keys in cache (values will end up on different cache nodes).
            for (int i = 0; i < 10; i++)
                cache.put(i, Integer.toString(i));

            for (int i = 0; i < 10; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');

            IgniteCache<String, Integer> cache2 = ignite.getOrCreateCache("myCacheName2");
            // Put-if-absent which returns previous value.
            Integer oldVal = cache2.getAndPutIfAbsent("Hello", 11);
            System.out.println(oldVal);

// Put-if-absent which returns boolean success flag.
            boolean success = cache2.putIfAbsent("World", 22);
            System.out.println(success);

// Replace-if-exists operation (opposite of getAndPutIfAbsent), returns previous value.
            oldVal = cache2.getAndReplace("Hello", 11);
            System.out.println(oldVal);

// Replace-if-exists operation (opposite of putIfAbsent), returns boolean success flag.
            success = cache2.replace("World", 22);
            System.out.println(success);

// Replace-if-matches operation.
            success = cache2.replace("World", 2, 22);
            System.out.println(success);

// Remove-if-matches operation.
            success = cache2.remove("Hello", 1);
            System.out.println(success);
           // IgniteCache<String, Integer> cache3 = ignite.getOrCreateCache("myCacheName2");
            try (Transaction tx = ignite.transactions().txStart()) {
                Integer hello = cache2.get("Hello");
                if (hello == 1)
                    cache2.put("Hello", 11);

                cache2.put("World", 100000);
                tx.commit(); //tx.rollback
            }
            Integer v = cache2.get("World");
            System.out.println("v="+v);

            // Lock cache key "Hello".
            Lock lock = cache2.lock("Hello");

            lock.lock();

            try {
                cache2.put("Hello", 11);
                cache2.put("World", 22);
            }
            finally {
                lock.unlock();
            }
        }
    }
}
