import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr, totals;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        totals = arr.clone();
        for (int i = 1; i < n; i++) {
            totals[i] += totals[i - 1];
        }
        int ans = Math.max(fixBeeHoney(), Math.max(fixHoneyBee(), fixBees()));
        System.out.println(ans);
    }

    public static int fixBeeHoney() {
        // 꿀벌 2의 위치
        int max = 0, total = totals[n - 1];
        for (int i = 1; i < n - 1; i++) {
            int bee1 = total - arr[0] - arr[i];
            int bee2 = total - totals[i];
            max = Math.max(max, bee1 + bee2);
        }
        return max;
    }

    public static int fixHoneyBee() {
        // 꿀벌 2의 위치
        int max = 0, total = totals[n - 1];
        for (int i = 1; i < n - 1; i++) {
            int bee1 = total - arr[n - 1] - arr[i];
            int bee2 = totals[i - 1];
            max = Math.max(max, bee1 + bee2);
        }
        return max;
    }

    public static int fixBees() {
        // 벌통의 위치
        int max = 0;
        for (int i = 1; i < n - 1; i++) {
            int bee1 = totals[i] - arr[0];
            int bee2 = totals[n - 2] - totals[i - 1];
            max = Math.max(max, bee1 + bee2);
        }
        return max;
    }
}