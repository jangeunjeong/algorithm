package day1013;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_��ȣ�ʸ� {

	static int D, W, K;
	static int[][] arr, tmp;
	static int[] result;
	static boolean[] check;
	static boolean isEnd;

	public static boolean isPass() {

		int cnt, pre;
		for (int j = 0; j < W; j++) {
			cnt = 1;
			pre = tmp[0][j];
			for (int i = 1; i < D; i++) {
				if (tmp[i][j] == pre) {
					cnt++;
					pre = tmp[i][j];
				} else {
					cnt = 1;
					pre = tmp[i][j];
				}
				if (cnt >= K)
					break;
			}
			if (cnt < K)
				return false;
		}
		return true;
	}

	public static void perm(int st, int cnt, int r) {
		if (isEnd)
			return;
		if (cnt == r) {
			// �����غ��� �հݱ��ؿ� �����ϴ��� �˻��غ���
			for (int i = 0; i < D; i++) {
				tmp[i] = Arrays.copyOf(arr[i], W);
			}

			// A�� ������ B�� �������� �̾������
			for (int i = 0; i < (1 << r); i++) {
				for (int j = 0; j < r; j++) {
					if ((i & (1 << j)) != 0)
						Arrays.fill(tmp[result[j]], 0);
					else
						Arrays.fill(tmp[result[j]], 1);
				}
				if (isPass()) {
					isEnd = true;
					return;
				}
			}
			return;
		}
		for (int i = st + 1; i < D; i++) {

			if (!check[i]) {
				result[cnt] = i;
				check[i] = true;
				perm(i, cnt + 1, r);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();

			arr = new int[D][W];
			tmp = new int[D][W];
			check = new boolean[D];
			result = new int[D];

			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			// �Է� ��, ������ ������
			int answer = 0;
			isEnd = false;
			for (int i = 0; i <= D; i++) {
				Arrays.fill(check, false);
				Arrays.fill(result, -1);
				perm(-1, 0, i);
				if (isEnd) {
					answer = i;
					break;
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}