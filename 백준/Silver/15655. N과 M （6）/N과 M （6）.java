import java.util.*;

public class Main {
    static int n, m;
    static int[] result, nList; // 결과 저장 배열
    static boolean[] used; // 사용여부 확인 여부
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
        used = new boolean[10001]; // n은 10,000보다 작거나 같은 자연수

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

        // 고른 수열은 오름차순
        for (int i = start; i < n; i++) {
            int num = nList[i];
            if (!used[num]) {
                result[cnt] = num;
                used[num] = true;
                dfs(i + 1, cnt + 1);
                used[num] = false;
            }
        }
    }
}