import java.util.*;

public class Main {
    static int n, m;
    static int[] result, nList; // 결과 저장 배열
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        nList = new int[n];
        for (int i = 0; i < n; i++) {
            nList[i] = sc.nextInt();
        }
        Arrays.sort(nList); // 증가하는 순서대로 출력

        result = new int[m];
        dfs(0, 0);
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

        for (int i = start; i < n; i++) {
            // 같은 수 여러 번 골라도 됨 & 앞의 수보다 같거나 커야 함
            result[cnt] = nList[i];
            dfs(i, cnt + 1);
        }
    }
}