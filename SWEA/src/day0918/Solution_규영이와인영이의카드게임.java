package day0918;

import java.util.Scanner;

public class Solution_규영이와인영이의카드게임 {

	static int[] gyu, in;
	static int[] pick; // 인영이가 고를 수 있는 수 set. 없어도 될 듯. 확인용
	static boolean[] check;
	static int win, lose;

	static void make_set(int cnt, int r, int g_cnt, int i_cnt) {
		if (cnt == r) {
			// 누가 이기는지 확인해보자. 무승부도 있어!
			if (g_cnt > i_cnt)
				win++;
			else if (g_cnt < i_cnt)
				lose++;
			return;
		}

		for (int i = 0; i < pick.length; i++) {
			if (!check[i]) {
				check[i] = true;
				in[cnt] = pick[i];
				if (gyu[cnt] > in[cnt])
					make_set(cnt + 1, r, g_cnt + gyu[cnt] + in[cnt], i_cnt);
				else
					make_set(cnt + 1, r, g_cnt, i_cnt + gyu[cnt] + in[cnt]);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int time = 9;
			win = 0;
			lose = 0;

			gyu = new int[time];
			in = new int[time];
			pick = new int[time];
			check = new boolean[time];

			boolean[] impo = new boolean[19]; // 인영이가 고를 수 있는 수 = 1~18
			for (int i = 0; i < time; i++) {
				gyu[i] = sc.nextInt();
				impo[gyu[i]] = true;
			}

			int idx = 0;
			for (int i = 1; i < impo.length; i++) {
				if (!impo[i])
					pick[idx++] = i;
			}
			// 입력 끝. 인영이 순서를 정해주자
			make_set(0, time, 0, 0);

			System.out.println("#" + tc + " " + win + " " + lose);
		}
	}
}