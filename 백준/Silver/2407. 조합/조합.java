import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        BigInteger[][] arr = new BigInteger[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) arr[i][j] = new BigInteger(i + "");
                else if (i == j) arr[i][j] = new BigInteger("1");
                else arr[i][j] = arr[i - 1][j - 1].add(arr[i - 1][j]);
            }
        }
        System.out.println(arr[n][m]);
    }
}