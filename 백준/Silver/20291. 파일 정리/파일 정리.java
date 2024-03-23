import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int n;
    static HashMap<String, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine().split("\\.")[1];
            if (hashMap.containsKey(s)) {
                int v = hashMap.get(s);
                hashMap.put(s, v + 1);
            } else {
                hashMap.put(s, 1);
            }
        }

        List<String> name = hashMap.keySet().stream().collect(Collectors.toList());
        List<Integer> count = hashMap.values().stream().collect(Collectors.toList());
        PriorityQueue<File> pq = new PriorityQueue<>();
        for (int i = 0; i < name.size(); i++) {
            pq.offer(new File(name.get(i), count.get(i)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            File file = pq.poll();
            sb.append(file.name).append(" ").append(file.count).append("\n");
        }
        System.out.println(sb);
    }

    static class File implements Comparable<File> {
        String name;
        int count;

        public File(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(File o) { // 사전순 정렬
            return this.name.compareTo(o.name);
        }
    }
}