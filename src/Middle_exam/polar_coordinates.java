package Middle_exam;

import java.text.DecimalFormat;
import java.util.Scanner;

public class polar_coordinates {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String polar = input.nextLine();
            String[] polar_coordinates = polar.substring(2,polar.length()-2).split(", ");
            double r = Double.parseDouble(polar_coordinates[0]);
            double theta = Double.parseDouble(polar_coordinates[1]);
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);
            DecimalFormat decimalFormat = new DecimalFormat("0.#########");
            String x_str = decimalFormat.format(x+0.00000000005);
            String y_str = decimalFormat.format(y+0.00000000005);
            System.out.println("( "+x_str + ", " + y_str+" )");
        }
    }
}
