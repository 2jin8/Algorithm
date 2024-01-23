import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] submit = new boolean[31];
        for (int i = 0; i < 28; i++) {
            submit[Integer.parseInt(br.readLine())] = true;
        }

        for (int i = 1; i <= 30; i++) {
            if (!submit[i]) {
                System.out.println(i);
            }
        }
    }
}