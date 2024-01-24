import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] board;
    static boolean[][] bomb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        bomb = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '*') bomb[i][j] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        fillBoard();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void fillBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'x') {
                    // 지뢰 획인
                    if (bomb[i][j]) { // 지뢰가 있는 지점인 경우
                        setBomb(); // 지뢰가 있는 지점 모두 표시
                    } else { // 지뢰가 없는 지점인 경우
                        // 주변 지뢰 개수 구하기
                        board[i][j] = (char) (findBomb(i, j) + '0'); 
                    }
                }
            }
        }
    }

    public static void setBomb() { // 지뢰 표시하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (bomb[i][j]) board[i][j] = '*';
            }
        }
    }

    public static int findBomb(int x, int y) { // 주변에 위치한 지뢰 개수 찾기
        int[] dx = {1, -1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, 1, -1, -1, 1, -1, 1};
        int bombCnt = 0;
        for (int i = 0; i < 8; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= N || ty >= N) {
                continue;
            }

            // 주변에 지뢰가 있는 경우
            if (bomb[tx][ty]) bombCnt++;
        }
        return bombCnt;
    }
}