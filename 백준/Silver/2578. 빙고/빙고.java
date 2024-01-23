import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static final int N = 5;
    static boolean[][] check = new boolean[N][N];
    static int[] nums = new int[N * N];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                String point = i + "," + j;
                hashMap.put(Integer.parseInt(st.nextToken()), point);
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                nums[i * N + j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer point = new StringTokenizer(hashMap.get(nums[i]), ",");
            int x = Integer.parseInt(point.nextToken());
            int y = Integer.parseInt(point.nextToken());
            check[x][y] = true;
        }

        for (int i = 5; i < N * N; i++) {
            StringTokenizer point = new StringTokenizer(hashMap.get(nums[i]), ",");
            int x = Integer.parseInt(point.nextToken());
            int y = Integer.parseInt(point.nextToken());
            check[x][y] = true;

            if (checkRow() + checkCol() + checkLeft()+checkRight() >= 3) {
                System.out.println(i + 1);
                return;
            }
        }
    }

    public static int checkRow() {
        int line = 0;
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) break;
                tmp++;
            }
            if (tmp == N) line++;
        }
        return line;
    }

    public static int checkCol() {
        int line = 0;
        for (int j = 0; j < N; j++) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                if (!check[i][j]) break;
                tmp++;
            }
            if (tmp == N) line++;
        }
        return line;
    }

    public static int checkRight() {
        for (int i = 0; i < N; i++) {
            if (!check[i][N - 1 - i]) return 0;
        }
        return 1;
    }

    public static int checkLeft() {
        for (int i = 0; i < N; i++) {
            if (!check[i][i]) return 0;
        }
        return 1;
    }
}