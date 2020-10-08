package day1008;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solution_줄기세포배양 {

	static class Data implements Comparable<Data> {
		int x, y, vita, state, remain;

		public Data(int x, int y, int vita) {
			this.x = x;
			this.y = y;
			this.vita = vita;
			this.state = 1; // 1: 비활성, 0: 활성, -1: 죽음
			this.remain = vita;
		}

		public void setState(int state) {
			this.state = state;
		}

		public void setRemain(int remain) {
			this.remain = remain;
		}

		@Override
		public int compareTo(Data o) {
			return o.vita - this.vita;
		}

	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return (x + " " + y).hashCode();
		}

		@Override
		public boolean equals(Object obj) {

			Point p = (Point) obj;
			return (this.x == p.x) && (this.y == p.y);
		}

	}

	public static void main(String[] args) {
		List<Data> list = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>(); // 죽은세포들은 한번에 삭제할거야. 임시저장소
		HashSet<Point> set = new HashSet<>(); // 중복제거용

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();

			list.clear();
			set.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int vita = sc.nextInt();
					if (vita > 0) {
						if (set.add(new Point(i, j)))
							list.add(new Data(i, j, vita));
					}
				}
			}
			// 입력끝!

			int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int time = 0; time < K; time++) {

				Collections.sort(list);
				int size = list.size();
				for (int idx = 0; idx < size; idx++) {

					Data now = list.get(idx);
					now.setRemain(now.remain - 1);

					if (now.state == 0) {// 번식하자
						for (int d = 0; d < 4; d++) {
							int nx = now.x + dir[d][0];
							int ny = now.y + dir[d][1];

							if (set.add(new Point(nx, ny))) {
								list.add(new Data(nx, ny, now.vita));
							}
						}

					}

					// 활성화된 후 +1시간 후에 번식하기때문
					if (now.remain == 0) {
						now.setRemain(now.vita * now.state);
						now.setState(now.state - 1);
					}

					if (now.state == -1) {// 죽음. 한번에 삭제해야지. 왜냐하면 for 인덱스는 계속커져 이럴거면 사이즈로 안돌렸지
						tmp.add(idx);
					}
				}
				Collections.reverse(tmp);// 뒤에서부터 삭제해야해. 앞에서지우면 인덱스 바뀌니깐
				while (!tmp.isEmpty()) {
					int idx = tmp.remove(0);
					list.remove(idx);
				}
			}
			int answer = list.size();
			System.out.println("#" + tc + " " + answer);
		}
	}
}
