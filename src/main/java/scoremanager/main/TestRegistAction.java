package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class TestRegistAction extends Action { 
	  public String execute (HttpServletRequest request , HttpServletResponse response)
	      throws Exception { 
		  HttpSession session =request.getSession ();
		  Teacher teacher =(Teacher) session.getAttribute("user");
		  
		  int year = LocalDate.now().getYear();
		  List<Integer> entYearList = new ArrayList<>();
		  for (int i = year - 10; i <= year; i++) { 
			  entYearList.add(i);
		  }
		  ClassNumDao classNumDao = new ClassNumDao();
		  List<String> classNumList = classNumDao.filter(teacher.getSchool());
		  
		  SubjectDao subjectDao = new SubjectDao () ;
		  List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
		  
		  
		  request.setAttribute("ent year set", entYearList);
		  request.setAttribute("class num set", classNumList);
		  request.setAttribute("subject set",subjectList);
		  
		  request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		  return null; 
	  }
}