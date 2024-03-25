import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "<|>", true);
        Stack<String> stack = new Stack<>();
        while (st.hasMoreTokens()) {
            stack.push(st.nextToken());
        }

        Stack<String> ans = new Stack<>();
        while (!stack.isEmpty()) {
            String s = stack.pop();
            if (s.equals(">")) {
                StringBuilder sb = new StringBuilder();
                sb.append("<").append(stack.pop()).append(">");
                stack.pop();
                ans.push(sb.toString());
            } else {
                st = new StringTokenizer(s, " ", true);
                StringBuilder sb = new StringBuilder();
                while (st.hasMoreTokens()) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(st.nextToken());
                    sb.append(sb1.reverse());
                }
                ans.push(sb.toString());
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!ans.isEmpty()) {
            sb.append(ans.pop());
        }
        System.out.println(sb);
    }
}