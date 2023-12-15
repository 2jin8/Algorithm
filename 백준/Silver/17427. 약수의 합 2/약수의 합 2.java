import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] fx = new int[n + 1];

        Arrays.fill(fx, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; i * j <= n; j++) {
                fx[i * j] += i;
            }
        }
        
        long sum = 0L;
        for (int i = 1; i <= n; i++) {
            sum += fx[i];
        }
        System.out.println(sum);
    }
}