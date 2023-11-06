import java.util.*;

public class Main {

    public static final int INF = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[INF];
        int[] before = new int[INF];
        dp[1] = 0; dp[2] = 1; dp[3] = 1;
        before[1] = 1; before[2] = 1; before[3] = 1;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;
            if (i % 3 == 0) {
                if (dp[i] > (dp[i / 3] + 1)) {
                    dp[i] = dp[i / 3] + 1;
                    before[i] = i / 3;
                }
            }
            if (i % 2 == 0) {
                if (dp[i] > (dp[i / 2] + 1)) {
                    dp[i] = dp[i / 2] + 1;
                    before[i] = i / 2;
                }
            }
        }
        System.out.println(dp[n]);
        StringBuilder sb = new StringBuilder(n + " ");
        int beforeN = n;
        for (int i = 0; i < dp[n]; i++) {
            beforeN = before[beforeN];
            sb.append(beforeN + " ");
        }
        System.out.println(sb);
    }
}