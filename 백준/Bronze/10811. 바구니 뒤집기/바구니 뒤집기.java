import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] bucket = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bucket[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            for (int j = start, l = end; j <= l; j++, l--) {
                int tmp = bucket[j];
                bucket[j] = bucket[l];
                bucket[l] = tmp;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(bucket[i]).append(" ");
        }
        System.out.println(sb);
    }
}