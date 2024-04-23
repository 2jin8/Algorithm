import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] submit = new boolean[30];
        for (int i = 0; i < 28; i++) {
            int num = Integer.parseInt(br.readLine()) - 1;
            submit[num] = true;
        }

        for (int i = 0; i < submit.length; i++) {
            if (!submit[i]) System.out.println(i + 1);
        }
    }
}