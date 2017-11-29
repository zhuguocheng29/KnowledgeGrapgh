package com.cetc28.seu.loading.theme.output;

import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.loading.theme.model.Property;

/**
 * Description : interface of themeParser's writer
 * @author yaosheng
 *
 */
public interface ParserWriter {
	public void writer(Property prop);
	public void update(Property prop);
	public void writer(ClassRelation cr);
	public void flush();

}
