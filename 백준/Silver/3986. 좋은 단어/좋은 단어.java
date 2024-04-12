import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int total = 0;
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < word.length(); j++) {
                // 스택이 비어있지 않고, 현재 문자와 peek 값이 같다면 pop
                if (!stack.isEmpty() && stack.peek() == word.charAt(j)) {
                    stack.pop();
                } else { // 그렇지 않다면 push
                    stack.push(word.charAt(j));
                }
            }
            if (stack.isEmpty()) total++;
        }
        System.out.println(total);
    }
}