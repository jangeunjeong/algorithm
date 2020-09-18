package day0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_오나의여신님 {

	static int N, M;
	static char[][] map;
	static boolean[][] d_visit, s_visit;
	static Queue<Point> dq, sq;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static class Point {
		int x, y, cnt;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	/*
	 * 전체 지도에서 ‘S’와 ‘D’는 정확히 한 번 나타난다. > 악마는 많을수도 있나...?
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);

			map = new char[N][M];
			d_visit = new boolean[N][M];
			s_visit = new boolean[N][M];
			dq = new LinkedList<Solution_오나의여신님.Point>();
			sq = new LinkedList<Solution_오나의여신님.Point>();

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '*') {
						dq.add(new Point(i, j));
						d_visit[i][j] = true;
					} else if (map[i][j] == 'S') {
						sq.add(new Point(i, j, 0));
						s_visit[i][j] = true;
					}
				}
			}

			int ans = -1;
			// 입력 끝, bfs 돌아보자, 악마부터
			while (!sq.isEmpty()) {
				// 수연이 이동해보자
				int size = sq.size();
				for (int s = 0; s < size; s++) {
					Point now = sq.poll();

					// 원래는 길이었는데요, 지금은 아닙니다, 악마 때문에 갈 수 없는 길이면 더 탐색하지 말자
					if (map[now.x][now.y] == '*')
						continue;

					for (int d = 0; d < 4; d++) {
						int nx = now.x + dir[d][0];
						int ny = now.y + dir[d][1];

						if (nx >= 0 && nx < N && ny >= 0 && ny < M && !s_visit[nx][ny] && map[nx][ny] != 'X'
								&& map[nx][ny] != '*') {
							// 여신 만나면 끝, 반복문은 전부 빠져나가야 해. 아니면 다음을 탐색하니까!
							if (map[nx][ny] == 'D') {
								ans = now.cnt + 1;
								break;
							}
							s_visit[nx][ny] = true;
							map[nx][ny] = 'S';
							sq.add(new Point(nx, ny, now.cnt + 1));

						}
					}
					if (ans > 0)
						break;
				}
				if (ans > 0)
					break;
				// 악마도 이동해보자
				size = dq.size();
				for (int s = 0; s < size; s++) {
					Point now = dq.poll();

					for (int d = 0; d < 4; d++) {
						int nx = now.x + dir[d][0];
						int ny = now.y + dir[d][1];

						if (nx >= 0 && nx < N && ny >= 0 && ny < M && !d_visit[nx][ny] && map[nx][ny] != 'D'
								&& map[nx][ny] != 'X') {
							d_visit[nx][ny] = true;
							map[nx][ny] = '*';
							dq.add(new Point(nx, ny));
						}
					}
				}
			}
			System.out.println("#" + tc + " " + (ans < 0 ? "GAME OVER" : ans));
		}
	}
}
