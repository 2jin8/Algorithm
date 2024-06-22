import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[] students;
    static boolean[] check, visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            students = new int[N + 1];
            check = new boolean[N + 1];
            visited = new boolean[N + 1];
            answer = N;

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                students[i] = Integer.parseInt(st.nextToken());
                if (students[i] == i) { // 자기 자신과 팀을 이루는 경우
                    check[i] = true;
                    answer--;
                }
            }

            for (int i = 1; i <= N; i++) {
                if (!check[i]) {
                    dfs(i);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int x) {
        if (check[x]) return; // 이미 사이클 여부를 확인한 곳은 탐색할 필요 X
        if (visited[x]) { // 이미 방문한 지점을 방문한 경우
            answer--;
            check[x] = true;
        }

        visited[x] = true;
        dfs(students[x]);
        visited[x] = false;

        check[x] = true; // 팀을 구성하지 않았어도, 사이클 여부를 확인했으므로 true로 변경
    }
}