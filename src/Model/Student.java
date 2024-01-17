package Model;

public class Student implements Comparable<Student> {
    private int studentID;
    private String name;
    private int semester;
    private String courseName;
    private int totalCourse;

    public Student(int studentID, String name, int semester,
                   String courseName, int totalCourse) {
        this.studentID = studentID;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
        this.totalCourse = totalCourse;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        boolean cond = courseName.equals("JAVA") ||
                courseName.equals(".NET") ||
                courseName.equals("C/C++");
        if (cond) {
            this.courseName = courseName;
        } else {
            this.courseName = "sai roi ne!!!!";
        }
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }

    public int compareTo(Student o) {
        return name.compareToIgnoreCase(o.getName());
    }
}
