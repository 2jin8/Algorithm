import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int[] nums = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, nums[i]);
        }

        long[] dp = new long[1000001];
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for (int i = 4; i <= max; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }
        for (int i = 0; i < t; i++) {
            bw.write((dp[nums[i]]) + "\n");
        }
        bw.flush(); bw.close();
    }
}