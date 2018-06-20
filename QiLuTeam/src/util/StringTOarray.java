package util;

import java.util.Arrays;
import java.util.List;

public class StringTOarray 
{

	public static List<String> stringchange(String s)
	{
		String[] sarray = s.split(",");
		List<String> list = Arrays.asList(sarray);
		return list;
	}
}
