import java.util.*;
import java.io.*;

public class Main {
    static int R, C, N;
    static char[][] board, full;
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        full = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'O') {
                    queue.offer(new Point(i, j));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (N % 2 == 0) { // 짝수번째라면 모두 폭탄으로 차있는 상태
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append("O");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            return;
        }

        // 횟수가 1이면 처음 상태와 동일
        if (N != 1) bfs();
        sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs() {
        int time = 1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (time != N) {
            time += 2;
            for (int i = 0; i < R; i++) {
                Arrays.fill(board[i], 'O');
            }

            // 큐에 담긴 폭탄 터뜨리기
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                board[point.x][point.y] = '.';

                for (int j = 0; j < 4; j++) {
                    int tx = point.x + dx[j];
                    int ty = point.y + dy[j];
                    if (tx < 0 || ty < 0 || tx >= R || ty >= C)
                        continue;

                    board[tx][ty] = '.';
                }
            }
            // 남은 폭탄 큐에 저장하기
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 'O')
                        queue.offer(new Point(i, j));
                }
            }
        }
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