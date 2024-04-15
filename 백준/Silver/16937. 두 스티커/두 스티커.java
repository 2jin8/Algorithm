import java.util.*;
import java.io.*;

public class Main {
    static int H, W, N;
    static int ans = 0;
    static Sticker[] stickers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        stickers = new Sticker[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            stickers[i] = new Sticker(h, w);
        }
        oneSticker();
        System.out.println(ans);
    }

    public static void oneSticker() {
        for (int i = 0; i < N; i++) {
            Sticker sticker = stickers[i];
            int h = sticker.h, w = sticker.w;
            // 원래 모양대로 붙이기
            if (h <= H && w <= W) { // 해당 스티커를 붙일 수 있는 경우
                twoSticker(H - h, W - w, i + 1, h * w);
            }

            // 90도 회전해서 붙이기
            if (h <= W && w <= H) { // 해당 스티커를 붙일 수 있는 경우
                twoSticker(H - w, W - h, i + 1, h * w);
            }
        }
    }

    public static void twoSticker(int newH, int newW, int idx, int size) {
        for (int i = idx; i < N; i++) {
            Sticker sticker = stickers[i];
            int h = sticker.h, w = sticker.w;
            // 원래 모양대로 붙이기
            if ((h <= newH && w <= W) || (h <= H && w <= newW)) {
                ans = Math.max(ans, size + h * w);
            }

            // 90도 회전해서 붙이기
            h = sticker.w;
            w = sticker.h;
            if ((h <= newH && w <= W) || (h <= H && w <= newW)) {
                ans = Math.max(ans, size + h * w);
            }
        }

    }


    static class Sticker {
        int h, w;

        public Sticker(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }
}