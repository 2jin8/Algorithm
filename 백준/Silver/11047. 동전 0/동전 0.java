import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int coinCnt = 0, i = 0;
        while (k > 0) {
            coinCnt += k / coins[i];
            k %= coins[i++];
        }
        System.out.println(coinCnt);
    }
}