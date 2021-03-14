package u02

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Part2bTest {

  @Test def testCurrying() {
    //FUNCTIONS (val)
    //non curried with Int
    val p2: (Int, Int, Int)=>Boolean = (X, Y, Z) => (X<=Y)&&(Y<=Z)
    assertEquals(true, p2(6,8,10))
    assertEquals(false, p2(10,8,10))
    //non curried with generics
    //val p2g:[X,Y,Z]=>Boolean = (X, Y, Z) => (X<=Y)&&(Y<=Z)
    //val p2: ([A], [B], [C])=>Boolean = (X, Y, Z) => (X<=Y)&&(Y<=Z)
    //TODO: HOW?


    //curried with Int
    val p1: Int=>Int=>Int=>Boolean = X => Y => Z => (X<=Y)&&(Y<=Z)
    //val p1: Int=> Int=>(Int=>Boolean)  = X => Y => (Z=>(X<=Y)&&(Y<=Z))
    //prende int, torna funzione da int a (funzione da int a Bool)
    assertEquals(true, p1(6)(8)(10))
    val greaterThanSix=p1(6) //questa val è funzione da int a (funzione da int a Bool)
    val greaterThanSixEight=p1(6)(8) //la val è funzione da int a Bool
    val greaterThanSixEight2=greaterThanSix(8) //equivalente a quella sopra
    assertEquals(true, greaterThanSix(8)(10))
    assertEquals(true, greaterThanSixEight(10))
    assertEquals(true, greaterThanSixEight2(10))
    assertEquals(false, greaterThanSix(12)(10))  //devono essere in ordine crescente
    assertEquals(false, greaterThanSixEight(6))
    assertEquals(false, greaterThanSixEight2(6))
    //curried with Generics
    //val p1: [X]=>[Y]=>[Z]=>Boolean = X=>Y=>Z=> (X<=Y)&&(Y<=Z)
    //assertEquals(true, p1(10)(8)(6))
    //TODO: HOW?



    //METHODS (def)
    //non curried with Int
    def p4(X:Int, Y:Int, Z:Int): Boolean = (X<=Y)&&(Y<=Z)
    assertEquals(true, p4(6,8,10))
    assertEquals(false, p4(10,8,10))
    //non curried with generics
    //def p4g[X,Y,Z](X:X, Y:Y, Z:Z): Boolean = (X<=Y)&&(Y<=Z)
    //TODO HOW? error in (X<=Y)&&(Y<=Z)

    //curried with int
    def p3(X:Int)(Y:Int)(Z:Int): Boolean = (X<=Y)&&(Y<=Z)
    assertEquals(true, p3(6)(8)(10))
    assertEquals(false, p3(10)(8)(10))
    val greaterThanTen=p3(9)(10)(_) //todo why this?
    val greaterThanNine=p3(9) _
    //val greaterThanTen2=greaterThanNine(19)(_) //todo why not here?
    assertEquals(true, greaterThanTen(11))
    assertEquals(false, greaterThanTen(8))
    assertEquals(true, greaterThanNine(10)(12))//todo why like this now :(
    assertEquals(false, greaterThanNine(12)(8))
    //curried with generics
    //def p3[A,B,C](X:A)(Y:B)(Z:C): Boolean = (X<=Y)&&(Y<=Z)
    //TODO same problem as above (X<=Y)&&(Y<=Z) error


  }

  @Test
  def testFunctionalComposition() {
    //composizione interi
    def compose(f:Int =>Int, g:Int => Int) : Int=>Int = num=>f(g(num))
    //prende in ingresso due funzioni (da int a int) e restituisce una (da int a int)
    // dopo "=" ci va la corrispondenza Int=>Int che trovo dopo i ":"
    assertEquals(9, compose(_-1,_*2)(5))
    val minusThree= compose(_-1,_-2) //funzione da Int in Int
    assertEquals(9, minusThree(12))

    //composizione generica
    def composeGen[A, B, C](f:B =>C, g:A => B) : A=>C = a=>f(g(a))
    //f prende in ingresso il risultato di g, quindi B
    //assertEquals(9, composeGen(_-1,_*2)(5)) //no perchè non riesce a deferire i tipi
    //assertEquals(9, composeGen(_-1:Int=>Int,_*2:Int=>Int)(5)) //no
    val sub: Int=>Int = _-1
    val twice: Int=>Int = _*2
    assertEquals(9, composeGen(sub,twice)(5)) //ora sa che vanno da Int in Int
    val subDoub: Double=>Double = _-0.5
    val twiceDouble: Double=>Double = _*2.5
    assertEquals(10, composeGen(twiceDouble, subDoub)(4.5)) //ora sa che vanno da Double a Double
  }

}
