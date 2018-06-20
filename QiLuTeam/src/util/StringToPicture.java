package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;
import sun.misc.BASE64Decoder;

public class StringToPicture {

	
	public static void change(String path,String pictur){
		String picture = pictur.replaceAll("check", "/");
		 BASE64Decoder enc=new BASE64Decoder();
		 try {
			byte[] data = enc.decodeBuffer(picture);
			 if(data.length<3||path.equals("")) return;		    
			 FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
			 imageOutput.write(data, 0, data.length);
			 imageOutput.close();
			 System.out.println("Make Picture success,Please find image in " + path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
