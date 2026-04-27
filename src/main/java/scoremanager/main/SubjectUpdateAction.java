package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        SubjectDao dao = new SubjectDao();

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 初期表示
        if (name == null) {
            Subject subject = dao.get(cd, teacher.getSchool());

            if (subject == null) {
                request.setAttribute("error", "科目情報が見つかりません。");
                request.getRequestDispatcher("subject_list.jsp").forward(request, response);
                return null;
            }

            request.setAttribute("cd", subject.getCd());
            request.setAttribute("name", subject.getName());

            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return null;
        }

        // 入力チェック
        if (name.isEmpty() || name.length() > 20) {
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("error", "科目名は1～20文字で入力してください");

            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return null;
        }

        // 更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        dao.update(subject);

        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        return null;
    }
}