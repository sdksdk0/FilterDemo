package cn.tf.filter1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderDemo {
	
	public static void main(String[] args) throws IOException {
		Reader reader=	new FileReader("src/cn/tf/filter1/BufferedReaderDemo.java");
		BufferedReader br=new BufferedReader(reader);
		LineBufferedReader lbr=new LineBufferedReader(br);
		String data=null;
		while((data=lbr.readLine())!=null){
			System.out.println(data);
		}
		br.close();
	
	}
	

}
