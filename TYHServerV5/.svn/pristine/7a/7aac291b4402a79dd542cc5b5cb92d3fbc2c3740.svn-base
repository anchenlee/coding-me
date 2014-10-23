package cn.youhui.jfbhis;

/**
 * 形成集分宝历史记录的json字符串
 * @author hufan
 * @since 2014-9-19
 */
public class JFBHisJson {
	
	public static String  toJson(JFBHis jhis){
		
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("{\"allsavenum\":").append(jhis.getAllSaveNum()).append(",")
		      .append("\"nickname\":\"").append(jhis.getNickName()).append("\",")
		      .append("\"trade_award_num\":").append(jhis.getTradeAwardNum()).append(",")
		      .append("\"fenhongnum\":").append(jhis.getFenhongNum()).append(",")
		      .append("\"img\":\"").append(jhis.getImg()).append("\",")
		      .append("\"level\":\"").append(jhis.getLevel()).append("\",")
		      .append("\"fenhong_ratio\":\"").append(jhis.getFenhongRatio()).append("\",")
		      .append("\"send_ratio\":\"").append(jhis.getSendRatio()).append("\",")
		      .append("\"mingxi\":[");
		
		int temp=0;
		int sum=0;
		int odevity=0;
		String lastType=JFBConfig.TYPE_DAY;
		for(int i=0;i<jhis.getMingxi().size();i++){
			if(temp%2==0){
				if(odevity%2==0){
					odevity++;
					buffer.append("{\"odevity\":\"").append("ji").append("\",");
				}else{
					odevity++;
					buffer.append("{\"odevity\":\"").append("ou").append("\",");
				}
				buffer.append("\"mx\":{");
			}
			if(sum==0){
				buffer.append("\"mingxi1\":");
			}else{
				buffer.append("\"mingxi2\":");
			}
			if(jhis.getMingxi().get(i).getType().equals("1")){
				temp++;
				sum++;
				DayMingxi dmx=jhis.getMingxi().get(i).getDayMingxi();
				buffer.append("{\"date\":\"").append(dmx.getDate()).append("\",\"describe\":\"").append(dmx.getDescribe()).append("\",\"id\":\"").append(dmx.getId()).append("\",\"img\":\"").append(dmx.getImg()).append("\",\"type\":\"").append(JFBConfig.TYPE_DAY).append("\"}");
				lastType=JFBConfig.TYPE_DAY;
			}else if(jhis.getMingxi().get(i).getType().equals("2")){
					temp++;
					sum++;
					if(!JFBConfig.TYPE_DAY.equals(lastType)){
						i=i-1;
						lastType=JFBConfig.TYPE_DAY;
						buffer.append("{\"type\":\"").append(JFBConfig.CON).append("\"}");
					}else{
						TongjiMingxi tmx=jhis.getMingxi().get(i).getTongjiMingxi();
						if(tmx.getTotalNum()>0){
							lastType=JFBConfig.TYPE_TONGJI;
							TongjiBean tjb=tmx.getDescribe();
							buffer.append("{\"describe\":[{\"sign\":\"").append(tjb.getSign()).append("\",\"sign_num\":").append(tjb.getSignNum())
			                .append(",\"fenhong\":\"").append(tjb.getFenhong()).append("\",\"fenhong_num\":").append(tjb.getFenhongNum())
			                .append(",\"trade\":\"").append(tjb.getTrade()).append("\",\"trade_num\":").append(tjb.getTradeNum()).append("}],");
			                buffer.append("\"id\":\"").append(tmx.getId()).append("\",\"total_num\":").append(tmx.getTotalNum()).append(",\"date\":\"").append(tmx.getDate()).append("\",\"img\":\"").append(tmx.getImg()).append("\",\"type\":\"").append(JFBConfig.TYPE_TONGJI).append("\"}");
						}else{
							lastType=JFBConfig.TYPE_NULL;
							 buffer.append("{\"id\":\"").append(tmx.getId()).append("\",\"tishi\":\"").append(tmx.getTishi()).append("\",\"img\":\"").append(tmx.getImg()).append("\",\"type\":\"").append(JFBConfig.TYPE_NULL).append("\"}");
						}
					}
				}
			if(sum==1){
				if(jhis.getMingxi().size()-1!=i){
					buffer.append(",");
				}else{
					buffer.append(",\"mingxi2\":{\"type\":\"").append(JFBConfig.CON).append("\"}");
				}
			}
			if(sum==2||jhis.getMingxi().size()-1==i){
				buffer.append("}}");
				sum=0;
				lastType=JFBConfig.TYPE_DAY;
				if(jhis.getMingxi().size()>1&&i<jhis.getMingxi().size()-1){
					buffer.append(",");
				}
			}
		}
		buffer.append("]}");
		System.out.println("buffer:"+buffer.toString());
		return buffer.toString();
	}
}
