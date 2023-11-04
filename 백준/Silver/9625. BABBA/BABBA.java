import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i <= k; i++) {
            if (i == 0) {
                dp[0][0] = -1; dp[0][1] = 1;
            } else if (i == 1) {
                dp[1][0] = 1; dp[1][1] = 0;
            } else if (i == 2) {
                dp[2][0] = 0; dp[2][1] = 1;
            } else {
                dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
                dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
            }
        }

        int[] answer = new int[2];
        answer[0] = 1;
        for (int i = 0; i < k; i++) {
            answer[0] += dp[i][0];
            answer[1] += dp[i][1];
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}