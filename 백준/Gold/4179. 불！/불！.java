import java.util.*;
import java.io.*;

public class Main {

    static char[][] board; // 미로
    static int[][] moves; // 탈출 시간 기록
    static boolean[][] visited; // 불 방문 기록
    static int r, c;
    static Queue<Point> fireQ = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visited = new boolean[r][c];
        moves = new int[r][c];
        Point person = null;
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'J') person = new Point(i, j);
                if (board[i][j] == 'F') fireQ.offer(new Point(i, j)); // 불은 여러 곳에서 날 수 있다..!
            }
        }
        System.out.println(bfs(person));
    }
    public static String bfs(Point person) {
        Queue<Point> personQ = new LinkedList<>();
        personQ.offer(person);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!personQ.isEmpty()) {
            // 불 먼저 이동
            int size = fireQ.size();
            for (int s = 0; s < size; s++) {
                Point fire = fireQ.poll();
                visited[fire.x][fire.y] = true;

                for (int i = 0; i < 4; i++) {
                    int tx = fire.x + dx[i];
                    int ty = fire.y + dy[i];

                    if (tx < 0 || ty < 0 || tx >= r || ty >= c)
                        continue;

                    if (board[tx][ty] == '#') // 벽이면 이동 불가
                        continue;

                    if (!visited[tx][ty]) { // 불이 번지지 않은 경우 이동 가능
                        visited[tx][ty] = true;
                        fireQ.offer(new Point(tx, ty));
                    }
                }
            }

            // 지훈 이동
            size = personQ.size();
            for (int s = 0; s < size; s++) {
                person = personQ.poll();
                for (int i = 0; i < 4; i++) {
                    int tx = person.x + dx[i];
                    int ty = person.y + dy[i];

                    if (tx < 0 || ty < 0 || tx >= r || ty >= c) // 탈출하면 탈출 시간 반환
                        return String.valueOf(moves[person.x][person.y] + 1);

                    if (board[tx][ty] == '#') // 벽이면 이동 불가
                        continue;

                    if (moves[tx][ty] != 0) // 이미 방문한 곳이면 이동 불가
                        continue;

                    if (!visited[tx][ty]) { // 불이 번지지 않은 경우 이동 가능
                        moves[tx][ty] = moves[person.x][person.y] + 1;
                        personQ.offer(new Point(tx, ty));
                    }
                }
            }
        }
        return "IMPOSSIBLE";
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}