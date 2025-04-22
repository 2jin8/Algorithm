import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 참여자 기록
        HashMap<String, Integer> participantMap = new HashMap<>();
        for (String p : participant) {
            Integer count = participantMap.get(p);
            participantMap.put(p, count == null? 1 : count + 1);
        }
        
        // 완주자 기록
        for (String c : completion) {
            participantMap.put(c, participantMap.get(c) - 1);
        }
        
        // 완주하지 못한 참여자 찾기 (= HashMap value가 0이 아님)
        String answer = "";
        for (String p : participantMap.keySet()) {
            if (participantMap.get(p) != 0) {
                answer = p;
                break;
            }
        }
        return answer;
    }
}