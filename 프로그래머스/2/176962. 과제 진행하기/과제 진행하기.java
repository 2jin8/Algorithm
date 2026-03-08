import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        // 큐: 새로운 과제 목록, 스택: 진행 중인 과제 목록
        PriorityQueue<Homework> pq = new PriorityQueue<>((h1, h2) -> 
                                                        Integer.compare(h1.startTime, h2.startTime));
        Deque<Homework> stack = new ArrayDeque<>(); 
         
        for (String[] plan : plans) {
            String[] start = plan[1].split(":");
            int startTime = Integer.valueOf(start[0]) * 60 + Integer.valueOf(start[1]);
            
            pq.offer(new Homework(plan[0], startTime, Integer.valueOf(plan[2])));
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            // 새로운 과제 선택
            Homework hw = pq.poll();
            int time = hw.startTime;
            
            // 다음 과제 시작 전까지 현재 과제하기
            while (!pq.isEmpty() && time < pq.peek().startTime && hw.playTime > 0) {
                time++;
                hw.playTime--;
            }     
            
            // 현재 과제 끝낸 경우
            if (hw.playTime == 0) {
                answer[idx++] = hw.name;
                
                // 진행 중이던 과제 시작 (새로운 과제 시작 전까지)
                while (!stack.isEmpty()) {
                    Homework stacked = stack.pop();
                    while (!pq.isEmpty() && time < pq.peek().startTime && stacked.playTime > 0) {
                        time++;
                        stacked.playTime--;
                    }
                    
                    if (stacked.playTime == 0) {
                        answer[idx++] = stacked.name;
                    } else {
                        stack.push(stacked);
                        break;
                    }
                }
                
            } else { // 다음 과제 시작 전까지 못 끝낸 경우
                stack.push(hw); // 스택에 저장
            }
        }
     
        // 스택에 남은 진행 중 과제 다 끝내기
        while (!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }
        
        return answer;
    }
    
    static class Homework {
        String name;
        int startTime, playTime;
        
        public Homework(String name, int startTime, int playTime) {
            this.name = name;
            this.startTime = startTime;
            this.playTime = playTime;
        }
    }
}
