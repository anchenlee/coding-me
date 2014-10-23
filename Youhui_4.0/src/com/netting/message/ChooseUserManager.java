package com.netting.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.netting.bean.UserMsgBean;
import com.netting.dao.admin.UserMsg_DAO;

/**
 * @category 根据条件选择需要推送的用户
 * @author leejun
 * @since 2012-11-22
 */
public class ChooseUserManager 
{	
	public static Map<String, UserMsgBean> ubeanmap = new HashMap<String, UserMsgBean>();
	private final static String matchDate = "_[0-9]*";
	private final static String matchNumber = "-?\\d*\\.?\\d+";
	
	public static Map<String, String > getResult(String formula, String query) throws Exception
	{
		Map<String, String> ret = new HashMap<String, String>();
		
		init(formula+query);
		formula = formula.replaceAll(matchDate, "");
		
		for(Map.Entry<String, UserMsgBean> m : ubeanmap.entrySet())
		{
			if(formula.contains("uid"))
			{
				if(isContainUid(formula, m.getKey()))
				{
					ret.put(m.getKey(), change(m.getValue(), query));
				}
			}
			else if(doAnalysis(m.getValue(), formula))
			{
				ret.put(m.getKey(), change(m.getValue(), query));
			}
		}
		return ret;
	}
	
	private static boolean isContainUid(String formula, String uid)
	{
		boolean flag = false;
		if(formula.contains(uid))
		{
			flag = true;
		}
		return flag;
	}
	
	private static boolean doAnalysis(UserMsgBean bean,String formula) throws Exception
	{
		if(formula == null || "".equals(formula))
		{
			return true;
		}
		formula = formula.replaceAll(" *", "");
		LinkedList<Integer> stack = new LinkedList<Integer>(); 
		int curPos = 0;
		String beforePart = "";
		String afterPart = "";
		String calculator = "";
		while((formula.indexOf('(') >= 0||formula.indexOf(')') >= 0))
		{
			curPos = 0;
			for(char s : formula.toCharArray())
			{
				if(s == '(')
				{
					stack.add(curPos);
				}
				else if(s == ')')
				{
					if(stack.size() > 0)
					{
						beforePart = formula.substring(0, stack.getLast());
						afterPart = formula.substring(curPos + 1);
						calculator = formula.substring(stack.getLast() + 1, curPos);
						formula = beforePart + gather(calculator , bean) + afterPart;
						stack.clear();
						break;
					}
					else
					{
						throw new Exception("有未关闭的右括号！");
					}
				}
				curPos++;
			}
			if(stack.size() > 0){
				throw new Exception("有未关闭的左括号！");
			}
		}
		return gather(formula, bean);
		
	}
	
	private static String change(UserMsgBean bean, String quety)
	{
		String ret = "";
		if(quety == null || "".equals(quety))
		{
			return "";
		}
		String[] quetys = quety.split(";");
		for(String s : quetys)
		{
			if(s.equals("fanli")) 
			{
				ret += bean.getFanli() +";";
				continue;
			}
			if(s.equals("waihui"))
			{
				ret += bean.getWaihui()+";";
				continue;
			}
			if(s.equals("tixian")) 
			{
				ret += bean.getTixian()+";";
				continue;
			}
			if(s.equals("yue"))
			{
				ret += bean.getYue()+";";
				continue;
			}
			if(s.equals("allflrank")) 
			{
				ret += bean.getAllFanliRank()+";";
				continue;
			}
			if(s.equals("monflrank")) 
			{
				ret += bean.getMonFanliRank()+";";
				continue;
			}
			if(s.equals("allwhrank")) 
			{
				ret += bean.getAllWaihuiRank()+";";
				continue;
			}
			if(s.equals("monwhrank"))
			{
				ret += bean.getMonWaihuiRank()+";";
				continue;
			}
		}
		return ret;
	}
	
	private static boolean gather(String str, UserMsgBean bean) throws Exception
	{
		str = str.replaceAll(" *", "");
		if(str.contains("&&"))
		{
			String[] strs = str.split("&&");
			for(String s : strs){
				if(s.equals("false")) return false;
				else if(!s.equals("true")){
					if(!compare(s, bean)) return false;
				}
			}
			return true;
		}
		if(str.contains("||"))
		{
			String[] strs = str.split("\\|\\|");
			for(String s : strs){
				if(s.equals("true")) return true;
				else if(!s.equals("false")){
					if(compare(s, bean)) return true;
				}
			}
			return false;
		}
		if(str.equals("true")||str.equals("false"))
		{
			return Boolean.parseBoolean(str);
		}
		else
		{
			return compare(str, bean);
		}
	}
	
	private static boolean compare(String equa, UserMsgBean bean) throws Exception
	{
		equa = equa.replaceAll(" *", "");
		if(equa.contains("<="))
		{
			int start = equa.indexOf("<=");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 2, equa.length());
			double ed = 0;
			if(e.matches(matchNumber)) ed = Double.parseDouble(e);
			if("fanli".equals(s) ) if(bean.getFanli() <= ed) return true; else return false;
			if("waihui".equals(s) ) if( bean.getWaihui() <= ed) return true; else return false;
			if("tixian".equals(s) ) if( bean.getTixian() <= ed) return true; else return false;
			if("yue".equals(s) ) if( bean.getYue() <= ed) return true; else return false;
			if("allfanlirank".equals(s) ) if( bean.getAllFanliRank() != 0 && bean.getAllFanliRank() <= ed) return true; else return false;
			if("monfanlirank".equals(s) ) if( bean.getMonFanliRank() != 0 && bean.getMonFanliRank() <= ed) return true; else return false;
			if("allwaihuirank".equals(s) ) if( bean.getAllWaihuiRank() != 0 && bean.getAllWaihuiRank() <= ed) return true; else return false;
			if("monwaihuirank".equals(s) ) if( bean.getMonWaihuiRank() != 0 && bean.getMonWaihuiRank() <= ed) return true; else return false;
		}
		if(equa.contains(">="))
		{
			int start = equa.indexOf(">=");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 2, equa.length());
			double ed = 0;
			if(e.matches(matchNumber)) ed = Double.parseDouble(e);
			if("fanli".equals(s) ) if( bean.getFanli() >= ed) return true; else return false;
			if("waihui".equals(s) ) if( bean.getWaihui() >= ed) return true; else return false;
			if("tixian".equals(s) ) if( bean.getTixian() >= ed) return true; else return false;
			if("yue".equals(s) ) if( bean.getYue() >= ed) return true; else return false;
			if("allflrank".equals(s) ) if( bean.getAllFanliRank() >= ed) return true; else return false;
			if("monflrank".equals(s) ) if( bean.getMonFanliRank() >= ed) return true; else return false;
			if("allwhrank".equals(s) ) if( bean.getAllWaihuiRank() >= ed) return true; else return false;
			if("monwhrank".equals(s) ) if( bean.getMonWaihuiRank() >= ed) return true; else return false;
		}
		if(equa.contains(">")){
			int start = equa.indexOf(">");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 1, equa.length());
			double ed = 0;
			if(e.matches(matchNumber)) ed = Double.parseDouble(e);
			if("fanli".equals(s) ) if( bean.getFanli() > ed) return true; else return false;
			if("waihui".equals(s) ) if( bean.getWaihui() > ed) return true; else return false;
			if("tixian".equals(s) ) if( bean.getTixian() > ed) return true; else return false;
			if("yue".equals(s) ) if( bean.getYue() >= ed) return true; else return false;
			if("allflrank".equals(s) ) if( bean.getAllFanliRank() > ed) return true; else return false;
			if("monflrank".equals(s) ) if( bean.getMonFanliRank() > ed) return true; else return false;
			if("allwhrank".equals(s) ) if( bean.getAllWaihuiRank() > ed) return true; else return false;
			if("monwhrank".equals(s) ) if( bean.getMonWaihuiRank() > ed) return true; else return false;
		}
		if(equa.contains("<")){
			int start = equa.indexOf("<");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 1, equa.length()); 
			double ed = 0;
			if(e.matches(matchNumber)) ed = Double.parseDouble(e);
			if("fanli".equals(s) ) if( bean.getFanli() < ed) return true; else return false;
			if("waihui".equals(s) ) if( bean.getWaihui() < ed) return true; else return false;
			if("tixian".equals(s) ) if( bean.getTixian() < ed) return true; else return false;
			if("yue".equals(s) ) if( bean.getYue() <= ed) return true; else return false;
			if("allflrank".equals(s) ) if( bean.getAllFanliRank() != 0 && bean.getAllFanliRank() < ed)	return true; else return false;
			if("monflrank".equals(s) ) if( bean.getMonFanliRank() != 0 && bean.getMonFanliRank() < ed) return true; else return false;
			if("allwhrank".equals(s) ) if( bean.getAllWaihuiRank() != 0 && bean.getAllWaihuiRank() < ed) return true; else return false;
			if("monwhrank".equals(s) ) if( bean.getMonWaihuiRank() != 0 && bean.getMonWaihuiRank() < ed) return true; else return false;
		}
		if(equa.contains("!=")){
			int start = equa.indexOf("!=");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 2, equa.length());
			if("sex".equals(s) ) if( !e.equals(bean.getSex())) return true; else return false;
			if("province".equals(s) ) if( !e.equals(bean.getProvince())) return true; else return false;
			if("city".equals(s) ) if( !e.equals(bean.getCity())) return true; else return false;
			if("uid".equals(s) ) if( !e.equals(bean.getUid())) return true; else return false;
			if("taobaonick".equals(s) ) if( !e.equals(bean.getTaobao_nick())) return true; else return false;
		}
		if(equa.contains("=")){
			int start = equa.indexOf("=");
			String s = equa.substring(0, start);
			String e = equa.substring(start + 1, equa.length());
			double ed = 0;
			if(e.matches(matchNumber)) ed = Double.parseDouble(e); 
			if("sex".equals(s) ) if( e.equals(bean.getSex())) return true; else return false;
			if("province".equals(s) ) if( e.equals(bean.getProvince())) return true;  else return false;
			if("city".equals(s) ) if(e.equals(bean.getCity())) return true; else return false; 
			if("uid".equals(s) ) if( e.equals(bean.getUid())) return true; else return false;
			if("taobaonick".equals(s) ) if( e.equals(bean.getTaobao_nick())) return true; else return false;
			if("fanli".equals(s) ) if( bean.getFanli() == ed) return true; else return false;
			if("waihui".equals(s) ) if( bean.getWaihui() == ed) return true; else return false;
			if("tixian".equals(s) ) if( bean.getTixian() == ed) return true; else return false;
			if("yue".equals(s) ) if( bean.getYue() == ed) return true; else return false;
			if("allflrank".equals(s) ) if( bean.getAllFanliRank() == ed) return true; else return false;
			if("monflrank".equals(s) ) if( bean.getMonFanliRank() == ed) return true; else return false;
			if("allwhrank".equals(s) ) if( bean.getAllWaihuiRank() == ed) return true; else return false;
			if("monwhrank".equals(s) ) if( bean.getMonWaihuiRank() == ed) return true; else return false;
		}
		throw new Exception("格式不正确！");
	}
	
	// fanli=177.91fanli
	private static void init(String formula)
	{
		ubeanmap.clear();
		
		ubeanmap = UserMsg_DAO.getUser();
		
		if(formula.contains("fanli"))
		{
			ubeanmap = UserMsg_DAO.getFanliMsg(ubeanmap, getDate(formula, "fanli"));
		}
		if(formula.contains("waihui"))
		{
			ubeanmap = UserMsg_DAO.getWaihuiMsg(ubeanmap, getDate(formula, "waihui"));
		}
		if(formula.contains("tixian"))
		{
			ubeanmap = UserMsg_DAO.getTixianMsg(ubeanmap, getDate(formula, "tixian"));
		}
		if(formula.contains("yue"))
		{
			ubeanmap = UserMsg_DAO.getYueMsg(ubeanmap, getDate(formula, "yue"));
		}
		if(formula.contains("rank"))
		{
			ubeanmap = UserMsg_DAO.getRankMsg(ubeanmap);
		}
	}
	
	private static int getDate(String formula, String prop)
	{
		Pattern pattern = Pattern.compile(prop + matchDate);
		Matcher matcher = pattern.matcher(formula);
		if(matcher.find()) 
		{
			return Integer.valueOf(matcher.group().substring(prop.length()+1));
		}
		return 0;
	}

}
