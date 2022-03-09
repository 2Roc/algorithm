package class40;

public class Code07_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = 0;
		int dC = 0;
		// A和B一定会共同走到右下角的位置
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		// fromUp = true  斜线打印方向应该从右上走到左下
		// fromUp = false  斜线打印方向应该从左下走到右上
		boolean fromUp = false;
		while (tR != endR + 1) {
			printLevel(matrix, tR, tC, dR, dC, fromUp);
			tR = tC == endC ? tR + 1 : tR;
			tC = tC == endC ? tC : tC + 1;
			dC = dR == endR ? dC + 1 : dC;
			dR = dR == endR ? dR : dR + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	// (a,b)这个位置，一定在左下方
	// (c,d)这个位置，一定在右上方
	// (a,b) -> (c,d)是一条笔直的斜线！
	// up == true, 请你从(a,b)打印到(c,d)，斜线
	// up == false, 请你从(c,d)打印到(a,b)，斜线
	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
		if (f) {
			while (tR != dR + 1) {
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while (dR != tR - 1) {
				System.out.print(m[dR--][dC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{ 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
