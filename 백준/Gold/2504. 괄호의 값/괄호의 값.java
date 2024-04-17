import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int answer = 0, total = 1;
        boolean flag = false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            if (now == '(') { 
                stack.push(now); 
                total *= 2; // '(' 값은 2
            } else if (now == '[') {
                stack.push(now);
                total *= 3; // '[' 값은 3
            } else if (now == ')') {
                if (stack.isEmpty() || stack.peek() != '(') { // 짝이 맞는게 없다면
                    // 입력이 올바르지 못한 것
                    System.out.println(0);
                    return;
                }

                if (s.charAt(i - 1) == '(') { // 짝이 맞는 괄호가 연달아 나온다면 식의 마지막을 뜻함
                    answer += total; // answer에 이때까지 구한 값 더하기
                }
                total /= 2;
                stack.pop();
            } else if (now == ']') {
                if (stack.isEmpty() || stack.peek() != '[') { // 짝이 맞는게 없다면
                    // 입력이 올바르지 못한 것
                    System.out.println(0);
                    return;
                }

                if (s.charAt(i - 1) == '[') { // 짝이 맞는 괄호가 연달아 나온다면 식의 마지막을 뜻함
                    answer += total; // answer에 이때까지 구한 값 더하기
                }
                total /= 3;
                stack.pop();
            }
        }
        if (!stack.isEmpty()) System.out.println(0);
        else System.out.println(answer);
    }
}