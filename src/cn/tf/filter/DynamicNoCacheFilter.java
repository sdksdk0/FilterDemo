package cn.tf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DynamicNoCacheFilter implements Filter {

	@Override
	public void destroy() {
			

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//HttpServletRequest  request=(HttpServletRequest) req;
		//HttpServletResponse  response=(HttpServletResponse) resp;
		
		HttpServletRequest request;
		HttpServletResponse response;
		
		try {
			request=(HttpServletRequest) req;
			response=(HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		response.setDateHeader("Expires",-1);//缓存时间
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		chain.doFilter(request, response);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
