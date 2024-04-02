import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        HashSet<String> hashSet = new LinkedHashSet<>();
        for (int i = 0; i < L; i++) {
            String number = br.readLine();
            if (hashSet.contains(number)) { // 이미 대기열에 들어가 있는 상태일 때
                hashSet.remove(number); // 기존 위치는 없어지고
                hashSet.add(number); // 맨 뒤로 밀려남
            } else {
                hashSet.add(number);
            }
        }

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : hashSet) {
            if (idx == K) break;
            sb.append(s).append("\n");
            idx++;
        }
        System.out.println(sb.toString());
    }
}