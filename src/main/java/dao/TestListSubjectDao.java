package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // 基本SQL（UMLの baseSql）
    private static final String baseSql =
        "SELECT s.ent_year, s.no AS student_no, s.name AS student_name, " +
        "s.class_num, t.no AS test_no, t.point " +
        "FROM student s " +
        "LEFT JOIN test t ON s.no = t.student_no AND s.school_cd = t.school_cd " +
        "AND t.subject_cd = ? " +
        "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ? " +
        "ORDER BY s.no, t.no";

    /**
     * postFilter(ResultSet)
     * ResultSet → List<TestListSubject> に変換
     */
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        TestListSubject tls = null;
        String currentStudentNo = null;

        while (rSet.next()) {

            String studentNo = rSet.getString("student_no");

            // 学生が変わったら新しい TestListSubject を作成
            if (!studentNo.equals(currentStudentNo)) {
                tls = new TestListSubject();
                tls.setEntYear(rSet.getInt("ent_year"));
                tls.setStudentNo(studentNo);
                tls.setStudentName(rSet.getString("student_name"));
                tls.setClassNum(rSet.getString("class_num"));

                list.add(tls);
                currentStudentNo = studentNo;
            }

            // テスト番号と点数を Map に追加
            int testNo = rSet.getInt("test_no");
            int point = rSet.getInt("point");

            if (testNo != 0) { // null の場合 0 になるため
                tls.putPoint(testNo, point);
            }
        }

        return list;
    }

    /**
     * filter(entYear, classNum, subject, school)
     * 成績一覧を取得
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(baseSql);

            statement.setString(1, subject.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());

            ResultSet rSet = statement.executeQuery();

            list = postFilter(rSet);

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return list;
    }
}
