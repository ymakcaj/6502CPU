
package Main.CPU;

import Main.CPU.Memory;
import Main.CPU.WordMemory;
import Main.CPU.ByteMemory;

public class CPU {

	public WordMemory programCounter;
	public WordMemory stackPointer;
	public Memory mem;
	public static final byte INS_LDA_IM = (byte) 0xA9;
	public static final byte INS_LDA_ZP = (byte) 0xA5;

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

		this.programCounter = new WordMemory(0);
		this.stackPointer = new WordMemory(0);
		
		// this.programCounter.setWord(0xFFFC);
		// this.stackPointer.setWord(0x00FF);
		C = Z = I = D = B = V = N = new ByteMemory(0);
		A = X = Y = new ByteMemory(0);
		// memory.initiallise(); // still need to add in this cariable/method
	}

	/*method to read and return a byte from our memory (an array of bytes)
	 we'll incrememnt the program counter by one*/
	public byte fetchByte(int cycles, Memory mem){
		
		byte data = mem.operator(this.programCounter.getWord()); //investigate what's happening here
		this.programCounter.Word++;
		// cycles--;
		System.out.println("line fetched, cycles:" + cycles);
		return data;	
	}

	/*method to read a byte but not increment the programCounter */
	public byte readByte(int cycles, Memory mem, byte address) {
		byte data = mem.operator(address); 
		// cycles--;
		System.out.println("line read, cycles:" + cycles);
		return data;
	}

	/* method to abstract the setting of the zero and negative flags*/
	public void LDASetStatus() {

		Z.setByte(A.getByte() == (byte) 0);
		N.setByte((A.getByte() & 0b10000000) != 0);// using != rather than > as bytes are signed in Java
	}

	/** method to execute an instruction,
	*	@param cycles
	*	@param mem
	*/
	public void execute(int cycles, Memory mem) {
		System.out.println("cycles at: " + cycles);
		while (cycles > 0) {
			byte instruction = (byte) fetchByte(cycles, mem);
			switch (instruction) {
			case INS_LDA_IM:
				byte value = fetchByte(cycles, mem);
				A.setByte(value); // no need to use this.A, as Java will assume it's this.A
				LDASetStatus();
				break;
			case INS_LDA_ZP:
				byte zeroPageAddress = fetchByte(cycles, mem);
				A.setByte(readByte(cycles, mem, zeroPageAddress)); // passing a byte as the address here, need to check if we need to type-cast this as an int
				LDASetStatus();
				break;
			default:
				// System.out.printf("Instruction not handled %d", instruction);
				;
			}
			cycles--; // adding this here in case there is no better option to decrement cycles
		}
	}

	
}
