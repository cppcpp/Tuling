package com.ssm.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class QQMusicTest {
	public static void main(String[] args) {
		URL u;
		try {
			u = new URL("http://route.showapi.com/213-4?showapi_appid=39134&topid=5&showapi_sign=f4d12ea90fb6451cb2fe9b96b393f62b");
			 InputStream in=u.openStream();
		        ByteArrayOutputStream out=new ByteArrayOutputStream();
		        try {
		            byte buf[]=new byte[1024];
		            int read = 0;
		            while ((read = in.read(buf)) > 0) {
		                out.write(buf, 0, read);
		            }
		        }  finally {
		            if (in != null) {
		                in.close();
		            }
		        }
		        byte b[]=out.toByteArray( );
		        System.out.println(new String(b,"utf-8"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
}
