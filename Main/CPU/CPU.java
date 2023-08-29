
package Main.CPU;

import Main.CPU.Memory;
import Main.CPU.WordMemory;
import Main.CPU.ByteMemory;

public class CPU {

	public WordMemory programCounter;
	public WordMemory stackPointer;
	public Memory mem;
	static final byte INS_LDA_IM = (byte) 0xA9;

	// here we create the CPU's registers
	
	public ByteMemory A; 
	public ByteMemory X;
	public ByteMemory Y;

	// should we just use the existing byte class and type cast? Might be easier

	
	// below we create the status flags
	public ByteMemory C = new ByteMemory(1);
	public ByteMemory Z = new ByteMemory(1);
	public ByteMemory I = new ByteMemory(1);
	public ByteMemory D = new ByteMemory(1);
	public ByteMemory B = new ByteMemory(1);
	public ByteMemory V = new ByteMemory(1);
	public ByteMemory N = new ByteMemory(1);

	/**
	   this method reset is to reset the CPU, 
	 */
	
	public void reset() {

		this.programCounter = new WordMemory(1);
		this.stackPointer = new WordMemory(1);
		
		// this.programCounter.setWord(0xFFFC);
		// this.stackPointer.setWord(0x00FF);
		C = Z = I = D = B = V = N = new ByteMemory(0);
		A = X = Y = new ByteMemory(0);
		// memory.initiallise(); // still need to add in this cariable/method
	}

	public byte fetchByte(int cycles, Memory mem){
		
		byte data = (byte) mem.data[(byte)this.programCounter.getWord()];
		this.programCounter.Word++;
		cycles--;
		return data;	
	}

	/** method to execute an instruction,
	*	@param cycles
	*	@param mem
	*/
	public void execute(int cycles, Memory mem) {
		while (cycles > 0) {
			byte instruction = (byte) fetchByte(cycles, mem);
			switch (instruction) {
			case INS_LDA_IM:
				byte value = fetchByte(cycles, mem);
				A.setByte(value);
				Z.setByte(A.getByte() == (byte) 0);
				N.setByte((A.getByte() & 0b10000000) != 0);// using != rather than > as bytes are signed in Java		
				break;
			default:
				System.out.println("Instruction not handled %d");
			}
		}
	}

	
}
