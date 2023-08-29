
package Main;

import Main.CPU.CPU;
import Main.CPU.Memory;


public class main {


	public static void main(String[] args) {
		main mainInstance = new main();
		mainInstance.runMainMethod();
	}

	public int runMainMethod() {
		
		CPU cpu = new CPU();
		Memory mem = new Memory();
		byte INS_LDA_IM = (byte) 0xA9;
		
		cpu.reset();
		mem.data[0xFFFC] = INS_LDA_IM;
		cpu.execute(2, mem);
		return 0;
	}

}
