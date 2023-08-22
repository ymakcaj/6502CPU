package Main.CPU;

import Main.CPU.Byte;
import Main.CPU. Word;

public class CPU {

	public Word programCounter;
	public Word stackPointer;

	public Byte A;
	public Byte X;
	public Byte Y;

	public Byte C = new Byte(1);
	public Byte Z = new Byte(1);
	public Byte I = new Byte(1);
	public Byte D = new Byte(1);
	public Byte B = new Byte(1);
	public Byte V = new Byte(1);
	public Byte N = new Byte(1);

	void reset() {
		programCounter = 0xFFFC;
		stackPOinter = 0x00FF;
		D = Byte(0);
	}
	
}
