import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // N마리 중 N/2마리 가져가도 됨
        // 종류에 따라 번호를 붙여 구분함
        // 같은 종류 폰켓몬 == 같은 번호
        // 폰켓몬 종류 구분하기
        HashSet<Integer> kinds = new HashSet<>();
        for (int num : nums) {
            kinds.add(num);
        }
        
        // 폰켓몬의 종류가 n/2마리보다 크다면 최대 n/2마리 가져갈 수 있음
        int max = nums.length / 2;
        if (kinds.size() >= max) 
            return max;
        return kinds.size();
    }
}