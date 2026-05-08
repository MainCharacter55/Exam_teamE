package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.StudentScoreList;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentScoreListDao;
import dao.SubjectDao;
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

        // 学生番号で検索
        String studentNo = request.getParameter("student_no");
        if (studentNo != null && !studentNo.isEmpty()) {
            StudentScoreListDao dao = new StudentScoreListDao();
            List<StudentScoreList> scores = dao.findByStudentNo(studentNo);
            request.setAttribute("scores", scores);
            request.setAttribute("student_no", studentNo);
        }

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return null;
    }
}

