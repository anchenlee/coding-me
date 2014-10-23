package cn.youhui.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
/**
 * 集合操作函数
 * */
public class SetsOpera {
	
	/**
	 * 两个集合的交集操作
	 */
	 	public List intersect(List ls, List ls2) {   
	        List list = new ArrayList(Arrays.asList(new Object[ls.size()]));   
	        Collections.copy(list, ls);   
	        list.retainAll(ls2);   
	        return list;   
	    }   
	  
	 	/**
	 	 * 两个集合的并集操作
	 	 * @return
	 	 */
	 	
	    public List union(List ls, List ls2) {   
	        List list = new ArrayList(Arrays.asList(new Object[ls.size()]));   
	        Collections.copy(list, ls); 
	        for(int i =0;i<ls2.size();i++){
	        	String str = String.valueOf(ls2.get(i));
	        	if(list.indexOf(str)==-1){
	        		list.add(str);
	        	}
	        }
	        
	        return list;   
	    }   
	    
	    /**
	 	 * 两个集合的差集操作(ls2)
	 	 * @return
	 	 */
	    public List diff(List ls, List ls2) {   
			List list = new ArrayList(Arrays.asList(new Object[ls.size()]));   
	        Collections.copy(list, ls);   
	        list.removeAll(ls2);   
	        return list;   
	    }   
	    
	    /**
	     * 两个集合先求并集再求差集(ls2)
	     * */
	    public List unionAndDiff(List ls,List ls2){
	    	List list = union(ls, ls2);
	    	List list1 = diff(list, ls2);
	    	return list1;
	    }
	    
	    
	   public static void main(String[] args) {
		List li = new ArrayList<String>();
		List li1 = new ArrayList<String>();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		
		li1.add("0");
		li1.add("1");
		li1.add("4");
		li1.add("5");
		li1.add("8");
		
		
		
		SetsOpera set = new SetsOpera();
		List list = set.unionAndDiff(li,li1);
		for(int i =0;i<list.size();i++){
			System.out.println(list.get(i));
		}
//		System.out.println("---------");
//		List list1 = set.diff(list, li1);
//		for(int i =0;i<list1.size();i++){
//			System.out.println(list1.get(i));
//		}
		
	}
}
