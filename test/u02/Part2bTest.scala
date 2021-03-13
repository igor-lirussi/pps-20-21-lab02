package u02

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Part2bTest {

  @Test def testCurrying() {
    //with Int
    val p1: (Int, Int, Int)=>Boolean = (X, Y, Z) => (X>=Y)&&(Y>=Z)
    assertEquals(true, p1(10, 8, 6))


    //with generics
    val p1:[X,Y,Z]=>Boolean = (X, Y, Z) => (X>=Y)&&(Y>=Z)
    assertEquals(true, p1(10, 8, 6))

    // ASSERT

  }

  @Test
  def testFunctionalComposition() {

  }

}
