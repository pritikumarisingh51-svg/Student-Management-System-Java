import java.util.*;
import java.io.*;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE = "students.txt";

    public StudentService() {
        load();
    }

    public void addStudent(String name, int roll, double marks) {
        if (findStudent(roll) != null) {
            System.out.println("Student already exists!");
            return;
        }

        students.add(new Student(name, roll, marks));
        save();
        System.out.println("Student added!");
    }

    public Student findStudent(int roll) {
        for (Student s : students) {
            if (s.getRoll() == roll) return s;
        }
        return null;
    }

    public void deleteStudent(int roll) {
        Student s = findStudent(roll);
        if (s != null) {
            students.remove(s);
            save();
        }
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void save() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE));
            for (Student s : students) {
                pw.println(s.getData());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving");
        }
    }

    public void load() {
        try {
            File file = new File(FILE);
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                students.add(new Student(
                        data[0],
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2])
                ));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading");
        }
    }
}
