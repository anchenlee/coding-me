package cn.youhui.utils;

import java.util.HashSet;
import java.util.Set;

public class SetOpt {
	
	/**
	 * 合集
	 * 
	 * **/
	public static <T> Set<T> union(Set<T> s1 , Set<T> s2){
		Set<T> set = new HashSet<T>(s1) ;
		set.addAll(s2) ;
		return set ;
	}
	
	/**
	 * 交集
	 * 
	 * **/
	public static <T> Set<T> intersect(Set<T> s1 , Set<T> s2){
		Set<T> set = new HashSet<T>(s1) ;
		set.retainAll(s2) ;
		return set ;
	}
	
	/**
	 * 差集
	 * 
	 * **/
	public static <T> Set<T> diff(Set<T> s1 , Set<T> s2){
		Set<T> set = new HashSet<T>(s1) ;
		set.removeAll(s2) ;
		return set ;
	}
	  
}
