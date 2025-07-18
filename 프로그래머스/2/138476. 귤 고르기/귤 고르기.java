import java.util.*;

class Solution {
    // 종류별 귤의 개수 세기
    // PQ에 넣어서 개수가 많은 순대로 정렬
    // 거기서 K개만큼 뽑을 때의 종류 세기 (4개 있음 -> 2개만 선택하는거 가능)
    static HashMap<Integer, Integer> fruitMap = new HashMap<>();
    public int solution(int k, int[] tangerine) {
        for (int fruit : tangerine) {
            fruitMap.put(fruit, fruitMap.getOrDefault(fruit, 0) + 1);
        }
        
        PriorityQueue<Fruit> pq = new PriorityQueue<>();
        for (int fruit : fruitMap.keySet()) {
            pq.offer(new Fruit(fruit, fruitMap.get(fruit)));
        }
        
        int answer = 0, count = 0;
        while (!pq.isEmpty()) {
            Fruit now = pq.poll();
            answer++;
            
            // K개 귤을 담았으면 종료
            count += now.count;
            if (count >= k)
                break;
        }
        return answer;
    }
    
    static class Fruit implements Comparable<Fruit> {
        int kind, count;
        
        public Fruit(int kind, int count) {
            this.kind = kind;
            this.count = count;
        }
        
        @Override
        public int compareTo(Fruit f) {
            return Integer.compare(f.count, this.count);
        }
    }
}