package co.th.ford.tms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestEncoding {
	public static void main(String[] args) {
		try {
			File fileDir = new File("C:\\Users\\GL65\\Desktop\\log_ford_2020-10-28\\ford_tms.log");

			//BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "TIS-620"));
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "TIS-620"));
			
			String str;

			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}

			in.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		 String encoded = "��-3771";
		 String theString;
		 Charset charset = Charset.forName("TIS-620");
		 
		theString = new String(encoded.getBytes(Charset.forName("TIS-620")));
		System.out.println(theString);
		 
	    
	}
}
