import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int A, I;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 100_000; i++) {
            int average = (int) Math.ceil(i / (double) A);
            if (average == I) {
                System.out.println(i);
                break;
            }
        }
    }
}
