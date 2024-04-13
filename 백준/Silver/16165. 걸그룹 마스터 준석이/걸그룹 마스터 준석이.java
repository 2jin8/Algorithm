import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String> names = new HashMap<>(); // 이름, 그룹
        HashMap<String, List<String>> groups = new HashMap<>(); // 그룹, 인원

        for (int i = 0; i < N; i++) {
            String group = br.readLine(); // 팀의 이름
            int members = Integer.parseInt(br.readLine()); // 인원 수
            List<String> list = new ArrayList<>();
            for (int j = 0; j < members; j++) {
                String member = br.readLine();
                names.put(member, group);
                list.add(member);
            }
            Collections.sort(list);
            groups.put(group, list);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String value = br.readLine();
            int cmd = Integer.parseInt(br.readLine());
            if (cmd == 0) { // 팀의 이름 주어짐 > 해당 팀에 속한 멤버 이름 출력하기
                List<String> members = groups.get(value);
                for (String member : members) {
                    sb.append(member).append("\n");
                }
            } else {
                sb.append(names.get(value)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}