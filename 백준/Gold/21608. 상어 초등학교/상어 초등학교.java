import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    static HashSet<Integer>[] likeList;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] value = {0, 1, 10, 100, 1000};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        likeList = new HashSet[n * n + 1];
        for (int i = 0; i <= n * n; i++) {
            likeList[i] = new HashSet<>();
        }
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int like = Integer.parseInt(st.nextToken());
                likeList[student].add(like);
            }
            if (i == 0) arr[1][1] = student; // 처음 학생은 (1, 1)
            else findSeat(student);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int like = 0, student = arr[i][j];
                for (int k = 0; k < 4; k++) {
                    int ni = i + move[k][0];
                    int nj = j + move[k][1];
                    if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
                        continue;
                    }

                    if (likeList[student].contains(arr[ni][nj])) {
                        like++;
                    }
                }
                total += value[like];
            }
        }
        System.out.println(total);
    }

    static void findSeat(int student) {
        PriorityQueue<Seat> pq = new PriorityQueue<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (arr[i][j] != 0) continue; // 누가 앉은 자리면 넘어가기

                int empty = 0, like = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i + move[k][0];
                    int nj = j + move[k][1];
                    if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
                        continue;
                    }

                    if (arr[ni][nj] == 0) { // 빈자리인 경우
                        empty++;
                    } else if (likeList[student].contains(arr[ni][nj])) { // 자리에 앉은 친구가 좋아하는 친구인 경우
                        like++;
                    }
                }
                pq.offer(new Seat(like, empty, i, j));
            }
        }
        Seat seat = pq.poll();
        arr[seat.r][seat.c] = student;
    }

    static class Seat implements Comparable<Seat> {
        int like; // 좋아하는 학생의 수
        int empty; // 비어있는 칸의 수
        int r, c;

        public Seat(int like, int empty, int r, int c) {
            this.like = like;
            this.empty = empty;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Seat s) {
            if (this.like == s.like) {
                if (this.empty == s.empty) {
                    if (this.r == s.r) return this.c - s.c; // 3.2 행이 같으면 열 기준 오름차순 정렬
                    return this.r - s.r; // 3.1 행 기준 오름차순 정렬
                }
                return s.empty - this.empty; // 2. 비어있는 칸의 수 기준 내림차순 정렬
            }
            return s.like - this.like; // 1. 좋아하는 학생 수 기준 내림차순 정렬
        }
    }
}