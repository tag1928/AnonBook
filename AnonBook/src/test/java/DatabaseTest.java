import Database.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest
{
	@Test
	public void testPersistence()
	{
		var a = Database.getInstance();
	}
}