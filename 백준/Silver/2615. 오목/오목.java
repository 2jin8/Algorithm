import java.io.*;
import java.util.*;

public class Main {
    static final int N = 19;
    static int[][] board = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 검: 1, 흰: 2, 빈 칸: 0
        // 검 win: 1, 흰 win: 2, 결정 x: 0
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] move = {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (board[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int x = i, y = j;
                        int cnt = 1;

                        // 한 방향으로 탐색하기
                        while (true) {
                            x += move[k][0];
                            y += move[k][1];
                            if (x < 0 || y < 0 || x >= N || y >= N || board[x][y] != board[i][j]) {
                                break;
                            }
                            cnt++;
                        }

                        // 반대 방향으로 탐색하기
                        x = i; y = j;
                        while (true) {
                            x -= move[k][0];
                            y -= move[k][1];
                            if (x < 0 || y < 0 || x >= N || y >= N || board[x][y] != board[i][j]) {
                                break;
                            }
                            cnt++;
                        }
                        if (cnt == 5) { // 바둑알이 연속으로 다섯 개 놓인 경우
                            System.out.println(board[i][j]);
                            System.out.println((i + 1) + " " + (j + 1));
                            return;
                        }
                    }
                }
            }
        }
        // 아직 승부가 결정되지 않은 경우
        System.out.println(0);
    }
}