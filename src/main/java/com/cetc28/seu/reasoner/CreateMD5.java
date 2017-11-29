package com.cetc28.seu.reasoner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateMD5 {

	public static String getMd5(String value)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte[] b = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for(int j = 0; j < b.length; j++)
			{
				i = b[j];
				if(i < 0)
				{
					i += 256;
				}
				if(i < 16)
				{
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
					
			}
			//16位加密
			return buf.toString().substring(8,24);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
/*	public static void main(String[] args)
	{
		System.out.println(CreateMD5.getMd5("<http://www.bjut.edu.cn/ontologies/2009/10/esesgrid.owl#泥土>"));
		System.out.println(CreateMD5.getMd5("<http://www.bjut.edu.cn/ontologies/2009/10/esesgrid.owl#材料>"));
	}*/
}
