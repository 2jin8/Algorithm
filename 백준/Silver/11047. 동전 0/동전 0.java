import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int idx = n - 1, cnt = 0;
        while (k > 0) {
            if (coins[idx] > k) {
                idx--;
                continue;
            }

            int div = k / coins[idx];
            k -= div * coins[idx];
            cnt += div;
        }
        System.out.println(cnt);
    }
}