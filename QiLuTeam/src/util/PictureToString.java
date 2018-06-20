package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import sun.misc.BASE64Encoder;
import javax.imageio.stream.FileImageInputStream;
public class PictureToString {

	public static String change(String path){
		//图片到byte数组
     	   String mes = null;
		   byte[] data = null;
		   FileImageInputStream input = null;
		   try {
		     input = new FileImageInputStream(new File(path));
		     ByteArrayOutputStream output = new ByteArrayOutputStream();
		     byte[] buf = new byte[1024];
		     int numBytesRead = 0;
		     while ((numBytesRead = input.read(buf)) != -1) {
		     output.write(buf, 0, numBytesRead);
		   }
		     data = output.toByteArray();
		     output.close();
		     input.close();
			 BASE64Encoder enc=new BASE64Encoder();
			 mes = enc.encodeBuffer(data); //使用BASE64编码
		   } catch (FileNotFoundException ex1) {
		      ex1.printStackTrace();
		   } catch (IOException ex1) {
		      ex1.printStackTrace();
		   }
		   System.out.println("replace运行中");
		   mes = mes.replaceAll("\n", "").replaceAll("/", "check");
		   return mes;
	}
	
}
