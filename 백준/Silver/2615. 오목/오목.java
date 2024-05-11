import java.io.*;
import java.util.*;

public class Main {
    static final int N = 19;
    static Pos answer;
    static int[][] board = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 검: 1, 흰: 2, 빈 칸: 0
        // 검 win: 1, 흰 win: 2, 결정 x: 0
        // 이긴 돌의 가장 왼쪽에 있는 r, c 출력 (+1씩해서 출력해야 함)
        PriorityQueue<Pos> black = new PriorityQueue<>();
        PriorityQueue<Pos> white = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) black.offer(new Pos(i, j));
                else if (board[i][j] == 2) white.offer(new Pos(i, j));
            }
        }

        if (checkStone(black)) { // 검은색이 이긴 경우
            System.out.println(1);
            System.out.println(answer.x + " " + answer.y);
        } else if (checkStone(white)) { // 흰색이 이긴 경우
            System.out.println(2);
            System.out.println(answer.x + " " + answer.y);
        } else { // 승부가 결정되지 않은 경우
            System.out.println(0);
        }
    }

    public static boolean checkStone(Queue<Pos> queue) {
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            int x = pos.x, y = pos.y;
            boolean result = checkCol(x, y) || checkRow(x, y)
                    || checkLeftCross(x, y) || checkRightCross(x, y);
            // 연속으로 돌을 다섯개 놓았다면 true 반환하기
            if (result) {
                answer = new Pos(x + 1, y + 1);
                return true;
            }
        }
        return false;
    }

    public static boolean checkCol(int x, int y) { // 세로
        // 연속된 돌의 개수 구하기
        int cnt = 1; // board[x][y]

        for (int i = 1; i < N; i++) {
            if (x + i >= N || board[x + i][y] != board[x][y]) {
                break;
            }
            cnt++;
        }
        for (int i = 1; i < N; i++) {
            if (x - i < 0 || board[x - i][y] != board[x][y]) {
                break;
            }
            cnt++;
        }
        return cnt == 5;
    }

    public static boolean checkRow(int x, int y) { // 가로
        // 연속된 돌의 개수 구하기
        int cnt = 1; // board[x][y]

        for (int i = 1; i < N; i++) {
            if (y + i >= N || board[x][y + i] != board[x][y]) {
                break;
            }
            cnt++;
        }
        for (int i = 1; i < N; i++) {
            if (y - i < 0 || board[x][y - i] != board[x][y]) {
                break;
            }
            cnt++;
        }
        return cnt == 5;
    }

    public static boolean checkRightCross(int x, int y) { // 우대각선
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            if (x - i < 0 || y + i >= N || board[x - i][y + i] != board[x][y]) break;
            cnt++;
        }
        for (int i = 1; i < N; i++) {
            if (x + i >= N || y - i < 0 || board[x + i][y - i] != board[x][y]) break;
            cnt++;
        }
        return cnt == 5;
    }

    public static boolean checkLeftCross(int x, int y) { // 좌대각선
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (x + i >= N || y + i >= N || board[x + i][y + i] != board[x][y]) break;
            cnt++;
        }
        for (int i = 1; i < N; i++) {
            if (x - i < 0 || y - i < 0 || board[x - i][y - i] != board[x][y]) break;
            cnt++;
        }
        return cnt == 5;
    }

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Pos pos) {
            if (this.y == pos.y) return this.x - pos.x;
            return this.y - pos.y;
        }
    }
}