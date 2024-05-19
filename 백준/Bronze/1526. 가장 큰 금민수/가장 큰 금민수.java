import java.io.*;

public class Main {
    static int n, answer = 0;
    static int[] arr = {4, 7};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int total) {
        if (total > n) return;
        answer = Math.max(answer, total);

        dfs(total * 10 + 4);
        dfs(total * 10 + 7);
    }
}