package cn.youhui.test;

import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;

public class ServerApp {
	/*public String hello(String msg, int a) {
        return "FROM SERVER : "+msg + a;
    }*/
 
    public static void main(String[] args) throws Exception {
        EventLoop loop = EventLoop.defaultEventLoop();
 
        Server svr = new Server();
        svr.serve(new WorkerImpl());
        svr.listen(1985);
 
        loop.join();
    }
}
