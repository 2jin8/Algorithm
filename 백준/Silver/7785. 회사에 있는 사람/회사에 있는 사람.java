import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 출근 - list에 저장, 퇴근 - list에서 삭제
        LinkedList<String> list = new LinkedList<>();
        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, Boolean> hashMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            String status = st.nextToken();
            if (status.equals("enter")) {
                hashSet.add(name);
            } else if (status.equals("leave")) {
                hashSet.remove(name);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        hashSet.stream().sorted(Collections.reverseOrder()).forEach(h -> result.add(h));
        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}