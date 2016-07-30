package cn.tf.filter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class LineBufferedReader extends BufferedReader{

	private BufferedReader br;
	private int count=1;
	
	
	
	public LineBufferedReader(BufferedReader br) {
		super(br);
		this.br=br;
	}
	public String readLine() throws IOException  {
		String rtValue=br.readLine();
		if(rtValue==null){
			return null;
		}
		
		return count+++":"+br.readLine();
	}
	

}
