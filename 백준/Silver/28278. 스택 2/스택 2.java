import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case 2:
                    sb.append(stack.pop()).append("\n");
                    break;
                case 3:
                    sb.append(stack.count()).append("\n");
                    break;
                case 4:
                    sb.append(stack.isEmpty()).append("\n");
                    break;
                case 5:
                    sb.append(stack.peek()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static class Stack {
        LinkedList<Integer> stack = new LinkedList<>();

        public void push(int x) {
            stack.add(x);
        }

        public int pop() {
            if (stack.isEmpty()) return -1;
            return stack.remove(stack.size() - 1);
        }

        public int count() {
            return stack.size();
        }

        public int isEmpty() {
            if (stack.isEmpty()) return 1;
            return 0;
        }

        public int peek() {
            if (stack.isEmpty()) return -1;
            return stack.get(stack.size() - 1);
        }
    }
}