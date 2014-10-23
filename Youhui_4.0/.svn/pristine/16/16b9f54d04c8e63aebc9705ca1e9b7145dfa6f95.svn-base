package com.netting.action.admin;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.bean.TongjiBean;
import com.netting.bean.TongjiRequest;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.Admin_Tongji_DAO;
import com.netting.util.CodeUtil;
import com.netting.util.CreateXL;
import com.netting.util.NetManager;
import com.netting.util.ParameterUtil;

@WebServlet("/ad/statistic")
public class Admin_Tongji_Action extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	private String path = "";
	private String downName;
	private String sp = File.separator;

	public static boolean runtimedebug = true;
	public static String NewUrlTest = "http://localhost:8080/AliyunSkipServer/";
	public static String NewUrl = "http://b17.cn/skip/";
	
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( Admin_Tongji_Action.class );
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("actionmethod");
		if (null == methodName || "".equals(methodName) || "null".equals(methodName))
		{
			response.getWriter().print("<script>alert('Param actionmethod can not be null！');history.back();</script>");
			return;
		}
		try
		{
			if(methodName.equals("showStatisticJSP"))
			{
				showStatisticJSP(request,response);
			}
			else if(methodName.equals("checkClickNum"))
			{
				checkClickNum(request,response);
			}
			else if(methodName.equals("dataStatistic"))
			{
				dataStatistic(request,response);
			}
		}
		catch (Exception e)
		{
			response.getWriter().print("<script>alert('server process error！');history.back();</script>");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		this.doGet(req, resp);
	}
	
	/**
	 * 统计页面
	 * @throws Exception
	 */
	 public void showStatisticJSP(HttpServletRequest request, HttpServletResponse response) throws Exception
	 {
		 List<String> channelList = Admin_Tongji_DAO.getChannel();
		 List<KeywordBean> targetList = Admin_Tag_Item_DAO.getKeywordsIsShow("538",false);

		 request.setAttribute("channelList", channelList);
		 request.setAttribute("targetList", targetList);
		 
		 request.getRequestDispatcher("/admin/tongji/statistics.jsp").forward(request, response);
		 return;
	 }
	 
	/**
     * 查看点击量
     * @throws Exception
     */
    public void checkClickNum(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String adid = request.getParameter("id");
		String title = request.getParameter("title");
		if(title == null) 
		{
			title="";
		}
		
		if(title != null && !"".equals(title))
		{
			title = new String(title.getBytes("iso-8859-1"), "utf-8");
		}
		if(adid == null||"".equals(adid))
		{			
			request.setAttribute("content", "['',]");
			request.setAttribute("title", title);
			request.getRequestDispatcher("/admin/tongji/click_num.jsp").forward(request, response);
			return;
		}

		String strUrl = NewUrl+"GetAdViewServlet?id="+adid+"";

		String content =  NetManager.getContent(strUrl);			
	
		if(content==null)
		{
			content = "";
		}
//			content = "[{\"time\":\"2013-07-29\",\"count\":\"10\"},{\"time\":\"2013-07-30\",\"count\":\"10\"},{\"time\":\"2013-07-31\",\"count\":\"10\"}]";
		List<TongjiBean> list = getListFromJson(content);
		request.setAttribute("list", list);
		content = TongjiJsonConvert(content);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.getRequestDispatcher("/admin/tongji/click_num.jsp").forward(request, response);
		return;
    }
	 
	 
	 /**
	  * 数据统计
	  * @throws Exception
	  */
	public void dataStatistic(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String type = ParameterUtil.getTrueString(request,"type","");
		String keyword = ParameterUtil.getTrueString(request,"keyword","");
		String startDate = ParameterUtil.getTrueString(request,"startdate", "20121201");
		String endDate = ParameterUtil.getTrueString(request,"enddate","20141212");
		String frequency = ParameterUtil.getTrueString(request,"frequency","");
		String daochu = ParameterUtil.getTrueString(request,"daochu","");
		String channel = ParameterUtil.getTrueString(request,"channel","");
		
		if (type == null || "".equals(type)) 
		{
			response.getWriter().print("");
			return;
		}
		if(startDate.length()==8) 
		{
			startDate = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,8);
		}
		
		if(endDate.length()==8) 
		{
			endDate = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8);
		}
		
		TongjiRequest tjbean = new TongjiRequest();
		tjbean.setChannel(channel);
		tjbean.setDaochu(daochu);
		tjbean.setEndDate(endDate);
		tjbean.setFrequency(frequency);
		tjbean.setKeyword(keyword);
		tjbean.setStartDate(startDate);
		tjbean.setType(type);
		
		if(type.equals("targetItemClick")) 
		{
			startDate = CodeUtil.getUnixTimestemp(startDate+" 00:00:00")+"";
			endDate = CodeUtil.getUnixTimestemp(endDate+" 23:59:59")+"";
			String strUrl = getMysqlUrl()+"GetTagItemClickNumTopServlet?tag_id=11"+"&startTime="+startDate+"&endTime="+endDate+"";
			String data = "";
			try {					
				data = NetManager.getContent(strUrl);
			} catch (Exception e) {
				data="";
			}
			response.getWriter().println(data);
			return;	
		}
		
		List<TongjiBean> list1 = new ArrayList<TongjiBean>();
		List<TongjiBean> list2 = new ArrayList<TongjiBean>();
		List<TongjiBean> list3 = new ArrayList<TongjiBean>();
		list1 = Admin_Tongji_DAO.getTongjiData(tjbean, "1");
		list2 = Admin_Tongji_DAO.getTongjiData(tjbean, "2");
		list3 = Admin_Tongji_DAO.getTongjiData(tjbean, "3");

		String result = "";
		String timejson = getTime(startDate, endDate, frequency);
		String result1 = changeListContent(list1,tjbean) ;
		String result2 = changeListContent(list2,tjbean) ;
		String result3 = changeListContent(list3,tjbean) ;
		if(daochu==null||"".equals(daochu))
		{
			
//			String result1 = "[[1,26],[2,32]]";
//			String result2 = "[[1,29],[2,36]]";
//			String result3 = "[[1,25],[2,33]]";
			result = result1+"?"+result2+"?"+result3+"?"+timejson;
//			System.out.println("----------------"+result);
			response.getWriter().print(result);
			return;
		}
		else
		{
			String uploadPath = sp + "upload" + sp; // 用于存放上传文件的目录
			path = request.getServletContext().getRealPath("/");
			uploadPath = path + uploadPath;
			File file = new File(uploadPath);
			if (!file.exists()) 
			{
				file.mkdirs();
			} 
			else 
			{
				String[] fileList = file.list();
				for (String str : fileList) 
				{
					File f = new File(uploadPath + str);
					f.delete();
				}
			}
			
			String tishi1 ="订单结算"+","+"订单创建"+","+"订单成功";

			if(type.equals("jifenbao"))
			{
				tishi1 = "总集分宝量"+","+  "领取总人数"+","+"人均领取量";
			}
			else if(type.equals("userFanxian")) 
			{
				tishi1 ="佣金统计"+","+ "返利统计"+","+ "---";
			}
			else if(type.equals("itemJine")) 
			{
				tishi1 = "商品交易金额"+","+ "---"+","+ "---";
			}
			else if(type.equals("userRegister"))
			{
				tishi1 ="用户注册量"+","+ "---"+","+ "---";
			}
			downName = "tongjidate " + "table" + ".xls";
			path = uploadPath + downName;
			List<TongjiBean> list = new ArrayList<TongjiBean>();
			String filePath = request.getContextPath();
			String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ filePath + sp + "upload" + sp + downName;
			CreateXL.createExcel(path, list, downName, "test");
			CreateXL.createTongjiExcel(path, timejson, result1, result2, result3, tishi1, downName, "shujutongji");
			response.getWriter().println(basePath);
			return;
		}
	}
	
	/**
	 * 将数据日期变的连续，没有数据的日期 数据为0
	 * @param list
	 * @return
	 */
	public static String changeListContent(List<TongjiBean> list,TongjiRequest tjbean)
	{
		List<TongjiBean> list2 = new ArrayList<TongjiBean>();
		if(tjbean.getType().equals("jifenbao")||tjbean.getType().equals("userFanxian")||tjbean.getType().equals("ItemOrderTotalTongji")
				||tjbean.getType().equals("targetItemTrade")||tjbean.getType().equals("itemTrade")||tjbean.getType().equals("userRegister")||tjbean.getType().equals("itemJine")) 
		{
			if(tjbean.getFrequency().equals("2")) //按天
			{
				if(list==null||list.size()==0) 	//数据为空
				{
					String day = tjbean.getStartDate().substring(0,10);
					while(!day.equals(tjbean.getEndDate().subSequence(0, 10))) 
					{
						TongjiBean bean = new TongjiBean();
						bean.setTitle(day);
						bean.setValue("0");
						list2.add(bean);
						day = CodeUtil.getNextDateString(day);
						if(Integer.parseInt(day.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4)+1)) 
						{
							return "";
						}
					}
				}
				else 
				{
					String day = tjbean.getStartDate().substring(0,10);
					int i = 0;
					int j = list.size()-1;
					while(!day.equals(tjbean.getEndDate().subSequence(0, 10))) 
					{
						if(CodeUtil.getUnixTimestemp(list.get(0).getTitle()+" 00:00:00")>CodeUtil.getUnixTimestemp(list.get(list.size()-1).getTitle()+" 00:00:00")) 
						{//升序
							if(j>=0&&list.get(j).getTitle().equals(day)) 
							{
								list2.add(list.get(j));
								j--;
							}
							else 
							{
								TongjiBean bean = new TongjiBean();
								bean.setTitle(day);
								bean.setValue("0");
								list2.add(bean);
					
							}
						}
						else 
						{
							if(i<list.size()&&list.get(i).getTitle().equals(day))
							{
								list2.add(list.get(i));
								i++;
							}
							else
							{
								TongjiBean bean = new TongjiBean();
								bean.setTitle(day);
								bean.setValue("0");
								list2.add(bean);
							}
						}
						day = CodeUtil.getNextDateString(day);
						if(Integer.parseInt(day.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4)+1)) 
						{
							return "";
						}
					}
				}
			}
			else if(tjbean.getFrequency().equals("1"))	//按月
			{
				if(list==null||list.size()==0) 
				{
					String month = tjbean.getStartDate().substring(0,7);
					while(!month.equals(tjbean.getEndDate().substring(0,7))) 
					{

						TongjiBean bean = new TongjiBean();
						bean.setTitle(month);
						bean.setValue("0");
						list2.add(bean);
						month = CodeUtil.getNextMonth(month);
						if(Integer.parseInt(month.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4)+1)) 
						{
							return "";
						}
					}
					if(Integer.parseInt(month.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4)+1))
					{
						return "";
					}
				} 	
				else 
				{
					String month = tjbean.getStartDate().substring(0,7);
					int j = list.size()-1;
					while(!month.equals(tjbean.getEndDate().substring(0,7))) 
					{
						if(j>=0&&list.get(j).getTitle().equals(month)) 
						{
							list2.add(list.get(j));
							j--;
						}
						else 
						{
							TongjiBean bean = new TongjiBean();
							bean.setTitle(month);
							bean.setValue("0");
							list2.add(bean);
						}
						month = CodeUtil.getNextMonth(month);
						if(Integer.parseInt(month.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4)+1)) 
						{
							return "";
						}
					}
				} 	
	
			}
			else if(tjbean.getFrequency().equals("3")) 	//按小时
			{	
				String hour = tjbean.getStartDate().substring(0,10)+" 00";
				for(int i=list.size()-1;i>=0;i--) {
					while(!hour.equals(list.get(i).getTitle())) {
						TongjiBean bean = new TongjiBean();
						bean.setTitle(hour);
						bean.setValue("0");
						list2.add(bean);
						hour = CodeUtil.getNextHour(hour, 1);
						if(Integer.parseInt(hour.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4))+1) {
							return "";
						}
					}
					TongjiBean bean = new TongjiBean();
					bean.setTitle(hour);
					bean.setValue(list.get(i).getValue());
					list2.add(bean);
					hour = CodeUtil.getNextHour(hour, 1);
				}
				if(!hour.equals(tjbean.getEndDate()+" 00")) {
					while(!hour.equals(tjbean.getEndDate()+" 00")) {
						TongjiBean bean = new TongjiBean();
						bean.setTitle(hour);
						bean.setValue("0");
						list2.add(bean);
						hour = CodeUtil.getNextHour(hour, 1);
						if(Integer.parseInt(hour.substring(0,4))>Integer.parseInt(tjbean.getEndDate().substring(0,4))+1) {
							return "";
						}
					}
				}
			}
//			for(int i=0;i<list2.size();i++) {
//				System.out.println(list2.get(i).getTitle());
//			}
			return orderChangeToString(list2);
		}
		else 
		{
			if(list != null && list.size() > 0) 
			{
				return orderChangeToString(list);
			}
			else 
			{
				return "";
			}
		}
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String changeListTojson(List<TongjiBean> list) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (TongjiBean tongjiBean : list) 
		{
			sb.append(tongjiBean.formatToJson()+",");
		}
		
		if(sb.length() > 1 && sb.charAt(sb.length()-1) == ',')
		{
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static String orderChangeToString(List<TongjiBean> list) 
	{
		int i = 1;
		StringBuffer sb = new StringBuffer();
		DecimalFormat df = new DecimalFormat("#.##");
		sb.append("[");
		for (TongjiBean tongjiBean : list) 
		{
			sb.append("[");
			sb.append(i);
			sb.append(",");
			sb.append(df.format(Double.parseDouble(tongjiBean.getValue())));
			sb.append("],");
			i++;
		}
		
		if(sb.length() > 1 && sb.charAt(sb.length()-1) == ',')
		{
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 时间连续
	 * @param startDate
	 * @param endDate
	 * @param frequency 1：月；2：天；3：小时
	 * @return
	 */
	public static String getTime(String startDate,String endDate,String frequency)
	{
		String day = startDate.substring(0, 10);
		String month = startDate.substring(0, 7);
		StringBuffer sb = new StringBuffer();
		// sb.append("[");
		if (frequency.equals("2")) 
		{
			while (!day.equals(endDate.subSequence(0, 10))) 
			{
				sb.append("").append(day).append("").append(", ");
				day = CodeUtil.getNextDateString(day);
			}
			if (Integer.parseInt(day.substring(0, 4)) > Integer.parseInt(endDate.substring(0, 4) + 1)) 
			{
				return "";
			}
		} 
		else if (frequency.equals("1"))
		{
			while (!month.equals(endDate.subSequence(0, 7)))
			{
				sb.append("").append(month).append("").append(", ");
				month = CodeUtil.getNextMonth(month).substring(0, 7);
			}
			if (Integer.parseInt(month.substring(0, 4)) > Integer.parseInt(endDate.substring(0, 4) + 1)) 
			{
				return "";
			}
		}
		else if (frequency.equals("3")) 
		{
			String hour = startDate.substring(0, 10) + " 00";
			while (!hour.equals(endDate.substring(0, 10) + " 00")) 
			{
				sb.append("").append(hour).append("").append(", ");
				hour = CodeUtil.getNextHour(hour, 1);
				if (Integer.parseInt(hour.substring(0, 4)) > Integer.parseInt(endDate.substring(0, 4)) + 1) 
				{
					break;
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public static List<TongjiBean> getListFromJson(String content) 
    {
    	List<TongjiBean> list = new ArrayList<TongjiBean>();
    	JSONArray jsa;
		try 
		{
			if(content == null ||"".equals(content))
			{
				return null;
			}
			jsa = new JSONArray(content);
			if(jsa.length()>0) 
			{
				for(int i=0;i<jsa.length();i++) 
				{
					JSONObject jso = jsa.getJSONObject(i);
					TongjiBean bean = new TongjiBean();
					bean.setTitle(jso.getString("time"));
					bean.setValue(jso.getString("count"));
					list.add(bean);
				}
			}
		} 
		catch (JSONException e) 
		{
			logger.error("Admin_Tongji_Action.getListFromJson error :",e);
		}
		
		return list;
    }
	
	 /**
     * 统计的数据格式转换
     * @param content
     * @return
     * @throws JSONException 
     */
    public static String TongjiJsonConvert(String content)
    {
    	StringBuffer data = new StringBuffer();
    	JSONArray jsa;
		try 
		{
			if(content == null || "".equals(content))
			{
				return "[]";
			}
			jsa = new JSONArray(content);
			if(jsa.length()>0) 
			{
				for(int i=0;i<jsa.length();i++) 
				{
					JSONObject jso = jsa.getJSONObject(i);
					data.append("['"+jso.getString("time")+"',  "+jso.getString("count")+"],");
				}
				data.deleteCharAt(data.length()-1);
			}
		} 
		catch (JSONException e) 
		{
			logger.error("Admin_Tongji_Action.TongjiJsonConvert error:",e);
		}
    	return data.toString();
    }
	
	public static String getMysqlUrl() {
		return runtimedebug?NewUrlTest:NewUrl;
    }
}
