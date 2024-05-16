import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> words = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;

            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>((w1, w2) -> {
            if (w1.count == w2.count) {
                if (w1.word.length() == w2.word.length()) return w1.word.compareTo(w2.word); // 3. 알파벳 사전 순으로
                return w2.word.length() - w1.word.length(); // 2. 단어의 길이가 길수록 앞에 배치
            }
            return w2.count - w1.count; // 1. 자주 나오는 단어일수록 앞에 배치
        });

        for (String word : words.keySet()) {
            pq.offer(new Word(word, words.get(word)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().word).append("\n");
        }
        System.out.println(sb.toString());
    }

    static class Word {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}