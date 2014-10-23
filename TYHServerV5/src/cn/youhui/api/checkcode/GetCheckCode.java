package cn.youhui.api.checkcode;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取验证码
 * @author lijun
 * @since 2014-6-23
 */
@WebServlet("/checkcode")
public class GetCheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		if(sid == null){
			sid = "";
		}
		response.setContentType("image/jpeg");  
		//禁止图像缓存
        response.setHeader("Pragma", "no-cache");   
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);
        String imgUrl = CheckCodeManager.getCheckCodeImg(sid);
        try{
        	RenderedImage img = ImageIO.read(new URL(imgUrl));
        	ImageIO.write(img , "jpg", response.getOutputStream());
        }catch(Exception e){
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
