package Middle_exam;

import java.util.Scanner;

public class change_array {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String original = input.nextLine();
            String[] array = original.split(" ");
            for (String arrays : array) {
                System.out.println(arrays);
            }

            //最大元素与第一个交换，最小元素与最后一个交换
            int max = 0, min = 0;
            for (int i = 0; i < array.length; i++) {
                if (Integer.parseInt(array[i]) > Integer.parseInt(array[max])) {
                    max = i;
                }
                if (Integer.parseInt(array[i]) < Integer.parseInt(array[min])) {
                    min = i;
                }
            }
            String temp = array[0];
            array[0] = array[max];
            array[max] = temp;

            temp = array[array.length-1];
            array[array.length-1] = array[min];
            array[min] = temp;


            for (String arrays : array) {
                System.out.println(arrays);
            }

        }
    }
}
