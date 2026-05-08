package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import bean.TestListSubject;

public class TestDao extends Dao{

    // 仮データ（本来はDB）
    private List<Test> allTests = new ArrayList<>();

    public Test get(Student student, Subject subject, School school, int no) {
        return allTests.stream()
                .filter(t -> t.getStudent().equals(student))
                .filter(t -> t.getSubject().equals(subject))
                .filter(t -> t.getSchool().equals(school))
                .filter(t -> t.getNo() == no)
                .findFirst()
                .orElse(null);
    }

    public List<Test> postFilter(Test resultTest, School school) {
        return allTests.stream()
                .filter(t -> t.equals(resultTest))
                .filter(t -> school == null || t.getSchool().equals(school))
                .collect(Collectors.toList());
    }

    public List<Test> filter(int year, String classNum, Subject subject, int num, School school) {

        return allTests.stream()

                // 学年
                .filter(t -> year == 0 || 
                        (t.getStudent() != null && t.getStudent().getEntYear() == year))

                // クラス番号
                .filter(t -> classNum == null || classNum.isEmpty() || 
                        classNum.equals(t.getClassNum()))

                // 科目
                .filter(t -> subject == null || subject.equals(t.getSubject()))

                // 回数
                .filter(t -> num == 0 || t.getNo() == num)

                // 学校
                .filter(t -> school == null || school.equals(t.getSchool()))

                .collect(Collectors.toList());
    }

    public boolean save(List<Test> list) {
        return allTests.addAll(list);
    }

    public boolean save(Test test, Connection connection) {
        // connectionは未使用（簡易版）
        return allTests.add(test);
    }

	public Test getTest(String studentNo, String subjectCd, String schoolCd, int no) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void saveAll(List<Test> list) throws Exception {
		Connection connection = getConnection();
		PreparedStatement checkSt = null;
		PreparedStatement updateSt = null;
		PreparedStatement insertSt = null;

		try {
			connection.setAutoCommit(false);

			checkSt  = connection.prepareStatement(
				"SELECT COUNT(*) FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?");
			updateSt = connection.prepareStatement(
				"UPDATE test SET point=? WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?");
			insertSt = connection.prepareStatement(
				"INSERT INTO test (student_no, subject_cd, school_cd, no, point) VALUES (?,?,?,?,?)");

			for (Test test : list) {
				String studentNo = test.getStudent().getNo();
				String subjectCd = test.getSubject().getCd();
				String schoolCd  = test.getSchool().getCd();
				int no    = test.getNo();
				int point = test.getPoint();

				checkSt.setString(1, studentNo);
				checkSt.setString(2, subjectCd);
				checkSt.setString(3, schoolCd);
				checkSt.setInt(4, no);
				ResultSet rs = checkSt.executeQuery();
				rs.next();
				boolean exists = rs.getInt(1) > 0;
				rs.close();

				if (exists) {
					updateSt.setInt(1, point);
					updateSt.setString(2, studentNo);
					updateSt.setString(3, subjectCd);
					updateSt.setString(4, schoolCd);
					updateSt.setInt(5, no);
					updateSt.executeUpdate();
				} else {
					insertSt.setString(1, studentNo);
					insertSt.setString(2, subjectCd);
					insertSt.setString(3, schoolCd);
					insertSt.setInt(4, no);
					insertSt.setInt(5, point);
					insertSt.executeUpdate();
				}
			}

			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			throw e;
		} finally {
			if (checkSt  != null) checkSt.close();
			if (updateSt != null) updateSt.close();
			if (insertSt != null) insertSt.close();
			connection.close();
		}
	}
	
	// Inside TestDao.java
	public List<TestListSubject> filterTestListSubject(School school, int entYear, String classNum, String subjectCd) throws Exception {
	    List<TestListSubject> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    // This SQL retrieves students and their scores for the specific subject
	    String sql = "SELECT s.ent_year, s.no AS student_no, s.name AS student_name, s.class_num, t.no AS test_no, t.point " +
	                 "FROM student s " +
	                 "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? " +
	                 "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? " +
	                 "ORDER BY s.no ASC";

	    try {
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, subjectCd);
	        statement.setString(2, school.getCd());
	        statement.setInt(3, entYear);
	        statement.setString(4, classNum);
	        rSet = statement.executeQuery();

	        Map<String, TestListSubject> map = new HashMap<>();

	        while (rSet.next()) {
	            String sNo = rSet.getString("student_no");
	            TestListSubject ts = map.get(sNo);

	            if (ts == null) {
	                ts = new TestListSubject();
	                ts.setEntYear(rSet.getInt("ent_year"));
	                ts.setStudentNo(sNo);
	                ts.setStudentName(rSet.getString("student_name"));
	                ts.setClassNum(rSet.getString("class_num"));
	                map.put(sNo, ts);
	                list.add(ts);
	            }

	            // Map the test number (1, 2, etc.) to the point value
	            int testNo = rSet.getInt("test_no");
	            if (!rSet.wasNull()) {
	                ts.putPoint(testNo, rSet.getInt("point"));
	            }
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
	    return list;
	}
}