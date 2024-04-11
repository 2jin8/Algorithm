import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        StringBuilder sb = new StringBuilder();
        while (!(str = br.readLine()).equals(".")) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                switch (c) {
                    case '(':
                    case '[':
                        stack.push(c);
                        break;
                    case ')':
                        if (stack.isEmpty() || stack.peek() != '(') stack.push(c);
                        else stack.pop();
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.peek() != '[') stack.push(c);
                        else stack.pop();
                        break;
                }
            }
            sb.append(stack.isEmpty() ? "yes\n" : "no\n");
        }
        System.out.println(sb.toString());
    }
}