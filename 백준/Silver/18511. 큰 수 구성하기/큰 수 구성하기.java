import java.io.*;
import java.util.*;

public class Main {
    static int n, k, ans;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int total) {
        if (total > n) return; // n보다 크면 종료
        if (ans < total) ans = total; // 더 큰 값으로 ans 갱신

        for (int i = k - 1; i >= 0; i--) {
            dfs(total * 10 + arr[i]);
        }
    }
}