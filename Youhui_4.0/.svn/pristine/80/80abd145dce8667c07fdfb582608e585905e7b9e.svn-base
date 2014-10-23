package com.netting.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.cache.dao.Admin_Tag_Cache_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;

/**
 * 将标签同步至所有用户
 * @author UESR
 *
 */
public class SynchronizeAllUserTagJob extends Thread 
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Tag_DAO.class);
	
	public static int successNum = 0;
	
	private List<String> list ;
	
	private String tagid;
	
	private String ispad;
	
	private int rank;
	
	public SynchronizeAllUserTagJob(List<String> list, String tagid,String ispad,int rank)
	{
		this.list = list;
		this.tagid = tagid;
		this.ispad = ispad;
		this.rank = rank;
	}
	
	public void run() 
	{
		successNum = 0;
		int j = list.size() % 1000;
		if(j == 0) 
		{
			j = list.size() / 1000;
		}
		else 
		{
			j = list.size() / 1000 +1;
		}
		// JobExecutor.jobDetailMap.get(threadName).setAllNum(j);
		
		for(int k = 0; k < j; k++)
		{
			List<String> userList = new ArrayList<String>();
			for(int i = k * 1000; i < (k + 1) * 1000 && i < list.size(); i++)
			{
				userList.add(list.get(i));
			}
			
			Admin_Tag_DAO.synchronizeAllUserTag(userList, tagid, rank, ispad);
			successNum = k + 1;
			// JobExecutor.jobDetailMap.get(threadName).setSuccessNum(successNum);
		}
		
		try 
		{	
			if(ispad.equals("1"))
			{		
				Admin_Tag_Cache_DAO.reloadUserTagIpad();
			}
			else 
			{
				Admin_Tag_Cache_DAO.reloadUserTag();
			}
			
			logger.info("标签<" + tagid + "> 同步完成......");
		} 
		catch (Exception e) 
		{
			return;
		}
	}
}
