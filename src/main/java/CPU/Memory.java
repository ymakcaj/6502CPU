package CPU;

public class Memory {
	static final int MAX_MEM = 1024 * 64;
	public byte[] data = new byte[MAX_MEM];

	/**
	 * a basic constructor 
	 */

	public Memory() {
		// this.data = data;  // actually this isn't needed
	}

	/** a basic method to set all bytes to 0*/

	public void initialise() {
		for (int i=0;i<MAX_MEM;i++) {
			this.data[i] = (byte) 0;
		}
	}

	
	/** method to read 1 byte - of the address opperand */
	
	public byte operator(int address) {
		assert(address <= MAX_MEM);
		byte addressToReturn = data[address]; 
		return addressToReturn;
	}

	
	/* again, using polymorphism to make sure regardless of the
	   paramater's datatype we will still get the desired outcome
	 */
	public byte operator(short address) {
		int index = address & 0xFFFF; // here we use a bitwise AND operation to preserve the unsigned value of the address
		assert(index <= MAX_MEM);
		byte addressToReturn = this.data[index];  // using this.data to ensure we're referencing the correct memory
		return addressToReturn;
	}
	
	public byte operator(byte address) {
		int index = address & 0xFFFF; // here we use a bitwise AND operation to preserve the unsigned value of the address
		assert(index <= MAX_MEM);
		byte addressToReturn = this.data[index];  // using this.data to ensure we're referencing the correct memory
		return addressToReturn;
	}
	
	/* debating whether to have a setter or not */
	// public void setMemory(int index) {
	// 	this.data[index]
	// }


	/* adding a method to write to memory
	 * @param address int
	 * @param data byte 
	 * this will write to mememory the @param byte to index @address
	*/
	public void  operatorWrite(int address, byte newByte) {
		int newAddress = (int) address;
		assert(newAddress <= MAX_MEM);
		this.data[address] = this.data[newByte];
	}
}
