import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Building> stack = new Stack<>();
        int[] total = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(new Building(arr[i], i));
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push(new Building(arr[i], i));
                        break;
                    }

                    if (stack.peek().height < arr[i]) {
                        Building pop = stack.pop();
                        total[i] += total[pop.idx] + 1;
                    } else {
                        stack.push(new Building(arr[i], i));
                        break;
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += total[i];
        }
        System.out.println(sum);
    }

    static class Building {
        int height;
        int idx;

        public Building(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}