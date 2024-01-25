import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int now = 1;
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            stack.push(number);
            while (!stack.isEmpty() && stack.peek().intValue() == now) {
                stack.pop();
                now++;
            }
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (now == stack.peek().intValue()) {
                stack.pop();
                now++;
            }
        }
        if (stack.isEmpty()) System.out.println("Nice");
        else System.out.println("Sad");
    }
}