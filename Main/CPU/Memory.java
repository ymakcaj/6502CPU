package Main.CPU;

import static org.junit.Assert.*;

public class Memory {
	static final int MAX_MEM = 1024 * 64;
	public byte[] data = new byte[MAX_MEM];

	/* a basic method to set all bytes to 0*/

	public void initialise() {
		for (int i=0;i<MAX_MEM;i++) {
			this.data[i] = (byte) 0;
		}
	}

	
	/* method to read 1 byte - of the address opperand */
	
	public byte operator(int address) {
		assert(address <= MAX_MEM);
		byte addressToReturn = (byte) data[address]; // must be a better way to do this, but crrently getting  "CPU\Memory.java:14: error: incompatible types: byte cannot be converted to byte[]" as an error
		return addressToReturn;
	}
}
