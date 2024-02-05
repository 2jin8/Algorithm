import java.io.*;
import java.util.*;

public class Main {
    static int n, total = 0;
    static int[][] record;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int sqrt = (int) Math.sqrt(n);
        // 1개로 표현
        if (pow(sqrt) == n) {
            System.out.println(1);
            return;
        }

        // 2개로 표현
        for (int i = 1; i <= sqrt; i++) {
            for (int j = 1; j <= sqrt; j++) {
                if (pow(i) + pow(j) == n) {
                    System.out.println(2);
                    return;
                }
            }
        }

        // 3개로 표현
        for (int i = 1; i <= sqrt; i++) {
            for (int j = 1; j <= sqrt; j++) {
                for (int k = 1; k <= sqrt; k++) {
                    if (pow(i) + pow(j) + pow(k) == n) {
                        System.out.println(3);
                        return;
                    }
                }
            }
        }

        // 모든 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현 가능
        // 1, 2, 3개의 제곱수의 합으로 이미 표현 불가능 → 남은 것은 네 개로 표현하는 것
        System.out.println(4);
    }

    public static int pow(int a) {
        return (int) Math.pow(a, 2);
    }
}