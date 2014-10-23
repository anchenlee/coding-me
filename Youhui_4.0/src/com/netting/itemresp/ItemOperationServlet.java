package com.netting.itemresp;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netting.ItemUtils.AddItemOperation;
import com.netting.ItemUtils.DelItemOperation;
import com.netting.ItemUtils.UpdateItemOperation;


@WebServlet("/ad/itemoperation")
public class ItemOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ItemOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");//操作大的类型（增，删，改）
		String operationType = request.getParameter("operation_type");//操作的方法
		
		//参数
		String itemId = request.getParameter("item_id");
		String itemId1 = request.getParameter("item_id1");
		String rank = request.getParameter("rank");
		String rank1 = request.getParameter("rank1");
		String tagId = request.getParameter("tag_id");
		String parentId = request.getParameter("parent_id");
		String itemUrl = request.getParameter("item_url");
		String status = request.getParameter("status");
		if(type == null || "".equals(type) || operationType == null || "".equals(operationType)){
			response.getWriter().print("");
			return;
		}
		
		HashMap<String ,String> map = new HashMap<String, String>();
		map.put("itemId", itemId);
		map.put("itemId1", itemId1);
		map.put("rank", rank);
		map.put("rank1", rank1);
		map.put("tagId", tagId);
		map.put("parentId", parentId);
		map.put("itemUrl", itemUrl);
		map.put("status", status);
		
		if(type.equals("add")){	//添加操作
			if(operationType.equals("addTagItemById")){
				AddItemOperation.addTagItemById(map);
			}else if(operationType.equals("addTagItemByUrl")){
				AddItemOperation.addTagItemByUrl(map);
			}
		}else if(type.equals("del")){	//删除操作
			if(operationType.equals("delTagItems")){
				DelItemOperation.delTagItem(map);
			}else if(operationType.equals("delAllItems")){
				DelItemOperation.delTagItemAll(map);
			}
		}else if(type.equals("update")){	//修改操作
			if(operationType.equals("movePosition")){
				UpdateItemOperation.movePosition(map);
			}else if(operationType.equals("lockItem")){
				UpdateItemOperation.lockTagItem(map);
			}else if(operationType.equals("addCtag")){
				UpdateItemOperation.addItemToCtag(map);
			}else if(operationType.equals("delCtag")){
				UpdateItemOperation.delItemToCtag(map);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
