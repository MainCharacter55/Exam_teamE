package scoremanager.main;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import bean.Test;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/scoremanager/main/TestDelete")
public class TestDeleteAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = (Connection) request.getServletContext().getAttribute("con")) {

            String studentNo = request.getParameter("student_no");
            String subjectCd = request.getParameter("subject_cd");
            String noParam = request.getParameter("no");
            int no = (noParam != null && !noParam.isEmpty()) ? Integer.parseInt(noParam) : 0;
            String schoolCd = (String) request.getSession().getAttribute("schoolCd");

            TestDao dao = new TestDao();

            // まず test_no で絞る
            List<Test> candidates = dao.filter(0, null, null, no, null);

            Test found = null;
            for (Test t : candidates) {
                if (t == null) continue;

                if (t.getStudent() != null &&
                    t.getSubject() != null &&
                    t.getSchool() != null &&
                    studentNo.equals(t.getStudent().getNo()) &&
                    subjectCd.equals(t.getSubject().getCd()) &&
                    schoolCd.equals(t.getSchool().getCd())) {

                    found = t;
                    break;
                }
            }

            request.setAttribute("test", found);
            request.getRequestDispatcher("/scoremanager/main/test_delete.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
