package day0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_수지의수지맞는여행 {

	static int R, C;
	static char[][] map;
	static boolean[] visit;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int max;

	static void dfs(int x, int y, int sum) {

		if (max >= 26)
			return;

		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];

			if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visit[map[nx][ny] - 'A']) {
				visit[map[nx][ny] - 'A'] = true;
				dfs(nx, ny, sum + 1);
				visit[map[nx][ny] - 'A'] = false;
			}
			max = max < sum ? sum : max;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			R = Integer.parseInt(input[0]);
			C = Integer.parseInt(input[1]);
			map = new char[R][C];
			visit = new boolean[26];
			max = -1;
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}

			visit[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);

			System.out.println("#" + tc + " " + max);
		}

	}
}