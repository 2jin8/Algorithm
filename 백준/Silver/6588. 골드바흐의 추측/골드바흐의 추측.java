import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static final int MAX = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 홀수 소수 구하기
        boolean[] primeOdd = new boolean[MAX + 1];
        Arrays.fill(primeOdd, true); // true: 홀수 소수 O, false: 홀수 소수 X
        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (primeOdd[i]) {
                for (int j = 2; i * j <= MAX; j++) {
                    primeOdd[i * j] = false;
                }
            }
        }
        primeOdd[1] = false; primeOdd[2] = false;

        // 골드바흐의 추측
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = sc.nextInt()) != 0) {
            boolean complete = false;
            for (int a = 3; a <= n / 2; a++) {
                int b = n - a;
                if (primeOdd[a] && primeOdd[b]) {
                    sb.append(n).append(" = ").append(a).append(" + ").append(b).append("\n");
                    complete = true;
                    break;
                }
            }
            if (!complete) sb.append("Goldbach's conjecture is wrong.").append("\n");
        }
        System.out.println(sb);
    }
}