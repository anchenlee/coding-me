package cn.youhui.utils;

import cn.youhui.bean.Action;
import cn.youhui.common.Config;

public class ActionChangeUtil {
	
	 /**
	  * 低版本的终端请求后，actionType是tagStyleItem的情况下，actionType换成tagStyleWeb，actionValue用b17拼装
	  * @param id 广告、特惠、礼物...的ID
	  * @param uid 请求用户的UID
	  * @param platform 平台版本
	  * @param fromChannel 不同类目
	  * @param actionType
	  * @param actionValue
	  * @param version_code 终端版本
	  * @return
	  */
	 public static Action lowVersionItemAction(String id, String uid, String platform, String fromChannel, 
			 String actionType, String actionValue, String version_code)
	 {
		 Action action = new Action();
		 
		 boolean isLowVersion = false;
		 if (version_code == null || version_code.equals(""))
		 {
			 isLowVersion = true;
		 }
		 else
		 {
			 try
			 {
				 int versionNum = Integer.parseInt(version_code);
				 if (versionNum < 40)
				 {
					 isLowVersion = true;
				 }
			 }
			 catch (Exception e)
			 {
				 isLowVersion = true;
			 }
		 }
		 if (null != actionType && !"".equals(actionType))
		 {
			 if (isLowVersion)
			 {
				 if (actionType.equalsIgnoreCase("tagStyleItem"))
				 {
					 actionType = "tagStyleWeb";
					 
					 if (null != actionValue && !"".equals(actionValue))
					 {
						 String outerCode = OuterCode.getOuterCode(uid, platform, "0", fromChannel, id);
						 
						 actionValue = Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + actionValue;
					 }
				 }
			 }
		 }
		 
		 action.setActionType(actionType);
		 action.setActionValue(actionValue);
		 
		 return action;
	 }
	
}
