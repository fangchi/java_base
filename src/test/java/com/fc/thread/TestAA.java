package com.fc.thread;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;

public class TestAA {

	public static void main(String[] args) {
		String[] bigArray = new String[3000000];
		
		for (int i = 0; i <3000000 ; i++) {
			bigArray[i] = String.valueOf(new Random().nextInt(20000));
		}
		PriorityQueue<WordCount> current = new PriorityQueue<WordCount>(100,new CCComparator());
		for (int i = 0; i < 300; i++) {//read from file 
			PriorityQueue<WordCount> res = findTop100(bigArray,i*10000,(i+1)*10000-1); //保证每次能够全部放入map
			current.addAll(res);
			while(current.size() >100){
				current.poll();
			}
		}
		for (Iterator<WordCount> iterator = current.iterator(); iterator.hasNext();) {
			WordCount wordCount = (WordCount) iterator.next();
			System.out.println("关键词："+wordCount.getWord());
		}
	}
	
	static class WordCount{
		
		private String word; 
		private int count;
		
		public WordCount(String word,int count){
			this.word =word;
			this.count = count;
		}
		
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((word == null) ? 0 : word.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			WordCount other = (WordCount) obj;
			if (word == null) {
				if (other.word != null)
					return false;
			} else if (!word.equals(other.word))
				return false;
			return true;
		} 
	}
	
	static class CCComparator implements Comparator<WordCount>{

		@Override
		public int compare(WordCount o1, WordCount o2) {
			return o1.getCount()- o2.getCount();
		}
		
	}
	
	/**
	 * 获取每个分片中的最大100
	 * @param bigArray
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static PriorityQueue<WordCount> findTop100(String[] bigArray,int startIndex,int endIndex){
		Map<String, Integer> result = new HashMap<>();
		for (int i = startIndex; i < endIndex; i++) {
			String  current = bigArray[i];
			if(result.get(current)==null){
				result.put(current, 0);
			}else {
				result.put(current, result.get(current)+1);
			}
		}
		PriorityQueue<WordCount> integerPriorityQueue = new PriorityQueue<>(100,new CCComparator());
		
		integerPriorityQueue.add(new WordCount(bigArray[startIndex],1));
		
		//遍历词频
		for (Iterator<Entry<String, Integer>>  iterator = result.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Integer>  entry = (Entry<String, Integer>) iterator.next();
			integerPriorityQueue.add(new  WordCount(entry.getKey(), entry.getValue()));
			if(integerPriorityQueue.size() >100){
				integerPriorityQueue.poll();
			}
		}
		return integerPriorityQueue;
	}
}
