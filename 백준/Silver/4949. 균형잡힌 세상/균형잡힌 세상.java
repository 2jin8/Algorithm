import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;

            Stack<String> stack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '(' || c == ')' || c == '[' || c == ']')
                    stack.push(String.valueOf(c));
            }

            Stack<String> ansQueue = new Stack<>();
            while (!stack.isEmpty()) {
                ansQueue.push(stack.pop());

                int size = ansQueue.size();
                if (size > 1) {
                    String s1 = ansQueue.elementAt(size - 1);
                    String s2 = ansQueue.elementAt(size - 2);
                    if (s1.equals("(") && s2.equals(")")) {
                        ansQueue.pop();
                        ansQueue.pop();
                    } else if (s1.equals("[") && s2.equals("]")) {
                        ansQueue.pop();
                        ansQueue.pop();
                    }
                }
            }

            if (ansQueue.isEmpty()) System.out.println("yes");
            else System.out.println("no");
        }
    }
}