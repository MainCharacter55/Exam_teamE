package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.StudentScoreList;

public class StudentScoreListDAO {

    public List<StudentScoreList> findByStudentNo(String studentNo) throws Exception {

        List<StudentScoreList> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = """
            SELECT 
                s.NO AS STUDENT_NO,
                s.NAME AS STUDENT_NAME,
                sub.NAME AS SUBJECT_NAME,
                t.NO AS TEST_NO,
                t.POINT
            FROM STUDENT s
            JOIN TEST t ON s.NO = t.STUDENT_NO
            JOIN SUBJECT sub 
                ON t.SUBJECT_CD = sub.CD 
                AND t.SCHOOL_CD = sub.SCHOOL_CD
            WHERE s.NO = ?
            ORDER BY sub.NAME, t.NO
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, studentNo);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            StudentScoreList bean = new StudentScoreList();

            bean.setStudentNo(rs.getString("STUDENT_NO"));
            bean.setStudentName(rs.getString("STUDENT_NAME"));
            bean.setSubjectName(rs.getString("SUBJECT_NAME"));
            bean.setTestNo(rs.getInt("TEST_NO"));
            bean.setPoint(rs.getInt("POINT"));

            list.add(bean);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    // DB接続（あなたの環境に合わせて変更）
    private Connection getConnection() throws Exception {
        // 例：H2
        Class.forName("org.h2.Driver");
        return java.sql.DriverManager.getConnection(
            "jdbc:h2:~/exam", "sa", ""
        );
    }
}