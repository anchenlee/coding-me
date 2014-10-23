package cn.youhui.bean;

import java.util.ArrayList;
import java.util.List;

public class TeJiaCategoryBean {

	private String id = "";
	private String name = "";
	private String pic = "";
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		sb.append("<id>").append(id).append("</id>");
		 sb.append("<name><![CDATA[").append(name).append("]]></name>");
		 sb.append("<pic><![CDATA[").append(pic).append("]]></pic>");
		return sb.toString();
	}
	
	public String tejiaXML(){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	
	
	public static List<TeJiaCategoryBean> tejiaList(){
		List<TeJiaCategoryBean> list = new ArrayList<TeJiaCategoryBean>();
		TeJiaCategoryBean bean = new TeJiaCategoryBean();
		bean.setId("1");
		bean.setName("时尚女装");
		bean.setPic("http://img03.taobaocdn.com/imgextra/i3/11931031016249765/T1swrZFnlaXXXXXXXX_!!722601931-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("2");
		bean.setName("流行男装");
		bean.setPic("http://img02.taobaocdn.com/imgextra/i2/16249031443223350/T1KInWFhlaXXXXXXXX_!!921276249-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("3");
		bean.setName("男鞋女鞋");
		bean.setPic("http://img03.taobaocdn.com/imgextra/i3/11392031239621426/T1MvbAFi8XXXXXXXXX_!!180031392-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("4");
		bean.setName("包包配饰");
		bean.setPic("http://img02.taobaocdn.com/imgextra/i2/18386043531144112/T1tn7dFgxbXXXXXXXX_!!79098386-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("5");
		bean.setName("美容美发");
		bean.setPic("http://img01.taobaocdn.com/imgextra/i1/14268043706432252/T1B9P3FhFaXXXXXXXX_!!1680464268-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("6");
		bean.setName("数码家电");
		bean.setPic("http://img02.taobaocdn.com/imgextra/i2/18159031185241019/T1lVE5Fn4XXXXXXXXX_!!1627138159-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("7");
		bean.setName("家具生活");
		bean.setPic("http://img03.taobaocdn.com/imgextra/i3/15203043694680416/T1f_20Fc8aXXXXXXXX_!!77775203-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("8");
		bean.setName("美食特产");
		bean.setPic("http://img01.taobaocdn.com/imgextra/i1/14260031436970566/T1CmP5FfRaXXXXXXXX_!!683944260-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		
		bean = new TeJiaCategoryBean();
		bean.setId("9");
		bean.setName("母婴用品");
		bean.setPic("http://img02.taobaocdn.com/imgextra/i2/18255031013593524/T1vlnVFnRaXXXXXXXX_!!904198255-0-tejia.jpg_170x170.jpg_m.jpg");
		list.add(bean);
		return list;
	}
	
	
}
