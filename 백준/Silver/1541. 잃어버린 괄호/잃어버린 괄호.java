import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer minus = new StringTokenizer(br.readLine(), "-");
        int count = minus.countTokens();
        int[] totals = new int[count];
        for (int i = 0; i < count; i++) {
            StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+");
            int c = plus.countTokens();
            for (int j = 0; j < c; j++) {
                totals[i] += Integer.parseInt(plus.nextToken());
            }
        }

        int total = totals[0];
        for (int i = 1; i < count; i++) {
            total -= totals[i];
        }
        System.out.println(total);
    }
}