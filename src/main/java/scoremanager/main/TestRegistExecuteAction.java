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

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String subjectCd = request.getParameter("f3");
        int no = Integer.parseInt(request.getParameter("f4"));

        String[] studentNos = request.getParameterValues("student_no");
        String[] pointStrs  = request.getParameterValues("point");

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        StudentDao studentDao = new StudentDao();
        TestDao testDao = new TestDao();

        List<Test> testList = new ArrayList<>();
        for (int i = 0; i < studentNos.length; i++) {
            String pointStr = pointStrs[i];
            if (pointStr == null || pointStr.isEmpty()) continue;

            Student student = studentDao.get(studentNos[i]);
            int point = Integer.parseInt(pointStr);
            testList.add(new Test(student, student.getClassNum(), subject, school, no, point));
        }

        testDao.saveAll(testList);

        request.setAttribute("subject", subject);
        request.setAttribute("no", no);
        request.setAttribute("count", testList.size());

        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
        return null;
    }
}
