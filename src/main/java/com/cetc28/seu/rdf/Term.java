package com.cetc28.seu.rdf;

import java.io.Serializable;

public interface Term extends Serializable{
	public String getValue();
	public boolean isVariable();
}
