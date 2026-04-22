package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // フォームから変更後の値を取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        // 在学チェックボックス（チェックがない場合はnullになるため判定が必要）
        boolean isAttend = request.getParameter("is_attend") != null;

        // 更新用の学生インスタンスを作成
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no); // 元のデータを一度取得
        
        if (student != null) {
            student.setName(name);
            student.setClassNum(classNum);
            student.setAttend(isAttend);
            
            // DB保存（saveメソッドは内部でUPDATE文を実行するように作ります）
            sDao.save(student);
        }

        // 完了画面へフォワード
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}