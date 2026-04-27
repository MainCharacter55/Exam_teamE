package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからログインユーザー情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // SubjectDaoを使用して、当該学校の全科目を取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // 取得したリストをリクエスト属性にセット
        request.setAttribute("subjects", subjects);

        // JSPへフォワード
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
		return null;
    }
}