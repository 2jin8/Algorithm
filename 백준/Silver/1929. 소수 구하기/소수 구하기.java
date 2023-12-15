import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        // m 이상 n 이하 소수 출력
        // 1 ~ n까지 소수 구하기 -> 조건에 맞는 소수 출력
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // true: 소수 O, false: 소수 X
        isPrime[1] = false;
        
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = 2; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int i = m; i <= n; i++) {
            if (isPrime[i]) System.out.println(i);
        }
    }
}