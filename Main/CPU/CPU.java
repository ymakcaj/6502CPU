
package Main.CPU;

import Main.CPU.Memory;
import Main.CPU.WordMemory;

public class CPU {

	public WordMemory programCounter;
	public WordMemory stackPointer;
	public Memory mem;

	// here we create the CPU's registers
	
	public byte A; 
	public byte X;
	public byte Y;

	// should we just use the existing byte class and type cast? Might be easier

	
	// below we create the status flags
	public byte C = (byte) 1;
	public byte Z = (byte) 1;
	public byte I = (byte) 1;
	public byte D = (byte) 1;
	public byte B = (byte) 1;
	public byte V = (byte) 1;
	public byte N = (byte) 1;

	/**
	   this method reset is to reset the CPU, 
	 */
	
	public void reset() {
		this.programCounter = new WordMemory(0xFFFC);
		this.stackPointer = new WordMemory(0x00FF);
		C = Z = I = D = B = V = N = (byte) 0;
		A = X = Y = (byte) 0;
		// memory.initiallise(); // still need to add in this cariable/method
	}

	public byte fetchByte(int Cycles, Mem memory){
		
		new byte data = (byte) memory[this.prgramCounter];
		programCounter++;
		Cycles--;
		return data
		
	}

	
}
