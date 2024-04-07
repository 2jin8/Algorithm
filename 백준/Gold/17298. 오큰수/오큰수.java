import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                ans[i] = -1;
                stack.push(arr[i]);
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        ans[i] = -1;
                        stack.push(arr[i]);
                        break;
                    }

                    if (stack.peek().intValue() > arr[i]) {
                        ans[i] = stack.peek().intValue(); // 기록하고
                        stack.push(arr[i]); // push
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int a : ans) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}