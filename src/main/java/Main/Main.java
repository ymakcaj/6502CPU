
package Main;

import CPU.CPU;
import CPU.Memory;


public class Main {


	public static void main(String[] args) {
		Main mainInstance = new Main();
		mainInstance.runMainMethod();
	}

	public int runMainMethod() {
		
		CPU cpu = new CPU();
		Memory mem = new Memory();
		int cycles = 3;
		
		cpu.reset();
		// inlining a small program
		mem.data[0xFFFC] = CPU.INS_LDA_ZP;
		mem.data[0xFFFD] = (byte) 0x42;
		mem.data[0x0042] = (byte) 0x84;
		// ending the program
		cpu.execute(cycles, mem);
		return 0;
	}

}
