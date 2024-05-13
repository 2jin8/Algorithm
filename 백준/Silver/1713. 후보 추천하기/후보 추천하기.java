import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 사진틀의 개수
        M = Integer.parseInt(br.readLine()); // 전체 학생의 총 추천 횟수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        HashMap<Integer, Recommend> pictures = new HashMap<>();
        for (int i = 0; i < M; i++) {
            int recommendStudent = Integer.parseInt(st.nextToken());
            if (pictures.containsKey(recommendStudent)) { // 이미 사진이 게시된 경우
                // 그냥 추천 횟수 1 증가해서 넣으면 됨
                Recommend rc = pictures.get(recommendStudent);
                pictures.put(recommendStudent, new Recommend(recommendStudent, rc.idx, rc.cnt + 1));
            } else { // 사진이 게시되지 않은 경우
                // 만약 현재 게시된 사진의 개수가 사진틀의 개수와 같다면, 하나를 빼야 함
                if (pictures.size() == N) {
                    // 우선순위: 추천 횟수가 가장 적은 사진 → 가장 오래 게시된 사진
                    PriorityQueue<Recommend> pq = new PriorityQueue<>();
                    for (int student : pictures.keySet()) {
                        Recommend rc = pictures.get(student);
                        pq.offer(new Recommend(student, rc.idx, rc.cnt));
                    }
                    int removeStudent = pq.poll().student;
                    pictures.remove(removeStudent);
                }
                // 새로운 학생의 사진 게시하기
                pictures.put(recommendStudent, new Recommend(recommendStudent, i, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        pictures.keySet().stream().sorted().forEach(student -> sb.append(student).append(" "));
        System.out.println(sb.toString());
    }

    static class Recommend implements Comparable<Recommend> {
        int student; // 학생 번호
        int idx; // 게시 순서
        int cnt; // 추천 횟수

        public Recommend(int student, int idx, int cnt) {
            this.student = student;
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Recommend r) {
            // 추천 횟수가 같으면, 가장 오래된 사진 삭제
            if (this.cnt == r.cnt) return this.idx - r.idx;
            return this.cnt - r.cnt; // 그렇지 않으면, 추천 횟수가 제일 적은 학생 삭제
        }
    }
}