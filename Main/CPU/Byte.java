package Main.CPU;

public class Byte {

	public byte Byte;

	public Byte(byte Byte) {
		this.Byte = Byte;
	}

	public Byte(int Byte) {
		Integer newByte = new Integer(Byte);
		this.Byte =  newByte.byteValue(); // need to type cast from int to byte here
	}
}
