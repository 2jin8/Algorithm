import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        long sum = 0;

        for (int i = 1; i <= x; i++) {
            sum += (long) (x / i) * i;
        }
        System.out.println(sum);
    }
}