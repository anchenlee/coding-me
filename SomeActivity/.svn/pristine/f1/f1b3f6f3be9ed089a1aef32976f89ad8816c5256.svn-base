package cn.suishou.some.talk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理中文乱码
 * @author weifeng
 * 2014-09-17
 *
 */
@WebFilter(urlPatterns="/*",initParams={@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharSetFilter implements Filter {
	
	private String encoding;

    public CharSetFilter() {
    }
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);
		
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getMethod().equals("GET")){
			request = new CharSetRequest(req);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
