import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Queue<Character> pipes = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            pipes.offer(str.charAt(i));
        }

        int total = 0;
        Stack<Character> stack = new Stack<>();
        while (!pipes.isEmpty()) {
            char p1 = pipes.poll().charValue();
            if (p1 == ')') { // 쇠막대기의 오른쪽 끝 → 1 더하고, 스택에서 pop
                stack.pop();
                total++;
            } else if (p1 == '(') { // 쇠막대기의 왼쪽 끝
                if (!pipes.isEmpty() && pipes.peek().charValue() == ')') { // 레이저인 경우
                    total += stack.size(); // 스택에 있는 수만큼 잘라짐
                    pipes.poll();
                } else { // 쇠막대기인 경우
                    stack.push(p1);
                }
            }
        }
        System.out.println(total);
    }
}