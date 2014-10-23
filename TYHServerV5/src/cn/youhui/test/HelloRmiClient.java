package cn.youhui.test;

import java.rmi.Naming;

public class HelloRmiClient {
	public static void main(String[] args){
		 try  
	      {   
	         HelloRmiInterface hello = (HelloRmiInterface) Naming.lookup("Hello");   
	            
	         //如果要从另一台启动了RMI注册服务的机器上查找hello实例   
	         //HelloInterface hello = (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");   
	            
	         //调用远程方法   
	         long l1 = System.currentTimeMillis() ;
	         for(int i = 0 ; i < 10000 ; i++){
	        	 System.out.println(hello.say());   
	         }
	         long l2 = System.currentTimeMillis() ;
	         
	         System.out.println("cost:"+(l2 -l1)) ;
	      }   
	      catch (Exception e)   
	      {   
	         System.out.println("HelloClient exception: " + e);   
	      }   
	}
}
