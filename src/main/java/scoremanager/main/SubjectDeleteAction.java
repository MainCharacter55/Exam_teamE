package scoremanager.main;

import java.io.IOException;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/scoremanager/main/SubjectDelete")
public class SubjectDeleteAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // パラメータ取得
            String cd = request.getParameter("cd");

            // ログイン中の学校をセッションから取得
            School school = (School) request.getSession().getAttribute("school");

            // 科目取得
            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd, school);

            // JSP に渡す
            request.setAttribute("subject", subject);

            // 削除確認画面へ
            request.getRequestDispatcher("/WEB-INF/jsp/subject_delete.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
