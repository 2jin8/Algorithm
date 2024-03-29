import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hashSet.add(arr[i]);
        }
        List<Integer> list = hashSet.stream().sorted().collect(Collectors.toList());
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            hashMap.put(list.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(hashMap.get(arr[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}