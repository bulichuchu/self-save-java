package Middle_exam;

import java.util.Arrays;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();

            // 创建二维字符串数组存储乘法口诀表
            String[][] multiplicationTable = new String[n][];


            // 填充二维数组，并格式化输出
            for (int i = 0; i < n; i++) {
                multiplicationTable[i] = new String[i + 1];
                for (int j = 0; j <= i; j++) {
                    int result = (i + 1) * (j + 1);
                    multiplicationTable[i][j] = String.format("%d*%d=%d", i + 1, j + 1, result);
                }
            }

            for (String[] row : multiplicationTable) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == null) {
                        row[i] = "";
                    }
                }
            }

            // 使用循环打印二维数组内容


            for (int i = 0; i < n; ++i) {
                for (int j = 0; j <= i-1; ++j) {
                    System.out.print(multiplicationTable[i][j]);
                    if (Integer.parseInt(multiplicationTable[i][j].split("=")[1]) >= 10)
                        System.out.print(" ");
                    else
                        System.out.print("  ");
                }
                System.out.print(multiplicationTable[i][i]);
                System.out.println();
            }

            // 使用Arrays.deepToString()打印二维数组内容


            System.out.println(Arrays.deepToString(multiplicationTable));
        }
    }
}

