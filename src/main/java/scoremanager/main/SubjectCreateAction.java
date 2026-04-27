package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 科目登録はプルダウン等の準備が不要なので、そのままJSPへ
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        return null;
    }
}