import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 9:17
 * @Version 1.0
 */
public class Palindrome {
    /**
     * Given a String, wordToDeque should return a Deque
     * where the characters appear in the same order as in the String.
     * For example, if the word is “persiflage”, then the returned Deque
     * should have ‘p’ at the front, followed by ‘e’, and so forth
     * <p>
     * I return a LinkListDeque in this implementation，it works
     * the same if you use ArrayDeque.
     */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        if (word == null || word.length() == 0) {
            return deque;
        }
        int length = word.length();
        for (int i = 0; i < length; i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    public boolean isPalindrome(String word) {

        /*iteration*/
        Deque<Character> deque = wordToDeque(word);
        /*Note:
         * 1 removeFirst and removeLast only take constant time.
         * 2 Objects.equals is much safer since it can prevent null pointer exception happening.
         * though in our case it won't happen.
         * */
        while (deque.size() > 1) {
            if (!Objects.equals(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;

        /*recursion*/
        // Deque<Character> deque = wordToDeque(word);
        // return isPalindrome(deque);

    }

    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        return Objects.equals(deque.removeFirst(), deque.removeLast()) && isPalindrome(deque);
    }

    /**
     * Here I don't use Deque to implement this method
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() == 0) {
            return true;
        }
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
