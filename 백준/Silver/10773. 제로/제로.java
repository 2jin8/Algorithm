import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) deque.pollLast();
            else deque.offerLast(value);
        }

        int sum = 0;
        for (int d : deque) {
            sum += d;
        }
        System.out.println(sum);
    }
}