import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        // 전체 전화번호 저장
        HashSet<String> hashSet = new HashSet<>();
        for (String phone : phone_book) {
            hashSet.add(phone);
        }
        
        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                String prefix = phone.substring(0, i);
                if (hashSet.contains(prefix)) return false;
            }
        }
        return true;
    }
}