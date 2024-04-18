import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            String record = st.nextToken();
            if (record.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }
        List<String> result = set.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (String r : result) {
            sb.append(r).append("\n");
        }
        System.out.println(sb.toString());
    }
}