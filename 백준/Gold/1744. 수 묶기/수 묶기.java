import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    private static int[] num;
    private static boolean[] visited;
    private static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        num = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);

//        int sum = 0;
        for (int i = N - 1; i > 0; i--) {
            if (num[i] <= 0) {
                countM(i);
                break;
            }

            if (!visited[i] && !visited[i - 1]) {
                int mul = num[i] * num[i - 1];
                int add = num[i] + num[i - 1];
                if (mul > add) {
                    sum += mul;
                    visited[i] = true;
                    visited[--i] = true;
                } else {
                    sum += num[i];
                    visited[i] = true;
                }
            }
        }

        if (!visited[0])
            sum += num[0];
        
        System.out.println(sum);
    }

    private static void countM(int idx) {
        for (int i = 0; i < idx; i++) {
            if (!visited[i] && !visited[i + 1]) {
                int mul = num[i] * num[i + 1];
                int add = num[i] + num[i + 1];
                if (mul > add) {
                    sum += mul;
                    visited[i] = true;
                    visited[++i] = true;
                } else {
                    sum += num[i];
                }
            }
        }
        if (!visited[idx])
            sum += num[idx];
    }
}