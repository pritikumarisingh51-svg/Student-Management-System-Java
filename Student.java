import java.io.Serializable;

public class Student {
    private String name;
    private int roll;
    private double marks;

    public Student(String name, int roll, double marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    public int getRoll() {
        return roll;
    }

    public String getData() {
        return name + "," + roll + "," + marks;
    }

    public String toString() {
        return "Name: " + name + " | Roll: " + roll + " | Marks: " + marks;
    }

    public void update(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}
