import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static HashMap<String, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        int total = 0;
        while ((str = br.readLine()) != null) {
            if (hashMap.containsKey(str)) {
                hashMap.put(str, hashMap.get(str) + 1);
            } else {
                hashMap.put(str, 1);
            }
            total++;
        }
        List<String> trees = hashMap.keySet().stream().collect(Collectors.toList());
        Collections.sort(trees);

        StringBuilder sb = new StringBuilder();
        for (String tree : trees) {
            int cnt = hashMap.get(tree);
            sb.append(tree).append(" ").append(String.format("%.4f", (double) cnt / total * 100)).append("\n");
        }
        System.out.println(sb.toString());
    }
}