import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int idx = 1;
        while (true) {
            // 연속하는 P일 중, L일만 사용 가능(휴가는 V일)
            int L = scan.nextInt();
            int P = scan.nextInt();
            int V = scan.nextInt();
            if (L == 0 && P == 0 && V == 0)
                break;

            int div = V / P;
            int mod = V % P;
            if (mod > L) mod = L;
            long totalDay = (long) div * L + mod;
            System.out.println("Case " + idx + ": " + totalDay);
            idx++;
        }

    }
}