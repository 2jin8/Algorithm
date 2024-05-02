import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            String code = br.readLine();
            if (code.equals("END")) break;

            StringBuilder sb = new StringBuilder();
            sb.append(code).reverse();
            answer.append(sb.toString()).append("\n");
        }
        System.out.println(answer.toString());
    }
}