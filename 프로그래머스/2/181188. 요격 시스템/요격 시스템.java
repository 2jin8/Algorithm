import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // 종료 시간 기준 정렬
        Arrays.sort(targets, (t1, t2) -> {
            if (t1[1] == t2[1])
                return Integer.compare(t1[0], t2[0]);
            return Integer.compare(t1[1], t2[1]);
        });

        // 편의상 (s, e) 중에서 s는 포함, e는 포함하지 않는다고 가정
        int answer = 1, end = targets[0][1]-1; // e는 포함하지 않으니 1 빼서 저장
        for (int i=0; i<targets.length; i++) {
            // s <= end < e 인 경우, 새로운 미사일 추가할 필요 X (이미 있는걸로 맞춰짐)
            if (targets[i][0] <= end && end < targets[i][1]) {
                continue;
            } else { // 그렇지 않으면 새로운 미사일 추가해야 함
                answer++;
                end = targets[i][1] - 1; // 미사일 쏘는 위치도 새로 갱신해야 함
            }
        }
        return answer;
    }
}

/*

a 나라: x축
b 나라: y축
x 좌표가 주어질 때 모든 미사일 .. 

그니까 다 한번씩 거치게 뚫는 최소 수를 구해라 이거네

*/
