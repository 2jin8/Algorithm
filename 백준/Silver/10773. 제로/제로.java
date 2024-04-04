import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < K; i++) {
            int value = Integer.parseInt(br.readLine());
            if (value == 0) stack.pop();
            else stack.push(value);
        }

        int sum = 0;
        for (int s : stack) {
            sum += s;
        }
        System.out.println(sum);
    }
}