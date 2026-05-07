package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. Get Parameters
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String numStr = request.getParameter("f4");

        int entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : 0;
        int num = (numStr != null && !numStr.isEmpty()) ? Integer.parseInt(numStr) : 0;

        // 2. Prepare Dropdowns (Always needed)
        int currentYear = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) entYearSet.add(i);

        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumSet = cNumDao.filter(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectSet = sDao.filter(teacher.getSchool());

        // 3. Search Logic
        List<TestListSubject> tests = null;
        boolean isSearchPerformed = false;
        Map<String, String> errors = new HashMap<>();

        if (entYearStr != null || classNum != null || subjectCd != null) {
            isSearchPerformed = true; // Triggered after clicking "表示"

            if (entYear == 0 || classNum == null || classNum.equals("0") || subjectCd == null || subjectCd.equals("0")) {
                errors.put("filter", "入学年度、クラス、科目を選択してください");
            } else {
            	TestDao tDao = new TestDao();
                tests = tDao.filterTestListSubject(teacher.getSchool(), entYear, classNum, subjectCd);
                
                Subject selectedSubject = sDao.get(subjectCd, teacher.getSchool());
                request.setAttribute("subject_name", selectedSubject.getName());
                // Ensure f4 is explicitly sent for the header display
                request.setAttribute("selected_num", num);
            }
        }

        // 4. Request Attributes
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", num);
        
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("subject_set", subjectSet);
        
        request.setAttribute("tests", tests);
        request.setAttribute("isSearchPerformed", isSearchPerformed);
        request.setAttribute("errors", errors);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
        return null;
    }
}