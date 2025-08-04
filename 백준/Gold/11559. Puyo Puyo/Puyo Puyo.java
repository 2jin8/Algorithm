import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {

    static boolean isPop;
    static int N = 12, M = 6, count;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 뿌요 터지기
        // 1. BFS 탐색으로 몇 개 연결되어 있는지 확인하기
        // 2. 4개 이상 연결되어 있다면 해당 칸 .으로 변경하기

        // 뿌요 내려오기
        // 1. .이 아닌 곳에서 아래를 확인하기
        // 2. 아래가 '.'이면 덮어쓰기
        // 3. 아래가 '.'이 아닐 때까지 반복하기

        while (true) {
            visited = new boolean[N][M];
            isPop = false;
            // 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 함 (연쇄는 1번 추가)
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        findPuyoGroup(i, j); // 뿌요 터뜨리기
                    }
                }
            }
            if (!isPop) break;

            movePuyo(); // 터진 뿌요가 있으면 내리기
            count++;
        }
        System.out.println(count);
    }

    static void findPuyoGroup(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        ArrayList<int[]> groupInfo = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            groupInfo.add(new int[]{now[0], now[1]});

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 방문하지 않은 칸 & 같은 색일 때만 같은 그룹
                if (!visited[nx][ny] && map[nx][ny] == map[now[0]][now[1]]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // 4개 이상 연결되어 있다면 뿌요 터짐
        if (groupInfo.size() >= 4) {
            isPop = true;
            for (int[] info : groupInfo) {
                map[info[0]][info[1]] = '.';
            }
        }
    }

    static void movePuyo() {
        // 열 기준으로
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < M; j++) {
            sb.setLength(0);
            for (int i = N - 1; i >= 0; i--) {
                sb.append(map[i][j]);
            }

            char[] line = sb.toString().replace(".", "").toCharArray();
            for (int i = 0; i < line.length; i++) {
                map[N - 1 - i][j] = line[i];
            }
            for (int i = line.length; i < N; i++) {
                map[N - 1 - i][j] = '.';
            }
        }
    }
}
