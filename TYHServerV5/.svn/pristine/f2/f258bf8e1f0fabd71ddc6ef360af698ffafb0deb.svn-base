package cn.youhui.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class YouhuiLog {
	private static YouhuiLog instance = null;
	private YouhuiLog() {}
	public static YouhuiLog getInstance(){
		if(instance == null) instance = new YouhuiLog();
		return instance;
	}
	public void logYouhui(String s){
		Logger logger = Logger.getLogger("addnewrecord");
		FileHandler fh;
		try {
			fh = new FileHandler("/var/youhui/test111.log",true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
			logger.log(Level.INFO,s);
			fh.close();
			//logger.removeHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
