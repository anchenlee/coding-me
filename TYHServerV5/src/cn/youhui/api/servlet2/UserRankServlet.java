package cn.youhui.api.servlet2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.UserRankManager;
import cn.youhui.ramdata.RankLockCacher;


/**
 * @category 提供排名数据
 * @author leejun
 * @since 2012-10-22
 *
 */
@WebServlet("/tyh2/rank")
public class UserRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static long old_time = 0;

	
	private static String Rank_update_status = "Rank_update_status";
	private static String update_status = "update_status";
	
	private final RankLockCacher lockCacher = RankLockCacher.getInstance();
	
	public UserRankServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
//		Thread update = new Thread() {
//			public void run(){
//				lockCacher.runRefrush();
//				if(!isUpdateTd()){	
//					UserRankManager ur = UserRankManager.getInstance();
//					if(ur.isSaveTd()){
//						ur.updateFormSave();
//					}else {
//						ur.updatedata();
//						ur.saveDatabase();
//					}
//					saveUpdateSta();
//				}
//				lockCacher.destoryRefrush();
//			};
//		};
		
//		Thread update2 = new Thread() {
//			public void run(){
//				lockCacher.runRefrush();
//				UserRankManager ur = UserRankManager.getInstance();
//				if(ur.isSaveTd()){
//					ur.updateFormSave();
//				}else {
//					ur.updatedata();
//					ur.saveDatabase();
//				}
//				saveUpdateSta();
//				lockCacher.destoryRefrush();
//			};
//		};
		
		String uid = request.getParameter("uid"); 
		if("update999".equals(uid)) {
//			update2.start();
			response.getWriter().print("updateing!!");
			return;
		}
		
		long now_time = System.currentTimeMillis();
		if (now_time - old_time > 1000*60*60*24) {
			Calendar now = Calendar.getInstance();
			Calendar zero = Calendar.getInstance();
			zero.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 2, 0, 0);      //0:0:0 开始更新
			old_time = zero.getTimeInMillis();
			boolean isRun = lockCacher.isRefrush();
			if(!isRun){
//				update.start();
		    }
		}
		response.getWriter().print(UserRankManager.getInstance().getResult(uid));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * redis中是否更新今天的数据
	 * @return 若已更新返回 true，否则false
	 */
	private boolean isUpdateTd(){
		JedisHashManager j = new JedisHashManager(Rank_update_status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		if(dateString.equals(j.get(update_status)))  return true;
		else return false;
	}
	
	/**
	 * 保存redis更新状态
	 */
	private void saveUpdateSta(){
		JedisHashManager j = new JedisHashManager(Rank_update_status);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(System.currentTimeMillis());
		j.clean();
		j.add(update_status, dateString);
	}
}