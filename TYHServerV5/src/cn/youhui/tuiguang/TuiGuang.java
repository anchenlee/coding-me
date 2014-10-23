/**
 * 
 */
package cn.youhui.tuiguang;

/**
 * 
 * @author lujiabin
 * @since 2014-9-10
 */
public interface TuiGuang {
	public boolean callback(String idfa,long activatetime,String adalias,String activateip,String code,String openudid);
}
