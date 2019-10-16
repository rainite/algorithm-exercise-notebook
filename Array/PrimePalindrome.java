// 这么做在lc上会超时， 但是isPrime 和 isPalindrome应该背下来
class Solution {
    public int primePalindrome(int N) {
        if (N == 1 || N == 2) return 2;
        if (N % 2 == 0) N++;
        while (true) {
            if (isPalindrome(N) && isPrime(N)) return N;
            N += 2;
        }
    }
    // 只做一半check
    private boolean isPalindrome(int n) {
        if (n % 10 == 0 && n != 0) return false;
        int n1 = 0;
        while (n > n1) {
            n1 = n1 * 10 + (n % 10);
            n /= 10;
        }
        return n1 == n || n == n1 / 10;
    }
    // 事先检查了偶数，是偶数直接跳过。这里i步进为2
    private boolean isPrime(int n) {
        int end = (int)Math.sqrt(n);
        for (int i = 3; i <= end; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}