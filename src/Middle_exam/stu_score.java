package Middle_exam;

import java.util.ArrayList;
import java.util.Scanner;

public class stu_score {
    public static class Student implements Comparable<Student> {
        public String name, id, course, grade;
        public int count = 1;

        public Student(String[] info) {
            this.name = info[0];
            this.id = info[1];
            this.course = info[2];
            this.grade = info[3];
        }

        public boolean equals(Student student) {
            return this.name.equals(student.name) && this.id.equals(student.id);
        }

        public int compareTo(Student o) {//根据平均成绩降序排序，平均成绩相同的学号小的在前面
            if (Double.parseDouble(this.grade) == Double.parseDouble(o.grade)) {
                return Integer.parseInt(this.id) - Integer.parseInt(o.id);
            }
            return Double.parseDouble(o.grade) - Double.parseDouble(this.grade) > 0 ? 1 : - 1;
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (input.hasNext())
        {
            ArrayList<Student> students = new ArrayList<>();
            while (true) {
                String[] info = input.nextLine().split(",");
                if (info[0].equals("exit")) break;


                if (students.isEmpty()) {
                    students.add(new Student(info));
                } else {
                    Student temp = new Student(info);
                    boolean found = false;

                    for (Student student : students) {
                        if (temp.equals(student)) {
                            student.grade = String.valueOf((Double.parseDouble(temp.grade) + Double.parseDouble(student.grade)) / 2);
                            student.count++;
                            found = true;
                            break;
                        }
                    }

                    if (! found) {
                        students.add(temp);
                    }
                }

            }


            for (Student student : students) {
                student.grade = String.valueOf(Double.parseDouble(student.grade) / student.count);
            }
            
            students.sort(Student::compareTo);

            int i = 1;
            for (Student student : students) {
                System.out.println("No" + i + ":" + student.id + "," + student.name);
                i++;
            }
        }
    }

}
