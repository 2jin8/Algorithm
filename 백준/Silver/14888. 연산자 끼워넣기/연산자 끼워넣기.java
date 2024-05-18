import java.io.*;
import java.util.*;

public class Main {
    static int n, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] operand, operator;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        operand = new int[n]; // 피연산자
        operator = new int[4]; // 연산자
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            operand[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, operand[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int total) {
        if (depth == n) {
            max = Math.max(max, total);
            min = Math.min(min, total);
            return;
        }

        // 덧셈
        if (operator[0] > 0) {
            operator[0]--;
            dfs(depth + 1, total + operand[depth]);
            operator[0]++;
        }

        if (operator[1] > 0) {
            operator[1]--;
            dfs(depth + 1, total - operand[depth]);
            operator[1]++;
        }

        if (operator[2] > 0) {
            operator[2]--;
            dfs(depth + 1, total * operand[depth]);
            operator[2]++;
        }

        if (operator[3] > 0) {
            operator[3]--;
            dfs(depth + 1, total / operand[depth]);
            operator[3]++;
        }
    }
}