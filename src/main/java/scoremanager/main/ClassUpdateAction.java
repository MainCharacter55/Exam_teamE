package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNum = request.getParameter("class_num").trim();
        String newClassNum = request.getParameter("new_class_num");

        // Initial display (GET)
        if (newClassNum == null) {
            request.setAttribute("class_num", classNum);
            request.getRequestDispatcher("class_update.jsp").forward(request, response);
            return null;
        }

        newClassNum = newClassNum.trim();

        // Validation
        if (newClassNum.isEmpty()) {
            request.setAttribute("class_num", classNum);
            request.setAttribute("error", "クラスを入力してください");
            request.getRequestDispatcher("class_update.jsp").forward(request, response);
            return null;
        }

        // Update
        ClassNum cn = new ClassNum();
        cn.setClass_num(newClassNum);
        cn.setSchool(teacher.getSchool());

        ClassNumDao dao = new ClassNumDao();
        dao.save(cn, classNum);

        request.setAttribute("old_class_num", classNum);
        request.setAttribute("class_num", newClassNum);
        request.getRequestDispatcher("class_update_done.jsp").forward(request, response);
        return null;
    }
}
