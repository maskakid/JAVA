package lis;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This is a Longest increasing subsequence implementation.
 * </p>
 * <p>
 * Adapted from <a href=
 * "http://lightoj.com/article_show.php?article=1000&language=english&type=pdf">LightOj
 * LIS Article </a>
 * </p>
 * 
 * @author abnormal
 *
 */
public class LIS {
	private static int INFINITY = Integer.MAX_VALUE;
	private static int NEG_INFINITY = Integer.MIN_VALUE;

	/**
	 * This is O(n<sup>2</sup>) approach.
	 * 
	 * @param seq {@code int[]} original sequence
	 */
	public void lisByN2(int[] seq) {
		System.out.println("LIS By O(n^2)");
		long start = System.currentTimeMillis();
		int size = seq.length;
		int[] L = new int[size];

		for (int i = 0; i < size; i++) {
			L[i] = 1;
		}

		for (int i = 0; i < size; i++) {

			for (int j = i + 1; j < size; j++) {

				if (seq[i] < seq[j]) {
					if (L[j] < L[i] + 1) {
						L[j] = L[i] + 1;
					}
				}

			}

		}

		int maxLengthOfLis = L[0];
		int index = 0;

		for (int i = 1; i < size; i++) {
			if (L[index] < L[i]) {
				maxLengthOfLis = L[i];
				index = i;
			}
		}

		System.out.println("maxLengthOfLis: " + maxLengthOfLis);
		System.out.println("Time Taken: " + (System.currentTimeMillis() - start));

		findSeq(seq, L, index);
	}

	/**
	 * <p>
	 * This is {@code O(n*logn)} approach.
	 * </p>
	 * 
	 * @param seq {@code int[]} original sequence
	 */
	public void lisbyNLogK(int[] seq) {
		System.out.println("\nLIS By O(n*logn)");
		long start = System.currentTimeMillis();
		int[] I = new int[seq.length + 1];
		int[] L = new int[seq.length];

		I[0] = NEG_INFINITY;
		for (int i = 1; i < I.length; i++) {
			I[i] = INFINITY;
		}
		for (int i = 0; i < L.length; i++) {
			L[i] = 1;
		}

		for (int i = 0; i < seq.length; i++) {

			int low = 0;
			int high = I.length - 1;

			while (low <= high) {
				int mid = (low + high) / 2;
				if (I[mid] > seq[i]) {

					high = mid - 1;

				} else {
					low = mid + 1;
				}
			}

			L[i] = low;
			I[low] = seq[i];

		}

		int maxLengthOfLis = L[0];
		int index = 0;

		for (int i = 1; i < seq.length; i++) {
			if (L[index] < L[i]) {
				maxLengthOfLis = L[i];
				index = i;
			}
		}

		System.out.println("maxLengthOfLis: " + maxLengthOfLis);
		System.out.println("Time Taken: " + (System.currentTimeMillis() - start));

		findSeq(seq, L, index);

	}

	/**
	 * <p>
	 * This method prints the LIS sequence. Reverse of LIS sequence is LDS(Longest
	 * decreasing subsequence).
	 * </p>
	 * 
	 * @param seq               {@code int[]} original sequence
	 * @param L                 {@code int[]} L[i] will contain the increasing
	 *                          subsequence length which includes the i-index item
	 * @param lastIndexofLisSeq {@code int}
	 */
	public void findSeq(int[] seq, int[] L, int lastIndexofLisSeq) {

		List<Integer> lisSeq = new ArrayList<Integer>();

		lisSeq.add(seq[lastIndexofLisSeq]);

		for (int i = lastIndexofLisSeq - 1; i >= 0; i--) {
			if (seq[i] < seq[lastIndexofLisSeq] && L[i] == L[lastIndexofLisSeq] - 1) {
				lisSeq.add(seq[i]);
				lastIndexofLisSeq = i;
			}

		}

		System.out.println("LDS:");
		for (int i = 0; i < lisSeq.size(); i++) {
			System.out.print(lisSeq.get(i) + "  ");
		}

		System.out.println("\nLIS:");
		for (int i = lisSeq.size() - 1; i >= 0; i--) {
			System.out.print(lisSeq.get(i) + "  ");
		}

	}

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int[] seq = new int[] { 8, 1, 9, 8, 3, 4, 6, 1, 5, 2 };

		LIS lis = new LIS();

		lis.lisByN2(seq);

		lis.lisbyNLogK(seq);

	}

}
