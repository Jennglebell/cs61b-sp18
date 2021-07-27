public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            result.addLast(c);
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (wordDeque.removeFirst() != wordDeque.removeLast()) {
            return false;
        }
        return isPalindromeHelper(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
            return false;
        }
        return isPalindromeHelper(wordDeque, cc);
    }

}
