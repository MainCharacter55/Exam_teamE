package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNum = request.getParameter("class_num").trim();

        ClassNumDao dao = new ClassNumDao();
        ClassNum existing = dao.get(classNum, teacher.getSchool());

        if (existing == null) {
            ClassNum cn = new ClassNum();
            cn.setClass_num(classNum);
            cn.setSchool(teacher.getSchool());
            dao.save(cn);

            request.setAttribute("class_num", classNum);
            request.getRequestDispatcher("class_create_done.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "このクラスはすでに存在しています");
            request.setAttribute("class_num", classNum);
            request.getRequestDispatcher("class_create.jsp").forward(request, response);
        }
        return null;
    }
}
