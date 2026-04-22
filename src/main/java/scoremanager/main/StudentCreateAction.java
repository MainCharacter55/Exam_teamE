package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 現在の年を取得して、入学年度の選択肢を作成
        LocalDate todaysData = LocalDate.now();
        int year = todaysData.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // ログイン講師の学校に所属するクラス一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> list = cNumDao.filter(teacher.getSchool());

        // リクエスト属性にセット
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", list);

        // JSPへフォワード
        request.getRequestDispatcher("student_create.jsp").forward(request, response);
    }
}