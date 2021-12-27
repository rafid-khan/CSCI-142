package test;

import rit.cs.Expression;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import rit.cs.*;

/**
 * A test unit for the rit.cs.IntExpression class.
 *
 * @author RIT CS
 */
public class TestIntExpression {
    @Test
    public void testIntExpression() {
        Expression root = new IntExpression(10);
        assertEquals(10, root.evaluate());
        assertEquals("10", root.emit());
    }
}
