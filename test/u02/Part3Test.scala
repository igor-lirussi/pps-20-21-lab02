package u02

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Part3Test {

  @Test def testFibonacci() {
    //fibonacci
    def fib(n:Int) : Int = n match {
      case 0 | 1 => n
      case _ => fib(n-1)+fib(n-2) //non è tail recursive, @annotation.tailrec va in errore
    }
    //equivalenti
    def fib2(n:Int) : Int = n match{ // "n match" è opzionale, può essere tolto
      case 0 | 1 => n
      case _ => fib(n-1)+fib(n-2)
    }
    val fib3: Int=>Int = {
      case n if n==0 | n==1 => n
      case n => fib(n-1)+fib(n-2)
    }

    assertEquals( (0,1,1,2,3) , (fib(0),fib(1),fib(2),fib(3),fib(4)) )

  }


}
