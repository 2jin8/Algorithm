import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) { // 스택이 비어있다면 push & 0 기록
                stack.push(new Top(heights[i], i + 1));
                sb.append("0 ");
                continue;
            }

            boolean check = false;
            while (!stack.isEmpty()) {
                if (stack.peek().height > heights[i]) { // 'peek 높이 > 현재 높이'인 경우
                    // 기록 & push
                    sb.append(stack.peek().idx).append(" ");
                    stack.push(new Top(heights[i], i + 1));
                    check = true;
                    break;
                } else { // 아니라면 그냥 pop
                    stack.pop();
                }
            }
            if (!check) {
                sb.append("0 ");
                stack.push(new Top(heights[i], i + 1));
            }
        }
        System.out.println(sb.toString());
    }

    static class Top {
        int height, idx;

        public Top(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}