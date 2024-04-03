import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i) + "");
        }

        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) { // 커서 맨 뒤로 옮기기
            iterator.next();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            switch (cmd) {
                case "L": // 왼쪽으로 커서 이동
                    if (iterator.hasPrevious()) { // 이전 요소가 있다면
                        iterator.previous(); // 커서 위치를 왼쪽으로 이동
                    }
                    break;
                case "D": // 오른쪽으로 커서 이동
                    if (iterator.hasNext()) { // 다음 요소가 있다면
                        iterator.next(); // 커서 위치를 오른쪽으로 이동
                    }
                    break;
                case "B": // 왼쪽의 문자 삭제
                    if (iterator.hasPrevious()) { // 왼쪽에 문자가 있다면
                        iterator.previous(); // 커서를 왼쪽으로 이동하고
                        iterator.remove(); // 그 위치의 문자 삭제하기
                    }
                    break;
                case "P": // 왼쪽에 문자 추가
                    String s = st.nextToken();
                    iterator.add(s);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}