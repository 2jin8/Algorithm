import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] peoples = new int[n];
        for (int i = 0; i < n; i++) {
            peoples[i] = scan.nextInt();
        }
        int b = scan.nextInt();
        int c = scan.nextInt();

        long total = n; // 총감독관은 1명씩 있어야 함
        for (int i = 0; i < n; i++) {
            peoples[i] -= b; // 총감독관이 감시할 수 있는 인원 빼기
            if (peoples[i] > 0) {
                total += (int) (Math.ceil((double) peoples[i] / c));
            }
        }
        System.out.println(total);
    }
}