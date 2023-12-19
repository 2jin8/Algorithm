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
        dfs(1, 0);
        System.out.println(sb);
    }

    public static void dfs(int start, int cnt) {
        if (cnt == m) {
            for (int r : result) {
                sb.append(r).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) { // 앞의 수와 같거나 커도 됨
            // 중복도 허용함
            result[cnt] = i;
            dfs(i, cnt + 1);
        }
    }
}