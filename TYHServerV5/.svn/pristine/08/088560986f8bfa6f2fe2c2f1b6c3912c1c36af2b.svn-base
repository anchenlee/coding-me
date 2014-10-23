/**
 * 
 */
package cn.youhui.test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.UserBean;
import cn.youhui.common.Config;
import cn.youhui.common.Config.TaobaoApp;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.RedisExecutor;
import cn.youhui.ramdata.UserCacher;
import cn.youhui.utils.SetOpt;


/**
 * @author Eric Sun
 *
 */
public class Test {
	
	static int counter = 0 ;
	
	public static void main(String[] args) throws MalformedURLException, IOException{
		/*for(int n = 0 ; n < 100 ;n++){
			final String TN = "T-"+n ;
			new Thread(){
				public void run(){
					for(int i = 0 ; i < 300000L ; i++){
						testUserCacher(TN) ;
					}
				}
			}.start() ;
		}*/
		
//		testImage() ;
		
		/*for(int n = 0 ; n < 100 ;n++){
			TaobaoApp app = Config.TB_UMP_APPS.get() ;
			
			System.err.println("key = " + app.getKey()) ;
			System.err.println("sct = " + app.getSecret()) ;
			
			System.err.println("-----------------") ;
		}*/
		
//		testWidget() ;
		testSetOpt() ;
	}
	
	static void testSetOpt(){
		Set<String> s1 = new HashSet<String>() ;
		s1.add("1") ;
		s1.add("2") ;
		s1.add("3") ;
		s1.add("4") ;
		s1.add("5") ;
		
		Set<String> s2 = new HashSet<String>() ;
		s2.add("3") ;
		s2.add("4") ;
		s2.add("5") ;
		s2.add("6") ;
		s2.add("7") ;
		
	}
//	
//	static void testWidget() throws ApiException{
//		String url = "http://gw.api.taobao.com/widget/rest" ;
//		TaobaoClient client=new DefaultTaobaoClient(url, "12512985", "8957dfa33c6b41ec20a209f1e68923c8");
//		WidgetLoginstatusGetRequest req=new WidgetLoginstatusGetRequest();
//		req.setNick("yaoyuewudi");
//		req.setTimestamp(System.currentTimeMillis()) ;
//		WidgetLoginstatusGetResponse response = client.execute(req,"6101311b2d57e0bcb0415e0c17f1db8d41e148362e1fd5c82792002");
//		
//		System.err.println("\r\ncode:" + response.getErrorCode() + 
//				"\r\nmsg:" + response.getMsg() + 
//				"\r\nbody:"+response.getBody()) ;
//	}
	
	static void testImage() throws MalformedURLException, IOException{
		long l1 = System.currentTimeMillis() ;
		BufferedImage bi = ImageIO.read(new URL("http://img03.taobaocdn.com/bao/uploaded/i3/T1sTu0XhFbXXaHl_kZ_033543.jpg")) ; 
		System.err.println("w="+bi.getWidth()) ;
		System.err.println("h="+bi.getHeight()) ;
		long l2 = System.currentTimeMillis() ;
		
	}
	
	static void testUserCacher(String threadName){
		UserCacher uc = UserCacher.getInstance() ;
		long uid = 55544910L ;
		String nick1 = uc.getTaoNick(uid) ;
		UserBean user = uc.loadUser2Redis(uid) ;
		String nick2 = user.getTaobaoNick() ;
		 
	}
	
	static void test(String threadName){
		RedisRunner<Long> runner = new RedisRunner<Long>(){

			@Override
			public Long run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				return jedis.incrBy("sun74533jian", 1) ;
			}
			
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;
		
		long v = re.exe(runner) ;
		
	}
}
