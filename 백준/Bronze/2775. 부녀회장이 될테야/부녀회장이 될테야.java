import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] apart = new int[15][15];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            apart = new int[15][15];
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());

            System.out.println(people(a, b));
        }
    }

    private static int people(int a, int b) {
        if (a == 0) {
            apart[a][b] = b;
            return b;
        }
        if (b == 1) {
            apart[a][b] = 1;
            return 1;
        }

        for (int i = 1; i <= b; i++) {
            if (apart[a - 1][i] != 0)
                apart[a][b] += apart[a - 1][i];
            else if (apart[a - 1][i] == 0)
                apart[a][b] += people(a - 1, i);
        }

        return apart[a][b];
    }
}