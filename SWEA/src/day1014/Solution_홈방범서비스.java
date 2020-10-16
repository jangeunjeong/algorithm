package day1014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution_Ȩ������� {

	static int N, M;
	static int[][] arr;

	static class Service {
		int[][] mask;
		int cost;

		public Service(int k) {
			if (k == 0)
				return;
			int size = 2 * k - 1;
			mask = new int[size][size];
			cost = (k * k) + ((k - 1) * (k - 1));

			for (int i = 0; i < size; i++) {
				int st = Math.abs(k - 1 - i);
				for (int j = st; j < size - st; j++) {
					mask[i][j] = 1;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Service> list = new ArrayList<>();
		for (int i = 0; i <= 21; i++) {
			list.add(new Service(i));
		}

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			int N = sc.nextInt();
			int M = sc.nextInt();

			int size = 3 * N;
			arr = new int[size][size];
			int[][] tmp = new int[size][size];

			int home = 0;
			for (int i = N - 1; i < N - 1 + N; i++) {
				for (int j = N - 1; j < N - 1 + N; j++) {
					int in = sc.nextInt();
					arr[i][j] = in;
					if (in > 0)
						home++;
				}
			} // �Է� ��. ����ũ�� ��������. N���� 2���� > ��ã���� ���� 1
			int answer = -1;

			int max_k = (int) ((Math.sqrt(2 * home * M - 1)) + 1) / 2;
			max_k = Math.min(max_k, N + 1);
			for (int k = max_k; k > 1; k--) {

				Service now = list.get(k);
				int st = N + 1 - k;
				for (int m1 = st; m1 < st + N; m1++) {
					for (int m2 = st; m2 < st + N; m2++) { // ���� ����ũ�� �̵���ų�ž�
						// tmp�� arr�� �ʱ�ȭ
						for (int i = 0; i < size; i++) {
							tmp[i] = Arrays.copyOf(arr[i], size);
						}
						// ���Ѱ��� 2���Ǵ� ��ҵ��� ���񽺸� �ް��ִ� ���̾�
						home = 0;
						for (int i = 0; i < k * 2 - 1; i++) { // ���غ���
							for (int j = 0; j < k * 2 - 1; j++) {
								tmp[m1 + i][m2 + j] += now.mask[i][j];
								if (tmp[m1 + i][m2 + j] == 2)
									home++;
							}
						}
						if ((home * M) >= now.cost) { // ���ظ� �Ⱥ��� �ִ밪���� answer�� �����غ���
							answer = Math.max(answer, home);
						}
					}
				}
			}
			answer = Math.max(answer, 1);
			System.out.println("#" + tc + " " + answer);

		}
	}
}