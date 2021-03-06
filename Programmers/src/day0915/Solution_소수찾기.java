package day0915;
import java.util.HashSet;

public class Solution_소수찾기 {
	static char[] arr;
	static int[] result;
	static boolean[] used;
	static int count;
	static HashSet<Integer> set;

	public static boolean isPrime(int num) {
		if (num == 1)
			return false;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public static void make_number(int cnt, int r) {

		if (cnt == r) {
			String str = "";
			for (int i = 0; i < r; i++) {
				str += result[i];
			}
			// 중복되지 않는 이 숫자가 소수인지 판별해보자
			int num = Integer.parseInt(str);
			if (set.add(num)) {
				if (isPrime(num)) {
					// System.out.println(num);
					count++;
				}
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (cnt == 0 && arr[i] == '0')
				continue;
			if (!used[i]) {
				used[i] = true;
				result[cnt] = arr[i] - '0';
				make_number(cnt + 1, r);
				used[i] = false;
			}
		}
	}

	public static int solution(String numbers) {
		int answer = 0;
		// 01이랑 1은 같은거야. 헷갈리니까 애초에 0은 첫자리에 두지 말자
		// 중복도 빼야해..
		arr = numbers.toCharArray();
		set = new HashSet<>();
		count = 0;
		for (int r = 1; r <= numbers.length(); r++) {
			result = new int[r];
			used = new boolean[arr.length];
			// 숫자도 만들고 소수인지 판별까지 해보자
			make_number(0, r);
		}
		answer = count;
		return answer;
	}

	public static void main(String[] args) {
		String[] numbers = { "17", "011" };

		for (int i = 0; i < numbers.length; i++) {
			System.out.println((i + 1) + "번째: " + solution(numbers[i]));
		}
	}

}