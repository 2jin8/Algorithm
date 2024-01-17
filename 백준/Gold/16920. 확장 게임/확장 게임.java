import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P;
    static char[][] map;
    static boolean[][] visited;
    static Player[] players;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        P = Integer.parseInt(str[2]);
        map = new char[N][M];
        visited = new boolean[N][M];
        players = new Player[P + 1]; // 1 ~ P번
        for (int i = 0; i <= P; i++) {
            players[i] = new Player(0);
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < P; i++) {
            players[i + 1].move = Integer.parseInt(str[i]);
        }
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] != '#' && map[i][j] != '.') {
                    players[map[i][j] - '0'].queue.offer(new int[]{i, j});
                    players[map[i][j] - '0'].castle++;
                }
            }
        }
        expandCastle();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(players[i].castle).append(" ");
        }
        System.out.println(sb);
    }

    public static void expandCastle() {

        while (canMove()) {
            for (int i = 1; i <= P; i++) {
                int move = players[i].move;
                for (int j = 0; j < move; j++) {
                    if (players[i].queue.isEmpty()) break;
                    bfs(players[i]);
                }
            }
        }
    }

    public static boolean canMove() {
        for (int i = 1; i <= P; i++) {
            // 큐가 모두 비어야지 게임 종료
            if (!players[i].queue.isEmpty()) return true; 
        }
        return false;
    }

    public static void bfs(Player player) {
        int size = player.queue.size();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < size; i++) {
            int[] poll = player.queue.poll();

            for (int j = 0; j < 4; j++) {
                int tx = poll[0] + dx[j];
                int ty = poll[1] + dy[j];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M)
                    continue;

                if (map[tx][ty] == '.' && !visited[tx][ty]) {
                    player.queue.offer(new int[]{tx, ty});
                    player.castle++;
                    visited[tx][ty] = true;
                }
            }

        }
    }

    static class Player {
        int move; // 한 번에 이동할 수 있는 횟수
        int castle; // 가진 성의 수
        Queue<int[]> queue = new LinkedList<>(); // BFS 탐색을 위한 큐

        public Player(int move) {
            this.move = move;
            this.castle = 0;
        }
    }
}