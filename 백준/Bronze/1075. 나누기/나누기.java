import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        n = n / 100 * 100;
        for (int i = 0; i < 100; i++) {
            if (n % k == 0) break;
            n++;
        }

        String strN = String.valueOf(n);
        System.out.println(strN.substring(strN.length() - 2));
    }
}