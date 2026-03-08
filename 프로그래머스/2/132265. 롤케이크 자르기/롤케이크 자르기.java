import java.util.*;

class Solution {
    public int solution(int[] topping) {
        HashSet<Integer> set = new HashSet<>(); // 왼쪽 (토핑 종류)
        HashMap<Integer, Integer> map = new HashMap<>(); // 오른쪽 (토핑 종류 & 개수)
        
        // 토핑 종류 & 개수 저장
        for (int t : topping) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        // 인덱스 왼쪽에서 오른쪽으로 이동하면서 방법의 수 찾기
        int answer = 0;
        for (int i=0; i<topping.length; i++) {
            int target = topping[i]; // 이제 왼쪽에 포함됨
            set.add(target);
            int newCnt = map.get(target) - 1; // 개수 1개 감소
            if (newCnt == 0) map.remove(target); // 오른쪽에 target 종류가 없으면 제거
            else map.put(target, newCnt); // 아직 있으면 개수 조정 후 다시 넣기
            
            // 왼쪽, 오른쪽 토핑 종류 개수가 같은 경우
            if (set.size() == map.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}

// 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것
// 조각의 크기 & 토핑 개수 상관X

// 토핑의 가짓수가 중요함!!!
