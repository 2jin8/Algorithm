import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Pos start;
    static ArrayList<Pos> endList = new ArrayList<>();
    static char[][] map;
    static int[][][] visited;
    static int[] keyType = {1, 2, 4, 8, 16, 32}; // a: 1, b:2, ..., f:32
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M][64]; // 비트마스킹으로 현재 가진 열쇠 표현

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') { // 현재 위치
                    start = new Pos(i, j);
                } else if (map[i][j] == '1') { // 출구
                    endList.add(new Pos(i, j));
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(start.x, start.y, 0));
        visited[start.x][start.y][0] = 1;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            int x = now.x, y = now.y, key = now.key;
            if (isEnd(x, y)) {
                return visited[x][y][key] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 벗어나면 X
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 벽이거나 이미 방문했으면 X
                if (map[nx][ny] == '#' || visited[nx][ny][key] != 0)
                    continue;

                // 열쇠 집고 이동
                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int newKey = key | keyType[map[nx][ny] - 'a'];
                    if (visited[nx][ny][newKey] == 0) {
                        visited[nx][ny][newKey] = visited[x][y][key] + 1;
                        queue.offer(new Pos(nx, ny, newKey));
                    }
                }
                // 문 -> 열쇠 확인
                else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if ((now.key & keyType[map[nx][ny] - 'A']) > 0) {
                        queue.offer(new Pos(nx, ny, key));
                        visited[nx][ny][key] = visited[x][y][key] + 1;
                    }
                }
                // 나머지 칸은 그냥 이동
                else {
                    queue.offer(new Pos(nx, ny, key));
                    visited[nx][ny][key] = visited[x][y][key] + 1;
                }
            }
        }
        return -1;
    }

    // 출구는 적어도 한 개 == 여러 개 존재
    static boolean isEnd(int x, int y) {
        for (Pos pos : endList) {
            if (pos.x == x && pos.y == y)
                return  true;
        }
        return false;
    }

    static class Pos {
        int x, y, key;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int key) {
            this(x, y);
            this.key = key;
        }
    }
}
