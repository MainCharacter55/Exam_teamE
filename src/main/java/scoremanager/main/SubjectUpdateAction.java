package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();

        // ===== ① 初期表示（GET的な動き）=====
        if (name == null) {
            Subject subject = dao.get(cd, null);

            // JSPに渡す（設計書どおり）
            request.setAttribute("cd", subject.getCd());
            request.setAttribute("name", subject.getName());

            return "subject_update.jsp";
        }

        // ===== ② バリデーション =====
        if (name.isEmpty() || name.length() > 20) {

            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("error", "科目名は1～20文字で入力してください");

            return "subject_update.jsp";
        }

        // ===== ③ 更新処理 =====
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        dao.update(subject);

        // ===== ④ 完了画面 =====
        return "subject_update_done.jsp";
    }
}
