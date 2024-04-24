import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        HashSet<Integer> aSet = new HashSet<>();
        for (int i = 0; i < nA; i++) {
            aSet.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < nB; i++) {
            int b = Integer.parseInt(st.nextToken());
            if (aSet.contains(b)) {
                aSet.remove(b);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(aSet.size()).append("\n");
        List<Integer> list = aSet.stream().collect(Collectors.toList());
        Collections.sort(list);
        for (int a : list) {
            sb.append(a).append(" ");
        }
        System.out.println(sb.toString());
    }
}