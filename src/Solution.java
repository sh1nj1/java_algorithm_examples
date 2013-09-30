
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

	public int findFirstCoveringPrefix(int[] A) {
		int coveringPrefix = 0;
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		for (int i = 0, l = A.length; i < l; ++i) {
			int a = A[i];
			if (map.containsKey(a)) {
				continue;
			} else {
				map.put(a, null);
				coveringPrefix = i;
			}
		}
		return coveringPrefix;
	}

	int findNumIntersects(int A[]) {
		final int limit = 10000000;
		int numIntersects = 0;
		int length = A.length;
		class Bound implements Comparable<Bound> {
			int left;
			int right;

			Bound(int start, int end) {
				this.left = start;
				this.right = end;
			}

			@Override
			public int compareTo(Bound o) {
				return left - o.left;
			}

			int rank(int key, List<Bound> b) {
				int lo = 0;
				int hi = b.size() - 1;
				while (lo <= hi) {
					// Key is in a[lo..hi] or not present.
					int mid = lo + (hi - lo) / 2;
					if (key < b.get(mid).left) {
						hi = mid - 1;
					} else if (key >= b.get(mid).left) {
						lo = mid + 1;
					}
					// else return mid;
				}
				return hi;
			}

			@Override
			public String toString() {
				return "{" + this.left + "," + this.right + "}";
			}

		}
		;
		List<Bound> bounds = new ArrayList<Bound>(length);
		for (int i = 0; i < length; ++i) {
			Bound b = new Bound(Math.max(i - A[i], 0), Math.min(i + A[i], length - 1));
			bounds.add(b);
		}
		Collections.sort(bounds);
		// System.out.println(bounds);
		for (int i = 0; i < length; ++i) {
			Bound bound = bounds.get(i);
			int index = bound.rank(bound.right, bounds);
			// System.out.println("index=" +index);
			if (index >= 0) {
				numIntersects += index - i;
				if (numIntersects > limit) {
					return -1;
				}
			}

		}
		return numIntersects;
	}

	public int findPalindrome(String S) {
		class Bound {
			java.util.List<Integer> points = new java.util.ArrayList<Integer>();

			Bound add(int p) {
				points.add(p);
				return this;
			}
		}
		int numPalindrome = 0;
		java.util.Map<Character, Bound> map = new java.util.HashMap<Character, Bound>();

		int len = S.length();
		if (len > 20000)
			return -1;
		int half = (int) (len / 2 + 0.5);
		for (int i = 0; i < len; ++i) {
			char ch = S.charAt(i);
			int odd = 0;
			for (int j = 1; j <= half; ++j) {
				int back = i - j;
				int forward = i + j + odd;
				if (back < 0)
					break;
				if (forward >= len)
					break;
				char a = S.charAt(back);
				char b = S.charAt(forward);
				System.out.println(ch + ", " + a + "," + b + ", " + i + ", " + j + ", " + odd + ", " + numPalindrome);
				// if (ch == a) ++ numPalindrome;
				if (ch == b && j == 1) {
					++numPalindrome;
					++odd;
					if (forward + 1 >= len)
						break;
					b = S.charAt(forward + 1);
					System.out.println("nn=" + numPalindrome);
				}
				if (a == b) {
					System.out.println("n=" + numPalindrome);
					++numPalindrome;
				} else {
					break;
				}
			}
		}
		return numPalindrome;
	}

	public int sumOfFactorial(int N) {
		long factorial = 1;
		for (long i = 1; i <= N; ++i) {
			System.out.println(i);
			factorial *= i;
		}
		int sum = 0;
		String digit = String.valueOf(factorial);
		for (int i = 0, l = digit.length(); i < l; ++i) {
			char ch = digit.charAt(i);
			sum += (ch - '0');
		}
		return sum;
	}
}
