import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  0인 것들을 BFS 탐색 수행함
 *  그러다가 1을 만나면 그냥 카운팅만 하기
 *  BFS 탐색 끝나면 이제 0인 것들은 녹기 처리하기 & 그만큼 수 제거
 *  치즈 수가 0이 되면 탐색 종료
 */
public class Main {

    static int N, M, cheese;
    static int[][] map, check;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cheese += map[i][j];
            }
        }

        int time = 0;
        while (cheese != 0) {
            visited = new boolean[N][M];
            check = new int[N][M];
            bfs(0, 0); // 가장자리는 치즈가 놓이지 않음

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (check[i][j] >= 2) { // 공기랑 닿는 부분이 2개 이상이면 녹음
                        map[i][j] = 0;
                        cheese--;
                    }
                }
            }
            time++;
        }
        System.out.println(time);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 0) { // 치즈가 없으면 계속 BFS 탐색
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                } else { // 치즈가 있는 부분이면 기록만
                    check[nx][ny]++;
                }
            }
        }
    }
}
