import java.util.*;
import java.io.*;

public class Main {

    private static long A, B;
    private static Queue<Long> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        queue.add(A);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long num = queue.poll();

                if (num == B) {
                    System.out.println(count + 1);
                    return;
                }

                if (num * 2 <= B) queue.add(num * 2);
                if (num * 10 + 1 <= B)queue.add(num * 10 + 1);
            }
            count++;
        }
        System.out.println(-1);
    }

    private static int countCalc(int n) {
        int count = 0;
        while (n != 0) {
            n /= 2;
            count++;
        }
        return count;
    }

}