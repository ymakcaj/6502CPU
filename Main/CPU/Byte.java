package Main.CPU;

public class Byte {

	public byte Byte;

	public Byte(byte Byte) {
		this.Byte = Byte;
	}

	public Byte(int Byte) {
		Integer newByte = Integer.valueOf(Byte); // using .valueOf() as Integer(i) hasbeen depreciated
		this.Byte = newByte.byteValue(); 
	}
}
