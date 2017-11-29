package com.cetc28.seu.query.input;

import java.util.Scanner;

public class QueryConsoleInput implements QueryInput{
	private static Scanner scan=new Scanner(System.in);
	public String read(){
		System.out.println("输出查询语句 ：\n");
		String read=scan.nextLine();
		return read;
	}
}
