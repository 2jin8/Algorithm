import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, ans;
    static boolean[] placed, leftCross, rightCross; // 해당 열에 퀸을 놓았는지 확인하기 위한 배열
    static int[] places; // places[4]: 4행 places[4]열
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        places = new int[N];
        placed = new boolean[N];
        leftCross = new boolean[2 * N - 1];
        rightCross = new boolean[2 * N - 1];
        setQueen(0);
        System.out.println(ans);
    }

    static void setQueen(int row) {
        if (row == N) {
            ans++;
            return;
        }

        // 같은 열에 놓지 않음
        for (int col = 0; col < N; col++) {
            if (placed[col]) continue;

            // 해당 열에 놓을 때, 대각선에 걸리는 것이 없다면 놓기 가능
            int left = row - col + N - 1, right = row + col;
            if (!leftCross[left] && !rightCross[right]) { // 좌대각선: row - col 같음, 우대각선: row + col 같음
                placed[col] = true;
                places[row] = col;
                rightCross[right] = true; leftCross[left] = true;
                setQueen(row + 1);
                rightCross[right] = false; leftCross[left] = false;
                placed[col] = false;
            }
        }
    }
}
