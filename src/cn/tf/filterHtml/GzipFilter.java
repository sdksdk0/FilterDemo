package cn.tf.filterHtml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipFilter implements Filter{

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
		
		MyHttpServletResponse  mresponse=new MyHttpServletResponse(response);
		chain.doFilter(request, mresponse);
		
		
		//获取原始数据
		byte b[] = mresponse.getOldData();
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
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}


	class MyHttpServletResponse  extends HttpServletResponseWrapper{

		private ByteArrayOutputStream baos=new ByteArrayOutputStream();
		private PrintWriter pw = null;
		public MyHttpServletResponse(HttpServletResponse response) {
			super(response);
			
		}
		
		//截获数据：字符流
		public PrintWriter getWriter() throws IOException {
			pw = new PrintWriter(new OutputStreamWriter(baos, super.getCharacterEncoding()));
			return pw;
		}
		
		//截获数据:字节流
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyServletOutputStream(baos);
		}
	
		//返回截取的数据
		public byte[] getOldData(){
			try {
				if(pw!=null){
					pw.close();
				}
				baos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return baos.toByteArray();
		}
		
	}
	
		class MyServletOutputStream extends ServletOutputStream{

			private ByteArrayOutputStream  baos;
			
			public MyServletOutputStream(ByteArrayOutputStream baos){
				this.baos=baos;
			}
			
			@Override
			public void write(int b) throws IOException {
				baos.write(b);
				
			}
			
		}




