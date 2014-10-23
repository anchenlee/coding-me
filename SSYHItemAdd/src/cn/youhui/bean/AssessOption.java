package cn.youhui.bean;

/**
 * 反馈意见bean
 *
 */
public class AssessOption 
{
	//主键id
	private int id;
	
	//显示内容
	private String content;
	
	//是否显示
	private String isShow;
	
	//是否删除
	private String enable;
	
	//创建时间
	private long createTime;
	
	//更新时间
	private long updateTime;
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
	
}