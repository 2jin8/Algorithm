import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] ary = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = scan.nextInt();
        }
        dp[0] = ary[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                dp[i] = ary[0] + ary[1];
            } else if (i == 2) {
                dp[i] = Math.max(ary[0] + ary[1], Math.max(ary[0], ary[1]) + ary[2]);
            } else {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + ary[i - 1] + ary[i], dp[i - 2] + ary[i]));
            }
        }
        System.out.println(dp[n - 1]);
    }
}