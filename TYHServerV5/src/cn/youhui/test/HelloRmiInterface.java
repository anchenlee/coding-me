package cn.youhui.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloRmiInterface extends Remote {
	String say() throws RemoteException ;
}
