package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistSearchAction extends Action {

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

        // 検索パラメータ取得
        int f1 = Integer.parseInt(request.getParameter("f1"));
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");
        int f4 = Integer.parseInt(request.getParameter("f4"));

        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);
        request.setAttribute("f4", f4);
        request.setAttribute("isSearchPerformed", true);

        Subject subject = subjectDao.get(f3, teacher.getSchool());
        if (subject != null) {
            request.setAttribute("subject_name", subject.getName());
        }

        TestDao testDao = new TestDao();
        List<TestListSubject> registList = testDao.filterTestListSubject(
                teacher.getSchool(), f1, f2, f3);

        request.setAttribute("regist_list", registList);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        return null;
    }
}
