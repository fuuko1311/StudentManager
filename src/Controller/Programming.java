package Controller;

import Model.Library;
import Model.Student;
import View.Menu;

import java.util.ArrayList;
import java.util.Collections;

public class Programming extends Menu {

    static String[] mchoice = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    Library week3;
    ArrayList<Student> list_s;
    ArrayList<Student> list_cs;
    Student s;

    public Programming() {
        super("WELCOME TO STUDENT MANAGEMENT", mchoice);
        week3 = new Library();
        list_s = new ArrayList<>();
        list_cs = new ArrayList<>();
        //s = new Student();
    }


    public void execute(int n) {
        switch (n) {
            case 1:
                create();
                break;
            case 2:
                findAndSort();
                break;
            case 3:
                updateDelete();
                break;
            case 4:
                report();
                break;
            case 5:
                System.out.println("Bye~~~~");
                System.exit(0);
        }
    }

    public void report() {
        // Add your report logic here
    }

    public void create() {
        int id = week3.getInt("Enter Student Id", 1, 1000);
        if (!checkID(list_s, id)) {
            String name = week3.getString("Enter Student name: ");
            int semester = week3.getInt("Enter semester", 1, 10);
            String courseName = week3.getString("Enter courseName: ");
            int totalCourse = week3.getInt("Enter total courses", 0, 100);

            list_s.add(new Student(id, name, semester, courseName, totalCourse));
        }
    }

    public void displayStudent(ArrayList<Student> list_s) {
        for (Student s : list_s) {
            System.out.println("Id: " + s.getStudentID() + " - Name: " + s.getName());
            for (Student cs : list_cs) {
                if (s.getStudentID() == cs.getStudentID()) {
                    System.out.println("Semester: " + cs.getSemester() + " - courseName: " + cs.getCourseName());
                }
            }
        }
    }

    public void findAndSort() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Student> list_ByName = listByName(list_s);
        if (list_ByName.isEmpty()) {
            System.err.println("Not exist");
        } else {
            Collections.sort(list_ByName);
            displayStudent(list_ByName);
        }
    }

    public ArrayList<Student> listByName(ArrayList<Student> list_s) {
        ArrayList<Student> list_Found = new ArrayList<>();
        String name = week3.getString("Enter name to search: ");
        for (Student s : list_s) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public void updateDelete() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        int id = week3.getInt("Enter id to search", 1, 1000);
        ArrayList<Student> list_ById = listById(list_s, id);
        if (list_ById.isEmpty()) {
            System.err.println("Not exist");
        } else {
            System.out.println("Do you want to update or delete?");
            System.out.println("1. Update");
            System.out.println("2. Delete");
            Student s = list_ById.get(0);
            int c = week3.getInt("Enter choice: ", 1, 2);
            switch (c) {
                case 1:
                    // Exec update
                    s.setStudentID(id);
                    s.setName(week3.getString("Enter name: "));
                    s.setSemester(week3.getInt("Enter Semester: ", 1, 10));
                    s.setCourseName(week3.getString("Enter Course name: "));
                    System.out.println("Update Success");
                    break;
                case 2:
                    list_s.remove(s);
                    System.out.println("Delete success");
                    break;
                default:
                    return;
            }
        }
    }
    public ArrayList<Student> listByIdCS(ArrayList<Student> list_s, int id) {
        ArrayList<Student> list_Found = new ArrayList<Student>();

        for (Student s : list_s) {
            if (s.getStudentID() == id) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public ArrayList<Student> listById(ArrayList<Student> list_s, int id) {
        ArrayList<Student> list_Found = new ArrayList<Student>();

        for (Student s : list_s) {
            if (s.getStudentID() == id) {
                list_Found.add(s);
            }
        }
        return list_Found;
    }

    public Student GetById(int id) {
        for (Student st : list_s) {
            if (st.getStudentID() == id) {
                return st;
            }
        }
        return null;
    }

    public boolean checkID(ArrayList<Student> list, int id) {
        if (list.isEmpty()) {
            return false;
        } else {

            for (Student s : list_s) {
                if (s.getStudentID() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}