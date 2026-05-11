package scoremanager.main;
 
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String studentNo = request.getParameter("student_no");
        String subjectCd  = request.getParameter("subject_cd");
        int no    = Integer.parseInt(request.getParameter("no"));
        int point = Integer.parseInt(request.getParameter("point"));

        // 学生・科目を取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        // テストデータをDBに保存（UPDATE or INSERT）
        Test test = new Test(student, student.getClassNum(), subject, school, no, point);
        List<Test> list = new ArrayList<>();
        list.add(test);
        TestDao testDao = new TestDao();
        testDao.saveAll(list);
 
        // 完了画面へ渡す情報
        request.setAttribute("student", student);
        request.setAttribute("subject", subject);
        request.setAttribute("no", no);
        request.setAttribute("point", point);
 
        request.getRequestDispatcher("test_update_done.jsp").forward(request, response);
        return null;
    }
}