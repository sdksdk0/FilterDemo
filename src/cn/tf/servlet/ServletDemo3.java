package cn.tf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo3 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String data="你们好啊fyufbdljsbcjlksdnckdnjkdhjfbjcnjdjkdsnkklqispkxlndkehlfehihiohfdidsisildnckdnilfiefilefkkdhwgflkfnioufhyoinckdnckllhdefwvvewfefrvb54eqgdfbfdbgsfngfnhggfns";
		PrintWriter out = response.getWriter();
		out.write(data);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	
	}

}
