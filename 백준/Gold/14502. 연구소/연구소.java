import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[][] map;
    static int[] wallPos = new int[3];
    static ArrayList<Pos> emptyList = new ArrayList<>();
    static ArrayList<Pos> virusList = new ArrayList<>();
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
                if (map[i][j] == 0) {
                    emptyList.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    virusList.add(new Pos(i, j));
                }
            }
        }
        setWall(0, 0);
        System.out.println(ans);
    }

    // 벽 세울 위치 3개 선택 => 조합
    static void setWall(int count, int start) {
        // 벽을 3개 세웠다면 안전 영역 구하기
        if (count == 3) {
            getSafeArea();
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            Pos pos = emptyList.get(i);
            wallPos[count] = i;
            map[pos.x][pos.y] = 1;
            setWall(count + 1, i + 1);
            map[pos.x][pos.y] = 0;
        }
    }

    // 안전 영역 구하기
    static void getSafeArea() {
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> queue = new ArrayDeque<>();
        int empty = emptyList.size() - 3; // 빈 칸의 개수 (세운 벽의 개수는 빼야 함)
        for (Pos pos : virusList) {
            queue.offer(pos);
            visited[pos.x][pos.y] = true;
        }

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 벽이 세워졌거나 이미 방문한 곳인 경우
                if (map[nx][ny] == 1 || visited[nx][ny])
                    continue;

                queue.offer(new Pos(nx, ny));
                visited[nx][ny] = true;
                if (map[nx][ny] == 0) {
                    empty--;
                }
            }
        }
        ans = Math.max(ans, empty);
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}