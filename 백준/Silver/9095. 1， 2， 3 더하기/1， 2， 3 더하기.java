import java.util.*;
import java.io.*;

public class Main {

    private static int[] num = new int[11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] testNum = new int[T];

        for (int i = 0; i < T; i++) {
            testNum[i] = Integer.parseInt(br.readLine());
        }

        int[] sortNum = testNum.clone();
        Arrays.sort(sortNum);
        int maxNum = sortNum[T - 1];

        for (int i = 1; i <= maxNum; i++) {
            if (i == 1 || i == 2) {
                num[i] = i;
            } else if (i == 3) {
                num[i] = 4;
            } else {
                num[i] = num[i - 1] + num[i - 2] + num[i - 3];
            }
        }

        for (int i = 0; i < T; i++) {
            System.out.println(num[testNum[i]]);
        }
    }
}