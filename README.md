#简介

Filter,对web服务器管理的所有web资源对请求和响应对象进行拦截。例如实现URL级别的权限访问控制、过滤敏感信息。

#开发步骤
1、写一个类，继承Filter接口
2、配置web.xml。指定需要过滤的资源

    <!-- 定义过滤器 -->
	<filter>
		<filter-name>Demo1</filter-name>
		<filter-class>cn.tf.filter.Demo1</filter-class>
	</filter>
	
	<!-- 映射要过滤的资源 -->
	<filter-mapping>
		<filter-name>Demo1</filter-name>
		<url-pattern>/index.jsp</url-pattern>
	</filter-mapping>


#过滤器的执行过程和生命周期
###生命周期
初始化：当应用被加载时，由服务器调用默认的构造方法，并接着调用初始化方法。
doFilter：一直伴随着应用程序而存在，用户每次访问被过滤的资源，都会调用doFilter方法。
销毁：应用被卸载的时候。

###执行过程
1、在javaweb服务器：服务器启动时加载应用，加载应用的配置文件web.xml，实例化过滤器并初始化。
2、浏览器请求index.jsp。
3、创建request和response,经过过滤器，调用request，response，FilterChain。
4、通过FilterChain()处理。


#串联过滤器




#过滤器案例
编码过滤：

     private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null)
			encoding = "UTF-8";
		
		request.setCharacterEncoding(encoding);//解决POST请求参数编码
		response.setCharacterEncoding(encoding);//更改响应字符流使用的编码
		response.setContentType("text/html;charset="+encoding);//更改响应字符流使用的编码，还能告知浏览器用什么编码进行显示
		chain.doFilter(request, response);
	}
