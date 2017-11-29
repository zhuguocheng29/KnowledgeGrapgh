package com.cetc28.seu.loading.theme.model;

import java.util.HashSet;
import java.util.Set;

public class EquivalentObject {
	
	private static Set<Integer> hashCodeSet=new HashSet<Integer>();
	
	public static boolean isExist(Object obj){
		if(hashCodeSet.contains(obj.hashCode())){
			return true;
		}
		hashCodeSet.add(obj.hashCode());
		return false;
	}
}
