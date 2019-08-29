package com.fc.trie;

import org.trie4j.patricia.PatriciaTrie;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.UUID;

public class Sample {
    public static void main(String[] args) throws Exception{


        new Sample().print3();
    }



    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }


    public void print2(){
        long startTime = System.currentTimeMillis();
        // 的JVM内存总量（单位是字节）
        long startMem  = Runtime.getRuntime().totalMemory();

        PatriciaTrie pat = new PatriciaTrie();
        String searchWord = "";
        Long totalNumber = 50000000l;
        for (int i = 0; i < totalNumber; i++) {
            String uuid = UUID.randomUUID().toString();
            pat.insert(uuid);
            if(i == 120){
                searchWord = uuid;
            }
            if(i % 5000000l == 0){
                System.out.println("time used in "+i+" use "+(System.currentTimeMillis()-startTime)+"ms");
                System.out.println("mem used in "+i+" use "+getNetFileSizeDescription(Runtime.getRuntime().totalMemory()-startMem));
            }
        }
        System.out.println("all record: "+totalNumber);
        System.out.println("build in "+(System.currentTimeMillis()-startTime)+"ms");
        System.out.println("used in "+getNetFileSizeDescription(Runtime.getRuntime().totalMemory()-startMem));
        System.out.println(pat.contains(searchWord));
    }

    public void print3() throws IOException {
        RandomAccessFile rf = new RandomAccessFile("/Users/fangchi/Downloads/you.txt", "rw");
        long count = rf.length();
        System.out.println("在最后一行写入数据！" );
        rf.seek(count);
        long startTime = System.currentTimeMillis();
        Long totalNumber = 50000000l;
        for (int i = 0; i < totalNumber; i++) {
            String uuid = UUID.randomUUID().toString();
            // 如要换行，用 /r/n 次序不要乱
            rf.writeBytes(uuid +System.getProperty("line.separator")); //只是写入字符的时候不会乱码，如果需要写入汉字，需要用下面这一行的方法
            if(i % 5000000l == 0){
                System.out.println("time used in "+i+" use "+(System.currentTimeMillis()-startTime)+"ms");
            }
        }
        rf.close();
        System.out.println("total time used in "+(System.currentTimeMillis()-startTime)+"ms");
    }

}
