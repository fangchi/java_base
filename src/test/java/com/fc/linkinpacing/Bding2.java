package com.fc.linkinpacing;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author fangchi
 * @date 2019/6/22 16:51
 */
public class Bding2 {

    private static final double rt = 0.3;

    public static double ptr(double preptr,long st,long at){
        if(at >= st){
            return Math.min(preptr * (1 + rt),1);
        }else {
            return Math.max(preptr * (1 - rt),0);
        }
    }

    public static void main(String[] args){
        long totalIpress = 10000000;  //总流量
        long totalBidAmount = 10000000;  //总投入
        long bid  =   5;
        double preptr = 1;

        //花费权重
        Integer[] spendAmount = new Integer[]{
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1,1,
                1,1,1,1};
        long totalSpendAmountWeights =  Arrays.asList(spendAmount).stream().mapToLong(item  -> Long.valueOf(item)).sum();

        //流量权重
        Integer[] IpressWeights = new Integer[]{
                4,2,1,1,1,
                1,1,2,2,3,
                3,4,4,3,2,
                1,1,2,3,5,
                8,10,8,5};
        long totalIpressWeights =  Arrays.asList(IpressWeights).stream().mapToLong(item  -> Long.valueOf(item)).sum();

        long currentst = 0L;

        for(int i = 0 ; i < 24 ;i++){
            long hourst = 0L;
            long currentat = 0L;
            for(int m = 0 ; m< i+1;m++){
                currentat  = currentat  + spendAmount[m];
            }
            currentat = (long)(((double)currentat / (double)totalSpendAmountWeights) * totalBidAmount);

            //流量
            long impress =  Double.valueOf(totalIpress  * ((double)IpressWeights[i]/totalIpressWeights)).longValue();
            //每一次流量
            for(int j = 0 ; j<impress ;j++){
                double p = ptr(preptr,currentst,currentat);
                double p2 = Math.random();
                if(p >= p2){
                    //System.out.println("第"+j+"竞标成功");
                    hourst = hourst+bid;
                }
            }
            currentst = currentst + hourst;
            System.out.println("第"+(i+1)+"小时统计信息如下:");
            System.out.println("预算金额:"+ currentat);
            System.out.println("消耗金额:"+ hourst);
        }

    }
}
