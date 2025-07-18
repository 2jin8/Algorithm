import java.util.*;

class Solution {
    
    static int N;
    static HashSet<String> used = new HashSet<>();
    static HashSet<String> wordSet = new HashSet<>();
    public int solution(String begin, String target, String[] words) {
        N = begin.length();
        for (String word : words) {
            wordSet.add(word);
        }
        
        // 바꿀 수 있는 단어 집합에 target이 들어있지 않으면 변환 X
        if (!wordSet.contains(target))
            return 0;
        
        return bfs(begin, target);
    }
    
    static int bfs(String begin, String target) {
        Queue<Change> queue = new ArrayDeque<>();
        queue.offer(new Change(begin, 0));
        used.add(begin);
        
        char[] targets = target.toCharArray();
        while (!queue.isEmpty()) {
            Change now = queue.poll();
            String word = now.word;
            System.out.println("word=" + word);
            if (word.equals(target))
                return now.count;
            
            for (int i=0;i<word.length();i++) {
                char[] words = word.toCharArray();
                if (words[i] == targets[i]) continue;
                
                // 알파벳 숫자 교체하기
                for (int j=0;j<26;j++) {
                    words[i] = (char) (j + 'a');
                    String newWord = String.valueOf(words);
                    
                    // 바꿨던 문자열 아님 & 단어 집합에 들어있으면 큐에 넣기
                    if (!used.contains(newWord) && wordSet.contains(newWord)) {
                        queue.offer(new Change(newWord, now.count + 1));
                        used.add(newWord);
                    }
                }
            }
        }
        return 0;
    }
    
    static class Change {
        String word;
        int count;
        
        public Change(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}