package com.cetc28.seu.loading.theme.server;

import java.util.List;

public class ExtractUtils {
	public static List<Object> extract(String key, Class<?> forName, int ipage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	public static int getNums(String key, Class<?> forName){
		int nums=0;
		return nums;
	}
	public static int setPageNums(int pageSize,int totalCount){
		int pageNums=totalCount/pageSize+1;
		return pageNums;
	}
}
