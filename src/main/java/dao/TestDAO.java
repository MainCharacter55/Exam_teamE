package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO {

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
}