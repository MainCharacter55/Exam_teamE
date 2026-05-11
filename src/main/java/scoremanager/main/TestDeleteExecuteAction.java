package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String studentNo = request.getParameter("student_no").trim();
        String subjectCd = request.getParameter("subject_cd");
        int no = Integer.parseInt(request.getParameter("no"));

        TestDao testDao = new TestDao();
        Test test = testDao.getTest(studentNo, subjectCd, school.getCd(), no);
        testDao.delete(studentNo, subjectCd, school.getCd(), no);

        request.setAttribute("test", test);
        request.getRequestDispatcher("test_delete_done.jsp").forward(request, response);
        return null;
    }
}
