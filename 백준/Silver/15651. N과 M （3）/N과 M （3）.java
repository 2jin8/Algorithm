import java.util.*;

public class Main {

    static int n, m;
    static int[] result; // 결과 저장 배열
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1~n까지 m개 고르기
        n = sc.nextInt();
        m = sc.nextInt();

        result = new int[m];
        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int r : result) {
                sb.append(r).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            result[cnt] = i;
            dfs(cnt + 1);
        }
    }
}