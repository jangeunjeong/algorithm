package day0915;
import java.util.LinkedList;
import java.util.Queue;

class Solution_카카오프렌즈컬러링북 {
	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		int[] answer = new int[2];
		boolean[][] visit = new boolean[m][n];

		// 시작점을 정해보자
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j] && picture[i][j] > 0) {
					int tmp = bfs(m, n, i, j, picture, visit);
					numberOfArea++;
					maxSizeOfOneArea = tmp > maxSizeOfOneArea ? tmp : maxSizeOfOneArea;
				}
			}
		}

		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;

		return answer;
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int bfs(int m, int n, int x, int y, int[][] picture, boolean[][] visit) {
		Queue<Point> q = new LinkedList<>();
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

		q.add(new Point(x, y));
		visit[x][y] = true;
		int num = picture[x][y];
		int cnt = 0;

		while (!q.isEmpty()) {
			Point now = q.poll();
			int now_x = now.x;
			int now_y = now.y;
			cnt++;
			for (int d = 0; d < 4; d++) {
				int nx = now_x + dir[d][0];
				int ny = now_y + dir[d][1];

				if (nx < m && nx >= 0 && ny < n && ny >= 0 && !visit[nx][ny] && picture[nx][ny] == num) {
					visit[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}

		}

		return cnt;
	}

	public static void main(String[] args) {
		int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
				{ 0, 0, 0, 3 } };

		int[] answer = solution(6, 4, picture);

		System.out.println(answer[0] + " " + answer[1]);
	}
}