package com.netting.bean;

import com.netting.util.CodeUtil;

public class JobDetialBean
{
	// 任务ID
	public String id;
	
	// 任务名称
	public String jobName;
	
	// 创建任务的用户名
	public String userName;
	
	// 成功数
	public int successNum = 0;
	
	// 总数
	public int allNum = 0;
	
	// 创建时间
	public String create_time;
	
	// 备注
	public String remark;

	// 状态
	public int status;

	public JobDetialBean() {
		super();
	}

	public JobDetialBean(String id, String jobName, String userName, String create_time)
	{
		this.id = id;
		this.jobName = jobName;
		this.userName = userName;
		this.create_time = create_time;
	}
	
	public JobDetialBean(String id, String jobName, String userName, int successNum, int allNum)
	{
		this.id = id;
		this.jobName = jobName;
		this.userName = userName;
		this.setSuccessNum(1);
		this.setAllNum(1);
		this.create_time = CodeUtil.getDateTimeStr();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
