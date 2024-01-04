import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Top> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) { // 스택이 비어있으면
                bw.write("0 "); // 0 출력
            } else {
                // 자기보다 높은 탑이 나올 때까지 pop
                while (!stack.isEmpty() && height > stack.peek().height) {
                    stack.pop();
                }
                if (stack.isEmpty()) bw.write("0 ");
                else bw.write(stack.peek().order + " ");
            }
            stack.push(new Top(i + 1, height)); // 스택에 현재 탑 추가
        }
        bw.flush(); bw.close();
    }
}

class Top {
    int order; // 순서
    int height; // 높이

    public Top(int order, int height) {
        this.order = order;
        this.height = height;
    }
}