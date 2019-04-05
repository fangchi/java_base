package com.fc.concurrency.publish;

import com.fc.concurrency.annotations.ThreadSafe;

/**
 * @author chi.fang
 * @date 2019/04/05
 */
@ThreadSafe
public class SingleTon {

    
    private enum SingleTonEnum {
        //实例
        INSTANCE;
        
        private SingleTon singleTon;
        
        SingleTonEnum(){
            singleTon = new SingleTon();
        }
        
        SingleTon getInstance(){
            return singleTon;
        }
    }
    
    private SingleTon() {

    }

    private volatile static SingleTon instance = null;
    
//    static {
//        instance = new SingleTon();
//    }

    public static SingleTon getInstance() {
        synchronized (SingleTon.class) {
            if(instance == null){
                instance = new SingleTon();
            }
            return instance;
        }
    }
    
    public static SingleTon getInstance2() {
       return SingleTonEnum.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance2();
        System.out.println(singleTon);
    }
}
