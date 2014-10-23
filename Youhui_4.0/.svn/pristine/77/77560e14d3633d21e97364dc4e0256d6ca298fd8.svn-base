package com.netting.job;

import java.util.List;

import com.netting.bean.TeJiaGoodsBean;
import com.netting.dao.admin.Admin_Tag_Item_DAO;

public class RefreshTopItem extends Thread {

//	public String startTime;
//	
//	public String endTime;
//	
//	public RefreshTopItem(String startTime,String endTime){
//		this.startTime = startTime;
//		this.endTime = endTime;
//	}
//	
//	public void run(){
//		List<TeJiaGoodsBean> list = Admin_Tag_Item_DAO.getTopTradeItemFrom(startTime, endTime);
//		Admin_Tag_Item_DAO.insertTopTradeItem(list, startTime, endTime);
//	}
	
	public static void updateHotItem(long startTime, long endTime){
		List<TeJiaGoodsBean> list = Admin_Tag_Item_DAO.getTopTradeItemFrom(startTime, endTime);
		Admin_Tag_Item_DAO.insertTopTradeItem(list, startTime, endTime);
	}
	
	
}
