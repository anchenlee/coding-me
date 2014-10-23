package cn.youhui.platform.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetManager{

	private static NetManager instance = null;

	static public NetManager getInstance() {
		if (instance == null) {
			instance = new NetManager();
		}
		return instance;
	}

	public static String send(String urlPath, String content){
		StringBuffer sbf = new StringBuffer();     
		BufferedWriter writer = null;  
		BufferedReader reader = null;  
		HttpURLConnection uc = null;  
		try {     
			URL url = new URL(urlPath);     
			uc = (HttpURLConnection)url.openConnection();            
			uc.setDoInput(true);     
			uc.setDoOutput(true);     
			uc.setRequestMethod("POST");
			uc.setRequestProperty("Accept-Charset", "UTF-8");
			uc.setRequestProperty("contentType", "UTF-8");
			//uc.setRequestProperty("Content-Type", "multipart/form-data;charset=UTF-8");  传文件用�?
	        writer = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), "UTF-8")); //向服务器传�?数据     
	        writer.write(content);                                 //传�?的数�?     
	        writer.flush();      
	        writer.close();
			reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8")); //读取服务器响应信�?    
			String line;
			while ((line = reader.readLine()) != null){
				sbf.append(line);
			}
			reader.close();
			uc.disconnect();
		} catch (Exception e) {
//			throw new Exception("请求服务器失败！"+e);
			e.printStackTrace();
		} finally{
			try {
				if(writer != null){
					writer.close();
					writer = null;
				}
				if (reader != null) {
					reader.close();  
					reader = null;  
				}
			}catch (Exception e) {  
			}    
		} 
		return sbf.toString();
	}
	
	public String getContent(String strUrl) throws Exception{
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			throw new Exception("请求服务器失败！"+e);     
		}
	}
	
	public String getContentWithPost(String strUrl, String param) throws Exception
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
	
	public String getContentGBK(String strUrl) throws Exception{
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			throw new Exception("请求服务器失败！"+e);     
		}
	}
	
}
