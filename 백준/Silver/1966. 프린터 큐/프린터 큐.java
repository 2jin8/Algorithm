import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            ArrayList<Doc> docs = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                docs.add(new Doc(j, Integer.parseInt(st.nextToken())));
            }

            while (!docs.isEmpty()) {
                Doc doc = docs.remove(0);
                if (isFirst(docs, doc.level)) {
                    if (doc.m == m) {
                        bw.write(n - docs.size() + "\n");
                        break;
                    }
                } else {
                    docs.add(doc);
                }
            }
        }
        bw.flush(); bw.close();
    }
    public static boolean isFirst(ArrayList<Doc> docs, int level) {
        for (int i = 0; i < docs.size(); i++) {
            if (docs.get(i).level > level) return false;
        }
        return true;
    }
}

class Doc {
    int m; // 문서의 처음 위치
    int level; // 문서의 중요도

    public Doc(int m, int level) {
        this.m = m;
        this.level = level;
    }
}