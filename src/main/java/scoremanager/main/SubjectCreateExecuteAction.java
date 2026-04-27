package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. パラメータの取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 2. 重複チェック
        SubjectDao sDao = new SubjectDao();
        Subject existing = sDao.get(cd, teacher.getSchool());

        if (existing == null) {
            // 重複がない場合：登録
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(teacher.getSchool());

            sDao.save(subject);

            request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        } else {
            // 重複がある場合：エラーを表示して入力画面に戻る
            Map<String, String> errors = new HashMap<>();
            errors.put("cd", "科目コードが重複しています");
            request.setAttribute("errors", errors);
            
            // 入力内容を保持
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        }
        return null;
    }
}