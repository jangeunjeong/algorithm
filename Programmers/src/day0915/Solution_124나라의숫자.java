package day0915;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_124나라의숫자 {
	public static String solution(int n) {
		String answer = "";
		Queue<Integer> q = new LinkedList<Integer>();

		// 10진법 n을 3진법으로 바꿔보자
		int quo = n, rem = n;
		while (quo > 0) {
			rem = quo % 3;
			quo /= 3;
			q.add(rem);
		}
		StringBuffer sb = new StringBuffer();
		boolean minus = false;
		// 꺼내보자. 0 > 4, -1 > 2로 바꿔주고 그 다음숫자는 1 빼주자
		while (!q.isEmpty()) {
			int num = q.poll();
			if (minus)
				num -= 1;
			if (num > 0) {
				minus = false;
			} else if (!q.isEmpty()) {
				if (num == 0) {
					num = 4;
					minus = true;
				} else {
					num = 2;
					minus = true;
				}
			}
			sb.append(num);
		}
		answer = sb.reverse().toString();
		if (answer.charAt(0) == '0')
			answer = sb.substring(1);

		return answer;
	}

	public static void main(String[] args) {
		/* n은 500,000,000이하의 자연수 입니다. */
		System.out.println(solution(500000000));
		int end = 1;
		for (int i = 1; i <= end; i++) {
			System.out.println(i + ": " + solution(i));
		}
	}
}