package cn.youhui.cacher.dao;

import cn.youhui.bean.SecondKill;
import cn.youhui.manager.JedisHashManager;

import com.google.gson.Gson;

public class GuessYouLikeCacher {
	private static final String cacheKey="guessyoulike.tag";
	private static GuessYouLikeCacher instance=null;
	public static GuessYouLikeCacher getInstance(){
		if(instance==null){
			instance=new GuessYouLikeCacher();
		}
		return instance;
	}
	public void add(String uid , String sk){
		new JedisHashManager(cacheKey).add(uid,sk);
	}
	
	public String getSecondKillByUid(final String uid,int pagNow,int numPerPage) {
		String str = new JedisHashManager(cacheKey).get(uid);
		if (str==null || str.equals("")) {
			return "";
		}
		
		String tmp="";
		String[] s=str.split(",");
		for(int i=0;i<s.length;i++){
			if(i>=(pagNow-1)*numPerPage&&i<=(pagNow*numPerPage-1)){
				String itemId=s[i];
				tmp=tmp+itemId+",";
			}
		}
		if(tmp.length()>0){
			tmp=tmp.substring(0,tmp.length()-1);
		}
		return tmp;
	}
	
	public int getTotalPages(String uid,int numPerPage){
		String str = new JedisHashManager(cacheKey).get(uid);
		if (str==null || str.equals("")) {
			return 0;
		}
		String[] s=str.split(",");
		int totalPages =s.length%numPerPage==0?(s.length/numPerPage):(s.length/numPerPage+1);;
		return totalPages;
	}
}
