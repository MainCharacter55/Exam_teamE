package scoremanager.main;
 
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class TestUpdateAction extends Action {
 
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
 
        // パラメータ取得
        String studentNo = request.getParameter("student_no").trim();
        String subjectCd  = request.getParameter("subject_cd");
        int no = Integer.parseInt(request.getParameter("no"));
 
        // 既存テストデータを取得
        TestDao testDao = new TestDao();
        Test test = testDao.getTest(studentNo, subjectCd, teacher.getSchool().getCd(), no);
 
        // 科目リスト取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.filter(teacher.getSchool());
 
        request.setAttribute("test", test);
        request.setAttribute("subjects", subjects);
 
        request.getRequestDispatcher("test_update.jsp").forward(request, response);
        return null;
    }
}