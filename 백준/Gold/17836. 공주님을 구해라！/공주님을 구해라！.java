import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T;
    static int[][] castle;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        castle = new int[N][M];
        visited = new boolean[N][M][2]; // [i][j][0]: 그람 획득 X, [i][j][1]: 그람 획득 O
        Pos gram = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
                if (castle[i][j] == 2) {
                    gram = new Pos(i, j, 0,false);
                }
            }
        }

        int time = bfs(gram);
        System.out.println(time == -1 ? "Fail" : time);
    }

    public static int bfs(Pos gram) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(0, 0, 0, false));
        visited[0][0][0] = true;

        int time = -1;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            if (pos.x == N - 1 && pos.y == M - 1) {
                if (pos.time <= T) time = pos.time;
                break;
            }

            // 현재 위치에 그람이 있는 경우, 그람을 획득함
            if (pos.x == gram.x && pos.y == gram.y) {
                pos.getGram = true;
                visited[pos.x][pos.y][1] = true;
            }

            // T 시간을 넘으면 구하지 못함
            if (pos.time > T) break;

            // 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int nx = pos.x + move[i][0];
                int ny = pos.y + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (pos.getGram) { // 그람을 획득한 경우
                    // 벽이 있어도 상관없음
                    if (!visited[nx][ny][1]) {
                        queue.offer(new Pos(nx, ny, pos.time + 1, true));
                        visited[nx][ny][1] = true;
                    }

                } else { // 그람을 획득하지 못한 경우
                    // 벽이 있으면 지나가지 못함
                    if (!visited[nx][ny][0] && castle[nx][ny] != 1) {
                        queue.offer(new Pos(nx, ny, pos.time + 1, false));
                        visited[nx][ny][0] = true;
                    }
                }
            }
        }
        return time;
    }

    static class Pos {
        int x, y;
        int time;
        boolean getGram;

        public Pos(int x, int y, int time, boolean getGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.getGram = getGram;
        }
    }
}