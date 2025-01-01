import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사진틀의 개수
		int M = Integer.parseInt(br.readLine()); // 추천 횟수

		HashMap<Integer, int[]> pictures = new HashMap<>(); // [0]: 추천수, [1]: 게시 순서
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int student = Integer.parseInt(st.nextToken());
			// 이미 게시된 경우
			if (pictures.containsKey(student)) {
				int[] picture = pictures.get(student);
				pictures.put(student, new int[] { picture[0] + 1, picture[1] });
				continue;
			}

			// 게시되지 않은 경우
			// 비어있는 사진틀이 있는 경우
			if (pictures.size() < N) {
				pictures.put(student, new int[] { 1, i });
				continue;
			}

			// 비어있는 사진틀이 없는 경우
			// 사진 삭제하기
			int minCount = 1001, minStudent = -1, minOrder = 1001;
			for (int s : pictures.keySet()) {
				int[] picture = pictures.get(s);
				// 추천 횟수가 가장 적은 학생의 사진 삭제
				// 추천 횟수가 같다면 가장 오래 게시된 사진 삭제
				if ((picture[0] < minCount) || (picture[0] == minCount && picture[1] < minOrder)) {
					minCount = picture[0];
					minOrder = picture[1];
					minStudent = s;
				}
			}
			pictures.remove(minStudent);

			// 새로운 학생 사진 게시하기
			pictures.put(student, new int[] { 1, i });
		}
		StringBuilder sb = new StringBuilder();
		pictures.keySet().stream().sorted().forEach(s -> sb.append(s).append(" "));
		System.out.println(sb);
	}
}