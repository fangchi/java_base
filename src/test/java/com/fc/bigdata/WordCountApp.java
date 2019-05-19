 package com.fc.bigdata;
 
 import java.io.IOException;

 import org.apache.hadoop.conf.Configuration;
 import org.apache.hadoop.fs.Path;
 import org.apache.hadoop.io.LongWritable;
 import org.apache.hadoop.io.Text;
 import org.apache.hadoop.mapreduce.Job;
 import org.apache.hadoop.mapreduce.Mapper;
 import org.apache.hadoop.mapreduce.Reducer;
 import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
 import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

 /**
 * @author chi.fang
 * @date 2019/05/06
 */
public class WordCountApp {

    public static class MyMapper extends org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, LongWritable>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
//split  函数是用于按指定字符（串）或正则去分割某个字符串，结果以字符串数组形式返回，这里按照"\t"来分割text文件中字符，即一个制表符，这就是为什么我在文本中用了空格分割，导致最后的结果有很大的出入。
            String[] splited = line.split("\t");
            for (String word : splited) {          
                context.write(new Text(word), new LongWritable(1));
            }
        }
    }
}
