import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int a : arr) {
            // 스택이 비어있거나 제일 마지막에 들어간 값과 다르면 넣기
            if (stack.isEmpty() || stack.peek() != a)
                stack.push(a);
        }
        
        int[] answer = new int[stack.size()];
        for (int i=answer.length-1; i>=0; i--) {
            answer[i] = stack.pop();
        }
        return answer;
    }
}