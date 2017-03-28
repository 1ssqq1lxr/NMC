package com.lxr.nvc.model;

import java.util.Map;

public interface Model {
		public void addAttribute(String key,Object value);
		public void addAttributeMap(Map<?,?> map);
}
