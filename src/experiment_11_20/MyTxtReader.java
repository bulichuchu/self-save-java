package experiment_11_20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MyTxtReader {
    public static class Student implements Comparable<Student> {
        public String id;
        public String name;
        public String help;

        public Student(String id,String name ) {
            if (id.contains("L")) {
                this.id = id.substring(3);
                this.help = id.substring(0, 3);
            }
            else {
                this.id = id.substring(2);
                this.help = id.substring(0, 2);
            }

            this.name = name;

        }

        public int compareTo(Student o) {
            return Integer.parseInt(this.id) - Integer.parseInt(o.id);
        }

        public String toString() {
            return help+id+ '\t'+name;
        }

    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("class_list.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                students.add(new Student(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(students);

        try (PrintWriter pw = new PrintWriter("sorted.txt")) {
            for (Student student : students) {
                pw.println(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String id = input.nextLine();

            if (id.contains("L"))
                id = id.substring(3);
            else
                id = id.substring(2);


            for (Student student : students) {
                if ((student.id).equals(id)) {
                    System.out.println(student);
                    break;
                }
            }
        }


    }
}

