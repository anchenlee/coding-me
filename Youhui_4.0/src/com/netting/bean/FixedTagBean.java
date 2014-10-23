package com.netting.bean;

public class FixedTagBean {

		private String id = "";
		
		private String img = "";
		
		private String title = "";
		
		private long createTime = 0;
		
		private long udpateTime = 0;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public long getCreateTime() {
			return createTime;
		}

		public long getUdpateTime() {
			return udpateTime;
		}

		public void setUdpateTime(long udpateTime) {
			this.udpateTime = udpateTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}
}
