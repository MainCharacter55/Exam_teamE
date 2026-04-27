package scoremanager.main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

import bean.Test;
import dao.TestDAO;
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
            int no = 0;
            if (noParam != null && !noParam.isEmpty()) {
                try {
                    no = Integer.parseInt(noParam);
                } catch (NumberFormatException ignored) {
                }
            }
            String schoolCd = (String) request.getSession().getAttribute("schoolCd");

            TestDAO dao = new TestDAO(); // keep TestDAO unchanged

            // Narrow by test number using existing DAO.filter
            List<Test> candidates = dao.filter(0, null, null, no, null);

            Test found = null;
            if (candidates != null) {
                for (Test t : candidates) {
                    if (t == null) continue;

                    // Extract identifiers using reflection with fallback getter names
                    String tStudentNo = getStringProperty(t.getStudent(),
                            "getStudentNo", "getStudentId", "getId", "getNo", "getStudent_no");
                    String tSubjectCd = getStringProperty(t.getSubject(),
                            "getCode", "getSubjectCd", "getSubjectCode", "getId", "getCd");
                    String tSchoolCd = getStringProperty(t.getSchool(),
                            "getCode", "getSchoolCd", "getSchoolCode", "getId", "getCd");

                    boolean studentMatches = (studentNo == null) || studentNo.equals(tStudentNo);
                    boolean subjectMatches = (subjectCd == null) || subjectCd.equals(tSubjectCd);
                    boolean schoolMatches = (schoolCd == null) || schoolCd.equals(tSchoolCd);

                    if (studentMatches && subjectMatches && schoolMatches) {
                        found = t;
                        break;
                    }
                }
            }

            request.setAttribute("test", found);
            request.getRequestDispatcher("/scoremanager/main/test_delete.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Try multiple getter names on the given object and return the first non-null string value.
     * Returns null if none found.
     */
    private String getStringProperty(Object bean, String... getterNames) {
        if (bean == null) return null;
        for (String getter : getterNames) {
            try {
                Method m = bean.getClass().getMethod(getter);
                if (m != null) {
                    Object val = m.invoke(bean);
                    if (val != null) return val.toString();
                }
            } catch (NoSuchMethodException ignored) {
                // try next getter name
            } catch (Exception ignored) {
                // invocation error: ignore and try next
            }
        }
        return null;
    }
}
