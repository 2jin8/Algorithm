import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += str.charAt(i) - '0';
        }
        System.out.println(total);
    }
}