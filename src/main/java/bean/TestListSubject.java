package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

    private int entYear;                 // 入学年度
    private String studentNo;            // 学籍番号
    private String studentName;          // 氏名
    private String classNum;             // クラス番号
    private Map<Integer, Integer> points; // key=回数, value=点数

    public TestListSubject() {
        points = new HashMap<>();
    }

    public int getEntYear() { 
    	return entYear; 
    }
    public void setEntYear(int entYear) { 
    	this.entYear = entYear; 
    }
    public String getStudentNo () { 
    	return studentNo; 
    }
    public void setStudentNo(String studentNo) { 
    	this.studentNo = studentNo; 
    }
    public String getStudentName() { 
    	return studentName; 
    }
    public void setStudentName(String studentName) { 
    	this.studentName= studentName; 
    }
    public String getClassNum () { 
    	return classNum ;
    }

    public void  setClassNum (String classNum) { 
    	this.classNum = classNum ;
    }
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // getPoint(key): String（UMLどおり）
    public String getPoint(int key) {
        Integer value = points.get(key);
        return value == null ? "" : value.toString();
    }

    // putPoint(key, value)
    public void putPoint(int key, int value) {
        points.put(key, value);
    }
}
