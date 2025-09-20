import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr, minDp, maxDp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minDp = new int[N][3];
        maxDp = new int[N][3];
        minDp[0] = arr[0].clone();
        maxDp[0] = arr[0].clone();

        for (int i = 1; i < N; i++) {
            maxDp[i][0] = arr[i][0] + Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            minDp[i][0] = arr[i][0] + Math.min(minDp[i - 1][0], minDp[i - 1][1]);


            maxDp[i][1] = arr[i][1] + Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2]));
            minDp[i][1] = arr[i][1] + Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2]));

            maxDp[i][2] = arr[i][2] + Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);
            minDp[i][2] = arr[i][2] + Math.min(minDp[i - 1][1], minDp[i - 1][2]);
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[N - 1][i]);
            max = Math.max(max, maxDp[N - 1][i]);
        }
        System.out.println(max + " " + min);
    }
}