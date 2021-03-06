package day0915;
import java.util.HashSet;

public class Solution_스킬트리 {

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		HashSet<Character> set = new HashSet<>();
		// 해시랑 배열에 skill을 넣어보자
		char[] arr = skill.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		// 비교해보자
		for (int i = 0; i < skill_trees.length; i++) {
			int idx = 0;
			boolean isTrue = true;
			String st = skill_trees[i];
			for (int j = 0; j < st.length(); j++) {
				char now = st.charAt(j);
				if (set.contains(now)) {
					if (now == arr[idx]) {
						idx++;
					} else {
						isTrue = false;
						break;
					}
				}
			}
			if (isTrue)
				answer++;
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] arr = { "BACDE", "CBADF", "AECB", "BDA" };
		System.out.println(solution("CBD", arr));
	}
}