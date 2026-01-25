import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, Person> people = new HashMap<>();
        int[] answer = new int[enroll.length];
        
        // 참여자 목록 저장
        people.put("-", new Person("center"));
        for (int i=0; i<enroll.length; i++) {
            people.put(enroll[i], new Person(enroll[i]));
        }
     
        // 추천인 정보 저장
        for (int i=0; i<referral.length; i++) {
            people.get(enroll[i]).parent = people.get(referral[i]);
        }
        
        // 이익 더하기
        for (int i=0; i<seller.length; i++) {
            // 칫솔 1개당 100원
            addProfit(people.get(seller[i]), amount[i] * 100);
        }
        
        // answer에 이익 저장
        for (int i=0; i<enroll.length; i++) {
            answer[i] = people.get(enroll[i]).profit;
        }
        return answer;
    }
    
    static void addProfit(Person person, int profit) {
        int pay = profit / 10;
        if (pay != 0 && person.parent != null) {
            person.profit += profit - pay;
            addProfit(person.parent, pay);
        } else {
            person.profit += profit;
        }
    }
    
    static class Person {
        String name;
        Person parent;
        int profit;
        
        public Person(String name) {
            this.name = name;
            this.parent = null;
            this.profit = 0;
        }
    }
}