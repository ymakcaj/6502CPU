package Main.CPU;

public class Word {
	public short Word;

	public Word(short Word) {
		this.Word = Word;
	}

	public Word(int Word) {
		Integer newWord = Integer.valueOf(Word);
		this.Word = newWord.shortValue();
	}
}
