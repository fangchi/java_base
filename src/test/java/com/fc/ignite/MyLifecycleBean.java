package com.fc.ignite;

import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

/**
 * @author fangchi
 * @date 2019/7/17 18:05
 */
public class MyLifecycleBean implements LifecycleBean {
    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
            System.out.println(evt);
    }
}
