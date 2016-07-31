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

public class HtmlFilter implements Filter{

	@Override
	public void destroy() {
		
		
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
		
		HttpServletRequest hrequest=new HtmlHttpServletRequest(request);
		chain.doFilter(hrequest,response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	

}

class HtmlHttpServletRequest extends HttpServletRequestWrapper{

	public HtmlHttpServletRequest(HttpServletRequest request) {
		super(request);
		
	}
	
	@Override
	public String getParameter(String name) {
		
		String value=super.getParameter(name);
		if(value==null)
			return null;
		value=filter(value);
		return value;
		
	
	}

	private String filter(String message) {
		 if (message == null)
	            return (null);

	        char content[] = new char[message.length()];
	        message.getChars(0, message.length(), content, 0);
	        StringBuilder result = new StringBuilder(content.length + 50);
	        for (int i = 0; i < content.length; i++) {
	            switch (content[i]) {
	            case '<':
	                result.append("&lt;");
	                break;
	            case '>':
	                result.append("&gt;");
	                break;
	            case '&':
	                result.append("&amp;");
	                break;
	            case '"':
	                result.append("&quot;");
	                break;
	            default:
	                result.append(content[i]);
	            }
	        }
	        return (result.toString());
	}
	}
	



