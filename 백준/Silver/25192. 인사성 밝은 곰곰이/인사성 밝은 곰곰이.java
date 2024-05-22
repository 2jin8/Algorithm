import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<String> hashSet = new HashSet<>();
        int total = 0;
        for (int i = 0; i < n; i++) {
            String talk = br.readLine();
            if (talk.equals("ENTER")) { // 새로운 사람의 입장이라면
                total += hashSet.size(); // 현재까지 인사한 사람의 수 저장
                hashSet.clear(); // hashSet 초기화
            } else { // 그렇지 않다면
                hashSet.add(talk); // 인사를 한 것이므로 hashSet 저장
            }
        }
        total += hashSet.size();
        System.out.println(total);
    }
}