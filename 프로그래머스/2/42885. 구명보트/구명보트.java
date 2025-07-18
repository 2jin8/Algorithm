import java.util.*;

// 사용하는 보트의 수를 최대한 적게
// 각 보트에는 최대 2명
// 한 명을 태우고 그 남은 무게를 최대한 꽉 채울수 있는 사람이 타야 함
// 최대 + 최소

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int answer = 0;
        int l = 0, r = people.length - 1;
        while (l < r) {
            if (people[l] + people[r] <= limit) {
                l++; r--;
                answer++;
            } else {
                r--;
                answer++;
            }
        }
        if (l == r) answer++;
        return answer;
    }
}