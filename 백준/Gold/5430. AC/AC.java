import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 뒤집는 것의 의미: 출력할 때 뒤집기 or 빼는 위치 바꾸기 => flag 사용
        int T = Integer.parseInt(br.readLine());
        loop: for (int i = 0; i < T; i++) {
            String[] func = br.readLine().split("");
            boolean flag = false; // false: 뒤집기 X, true: 뒤집기 O
            int n = Integer.parseInt(br.readLine());
            int start = 0, end = n - 1;
            String[] split = br.readLine().replace("[", "")
                    .replace("]", "").split(",");
            for (int j = 0; j < func.length; j++) {
                if (func[j].equals("R")) { // 뒤집기
                    flag = !flag;
                } else if (func[j].equals("D")) { // 첫 번째 수 버리기
                    if (start > end) { // 배열이 비어있는 경우
                        bw.write("error\n");
                        continue loop;
                    }
                    if (flag) { // 뒤집힌 상태
                        end--;
                    } else {
                        start++;
                    }
                }
            }
            StringBuilder sb = new StringBuilder("[");
            if (flag) {
                for (int j = end; j >= start; j--) {
                    sb.append(split[j]);
                    if (j != start) sb.append(",");
                }
                sb.append("]\n");
            } else {
                for (int j = start; j <= end; j++) {
                    sb.append(split[j]);
                    if (j != end) sb.append(",");
                }
                sb.append("]\n");
            }
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close(); br.close();
    }
}
