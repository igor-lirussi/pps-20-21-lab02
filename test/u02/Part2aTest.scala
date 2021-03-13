package u02


import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Part2aTest {

  @Test def testEvenOdd() {
    // function
    val f: Int => String = {
      case n if n % 2 == 0 => "even"
      case _ => "odd"
    }

    // method
    def m(n: Int): String = n match {
      case n if n%2==0 => "even"
      case n if n%2==1 => "odd"
    }



    // ASSERT
    assertEquals("odd",f(3))
    assertEquals("odd",f(5))
    assertEquals("even",f(6))

    assertEquals("odd",m(3))
    assertEquals("odd",m(5))
    assertEquals("even",m(6))

    assertEquals(f(3), m(3))
    assertEquals(f(2), m(2))
  }

  @Test
  def testNeg() {

    val empty: String => Boolean = _==""

    assertEquals(true, empty(""))
    assertEquals(false, empty("non empty"))

    //neg prende un predicato (String=>Boolean) e restituisce uno (String=>Boolean)
    // = f che va ora in una funzione String to boolean
    val neg: (String=>Boolean) => (String=>Boolean)   =   ( f ) => (s => !f(s))

    def neg2(fun:String=>Boolean): (String=>Boolean) =   s => !fun(s)

    //sbagliato perchè associa a dx
    //val neg: String=>Boolean =>Boolean = f => !f(a)

    //sbagliato perchè prende in ingresso una stringa e una funzione e restituisce un booleano
    val neg4: (String, String => Boolean ) => Boolean = (s , f) => !f(s)

    //sbagliato perchè non si sa cosa mettere dopo f
//    val neg3: (String=>Boolean) => (String=>Boolean) = {
//      case f==>??? if f =>
//      case _ => true
//    }

    val notEmpty = neg(empty)
    val notEmpty2 = neg2(empty)

    assertEquals(true, notEmpty("foo"))
    assertEquals(false, notEmpty("") )
    assertEquals(true, notEmpty2("foo"))
    assertEquals(false, notEmpty2("") )

  }

  @Test
  def testNegGenerics() {

    val empty: String => Boolean = _==""
    val sameBoolean: Boolean => Boolean = b => b
    val isZero: Int => Boolean = b => b==0


    def negGenerics[T](func: T => Boolean) : ( T => Boolean) =  gen => !func(gen)

    val notEmpty = negGenerics(empty)
    val notSameBoolean = negGenerics(sameBoolean)
    val notIsZero = negGenerics(isZero)

    assertEquals(true, notEmpty("foo") )
    assertEquals(false, notEmpty("") )
    assertEquals(true, notSameBoolean(false) )
    assertEquals(false, notSameBoolean(true) )
    assertEquals(true, notIsZero(1) )
    assertEquals(false, notIsZero(0) )

  }


}
