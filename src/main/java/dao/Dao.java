package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;

	public Connection getConnection() throws Exception {
		// データソースがnullの場合（初回実行時のみ）
		if (ds == null) {
			// InitialContextを初期化して、context.xmlの設定を見つける
			InitialContext ic = new InitialContext();
			// java:/comp/env/ の後ろに、context.xmlのResource nameを繋げる
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/exam");
		}
		// データベースへのコネクションを返却
		return ds.getConnection();
	}
}