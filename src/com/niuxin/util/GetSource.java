package com.niuxin.util;

import java.lang.reflect.Field;

import com.example.niuxin.R;



/*if (null != name) {  
    if (name.indexOf(".") != -1) {  
        name = name.substring(0, name.indexOf("."));  
      }  */

public class GetSource {

	/**
	 * 获取图片名称获取图片的资源id的方法
	 * 
	 * @param imageName
	 * @return
	 */
	public int getResourceByReflect(String imageName) {
		if (null != imageName) {
			if (imageName.indexOf(".") != -1) {
				imageName = imageName.substring(0, imageName.indexOf("."));
			}
		}
			Class drawable = R.drawable.class;
			Field field = null;
			int r_id = 0;
			try {
				field = drawable.getField(imageName);
				r_id = field.getInt(field.getName());
			} catch (Exception e) {

			}
			return r_id;
	}

}
