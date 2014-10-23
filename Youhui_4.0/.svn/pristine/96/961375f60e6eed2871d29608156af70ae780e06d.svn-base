package com.netting.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

/**
 * 忘了数据流访问工具类
 * @author YAOJIANBO
 *
 */
public class NetManager 
{
	public static String send(String urlPath, String content) throws Exception
	{
		StringBuffer sbf = new StringBuffer();     
		BufferedWriter writer = null;  
		BufferedReader reader = null;  
		HttpURLConnection uc = null;  
		try 
		{     
			URL url = new URL(urlPath);     
			uc = (HttpURLConnection)url.openConnection();            
			uc.setDoInput(true);     
			uc.setDoOutput(true);     
			uc.setRequestMethod("POST");   
			uc.setRequestProperty("Accept-Charset", "UTF-8");
			uc.setRequestProperty("contentType", "UTF-8");
			uc.setConnectTimeout(10000);
	        writer = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), "UTF-8")); //向服务器传送数据     
	        writer.write(content);                                 //传送的数据      
	        writer.flush();      
	        writer.close();
			
	        reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8")); //读取服务器响应信息     
			String line;     
			while ((line = reader.readLine()) != null)
			{     
				sbf.append(line);     
			}     
			reader.close();     
			uc.disconnect();     
		} 
		catch (Exception e) 
		{     
			throw new Exception("请求服务器失败！"+e);     
		} 
		finally
		{     
			try 
			{  
				if(writer != null)
				{
					writer.close();
					writer = null;
				}
				if (reader != null) 
				{    
					reader.close();  
					reader = null;  
				}
			}
			catch (Exception e) 
			{
				
			}    
		} 
		return sbf.toString();
	}
	
	public static String getContent(String strUrl)
	{
		String content = "";
		try 
		{
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) 
			{
				sb.append(s);
			}
			br.close();
			content = sb.toString();
		} 
		catch (Exception e)
		{
			content = "";
		}
		return content;
	}
	
	public static String getContentWithPost(String strUrl, String param) throws Exception
	{
		try
		{
			URL url = new URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			out.print(param);
			out.flush();
			out.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) 
			{
				sb.append(s);
			}
			br.close();
			return sb.toString();
		} 
		catch (Exception e) 
		{
			throw new Exception("请求服务器失败！"+e);     
		}
	}
	
	public static String getContentGBK(String strUrl) throws Exception
	{
		try 
		{
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) 
			{
				sb.append(s);
			}
			br.close();
			return sb.toString();
		} 
		catch (Exception e) 
		{
			throw new Exception("请求服务器失败！"+e);     
		}
	}
	
	public static String convertToPageViewUrl(String url, String title, String description,String id)
	{
		String ret = url;
		try
		{
			ret = new BASE64Encoder().encode(ret.getBytes());
			ret = send("http://b17.cn/skip/" + "AdUrlConvertServlet",
					"url_address="+ret+"&url_name="+title+"&url_describtion="+description+"&encode=1&uid=suishouyouhui&adid="+id+"");
		}
		catch(Exception e)
		{
			return url;
		}
		return ret;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		try 
		{
			NetManager.getContent("http://localhost:8080/TYHServerV5/jfb/index.jsp?param=d0FOVFU1SmpVd09ETXhNREUxdnA=&tyh_outer_code=AAAAESEVDEFU");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < 20; i++)
		{
			try 
			{
				NetManager.getContent("http://localhost:8080/TYHServerV5/tyh2/jfb_act?param=d0FOVFU1SmpVd09ETXhNREUxdnA=");
				System.out.println(i);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			Thread.sleep(100);
		}
		
	}
}
