import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = scan.nextInt();
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (ary[i] < ary[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int d : dp) {
            maxLen = Math.max(maxLen, d);
        }
        System.out.println(maxLen);
    }
}