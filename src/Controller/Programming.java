package Controller;

import Model.Library;
import Model.Student;
import View.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Programming extends Menu {

    static String[] mchoice = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    Library week3;
    ArrayList<Student> list_s;

    public Programming() {
        super("\nWELCOME TO STUDENT MANAGEMENT", mchoice);
        loadFromFile();
        week3 = new Library();
        list_s = new ArrayList<>();
    }
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stu.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int semester = Integer.parseInt(data[2]);
                int courseName = Integer.parseInt(data[3]);
                list_s.add(new Student(id, name, semester, courseName));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stu.csv"))) {
            for (Student student : list_s) {
                String line = String.format("%d,%s,%d,%d", student.getStudentID(), student.getName(),
                        student.getSemester(), student.getCourseName());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    private void deleteFile() {
        File file = new File("stu.csv");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.err.println("Failed to delete file");
            }
        } else {
            System.out.println("File does not exist");
        }
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
                saveToFile();
                System.out.println("Bye~~~~");
                System.exit(0);
        }
    }

    public boolean checkReport(ArrayList<Student> list_Rp, int id, int courseName, int total) {
        for (Student rp : list_Rp) {
            if (id == rp.getStudentID() && courseName == (rp.getCourseName()) && total == rp.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    public void report() {
        if (list_s == null) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Student> list_Rp = new ArrayList<>();
        for (Student cs : list_s) {
            int id = cs.getStudentID();
            String ten = cs.getName();
            int se = cs.getSemester();
            int course = cs.getCourseName();
            int total = 0;
            if (checkReport(list_Rp, id, course, total)) {
                list_Rp.add(new Student(id, ten, se, course));
            } else {
                for (Student r : list_Rp) {
                    if (cs.getStudentID() == r.getStudentID() && r.getCourseName()==(course)) {
                        total++;
                        r.setStudentID(id);
                        r.setCourseName(course);
                        r.setTotalCourse(total+1);
                    }

                }
            }
        }
        for (int i = 0; i < list_Rp.size(); i++) {
            System.out.println("Id:" + list_Rp.get(i).getStudentID() + "  - Course: " + list_Rp.get(i).getCourseName() + " - Total: " + list_Rp.get(i).getTotalCourse());
        }
    }

    public void create() {
        int id = week3.getInt("Enter Student Id", 1, 1000);


        if (!checkID(list_s, id)) {
            String name = week3.getString("Enter Student name: ");
            int semester = week3.getInt("Enter semester", 1, 10);
            int courseName = week3.getInt("""
                                            Enter course:
                                            1. JAVA
                                            2. .NET
                                            3. C/C++""", 1, 3);
            list_s.add(new Student(id, name, semester, courseName));
        }
    }


    public void displayStudent(ArrayList<Student> list_s) {
        for (Student s : list_s) {
            System.out.println("Id: " + s.getStudentID() + " - Name: " + s.getName());
            for (Student cs : list_s) {
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
        ArrayList<Student> list_Found = new ArrayList<Student>();
        String name = week3.getString("Enter name to search: ");
        for (Student s : list_s) {
            if (s.getName().contains(name)) {
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
                    //Exec update
                    s.setStudentID(id);
                    s.setName(week3.getString("Enter name: "));
                    s.setSemester(week3.getInt("Enter Semester: ", 1, 10));
                    s.setCourseName(week3.getInt("""
                                                    Enter course:
                                                    1. JAVA
                                                    2. .NET
                                                    3. C/C++""",1, 3));
                    System.out.println("Update Succcess");
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