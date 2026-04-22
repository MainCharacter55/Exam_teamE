package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;


public class ClassNumDao extends Dao {
	public ClassNum get(String class_num, School school) throws Exception {
	    // クラス番号インスタンスを初期化
	    ClassNum classNum = new ClassNum();
	    // データベースへのコネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");
	        // プリペアードステートメントに値をバインド
	        statement.setString(1, class_num);
	        statement.setString(2, school.getCd());
	        // プリペアードステートメントを実行
	        ResultSet rSet = statement.executeQuery();

	        // 学校Daoを初期化
	        SchoolDao sDao = new SchoolDao();
	        if (rSet.next()) {
	            // リザルトセットが存在する場合
	            // クラス番号インスタンスに検索結果をセット
	            classNum.setClass_num(rSet.getString("class_num"));
	            classNum.setSchool(sDao.get(rSet.getString("school_cd")));
	        } else {
	            // リザルトセットが存在しない場合
	            // クラス番号インスタンスにnullをセット
	            classNum = null;
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return classNum;
	}
	
	public List<String> filter(School school) throws Exception {
	    // リストを初期化
	    List<String> list = new ArrayList<>();
	    // データベースへのコネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection
	                .prepareStatement("select class_num from class_num where school_cd=? order by class_num");
	        // プリペアードステートメントに学校コードをバインド
	        statement.setString(1, school.getCd());
	        // プリペアードステートメントを実行
	        ResultSet rSet = statement.executeQuery();

	        // リザルトセットを全件走査
	        while (rSet.next()) {
	            // リストにクラス番号を追加
	            list.add(rSet.getString("class_num"));
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
	}
	
	/**
	 * saveメソッド：クラス番号インスタンスをデータベースに保存する（登録）
	 */
	public boolean save(ClassNum classNum) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			// 存在チェックを行い、存在しなければINSERT
			ClassNum check = get(classNum.getClass_num(), classNum.getSchool());
			if (check == null) {
				statement = connection.prepareStatement("insert into class_num (class_num, school_cd) values(?, ?)");
				statement.setString(1, classNum.getClass_num());
				statement.setString(2, classNum.getSchool().getCd());
				count = statement.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return count > 0;
	}

	/**
	 * saveメソッド：既存のクラス番号を新しいクラス番号で更新する
	 */
	public boolean save(ClassNum classNum, String oldClassNum) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			// クラス番号そのものが主キーの一部の場合、UPDATE文で更新
			statement = connection.prepareStatement("update class_num set class_num=? where class_num=? and school_cd=?");
			statement.setString(1, classNum.getClass_num());
			statement.setString(2, oldClassNum);
			statement.setString(3, classNum.getSchool().getCd());
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return count > 0;
	}
}