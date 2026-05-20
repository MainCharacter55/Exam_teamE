package scoremanager.main;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNum = request.getParameter("class_num");

        ClassNumDao dao = new ClassNumDao();
        dao.delete(classNum, teacher.getSchool());

        request.setAttribute("class_num", classNum);
        request.getRequestDispatcher("class_delete_done.jsp").forward(request, response);
        return null;
    }
}
