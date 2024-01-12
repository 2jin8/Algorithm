import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 최단 경로 - BFS
        // 최대 K개 벽 부술 수 있다 -> 상태 변경! 차원 추가
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == N - 1 && point.y == M - 1) {
                return point.move + 1;
            }

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (map[tx][ty] == 1) { // 다음이 벽인 경우
                    if (point.crash < K) { // 벽을 부술 기회가 남은 경우
                        if (!visited[tx][ty][point.crash + 1]) {
                            queue.offer(new Point(tx, ty, point.crash + 1, point.move + 1));
                            visited[tx][ty][point.crash + 1] = true;
                        }
                    }
                } else { // 다음이 벽이 아닌 경우(= 이동 가능)
                    if (!visited[tx][ty][point.crash]) {
                        queue.offer(new Point(tx, ty, point.crash, point.move + 1));
                        visited[tx][ty][point.crash] = true;
                    }
                }
            }
        }
        return -1;
    }


    private static class Point {
        int x;
        int y;
        int crash; // 부순 벽의 개수
        int move; // 이동 횟수

        public Point(int x, int y, int crash, int move) {
            this.x = x;
            this.y = y;
            this.crash = crash;
            this.move = move;
        }
    }
}