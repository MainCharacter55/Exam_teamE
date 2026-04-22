package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        // DAOの初期化
        StudentDao sDao = new StudentDao();
        // 学生番号の重複チェック
        Student existingStudent = sDao.get(no);

        if (existingStudent == null) {
            // 重複がない場合、登録処理
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            student.setAttend(true); // 初期状態は在学中
            student.setSchool(teacher.getSchool());

            sDao.save(student); // 保存実行

            // 完了画面へフォワード
            request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
        } else {
            // 重複がある場合、エラーメッセージをセットして入力画面に戻る
            Map<String, String> errors = new HashMap<>();
            errors.put("no", "学生番号が重複しています");
            request.setAttribute("errors", errors);
            
            // 入力内容を保持させる
            request.setAttribute("ent_year", entYear);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            
            // StudentCreateActionの処理を再度呼び出すか、直接JSPへ
            new StudentCreateAction().execute(request, response);
        }
    }
}