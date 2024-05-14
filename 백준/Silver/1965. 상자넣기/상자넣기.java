import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] size = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 자기 자신 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (size[j] < size[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}