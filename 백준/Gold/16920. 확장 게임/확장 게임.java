import java.util.*;
import java.io.*;

public class Main {
    static int N, M, P;
    static char[][] map;
    static boolean[][] visited;
    static Player[] players;
    static int[][] record, after;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        players = new Player[P + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= P; i++) {
            int move = Integer.parseInt(st.nextToken());
            players[i] = new Player(move);
        }
        record = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] != '.' && map[i][j] != '#') {
                    int player = map[i][j] - '0';
                    players[player].queue.offer(new int[]{i, j});
                    players[player].castle++;
                    visited[i][j] = true;
                    record[i][j] = player;
                }
            }
        }

        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(players[i].castle).append(" ");
        }
        System.out.println(sb);
    }

    static boolean checkComplete() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                
            }
        }
        return true;
    }

    static void bfs() {
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean isChange = true;
        while (isChange) {
            isChange = false;
            for (int p = 1; p <= P; p++) {
                Queue<int[]> queue = players[p].queue;
                int moveCnt = 0;
                while (!queue.isEmpty()) {
                    moveCnt++;
                    int size = queue.size();
                    for (int s = 0; s < size; s++) {
                        int[] now = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int nx = now[0] + move[i][0];
                            int ny = now[1] + move[i][1];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == '#') {
                                continue;
                            }

                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                            record[nx][ny] = p;
                            players[p].castle++;
                            isChange = true;
                        }
                    }
                    if (moveCnt == players[p].move) break;
                }
            }
        }
    }

    static class Player {
        int move; // 이동 가능 횟수
        int castle; // 가진 성의 개수
        Queue<int[]> queue = new LinkedList<>();

        public Player(int move) {
            this.move = move;
            this.castle = 0;
        }
    }
}