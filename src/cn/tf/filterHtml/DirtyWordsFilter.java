package cn.tf.filterHtml;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class DirtyWordsFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;
		
		try {
			request=(HttpServletRequest) req;
			response=(HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		DWHttpServletRequest  dwrequest=new DWHttpServletRequest(request) ;
		chain.doFilter(dwrequest, response);
	}
		
		

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}


	class DWHttpServletRequest extends HttpServletRequestWrapper{

		private String[] strs={"傻逼","人妖","他妈的"};
		
		public DWHttpServletRequest(HttpServletRequest request) {
			super(request);
			
		}
		
		@Override
		public String getParameter(String name) {
			String value=super.getParameter(name);
			if(value==null)
				return null;
			
			for(String s:strs){
				value=value.replace(s, "**");
			}
			return value;
		}
		
	}
