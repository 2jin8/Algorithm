import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int answer = 0, total = 1;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            // 왼쪽 괄호면 스택에 push & total에 괄호 값 곱하기
            if (now == '(') {
                stack.push(now);
                total *= 2;
            } else if (now == '[') {
                stack.push(now);
                total *= 3;
            } else if (now == ')') {
                // ')'의 짝인 '('가 없으면 입력이 올바르지 못한 괄호열
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }
                // 짝이 맞는 괄호가 연속해서 있다면 더할 차례
                if (str.charAt(i - 1) == '(') {
                    answer += total;
                }
                total /= 2;
                stack.pop();
            } else if (now == ']') {
                // ']'의 짝인 '['가 없으면 입력이 올바르지 못한 문자열
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }
                if (str.charAt(i - 1) == '[') {
                    answer += total;
                }
                total /= 3;
                stack.pop();
            }
        }
        if (stack.isEmpty()) System.out.println(answer);
        else System.out.println(0);
    }
}