package cn.youhui.common;

import java.util.Comparator;

public class Comp  implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Long l1=(Long) o1;
		Long l2=(Long) o2;
		return l2.compareTo(l1);
	}

}
