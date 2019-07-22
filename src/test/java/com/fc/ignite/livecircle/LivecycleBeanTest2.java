package com.fc.ignite.livecircle;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteState;
import org.apache.ignite.Ignition;
import org.apache.ignite.IgnitionListener;
import org.apache.ignite.internal.util.typedef.F;

import static org.apache.ignite.IgniteState.STARTED;

/**
 * @author fangchi
 * @date 2019/7/22 17:40
 */
public class LivecycleBeanTest2 {
    
    public static void main(String[] args) throws InterruptedException {
        Ignition.addListener(new IgnitionListener() {
            @Override public void onStateChange(String name, IgniteState state) {
               System.out.println("LivecycleBeanTest2 at here:"+ state.name());
            }
        });
        Ignite ignite = Ignition.start();
        Thread.sleep(2000);
        Ignition.stop(true);
    }
}
