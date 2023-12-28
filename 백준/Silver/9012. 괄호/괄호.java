import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            bw.write(findVPS(br.readLine()));
        }
        bw.flush();
        bw.close();
    }

    public static String findVPS(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') { // '('라면 스택에 넣기
                stack.push(c); 
            } else if (stack.isEmpty()) { // ')'와 짝지을 '('가 없는 경우
                return "NO\n";
            } else { // 스택이 비어있지 않고 ')'인 경우
                stack.pop();
            }
        }
        if (stack.isEmpty()) return "YES\n";
        else return "NO\n";
    }
}