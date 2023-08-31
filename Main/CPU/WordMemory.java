
package Main.CPU;

public class WordMemory {
	public short Word;

	public WordMemory(short Word) {
		this.Word = Word;
	}
	 /*
	  add a constructor that will be the primary one used.

	  We're dealing with primative datatypes - but java only allows us to initialise
	  an int - so this constructor has a @param of int, and type casts it to short. 
	 */

	public WordMemory(int wordInput) { 
		this.Word = (short) wordInput;
	}

	/* adding some setters */

	public void setWord(short newShort) {
		this.Word = newShort;
	}
	
	public void setWord(int newInt) {
		this.Word = (short) newInt;
	}

	

	/* now adding a getter */
	public short getWord() {
		return this.Word;
	}
}

