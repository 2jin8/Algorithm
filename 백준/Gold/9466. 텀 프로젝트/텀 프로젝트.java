import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N, team;
    static int[] arr;
    static boolean[] visited, done;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            done = new boolean[N + 1];
            String[] str = br.readLine().split(" ");
            team = 0;
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(str[i - 1]);
                if (i == arr[i]) { // 자신을 선택 → 혼자 팀 구성
                    done[i] = true;
                    team++;
                }
            }

            for (int i = 1; i <= N; i++) {
                if (!done[i]) {
                    dfs(i);
                }
            }
            sb.append(N - team).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int x) {
        if (done[x]) return; // 이미 검사가 끝난 원소 → 종료
        if (visited[x]) { // 이미 방문한 경우 → 사이클의 구성원에 포함
            team++;
            done[x] = true;
        }

        // 처음 방문한 경우
        visited[x] = true; // 방문 체크
        dfs(arr[x]);
        visited[x] = false; // 방문 체크 초기화

        done[x] = true; // 사이클이 아니더라도 검사가 끝났으니 true 체크
    }
}