package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.youhui.platform.config.param;
import cn.youhui.platform.util.NetManager;

/**
 * Servlet implementation class keywordsServlet
 */
@WebServlet("/keywords")
public class keywordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public keywordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		JSONObject jo=null;
		if("".equals(param.json)){
			String str=NetManager.getInstance().send("http://10.0.0.21:8080/Youhui/getcategory", "");
			jo=JSONObject.fromObject(str);
			param.json=jo.toString();
		}
		
		JSONObject d=(JSONObject) JSONObject.fromObject(param.json).get("data"); 
		JSONArray keywords=(JSONArray) d.get("keywords");
		
		if(request.getParameter("k_3")!=null&&request.getParameter("k_2")!=null&&request.getParameter("k_1")!=null&&!"".equals(request.getParameter("k_1"))&&!"".equals(request.getParameter("k_2"))&&!"".equals(request.getParameter("k_3"))){
			int k_1=Integer.parseInt(request.getParameter("k_1"));
			int k_2=Integer.parseInt(request.getParameter("k_2"));
			int k_3=Integer.parseInt(request.getParameter("k_3"));
			JSONObject tmpjo=(JSONObject) keywords.get(k_1);
			JSONArray tmpja=(JSONArray) tmpjo.get("cKeywords");
			JSONObject tmpjo2=(JSONObject) tmpja.get(k_2);
			JSONArray tmpja2=(JSONArray) tmpjo2.get("cKeywords");
			JSONObject tmpjo3=(JSONObject) tmpja2.get(k_3);
			JSONArray tmpja3=(JSONArray) tmpjo3.get("cKeywords");
			String str="";
			for(int i=0;i<tmpja3.size();i++){
				JSONObject tmpjo4=(JSONObject) tmpja3.get(i);
				if(i==tmpja3.size()-1){
					str=str+tmpjo4.getString("id")+"_"+tmpjo4.getString("name");
				}else{
					str=str+tmpjo4.getString("id")+"_"+tmpjo4.getString("name")+"*";
				}
			}
			response.getWriter().write(str);
		}else if(request.getParameter("k_2")!=null&&request.getParameter("k_1")!=null&&!"".equals(request.getParameter("k_1"))&&!"".equals(request.getParameter("k_2"))){
			String k_1=request.getParameter("k_1");
			String k_2=request.getParameter("k_2");
			JSONArray tmpja2=new JSONArray();
			for(int i=0;i<keywords.size();i++){
				if(((JSONObject)(keywords.get(i))).get("id").equals(k_1)){
					JSONObject tmpjo=(JSONObject)keywords.get(i);
					JSONArray tmpja=(JSONArray) tmpjo.get("cKeywords");
					for(int j=0;j<tmpja.size();j++){
						if(((JSONObject)(tmpja.get(j))).get("id").equals(k_2)){
							JSONObject tmpjo2=(JSONObject)tmpja.get(j);
							tmpja2=(JSONArray) tmpjo2.get("cKeywords");
						}
					}
				}
			}
//			JSONObject tmpjo=(JSONObject) keywords.get(k_1);
//			JSONArray tmpja=(JSONArray) tmpjo.get("cKeywords");
//			JSONObject tmpjo2=(JSONObject) tmpja.get(k_2);
//			JSONArray tmpja2=(JSONArray) tmpjo2.get("cKeywords");
			String str="";
			for(int i=0;i<tmpja2.size();i++){
				JSONObject tmpjo3=(JSONObject) tmpja2.get(i);
				if(i==tmpja2.size()-1){
					str=str+tmpjo3.getString("id")+"_"+tmpjo3.getString("name");
				}else{
					str=str+tmpjo3.getString("id")+"_"+tmpjo3.getString("name")+"*";
				}
			}
			response.getWriter().write(str);
		}else if(request.getParameter("k_1")!=null&&!"".equals(request.getParameter("k_1"))){
			String k_1=request.getParameter("k_1");
			JSONArray tmpja=new JSONArray();
			for(int i=0;i<keywords.size();i++){
				if(((JSONObject)(keywords.get(i))).get("id").equals(k_1)){
					JSONObject tmpjo=(JSONObject)keywords.get(i);
					tmpja=(JSONArray) tmpjo.get("cKeywords");
				}
			}
//			JSONObject tmpjo=(JSONObject) keywords.get(k_1);
//			JSONArray tmpja=(JSONArray) tmpjo.get("cKeywords");
			String str="";
			for(int i=0;i<tmpja.size();i++){
				JSONObject tmpjo2=(JSONObject) tmpja.get(i);
				if(i==tmpja.size()-1){
					str=str+tmpjo2.getString("id")+"_"+tmpjo2.getString("name");
				}else{
					str=str+tmpjo2.getString("id")+"_"+tmpjo2.getString("name")+"*";
				}
			}
			response.getWriter().write(str);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public static void main(String[] args) {
		
		JSONObject jo=null;
		if("".equals(param.json)){
			String str=NetManager.getInstance().send("http://10.0.0.21:8080/Youhui/getcategory", "");
			jo=JSONObject.fromObject(str);
			param.json=jo.toString();
		}
		
		JSONObject d=(JSONObject) JSONObject.fromObject(param.json).get("data"); 
		JSONArray keywords=(JSONArray) d.get("keywords");
		
		String k_1="922";
		JSONArray tmpja=new JSONArray();
		for(int i=0;i<keywords.size();i++){
			if(((JSONObject)(keywords.get(i))).get("id").equals(k_1)){
				JSONObject tmpjo=(JSONObject)keywords.get(i);
				tmpja=(JSONArray) tmpjo.get("cKeywords");
			}
		}
		String str="";
		for(int i=0;i<tmpja.size();i++){
			JSONObject tmpjo2=(JSONObject) tmpja.get(i);
			if(i==tmpja.size()-1){
				str=str+tmpjo2.getString("id")+"_"+tmpjo2.getString("name")+"/";
			}else{
				str=str+tmpjo2.getString("id")+"_"+tmpjo2.getString("name")+"/";
			}
		}
	}
}
