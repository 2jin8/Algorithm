import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int[] cars = new int[101];
        int max = 0;
        for (int i = 0; i < 3; i++) {
            int start = scan.nextInt();
            int end = scan.nextInt();
            max = Math.max(max, end);
            for (int j = start; j < end; j++) {
                cars[j]++;
            }
        }
        
        int total = 0;
        for (int i = 1; i < max; i++) {
            int car = cars[i];
            switch (car) {
                case 1:
                    total += a;
                    break;
                case 2:
                    total += 2 * b;
                    break;
                case 3:
                    total += 3 * c;
                    break;
            }
        }
        System.out.println(total);
    }
}