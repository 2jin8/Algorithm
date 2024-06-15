import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 제일 늦게 사용하는거 or 이제 사용안하는거 뽑는게 최적일 듯
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Integer> plugList = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            plugList.add(Integer.parseInt(st.nextToken()));
        }

        int offNum = 0;
        HashSet<Integer> multiTab = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int plug = plugList.remove(0);
            if (multiTab.size() < n) { // 멀티탭에 플러그를 꽂을 자리가 남아있는 경우
                multiTab.add(plug);
            } else if (!multiTab.contains(plug)) { // 멀티탭에 해당 플러그가 꽂혀있지 않은 경우
                int removePlug = -1, removeIdx = -1;
                for (int tab : multiTab) {
                    // 해당 플러그가 더이상 사용되지 않는 경우
                    if (!plugList.contains(tab)) {
                        removePlug = tab;
                        break;
                    }
                    // 모든 플러그가 사용된다면 가장 늦게 사용되는 플러그 찾기
                    int idx = plugList.indexOf(tab);
                    if (removeIdx < idx) {
                        removePlug = tab;
                        removeIdx = idx;
                    }
                }
                offNum++;
                multiTab.remove(removePlug);
                multiTab.add(plug);
            }
        }
        System.out.println(offNum);
    }
}