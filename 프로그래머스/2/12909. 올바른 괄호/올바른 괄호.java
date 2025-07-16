import java.util.*;
import java.io.*;

class Solution {
    boolean solution(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }
}