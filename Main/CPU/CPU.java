
package Main.CPU;

import Main.CPU.Byte;
import Main.CPU. Word;

public class CPU {

	public Word programCounter;
	public Word stackPointer;

	// here we create the CPU's registers
	
	public Byte A; 
	public Byte X;
	public Byte Y;

	
	// below we create the status flags
	public Byte C = new Byte(1);
	public Byte Z = new Byte(1);
	public Byte I = new Byte(1);
	public Byte D = new Byte(1);
	public Byte B = new Byte(1);
	public Byte V = new Byte(1);
	public Byte N = new Byte(1);

	/**
	   this method reset is to reset the CPU, 
	 */
	
	void reset() {
		programCounter = Word(0xFFFC);
		stackPointer = Word(0x00FF);
		D = Byte(0);
	}
	
}
