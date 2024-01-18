import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] light, visited;
    static List<int[]>[][] switches;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        light = new boolean[N][N];
        visited = new boolean[N][N];
        switches = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            switches[x1][y1].add(new int[]{x2, y2});
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        light[0][0] = true;
        int total = 1;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            // 스위치 켜기
            if (!switches[x][y].isEmpty()) { // 켤 수 있는 스위치가 있다면
                visited = new boolean[N][N]; // 방문 초기화 (스위치가 켜짐으로 인해 방문할 수 있는 방이 늘어나기 때문에)
                visited[x][y] = true;
                for (int[] s : switches[x][y]) {
                    if (!light[s[0]][s[1]]) { // 불이 켜지지 않았다면
                        light[s[0]][s[1]] = true;
                        total++;
                    }
                }
                switches[x][y].clear(); // 현재 방에서 켤 수 있는 스위치 다 켰기 때문에 clear
            }

            // 이동하기
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= N) {
                    continue;
                }

                // 아직 방문한 적 없고 불이 켜져있다면 방문
                if (light[tx][ty] && !visited[tx][ty]) {
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
        return total;
    }
}