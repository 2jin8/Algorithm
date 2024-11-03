import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static final int N = 5;
    static int[] arr[], number;
    static StringBuilder sb = new StringBuilder();
    static HashSet<String> numbers = new HashSet<>();
    static int[] dx={1, -1, 0, 0}, dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        number = new int[N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(0, i, j);
            }
        }
        System.out.println(numbers.size());
    }
    
    static void dfs(int depth, int x, int y) {
        if (depth == N + 1) {
            sb.setLength(0);
            for (int n : number) {
                sb.append(n);
            }
            numbers.add(sb.toString());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;

            number[depth] = arr[nx][ny];
            dfs(depth + 1, nx, ny);
        }
    }
}
