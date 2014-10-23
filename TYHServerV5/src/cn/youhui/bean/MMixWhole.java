package cn.youhui.bean;

public class MMixWhole {

	private MMixWhole onehalf;
	
	private MMixWhole anotnerHalf;
	
	private String sign;

	private MMixBean item;
	
	private String proportion;
	
	private int width;
	
	private int height;
	
	private String title;
	
	private String bgColor;
	
	private String titleIconUrl;
	
	

	public String getTitleIconUrl() {
		return titleIconUrl;
	}

	public void setTitleIconUrl(String titleIconUrl) {
		this.titleIconUrl = titleIconUrl;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public MMixBean getItem() {
		return item;
	}

	public void setItem(MMixBean item) {
		this.item = item;
	}
	
	public MMixWhole getOnehalf() {
		return onehalf;
	}

	public void setOnehalf(MMixWhole onehalf) {
		this.onehalf = onehalf;
	}

	public MMixWhole getAnotnerHalf() {
		return anotnerHalf;
	}

	public void setAnotnerHalf(MMixWhole anotnerHalf) {
		this.anotnerHalf = anotnerHalf;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String toXML(){
		StringBuffer sb = new StringBuffer();
		if(item != null){
//			sb.append("<whole>");
			sb.append(item.toXML());
//			sb.append("</whole>");
		}else{
			if(sign != null && sign.equals("left")){	//左右分
//				sb.append("<sign>").append("left").append("</sign>");
				sb.append("<whole postion=\"left\" proportion=\""+onehalf.getProportion()+"\">");
				sb.append(onehalf.toXML());
				sb.append("</whole>");
				sb.append("<whole postion=\"right\" proportion=\""+anotnerHalf.getProportion()+"\">");
				sb.append(anotnerHalf.toXML());
				sb.append("</whole>");
			}else if(sign != null && sign.equals("up")){	//上下分
//				sb.append("<sign>").append("up").append("</sign>");
				sb.append("<whole postion=\"up\" proportion=\""+onehalf.getProportion()+"\">");
				sb.append(onehalf.toXML());
				sb.append("</whole>");
				sb.append("<whole postion=\"down\" proportion=\""+anotnerHalf.getProportion()+"\">");
				sb.append(anotnerHalf.toXML());
				sb.append("</whole>");
			}
		}
		return sb.toString();
	}
}
