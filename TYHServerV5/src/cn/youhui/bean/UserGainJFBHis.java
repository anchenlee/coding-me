package cn.youhui.bean;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 集分宝获取历史
 * @author lijun
 * @since 2014-4-10
 */
public class UserGainJFBHis {

	private String uid = "";
	
	/**
	 * 总共获得
	 */
	private double allGain = 0;
	
	/**
	 * 等级
	 */
	private int level = 0;

	/**
	 * 头像
	 */
	private String myImg = "";
	
	/**
	 * 进入随手优惠时间
	 */
	private long startTime = 0;
	
	/**
	 * 按年分的
	 */
	Map<String, YearHis> hisList = new LinkedHashMap<String, UserGainJFBHis.YearHis>();
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public double getAllGain() {
		return allGain;
	}

	public void setAllGain(double allGain) {
		this.allGain = allGain;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMyImg() {
		return myImg;
	}

	public void setMyImg(String myImg) {
		this.myImg = myImg;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public Map<String, YearHis> getHisList() {
		return hisList;
	}

	public void setHisList(Map<String, YearHis> hisList) {
		this.hisList = hisList;
	}

	public class YearHis{
		
		/**
		 * xxxx年
		 */
		private String year = "";
		
		/**
		 * 返利获得集分宝个数
		 */
		private int fanliJFB = 0;
		
		/**
		 * 签到获取集分宝个数
		 */
		private int signJFB = 0;
		
		/**
		 * 其他方式获取集分宝数
		 */
		private int otherJFB = 0;
		
		private Map<String, OnceHis> list = new LinkedHashMap<String, UserGainJFBHis.YearHis.OnceHis>();
		
		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public int getFanliJFB() {
			return fanliJFB;
		}

		public void setFanliJFB(int fanliJFB) {
			this.fanliJFB = fanliJFB;
		}

		public int getSignJFB() {
			return signJFB;
		}

		public void setSignJFB(int signJFB) {
			this.signJFB = signJFB;
		}

		public int getOtherJFB() {
			return otherJFB;
		}

		public void setOtherJFB(int otherJFB) {
			this.otherJFB = otherJFB;
		}
				
		public Map<String, OnceHis> getList() {
			return list;
		}

		public void setList(Map<String, OnceHis> list) {
			this.list = list;
		}



		public class OnceHis{
			
			/**
			 * 月份
			 */
			private String month = "";
			
			/**
			 * 用来标识，是购物，签到，分红，还是其他
			 */
			private String type = "";
			
			/**
			 * 是否第一次
			 */
			private boolean isFirst = false;
			
			/**
			 * 时间
			 */
			private String time = "";
			
			/**
			 * 集分宝个数
			 */
			private int jfbNum = 0;

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public boolean isFirst() {
				return isFirst;
			}

			public void setFirst(boolean isFirst) {
				this.isFirst = isFirst;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public int getJfbNum() {
				return jfbNum;
			}

			public void setJfbNum(int jfbNum) {
				this.jfbNum = jfbNum;
			}

			public String getMonth() {
				return month;
			}

			public void setMonth(String month) {
				this.month = month;
			}
			
		}
		
	}
}
