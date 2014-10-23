package cn.youhui.jfbhis;

import java.util.ArrayList;
import java.util.List;

/**
 * 对拿到的集分宝历史记录进行分页处理
 * @author hufan
 * @since 2014-9-18
 */
public class FenyeUtil {

	public static int pageSize=8;
	
	public static void main(String[] args) {
		
	}

	/**
	 * 得到指定页的集分宝历史记录
	 * @param jfbmxList
	 * @param page
	 * @return
	 */
	public static List<JFBMingxi> getJFBListByPage(List<JFBMingxi> jfbmxList,int page){
		List<JFBMingxi> jfb=new ArrayList<JFBMingxi>();
		int start=getStart(jfbmxList.size(), page);
		int end=getEnd(start);
		System.out.println("start="+start);
		System.out.println("end="+end);
		for(int i=start;i>=end;i--){
			jfb.add(jfbmxList.get(i));
		}
		
		return jfb;
	}
	
	/**
	 * 得到指定页面的开始记录的位置下标
	 * @param page
	 * @return
	 */
	public static int getStart(int totalSize,int page){
		System.out.println("totalsize:"+totalSize);
		int start=0;
		if(totalSize>(page-1)*pageSize){
			start=(totalSize-1)-pageSize*(page-1);
		}else{
			return -1;
		}
		return start;
	}
	/**
	 * 得到指定页面的结束记录的位置下标
	 * @param start
	 * @param page
	 * @return
	 */
	public static int getEnd(int start){
		int end=0;
		if(start>pageSize-1){
			end=start-(pageSize-1);
		}
		return end;
	}
	
}
