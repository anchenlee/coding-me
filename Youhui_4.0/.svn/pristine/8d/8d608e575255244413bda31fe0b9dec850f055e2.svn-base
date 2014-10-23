package com.netting.taobao.api.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.netting.conf.SysConf;
import com.taobao.api.domain.TbkItem;

public class TaobaoRequestUtil 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( TaobaoRequestUtil.class );
	
	public static String taobaoItemGet(String item_ids)
	{
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        apiparamsMap.put("format", "xml");
        
        apiparamsMap.put("sign_method","md5");
        apiparamsMap.put("app_key", SysConf.taobao_appkey);
        apiparamsMap.put("v", "2.0");
        apiparamsMap.put("session","test");
        
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        apiparamsMap.put("timestamp",timestamp);
        
        //需要获取的字段
        apiparamsMap.put("method", "taobao.tbk.items.detail.get");
        apiparamsMap.put("fields","num_iid,title,price,pic_url");
        apiparamsMap.put("num_iids",item_ids);
        //生成签名
        
        String sign = md5Signature(apiparamsMap, SysConf.taobao_secret);
        apiparamsMap.put("sign", sign);
        
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) 
        {
            Map.Entry<String, String> e = it.next();
            param.append("&").append(e.getKey()).append("=").append(e.getValue());
        }
        
        return param.toString().substring(1);
    }
	
	/**
	 * 解析XML内容 获取商品列表
	 * @param xml
	 * @return
	 */
	public static ArrayList<TbkItem> getTbkItems(String ids)
	{
		String xml = null;
		if (ids == null || ids.equals("null") || ids.equalsIgnoreCase("null"))
		{
			return null;
		}
		else
		{
			xml = getResult(taobaoItemGet(ids));
		}
		
		if (xml == null || xml.equals("null") || xml.equalsIgnoreCase("null"))
		{
			return null;
		}
		
		ArrayList<TbkItem> resultList = null;
		
		Document doc = null;
		try
		{
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			
			// 检查<total_results>节点  
            Element totalResultsElement = root.element("total_results");  
            if (null == totalResultsElement)  
            {  
                return null;
            }
            else  
            {  
            	String totalResults = totalResultsElement.getText();  
                if (null == totalResults || "".equals(totalResults))  
                {  
                	return null; 
                }  
                else
                {  
                   int totalNum = Integer.parseInt(totalResults);
                   if (totalNum <= 0)
                   {
                	   return null;
                   }
                }
            }
            
            // 检查<tbk_items>节点  
            Element tbkitemsElement = root.element("tbk_items");
            if (null == tbkitemsElement)  
            {  
                return null;
            }
            else
            {
            	// 检查<tbk_item>节点
                @SuppressWarnings("unchecked")
				List<Element> itemElementList = tbkitemsElement.elements("tbk_item");
                if (null == itemElementList || 0 == itemElementList.size())
                {
                	return null;
                }
                else
                {
                	resultList = new ArrayList<TbkItem>();
                	
                    for (Element itemElement : itemElementList)
                    {
                    	TbkItem itemBean = new TbkItem();
                    	
                    	// 检查<num_iid>节点  
                        Element num_iid_Element = itemElement.element("num_iid");
                        if (null == num_iid_Element)
                        {
                            continue;
                        }
                        else
                        {
                            String num_iid = num_iid_Element.getText();  
                            if (null == num_iid || "".equals(num_iid))  
                            {
                            	continue;
                            }
                            else
                            {
                            	/**
                            	 * 检查商品是否支持返利
                            	 */
                            	boolean isRebate = getItemRebate(num_iid);
                            	if (!isRebate)
                            	{
                            		continue;
                            	}
                            	
                            	itemBean.setNumIid(Long.parseLong(num_iid));
                            }
                        }
                        
                        // 检查<pic_url>节点  
                        Element pic_url_Element = itemElement.element("pic_url");
                        if (null == pic_url_Element)
                        {
                            continue;
                        }
                        else
                        {
                            String pic_url = pic_url_Element.getText();  
                            if (null == pic_url || "".equals(pic_url))  
                            {
                            	continue;
                            }
                            else
                            {
                            	itemBean.setPicUrl(pic_url);
                            }
                        }
                        
                        // 检查<price>节点  
                        Element price_Element = itemElement.element("price");
                        if (null == price_Element)
                        {
                            continue;
                        }
                        else
                        {
                            String price = price_Element.getText();  
                            if (null == price || "".equals(price))  
                            {
                            	continue;
                            }
                            else
                            {
                            	itemBean.setPrice(price);
                            }
                        }
                        
                        // 检查<title>节点  
                        Element title_Element = itemElement.element("title");
                        if (null == title_Element)
                        {
                            continue;
                        }
                        else
                        {
                            String title = title_Element.getText();  
                            if (null == title || "".equals(title))  
                            {
                            	continue;
                            }
                            else
                            {
                            	itemBean.setTitle(title);
                            }
                        }
                        
                        resultList.add(itemBean);
                    }
                }
            }
		}
		catch(DocumentException e)   
        {  
            logger.error("XML解析出错：" + e);
            resultList = null;
        }
		catch (NumberFormatException de) 
		{
			logger.error("数字转换出错：" + de);
            resultList = null;
		}
		finally
		{
			doc = null;
		}
		
		return resultList;
	}
	
	public static String taobaoItemRebateAuthorizeGet(String item_id)
	{
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        apiparamsMap.put("format", "xml");
        
        apiparamsMap.put("sign_method","md5");
        apiparamsMap.put("app_key", SysConf.taobao_appkey);
        apiparamsMap.put("v", "2.0");
        apiparamsMap.put("session","test");
        
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        apiparamsMap.put("timestamp",timestamp);
        
        //需要获取的字段
        apiparamsMap.put("method", "taobao.taobaoke.rebate.authorize.get");
        apiparamsMap.put("fields","rebate");
        apiparamsMap.put("num_iid",item_id);
        //生成签名
        
        String sign = md5Signature(apiparamsMap, SysConf.taobao_secret);
        apiparamsMap.put("sign", sign);
        
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) 
        {
            Map.Entry<String, String> e = it.next();
            param.append("&").append(e.getKey()).append("=").append(e.getValue());
        }
        
        return param.toString().substring(1);
    }
	
	/**
	 * 解析XML内容 获取商品是否返利
	 * @param xml
	 * @return
	 */
	public static boolean getItemRebate(String itemID)
	{
		String xml = null;
		if (itemID == null || itemID.equals("null") || itemID.equalsIgnoreCase("null"))
		{
			return false;
		}
		else
		{
			xml = getResult(taobaoItemRebateAuthorizeGet(itemID));
		}
		
		if (xml == null || xml.equals("null") || xml.equalsIgnoreCase("null"))
		{
			return false;
		}
		
		Document doc = null;
		try
		{
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			
			// 检查<rebate>节点  
            Element rebateElement = root.element("rebate");  
            if (null == rebateElement)  
            {  
                return false;
            }
            else  
            {  
            	String rebate = rebateElement.getText();  
                if (null == rebate || "".equals(rebate))  
                {  
                	return false; 
                }  
                else
                {  
                   boolean rebateFlag = Boolean.parseBoolean(rebate);
                   return rebateFlag;
                }
            }
		}
		catch(DocumentException e)   
        {  
            logger.error("XML解析出错：" + e);
            return false;
        }
		catch (NumberFormatException de) 
		{
			logger.error("XML解析出错：" + de);
			return false;
		}
		finally
		{
			doc = null;
		}
	}
	
	 /**
	  * 新的md5签名，首尾放secret。
	  * @param secret 分配给您的APP_SECRET
     */
     public static String md5Signature(TreeMap<String, String> params, String secret) 
     {
         String result = null;
         StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
         if (orgin == null)
         {
        	 return result;
         }
         orgin.append(secret);
         try 
         {
             MessageDigest md = MessageDigest.getInstance("MD5");
             result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
         }
         catch (Exception e)
         {
             throw new java.lang.RuntimeException("sign error !");
         }
         return result;
     }
     
     /**
      *  二行制转字符串
	 */
     private static String byte2hex(byte[] b) 
     {
         StringBuffer hs = new StringBuffer();
         String stmp = "";
         for (int n = 0; n < b.length; n++) 
         {
             stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
             if (stmp.length() == 1)
             {
            	 hs.append("0").append(stmp);
             }
             else
             {
            	 hs.append(stmp); 
             }
         }
         return hs.toString().toUpperCase();
     }
     
     /**
     * 添加参数的封装方法
     */
     private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) 
     {
         if (params == null)
         {
        	 return null;
         }
         Map<String, String> treeMap = new TreeMap<String, String>();
         treeMap.putAll(params);
         Iterator<String> iter = treeMap.keySet().iterator();
         while (iter.hasNext()) 
         {
             String name = (String) iter.next();
             orgin.append(name).append(params.get(name));
         }
         return orgin;
     }
     
     /**
      * 连接到TOP服务器并获取数据
      * */
     public static String getResult(String content) 
     {
         URL url = null;
         HttpURLConnection connection = null;

         try 
         {
             url = new URL(SysConf.taobao_url);
             connection = (HttpURLConnection) url.openConnection();
             connection.setDoOutput(true);
             connection.setDoInput(true);
             connection.setRequestMethod("POST");
             connection.setUseCaches(false);
             
             connection.setConnectTimeout(3000);
             connection.setReadTimeout(3000);
             
             connection.connect();

             DataOutputStream out = new DataOutputStream(connection.getOutputStream());
             out.write(content.getBytes("utf-8"));
             out.flush();
             out.close();

             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
             StringBuffer buffer = new StringBuffer();
             String line = "";
             while ((line = reader.readLine()) != null)
             {
                 buffer.append(line);
             }
             reader.close();
             
             return buffer.toString();
         } 
         catch (IOException e) 
         {
             return null;
         } 
         finally 
         {
             if (connection != null) 
             {
                 connection.disconnect();
             }
         }
     }
     
     public static void main(String[] args) 
     {
    	 System.out.println(getResult(taobaoItemGet("18265261222,37461905054,37316595166")));
    	 
    	 ArrayList<TbkItem> list = getTbkItems("18265261222,37461905054,37316595166");
    	 for (TbkItem item : list)
    	 {
    		 System.out.println(item.getTitle());
    		 System.out.println(item.getNumIid());
    		 System.out.println(item.getPicUrl());
    		 System.out.println(item.getPrice());
    		 System.out.println("**************************");
    	 }
     }
}
