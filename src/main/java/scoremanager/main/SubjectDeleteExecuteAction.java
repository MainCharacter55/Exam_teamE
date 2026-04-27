package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(school);

        SubjectDao dao = new SubjectDao();
        dao.delete(subject);

        response.sendRedirect("SubjectList.action");
        return null;
    }
}
