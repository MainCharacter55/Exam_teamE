package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDao dao = new ClassNumDao();
        List<String> classes = dao.filter(teacher.getSchool());

        request.setAttribute("classes", classes);
        request.getRequestDispatcher("class_list.jsp").forward(request, response);
        return null;
    }
}
