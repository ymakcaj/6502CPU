

package CPU;

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
	public ByteMemory(int intInput) {
		// Integer newByte = (byte) ByteInput;
		this.Byte = (byte) intInput; 
	}

	/* trying to replitcate the use of bool -> byte in C++
	   we'll make a constructor that has an input of a boolean,
	   where true = 1 and anything else = 0
	   
	*/
	public ByteMemory(boolean boolInput) {
		if (boolInput = true) {
			this.Byte = (byte) 1;
		} else {
			this.Byte = (byte) 0;
		} 
	}

	/* adding some setters and setters for the ByteMemory class,
	   again, taking advantage of polymorphism to cater for a variety of dataypes
	   as parameters
	 */

	public void setByte(byte newByte) {
		this.Byte = newByte;
	}
	public void setByte(int newInt) {
		this.Byte = (byte) newInt;
	}
	public void setByte(boolean newBool) {
		if (newBool == true) {
			this.Byte = (byte) 1;
		} else {
			this.Byte = (byte) 0;
		}
	}


	/* now adding a getter, for the moment, just to return a byte */

	public byte getByte() {
		return this.Byte;
	}


}
