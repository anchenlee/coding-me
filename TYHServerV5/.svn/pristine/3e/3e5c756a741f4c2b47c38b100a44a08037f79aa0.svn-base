package cn.youhui.util.oufeicz;

import java.util.List;
public class CZReTryUtil {

	public static void CZRetry() {
		List<OufeiCZ> list = OufeiCZDAO.getInstance().getAllFalseOrderIds();
		for (int i = 0; i < list.size(); i++) {
			new OfPayUtil();
			OfPayUtil.huafeiCZRetry(list.get(i));
		}
	}
}
