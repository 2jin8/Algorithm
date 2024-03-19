import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = 4 * (n - 1) + 1;
        arr = new char[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(arr[i], ' ');
        }
        recur(m, 0, 0);
    }

    public static void recur(int idx, int x, int y) {
        if (idx < 1) {
            print();
            return;
        }

        for (int i = y; i < y + idx; i++) {
            arr[x][i] = '*';
            arr[x + idx - 1][i] = '*';
        }
        for (int i = x; i < x + idx; i++) {
            arr[i][y] = '*';
            arr[i][y + idx - 1] = '*';
        }
        recur(idx - 4, x + 2, y + 2);
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}