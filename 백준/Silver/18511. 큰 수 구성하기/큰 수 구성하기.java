import java.util.*;
import java.io.*;

public class Main {
    static int n, k, len, max = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        len = String.valueOf(n).length();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        System.out.println(max);
    }

    public static void dfs(int depth, int total) {
        if (total <= n) max = Math.max(max, total);
        if (depth == len) return;

        for (int i = 0; i < k; i++) {
            dfs(depth + 1, total * 10 + arr[i]);
        }
    }
}