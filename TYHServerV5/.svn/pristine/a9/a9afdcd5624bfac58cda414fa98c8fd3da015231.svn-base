package cn.youhui.util.oufeicz;

public class QueryThread implements	 Runnable {

	OufeiCZ order;
	String orderId = "";
	
	public QueryThread(OufeiCZ order,String orderId){
		this.order = order;
		this.orderId =  orderId;
	}
	
	@Override
	public void run() {
		String status = "";
		int i = 0;
		do {
			new OfPayUtil();
			status = OfPayUtil.OufeiQuery(orderId);
			if ("1".equals(status) || "9".equals(status)){
				order.setCpStatus(Integer.parseInt(status));
				break;
			}
			i++;
			try {
				Thread.sleep(3*60*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (i != 100);
		OufeiCZDAO.getInstance().updateStatus(order);
	}

}
