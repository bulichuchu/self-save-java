package Middle_exam;

import java.util.Scanner;

public class ID_analysis {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String ID = input.nextLine();
            System.out.println("year:"+ID.substring(0,4));
            System.out.println("department:"+ID.substring(4,6));
            System.out.println("class:"+ID.substring(6,8));
            System.out.println();
        }
    }
}
