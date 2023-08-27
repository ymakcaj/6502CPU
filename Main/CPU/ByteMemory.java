

package Main.CPU;

public class ByteMemory {

	public byte Byte;

	public ByteMemory(byte Byte) {
		this.Byte = Byte;
	}

	/*
	  add a constructor that will be the primary one used.

	  We're dealing with primative datatypes - but java only allows us to initialise
	  an int - so this constructor has a @param of int, and type casts it to byte. 
	 */
	public ByteMemory(int byteInput) {
		// Integer newByte = (byte) ByteInput;
		this.Byte = (byte) byteInput; 
	}
}
