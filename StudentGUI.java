import javax.swing.*;
import java.awt.*;

public class StudentGUI extends JFrame {

    private StudentService service;

    public StudentGUI() {
        service = new StudentService();

        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menu();
        setVisible(true);
    }

    private void menu() {
        JPanel panel = new JPanel(new GridLayout(5,1,10,10));

        JButton add = new JButton("Add Student");
        JButton view = new JButton("View Students");
        JButton update = new JButton("Update Student");
        JButton delete = new JButton("Delete Student");
        JButton exit = new JButton("Exit");

        panel.add(add);
        panel.add(view);
        panel.add(update);
        panel.add(delete);
        panel.add(exit);

        setContentPane(panel);
        revalidate();

        add.addActionListener(e -> addStudent());
        view.addActionListener(e -> viewStudents());
        update.addActionListener(e -> updateStudent());
        delete.addActionListener(e -> deleteStudent());
        exit.addActionListener(e -> System.exit(0));
    }

    private void addStudent() {
        try {
            String name = JOptionPane.showInputDialog("Enter Name:");
            int roll = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll:"));
            double marks = Double.parseDouble(JOptionPane.showInputDialog("Enter Marks:"));

            service.addStudent(name, roll, marks);
            JOptionPane.showMessageDialog(this, "Student added!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void viewStudents() {
        String data = "";
        for (Student s : service.getAllStudents()) {
            data += s + "\n";
        }
        JOptionPane.showMessageDialog(this, data.isEmpty() ? "No records" : data);
    }

    private void updateStudent() {
        try {
            int roll = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll:"));
            Student s = service.findStudent(roll);

            if (s != null) {
                String name = JOptionPane.showInputDialog("Enter New Name:");
                double marks = Double.parseDouble(JOptionPane.showInputDialog("Enter New Marks:"));

                s.update(name, marks);
                service.save();

                JOptionPane.showMessageDialog(this, "Updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Not found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }

    private void deleteStudent() {
        try {
            int roll = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll:"));
            service.deleteStudent(roll);
            JOptionPane.showMessageDialog(this, "Deleted!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}