package com.fc.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteFuture;

/**
 * @author fangchi
 * @date 2019/7/17 19:19
 */
public class Futrue {
    public static void main(String[] args){
        IgniteConfiguration conf = new IgniteConfiguration();
        try (Ignite ignite = Ignition.start(conf)) {
            IgniteCompute compute = ignite.compute();

// Execute a closure asynchronously.
            IgniteFuture<String> fut = compute.callAsync(() -> {
                return "Hello World";
            });
            System.out.println("exexuteHere");
// Listen for completion and print out the result.
            fut.listen(f -> System.out.println("Job result: " + f.get()));
        }
    }
}
