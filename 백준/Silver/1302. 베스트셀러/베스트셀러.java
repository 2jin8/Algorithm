import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> book = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            if (book.containsKey(name)) { // 이미 존재하면
                book.put(name, book.get(name) + 1); // 1 더해서 저장
            } else { // 존재하지 않으면
                book.put(name, 1); // 새로 저장
            }
        }

        int max = 0;
        List<String> names = book.keySet().stream().collect(Collectors.toList());
        List<Integer> values = book.values().stream().collect(Collectors.toList());
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (value > max) {
                ans = new ArrayList<>();
                ans.add(names.get(i));
                max = value;
            } else if (value == max) {
                ans.add(names.get(i));
            }
        }

        Collections.sort(ans);
        System.out.println(ans.get(0));
    }
}