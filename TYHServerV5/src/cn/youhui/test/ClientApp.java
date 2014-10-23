package cn.youhui.test;

import org.msgpack.rpc.Client;
import org.msgpack.rpc.loop.EventLoop;
 
public class ClientApp {
    /*public static interface RPCInterface {
        String hello(String msg, int a);
    }*/
 
    public static void main(String[] args) throws Exception {
        EventLoop loop = EventLoop.defaultEventLoop();
 
        Client cli = new Client("localhost", 1985, loop);
        WorkerInterface iface = cli.proxy(WorkerInterface.class);
 
        String remote = "" ;//= iface.hello("hello from client-----", 2);
        
        for(int i = 0 ; i < 100 ; i++){
        	remote = iface.fuck("  bitch -----" + i);
        	 System.err.println(remote) ;
        }
        
      
        
        cli.close() ;
        loop.shutdown() ;
    }
}