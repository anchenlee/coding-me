package cn.suishou.some.talk.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 对request声明包装类，处理get请求中文乱码
 * @author weifeng
 * 2014-09-17
 */
public class CharSetRequest extends HttpServletRequestWrapper {

	public CharSetRequest(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String val = super.getParameter(name);
		
		try {
			if (val != null && super.getMethod().equals("GET")){
				val = new String(val.getBytes("ISO-8859-1"),super.getCharacterEncoding());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return val;
	}

}
