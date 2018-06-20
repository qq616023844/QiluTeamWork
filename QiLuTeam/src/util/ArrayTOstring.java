package util;

import java.util.List;

public class ArrayTOstring 
{

	public static String arraychange(List<String> sarray)
	{
		StringBuffer s = new StringBuffer();
		for(String ss:sarray){
			s.append(ss);
			s.append(",");
		}
		s.deleteCharAt(s.length());
		
	    return s.toString();
	}
}
