package com.netting.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netting.bean.Searchkeyword;
import com.netting.dao.admin.Admin_SearchKeyWord_DAO;
import com.netting.dao.admin.Admin_Tag_DAO;


@WebServlet("/getcategory")
public class GetTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTagServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ArrayList<HashMap<String, String>> TagTypeValueMap = Admin_Tag_DAO.getTagActionType_Value_Map("538");
		List<Searchkeyword> list = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0","0");

		response.getWriter().print(ListToString(TagTypeValueMap, list));
	}

	public static String ListToString(ArrayList<HashMap<String, String>> TagTypeValueMap,List<Searchkeyword> list){
		StringBuffer sb = new StringBuffer();
		if((TagTypeValueMap == null || TagTypeValueMap.size() == 0 ) && (list == null || list.size() == 0) ){
			sb.append("{").append("\"status\":").append("\"1009\",");
			sb.append("\"desc\":").append("\"Did not return a result\"}");
		}else{
			sb.append("{").append("\"status\":").append("\"1000\",");
			sb.append("\"data\":{");
			sb.append("\"tags\":[");
			for(HashMap<String, String> map : TagTypeValueMap){

				Iterator<String> iter = map.keySet().iterator();

				if (iter.hasNext()) {
					sb.append("{");
					String key = iter.next();

					String value = map.get(key);
					sb.append("\"id\":").append("\""+key+"\"").append(",").append("\"name\":").append("\""+value+"\"");
					sb.append("},");
}
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append("]");
			sb.append(",\"keywords\":[");
			for(Searchkeyword sk : list){
				List<Searchkeyword> cList = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0",sk.getId());
				sb.append("{");
				sb.append("\"id\":").append("\""+sk.getId()+"\"").append(",").append("\"name\":").append("\""+sk.getName()+"\"");
				if(cList != null && cList.size() > 0){
					sb.append(",\"cKeywords\":[");
					for(Searchkeyword sk1 : cList){
						sb.append("{");
						sb.append("\"id\":").append("\""+sk1.getId()+"\"").append(",").append("\"name\":").append("\""+sk1.getName()+"\"");
						
						List<Searchkeyword> cList1 = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0",sk1.getId());
						if(cList1 != null && cList1.size() > 0){
							sb.append(",\"cKeywords\":[");
							for(Searchkeyword sk2 : cList1){
								sb.append("{");
								sb.append("\"id\":").append("\""+sk2.getId()+"\"").append(",").append("\"name\":").append("\""+sk2.getName()+"\"");
								
								List<Searchkeyword> cList2 = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0",sk2.getId());
								if(cList2 != null && cList2.size() > 0){
									sb.append(",\"cKeywords\":[");
									for(Searchkeyword sk3 : cList2){
										sb.append("{");
										sb.append("\"id\":").append("\""+sk3.getId()+"\"").append(",").append("\"name\":").append("\""+sk3.getName()+"\"");
										
//										List<Searchkeyword> cList3 = Admin_SearchKeyWord_DAO.getKeywordList( "1",  "0",sk3.getId());
										
									}
									sb.deleteCharAt(sb.lastIndexOf(","));
									sb.append("]");
								}
								sb.append("},");
							}
							sb.deleteCharAt(sb.lastIndexOf(","));
							sb.append("]");
						}
						sb.append("},");
					}
					sb.deleteCharAt(sb.lastIndexOf(","));
					sb.append("]");
				}
				sb.append("},");
			}			
			sb.deleteCharAt(sb.lastIndexOf(","));
			sb.append("]}");
			sb.append("}");
			
		}
		return sb.toString();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
