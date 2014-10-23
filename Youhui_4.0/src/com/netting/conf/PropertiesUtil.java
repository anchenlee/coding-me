package com.netting.conf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 参数配置文件工具类
 * @author  YAOJIANBO
 * @version  1.0
 */
public class PropertiesUtil
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(PropertiesUtil.class);
	
	private static Properties props = new Properties();
	
	private static InputStream in = null;
	
	/**
	 * 加载特定的配置文件
	 * @param filePath
	 */
	public static void load(String filePath)
	{
		
        try
        {
        	URL url = PropertiesUtil.class.getClassLoader().getResource(filePath);
			in = new BufferedInputStream(url.openStream());
			props.load(in);
		} 
        catch (IOException e)
        {
			e.printStackTrace();
		}
	}
	
    /**
     * 根据key读取value
     */
    public static String readValue(String key)
    {
    	return props.getProperty(key);
    }
    
    /**
     * 关闭properties文件输入流
     */
    public static void close()
    {
    	try 
    	{
    		props.clear();
			in.close();
			in = null;
		} 
    	catch (IOException e) 
    	{
			logger.error("关闭properties文件输入流异常：", e);
		}
    }
    
    public static void main(String[] args)
    {
    	load("conf.properties");
    	System.out.println(readValue( "RedisServer.IP" ));
    	close();
    }
}

