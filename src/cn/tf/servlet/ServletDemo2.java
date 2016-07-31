package cn.tf.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo2 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//需要压缩的字符或资源
		String data="fyufbdljsbcjlksdnckdnjkdhjfbjcnjdjkdsnkklqispkxlndkehlfehihiohfdidsisildnckdnilfiefilefkkdhwgflkfnioufhyoinckdnckllhdefwvvewfefrvb54eqgdfbfdbgsfngfnhggfns";
		
		/*byte b[] = data.getBytes();
		System.out.println("压缩前："+b.length);
		//GZIP压缩
		ByteArrayOutputStream out = new ByteArrayOutputStream();//内存字节缓存输出流
		GZIPOutputStream gout = new GZIPOutputStream(out);
		gout.write(b);
		gout.close();//压缩后的数据写到了ByteArrayOutputStream中了
		
		b = out.toByteArray();//取出压缩后的数据
		System.out.println("压缩后："+b.length);
		//告知客户端正文的压缩方式：gzip
		response.setHeader("Content-Encoding", "gzip");
		response.setContentLength(b.length);//响应消息头,告知客户端正文的长度
		response.getOutputStream().write(b);
	*/
		
		ServletOutputStream sos = response.getOutputStream();
		sos.write(data.getBytes("UTF-8"));
	
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
