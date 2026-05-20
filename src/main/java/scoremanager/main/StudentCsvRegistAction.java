package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

public class StudentCsvRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        Part filePart = request.getPart("csv_file");

        int successCount = 0;
        List<String> errors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(filePart.getInputStream(), "UTF-8"))) {

            String line;
            int rowNum = 0;
            StudentDao studentDao = new StudentDao();

            while ((line = reader.readLine()) != null) {
                rowNum++;
                if (rowNum == 1) continue; // ヘッダー行をスキップ
                if (line.trim().isEmpty()) continue;

                String[] cols = line.split(",", -1);
                if (cols.length < 4) {
                    errors.add(rowNum + "行目: 列数が不足しています");
                    continue;
                }

                String no       = cols[0].trim();
                String name     = cols[1].trim();
                String entYearStr = cols[2].trim();
                String classNum = cols[3].trim();

                if (no.isEmpty() || name.isEmpty() || entYearStr.isEmpty() || classNum.isEmpty()) {
                    errors.add(rowNum + "行目: 空の項目があります");
                    continue;
                }

                int entYear;
                try {
                    entYear = Integer.parseInt(entYearStr);
                } catch (NumberFormatException e) {
                    errors.add(rowNum + "行目: 入学年度が無効です（" + entYearStr + "）");
                    continue;
                }

                Student student = new Student();
                student.setNo(no);
                student.setName(name);
                student.setEntYear(entYear);
                student.setClassNum(classNum);
                student.setAttend(true);
                student.setSchool(school);

                studentDao.save(student);
                successCount++;
            }
        }

        request.setAttribute("successCount", successCount);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("student_csv_regist_done.jsp").forward(request, response);
        return null;
    }
}
