import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K = 8, minCnt;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        minCnt = K * K;
        for (int i = 0; i <= N - K; i++) {
            for (int j = 0; j <= M - K; j++) {
                // (i, j)에서 시작
                // 첫 칸을 검정으로 칠하는 경우
                minCnt = Math.min(minCnt, paintBoard('B', 'W', i, j));

                // 첫 칸을 흰색으로 칠하는 경우
                minCnt = Math.min(minCnt, paintBoard('W', 'B', i, j));
            }
        }
        System.out.println(minCnt);
    }

    static int paintBoard(char first, char second, int x, int y) {
        int paint = 0; // 칠해야 하는 정사각형의 개수
        for (int i = x; i < x + K; i++) {
            for (int j = y; j < y + K; j++) {
                // 짝수행 & 짝수열 or 홀수행 & 홀수열 ⇒ first 색으로 칠해야 함
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    if (board[i][j] != first) paint++;
                }
                // 짝수행 & 홀수열 or 홀수행 & 짝수열 ⇒ second 색으로 칠해야 함
                else {
                    if (board[i][j] != second) paint++;
                }
                // 칠하는 도중, 칠하는 최소 횟수보다 크다면 탐색 종료 (어짜피 답이 아님)
                if (paint > minCnt) return K * K;
            }
        }
        return paint;
    }
}