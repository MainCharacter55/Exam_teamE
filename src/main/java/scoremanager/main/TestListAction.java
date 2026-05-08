package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.StudentScoreList;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentScoreListDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ドロップダウン用データ準備
        int year = LocalDate.now().getYear();
        List<Integer> entYearList = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(teacher.getSchool());

        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        request.setAttribute("ent_year_set", entYearList);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("subject_set", subjectList);

        // 科目情報で検索
        String f3 = request.getParameter("f3");
        if (f3 != null && !f3.isEmpty() && !f3.equals("0")) {
            String f1Str = request.getParameter("f1");
            String f2    = request.getParameter("f2");
            int f1 = (f1Str != null && !f1Str.isEmpty()) ? Integer.parseInt(f1Str) : 0;

            Subject subject = subjectDao.get(f3, teacher.getSchool());

            TestListSubjectDao testListDao = new TestListSubjectDao();
            List<TestListSubject> tests = testListDao.filter(f1, f2, subject, teacher.getSchool());

            request.setAttribute("tests", tests);
            request.setAttribute("subject_name", subject != null ? subject.getName() : "");
            request.setAttribute("f1", f1);
            request.setAttribute("f2", f2);
            request.setAttribute("f3", f3);
            request.setAttribute("searchType", "subject");
            request.setAttribute("isSearchPerformed", true);

        // 学生情報で検索
        } else {
            String studentNo = request.getParameter("student_no");
            if (studentNo != null && !studentNo.isEmpty()) {
                StudentScoreListDao dao = new StudentScoreListDao();
                List<StudentScoreList> scores = dao.findByStudentNo(studentNo);
                request.setAttribute("scores", scores);
                request.setAttribute("student_no", studentNo);
                request.setAttribute("searchType", "student");
                request.setAttribute("isSearchPerformed", true);
            }
        }

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return null;
    }
}
