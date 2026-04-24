package scoremanager.main;

import java.io.IOException;
import java.sql.Connection;

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
            int no = Integer.parseInt(request.getParameter("no"));
            String schoolCd = (String) request.getSession().getAttribute("schoolCd");

            TestDao dao = new TestDao(con);
            Test test = dao.getTest(studentNo, subjectCd, schoolCd, no);

            request.setAttribute("test", test);

            request.getRequestDispatcher("/scoremanager/main/test_delete.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
