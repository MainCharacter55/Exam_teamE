package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから学生番号を取得
        String no = request.getParameter("no");

        // DBから学生情報を取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no);

        // クラス一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> class_list = cNumDao.filter(teacher.getSchool());

        // JSPに渡すデータをセット
        request.setAttribute("student", student);
        request.setAttribute("class_num_set", class_list);

        // 変更画面へフォワード
        request.getRequestDispatcher("student_update.jsp").forward(request, response);
    }
}