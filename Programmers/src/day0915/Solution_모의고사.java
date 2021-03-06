package day0915;
class Solution_모의고사 {
	public static int[] solution(int[] answers) {
		int[] answer = {};

		int[] fir_arr = { 1, 2, 3, 4, 5 };
		int[] sec_arr = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] thir_arr = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

		int[] score = new int[3];
		// 채점해보자
		for (int idx = 0; idx < answers.length; idx++) {
			int ans = answers[idx];
			if (fir_arr[idx % 5] == ans)
				score[0]++;

			if (sec_arr[idx % 8] == ans)
				score[1]++;

			if (thir_arr[idx % 10] == ans)
				score[2]++;
		}

		// 점수를 비교해보자
		// 최고점이 몇점인지 알아보자
		int max = 0;
		for (int i = 0; i < 3; i++) {
			max = score[i] > max ? score[i] : max;
		}
		// max점수와 같은 친구를 정답 배열에 넣어주자
		int[] tmp = new int[3];
		int idx = 0;
		for (int i = 0; i < 3; i++) {
			if (max == score[i])
				tmp[idx++] = i + 1;
		}
		answer = new int[idx];
		for (int i = 0; i < idx; i++) {
			answer[i] = tmp[i];
		}

		return answer;
	}

	public static void main(String[] args) {
		int[][] answers = { { 1, 2, 3, 4, 5 }, { 1, 3, 2, 4, 2 } };
		int[] answer = {};
		for (int i = 0; i < answers.length; i++) {
			answer = solution(answers[i]);
			for (int j = 0; j < answer.length; j++) {
				System.out.print(answer[j] + " ");
			}
			System.out.println();
		}
	}
}