package hangman;

public class Word {
    private final String word;
    private final String description;

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    public Word(String word, String description) {
        this.word = word;
        this.description = description;
    }

    @Override
    public String toString() {
        return getWord() + " - " + getDescription();
    }
}
