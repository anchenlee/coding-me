package cn.youhui.common;

import java.sql.Connection;
import java.sql.SQLException;

import cn.youhui.dao.MySQLDBIns;

public class MySqlExecutor<T> {
	public MySqlExecutor(){}
	
	public T exe(MySqlRunner<T> mysqlQuery){
		T rst = null ;
		Connection  conn = null ;
		try{
			conn = MySQLDBIns.getInstance().getConnection() ;
			rst = mysqlQuery.run(conn);
		} catch(SQLException e){
			e.printStackTrace() ;
		}  catch(Exception e){
			e.printStackTrace() ;
		} finally {
			MySQLDBIns.getInstance().release(conn) ;
		}
		return rst ;
	}
}
