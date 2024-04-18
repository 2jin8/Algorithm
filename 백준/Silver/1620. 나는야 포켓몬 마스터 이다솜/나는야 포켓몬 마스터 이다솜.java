import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, String> getName = new HashMap<>();
        HashMap<String, String> getNumber = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String number = String.valueOf(i);
            String name = br.readLine();
            getName.put(number, name);
            getNumber.put(name, number);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (getName.containsKey(input)) {
                sb.append(getName.get(input));
            } else {
                sb.append(getNumber.get(input));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}