package cn.tf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class Demo1 implements Filter {

	
	public Demo1(){
		System.out.println("构造方法");
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化");
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("过滤了");
		chain.doFilter(request,response);
	}

	

	@Override
	public void destroy() {
		System.out.println("销毁了");
		
	}

}
