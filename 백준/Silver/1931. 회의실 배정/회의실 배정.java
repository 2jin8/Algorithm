import java.io.*;
import java.util.*;

public class Main {
    private static int[][] buff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] ary = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ary[i][0] = a;
            ary[i][1] = b;
        }

        merge_sort(ary, n);

        int fi = ary[0][1]; // 끝나는 시간
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (ary[i][0] == ary[i][1]) {
                fi = ary[i][1];
                count++;
                continue;
            }
            if (ary[i][0] >= fi) {
                fi = ary[i][1];
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush(); bw.close();
        br.close();
    }

    private static void merge_sort(int[][] ary, int n) {
        buff = new int[n][2];
        merge_sort(ary, 0, n - 1);
        buff = null;
    }

    // 두 구역으로 분할
    private static void merge_sort(int[][] ary, int left, int right) {
        if (left == right) return;

        int mid = (left + right) / 2;

        merge_sort(ary, left, mid);
        merge_sort(ary, mid + 1, right);

        merge(ary, left, mid, right);
    }

    // 분할된 구역 오름차순 정렬
    private static void merge(int[][] ary, int left, int mid, int right) {
        int l = left, r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            if (ary[l][1] < ary[r][1]) {
                buff[idx][0] = ary[l][0];
                buff[idx][1] = ary[l][1];
                idx++; l++;
            } else if (ary[l][1] == ary[r][1]) {
                if (ary[l][0] <= ary[r][0]) {
                    buff[idx][0] = ary[l][0];
                    buff[idx][1] = ary[l][1];
                    idx++; l++;
                } else {
                    buff[idx][0] = ary[r][0];
                    buff[idx][1] = ary[r][1];
                    idx++; r++;
                }
            } else {
                buff[idx][0] = ary[r][0];
                buff[idx][1] = ary[r][1];
                idx++; r++;
            }
        }

        if (l > mid) {
            while (r <= right) {
                buff[idx][0] = ary[r][0];
                buff[idx][1] = ary[r][1];
                idx++; r++;
            }
        } else {
            while (l <= mid) {
                buff[idx][0] = ary[l][0];
                buff[idx][1] = ary[l][1];
                idx++; l++;
            }
        }

        for (int i = left; i <= right; i++) {
            ary[i][0] = buff[i][0];
            ary[i][1] = buff[i][1];
        }
    }
}