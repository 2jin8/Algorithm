import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pipes = br.readLine();
        int total = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < pipes.length() - 1; i++) {
            char now = pipes.charAt(i);
            if (now == '(') { // 현재 괄호가 열린 괄호라면
                char next = pipes.charAt(i + 1); // 다음 괄호 확인하기
                if (next == ')') { // 다음 괄호가 닫힌 괄호라면
                    // 레이저를 뜻하므로 잘린 파이프의 수(=스택의 크기) 더하기
                    total += stack.size();
                    i++;
                } else {
                    // 그렇지 않다면 막대기를 뜻하므로 스택에 넣기
                    stack.push(now);
                }
            } else { // 닫힌 괄호라면 파이프의 끝을 뜻하므로 1 더하기
                total++;
                stack.pop();
            }
        }
        // 편의상 pipes.length - 1까지 탐색했으므로, 마지막의 괄호(=파이프의 끝) 고려하기
        if (!stack.isEmpty()) total++;
        System.out.println(total);
    }
}