package cn.youhui.common;

import java.util.ArrayList;
import java.util.Random;

import cn.youhui.common.Config.TaobaoApp;

public class TaobaoUMPAppPairs {
	private static ArrayList<Config.TaobaoApp> list = new ArrayList<Config.TaobaoApp>() ;
	private static Random random = new Random() ;
	private static int listSize = 0 ;
	
	protected TaobaoUMPAppPairs(){
		
		//app name : UMP服务
		list.add(new TaobaoAppImpl("12607689","2e8425fa46e0fd889f1ef78e30807b7a")) ;
		
		//app name : UMP服务A
		list.add(new TaobaoAppImpl("12608457","51857f7b6dede28abf77b06afa292195")) ;
		
		//app name : UMP服务B
		list.add(new TaobaoAppImpl("12611148","79e91989a0aa621b25602212d9d56a53")) ;

		//app name : UMP服务C
		list.add(new TaobaoAppImpl("12611150","7558706d02f3c6ad1b3e7674d6769582")) ;
		
		//app name : UMP服务D
		list.add(new TaobaoAppImpl("12611151","f99e7a6a0f53732710c4cad88d3691e3")) ;
		
		
		listSize = list.size() ;
	}
	
	
	public TaobaoApp get(){
		return list.get(random.nextInt(listSize)) ;
	}
	
	public final class TaobaoAppImpl implements TaobaoApp {
		
		private final String key ;
		private final String secret ;
		
		public TaobaoAppImpl(String appKey , String appSecret) {
			this.key = appKey ;
			this.secret = appSecret ;
		}

		@Override
		public String getKey() {
			// TODO Auto-generated method stub
			return this.key;
		}

		@Override
		public String getSecret() {
			// TODO Auto-generated method stub
			return this.secret;
		}
		
	}
}
