package cn.youhui.utils;

public class FenHongUtil {
	
	public static final int LEVEL_NO_HEART = 0;           //空心
	public static final int LEVEL_HEART = 1;           //红心
	public static final int LEVEL_DIAMOND = 2;         //钻石
	public static final int LEVEL_CROWN = 3;           //皇冠
	public static final int LEVEL_GOLDENCROWN = 4;      //金冠
	public static final int LEVEL_SUPER = 5;            //至尊
	
	public static int getLevelImgByJFB(int jfbNum){
		if(jfbNum<1000){
			return LEVEL_NO_HEART;
		}else if(jfbNum<5000){
			return LEVEL_HEART;
		}else if(jfbNum<20000){
			return LEVEL_DIAMOND;
		}else if(jfbNum<100000){
			return LEVEL_CROWN;
		}else if(jfbNum<200000){
			return LEVEL_GOLDENCROWN;
		}else{
			return LEVEL_SUPER;
		}
	}
	
	/**
	 * 根据返利金额获取用户等级
	 * @param fanli
	 * @return
	 */
	public static int getLevel(double fanli){
		int level = 0;
		if(fanli >= 2000){
			level = LEVEL_SUPER;
		}else if(fanli >=1000){
			level = LEVEL_GOLDENCROWN;
		}else if(fanli >=200){
			level = LEVEL_CROWN;
		}else if(fanli >=50){
			level = LEVEL_DIAMOND;
		}else if(fanli >=10){
			level = LEVEL_HEART;
		}
		return level;
	}
	
	/**
	 * 根据分红比例得到等级
	 */
	public static int getLevelByRate(int rate){
		int level = LEVEL_NO_HEART;
		if(rate == 2){
			level = LEVEL_HEART;
		}else if(rate == 5){
			level = LEVEL_DIAMOND;
		}else if(rate == 8){
			level = LEVEL_CROWN;
		}else if(rate == 12){
			level = LEVEL_GOLDENCROWN;
		}else if(rate == 20){
			level = LEVEL_SUPER;
		}
		return level;
	}
	
	/**
	 * 根据等级获取分红比例
	 */
	public static int getRateByLevel(int level){
		int rate = 0;
		if(level == LEVEL_HEART){
			rate = 2;
		}else if(level == LEVEL_DIAMOND){
			rate = 5;
		}else if(level == LEVEL_CROWN){
			rate = 8;
		}else if(level == LEVEL_GOLDENCROWN){
			rate = 12;
		}else if(level == LEVEL_SUPER){
			rate = 20;
		}
		return rate;
	}
	
	/**
	 * 根据返利集分宝获得分红比例
	 * @return
	 */
	public static int getRateByFanli(int jfbNum){
		int rate = 0;
		if(jfbNum >= 5000){
			rate = 20;
		}else if(jfbNum >= 2000){
			rate = 12;
		}else if(jfbNum >= 1000){
			rate = 8;
		}
		return rate;
	}
	
	/**
	 * 获取下一个分红集分宝等级
	 * @param jfbNum
	 * @return
	 */
	public static int getNextFanli(int jfbNum){
		int rate = 0;
		if(jfbNum < 1000){
			rate = 1000;
		}else if(jfbNum < 2000){
			rate = 2000;
		}else{                
			rate = 5000;         //最高了  也返回5000
		} 
		return rate;
	}
	
	/**
	 * 根据等级分红比例获取等级
	 * @param levelRate
	 * @return
	 */
	public static int getLevelByLevelRate(double levelRate){
		int level = 0;
		if(levelRate <=2){
			level = 1;
		}else if(levelRate <=5){
			level = 2;
		}else if(levelRate <=8){
			level = 3;
		}else if(levelRate <=12){
			level = 4;
		}else if(levelRate <=20){
			level = 5;
		}
		return level;
		
	}
	
	/**
	 * 获取下个等级
	 * @param jfbNum
	 * @return
	 */
	public static int getNextLevel(int level){
		return level+1 > LEVEL_SUPER ? LEVEL_SUPER : level +1;
	}
}
