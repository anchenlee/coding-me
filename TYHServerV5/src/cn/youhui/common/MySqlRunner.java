package cn.youhui.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface MySqlRunner<T> {
	T run(Connection conn) throws SQLException ;
}
