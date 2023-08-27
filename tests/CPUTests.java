import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

import main.CPU.Memory;
import main.Game.GameBoard;

public class CPUTests {
	/**
	 * testing that the memory address being called is an allowable size
	 * @param int address
	 */
	@Test
	public void testAddressInMemory(int address) {
		Memory mem = new Memory;
		assert(address <= 1024 * 64);
	}
}
