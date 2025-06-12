import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static char[] chars, answer;
    static int L, C;
    static boolean[] used;
    static HashSet<String> keyList = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 서로 다른 L개의 알파벳 소문자로 구성
        // 최소 한개의 모음(aeiou), 최소 두 개의 자음
        // 알파벳은 증가하는 순서로 배열
        // abc (o) bac (x)

        // c개의 문자들이 주어졌을 때 가능성 있는 암호들 모두 구하기

        // 문자들 정렬하고 순열?

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);

        answer = new char[L];
        used = new boolean[C];
        makeKey(0, 0, 0, 0);
        sb.setLength(0);
        keyList.stream().sorted().forEach(key -> sb.append(key).append("\n"));
        System.out.println(sb);
    }

    static void makeKey(int depth, int start, int countA, int countB) { // countA: 모음의 개수, countB: 자음의 개수
        if (depth == L) {
            // 모음이나 자음 개수가 맞지 않으면 가능성있는 암호 X
            if (countA < 1 || countB < 2) return;

            sb.setLength(0);
            for (char c : answer) {
                sb.append(c);
            }
            keyList.add(sb.toString());
            return;
        }

        for (int i = start; i < C; i++) {
            answer[depth] = chars[i];
            // 모음, 자음의 개수 구하기
            if (check(chars[i])) makeKey(depth + 1, i + 1, countA + 1, countB);
            else makeKey(depth + 1, i + 1, countA, countB + 1);

        }
    }

    static boolean check(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
