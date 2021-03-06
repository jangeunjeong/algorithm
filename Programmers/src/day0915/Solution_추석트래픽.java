package day0915;
import java.util.ArrayList;

class Solution_추석트래픽 {
	static class Time {
		int st, ed;

		Time(int st, int ed) {

			this.st = st;
			this.ed = ed;
		}

		boolean check(int t1, int t2) {
			if ((ed < t1) || (st > t2))
				return false;
			return true;
		}
	}

	public static int solution(String[] lines) {
		int answer = 0;
		ArrayList<Time> arr = new ArrayList<>();

		// time table을 만들어보자
		String[] input = new String[3];
		String[] time = new String[3];
		for (int i = 0; i < lines.length; i++) {
			input = lines[i].substring(0, lines[i].length() - 1).split(" ");
			time = input[1].split(":");
			int diff = (int) (Double.parseDouble(input[2]) * 1000); // 캐스팅할 때 어디까지 캐스팅할 지 생각하자!

			// 시간계산을 용이하게 하기 위해 단위를 맞출거야. second로
			// 정확성이 필요한 문제에서 double은 피하는게 좋아 *1000
			// 최대 time은 235959999 > int로 표현 가능
			int end_time = Integer.parseInt(time[0]) * 60 * 60 * 1000;
			end_time += (Integer.parseInt(time[1]) * 60 * 1000);
			end_time += (Double.parseDouble(time[2]) * 1000);
			int start_time = end_time - diff + 1;

			arr.add(new Time(start_time, end_time));
		}

		int size = arr.size();
		int max = 1;
		for (int i = 0; i < size; i++) {
			int t1 = arr.get(i).ed; // 해당 작업이 끝난 시간
			int t2 = t1 + 999; // t1으로부터 1초 후
			int cnt = 1;
			for (int j = i + 1; j < size; j++) {
				if (arr.get(j).check(t1, t2))
					cnt++;
			}
			max = cnt > max ? cnt : max;
		}
		answer = max;

		return answer;
	}

	public static void main(String[] args) {
		String[][] lines = { { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" },
				{ "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" },
				{ "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s",
						"2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
						"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s",
						"2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s",
						"2016-09-15 21:00:02.066 2.62s" } };

		for (int i = 0; i < lines.length; i++) {
			System.out.println(i + " " + solution(lines[i]));
		}
	}
}