package cn.youhui.test;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class HelloRmiImpl extends UnicastRemoteObject implements
		HelloRmiInterface {
	static int counter = 0 ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1930057581174611602L;

	public HelloRmiImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public HelloRmiImpl(int port) throws RemoteException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	public HelloRmiImpl(int port, RMIClientSocketFactory csf,
			RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String say() throws RemoteException {
		// TODO Auto-generated method stub
		return "Response by RMI server ... times:"+ counter++;
	}

}
