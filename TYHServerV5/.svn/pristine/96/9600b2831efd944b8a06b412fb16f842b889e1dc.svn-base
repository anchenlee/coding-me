package cn.youhui.jfbhis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.youhui.utils.DateUtil;


/**
 * 添加集分宝记录接口
 * @author hufan
 * @since 2014-9-16
 */
@WebServlet("/addJFBHis")
public class AddJFBHisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type=request.getParameter("type");
		if(type!=null){
			if(type.equals(JFBConfig.DAY)){
				String uid=request.getParameter("uid");
				String des=request.getParameter("describe");
				String img=request.getParameter("img");
				if(des!=null&&uid!=null&&img!=null){
					String describe=new String(des.getBytes("iso-8859-1"), "UTF-8");
					System.out.println("describe="+describe);
					DayMingxi dmx=new DayMingxi();
					dmx.setDescribe(describe);
					dmx.setImg(img);
					if(JFBHisManager.getInstance().addDayMingxi(uid, dmx)){
						System.out.println("add SUCCESS");
					}
				}
			}else if(type.equals(JFBConfig.TONGJI)){
				String uid=request.getParameter("uid");
				String sign=request.getParameter("sign_num");
				String fenhong=request.getParameter("fenhong_num");
				String trade=request.getParameter("trade_num");
				String des=request.getParameter("describe");
				String img=request.getParameter("img");
				if(uid!=null&&!uid.equals("")){
					TongjiMingxi tmx=new TongjiMingxi();
					if(sign!=null&&!"".equals(sign)&&fenhong!=null&&!"".equals(fenhong)&&trade!=null&&!"".equals(trade)){
						int signNum=Integer.parseInt(sign);
						int fenhongNum=Integer.parseInt(fenhong);
						int tradeNum=Integer.parseInt(trade);
						int total=signNum+fenhongNum+tradeNum;
						System.out.println("total="+total);
						TongjiBean tjb=new TongjiBean();
						tjb.setSign(JFBConfig.SIGN);
						tjb.setSignNum(signNum);
						tjb.setFenhong(JFBConfig.FENHONG);
						tjb.setFenhongNum(fenhongNum);
						tjb.setTrade(JFBConfig.TRADE);
						tjb.setTradeNum(tradeNum);
						
						tmx.setDate(DateUtil.getMonth(System.currentTimeMillis()));
						tmx.setTotalNum(total);
						tmx.setDescribe(tjb);
						tmx.setImg(img);
						
						if(des!=null&&total==0){
							String describe=new String(des.getBytes("iso-8859-1"), "UTF-8");
							System.out.println("describe="+describe);
							tmx.setTishi(describe);
						}
						if(JFBHisManager.getInstance().addTongjiMingxi(uid, tmx)){
							System.out.println("add SUCCESS");
						}
					}
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
