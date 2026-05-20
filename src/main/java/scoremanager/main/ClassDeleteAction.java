package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String classNum = request.getParameter("class_num");
        request.setAttribute("class_num", classNum);
        request.getRequestDispatcher("class_delete.jsp").forward(request, response);
        return null;
    }
}
