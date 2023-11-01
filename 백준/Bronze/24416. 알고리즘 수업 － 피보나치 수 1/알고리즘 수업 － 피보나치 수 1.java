import java.util.*;

public class Main {
    static int recursiveCnt = 0, dpCnt = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        fib(n);
        System.out.print(recursiveCnt + " ");
        fibonacci(n);
        System.out.println(dpCnt);
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            recursiveCnt++;
            return 1;
        }
        return fib(n - 1) * fib(n - 2);
    }

    public static int fibonacci(int n) {
        int[] f = new int[n + 1];
        f[1] = 1; f[2] = 1;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
            dpCnt++;
        }
        return f[n];
    }
}