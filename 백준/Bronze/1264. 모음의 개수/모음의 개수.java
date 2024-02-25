import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String[] str = br.readLine().split("");
            int total = 0;
            for (int i = 0; i < str.length; i++) {
                switch (str[i]) {
                    case "A": case "a":
                    case "E": case "e":
                    case "I": case "i":
                    case "O": case "o":
                    case "U": case "u":
                        total++;
                        break;
                    case "#":
                        System.out.println(sb);
                        return;
                }
            }
            sb.append(total).append("\n");
        }
    }
}