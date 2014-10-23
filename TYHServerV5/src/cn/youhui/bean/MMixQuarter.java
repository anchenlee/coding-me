package cn.youhui.bean;

public class MMixQuarter {

	private MMixEighth oneeighth;
	
	private MMixEighth anothereighth;
	
	private String sign;

	private MMixBean item;

	public MMixBean getItem() {
		return item;
	}

	public void setItem(MMixBean item) {
		this.item = item;
	}
	
	public MMixEighth getOneeighth() {
		return oneeighth;
	}

	public void setOneeighth(MMixEighth oneeighth) {
		this.oneeighth = oneeighth;
	}

	public MMixEighth getAnothereighth() {
		return anothereighth;
	}

	public void setAnothereighth(MMixEighth anothereighth) {
		this.anothereighth = anothereighth;
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
			sb.append(item.toXML());
		}else{
			if(sign != null && sign.equals("left")){	//左右分
//				sb.append("<sign>").append("left").append("</sign>");
				sb.append("<left_half>");
				sb.append(oneeighth.toXML());
				sb.append("</left_half>");
				sb.append("<right_half>");
				sb.append(anothereighth.toXML());
				sb.append("</right_half>");
			}else if(sign != null && sign.equals("up")){	//上下分
//				sb.append("<sign>").append("up").append("</sign>");
				sb.append("<up_half>");
				sb.append(oneeighth.toXML());
				sb.append("</up_half>");
				sb.append("<down_half>");
				sb.append(anothereighth.toXML());
				sb.append("</down_half>");
			}
		}
		return sb.toString();
	}
}
