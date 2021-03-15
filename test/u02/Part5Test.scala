package u02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part5Test {

  @Test def testOption() {

    //estensione di Option
    sealed trait Option[A] // An Optional data type
    object Option {

      case class None[A]() extends Option[A]
      case class Some[A](a: A) extends Option[A]

      //currying method prende in ingresso l'Option[A] sum-type e la funzione da A a bool
      def filter[A](opt: Option[A])(fun:A=>Boolean): Option[A] = opt match {
        case Some(a) if fun(a) => Some(a)
        case _ => None()  //comprende case None() => None()  e se la funzione ritorna false

      }

      //currying method generics
//      def map[A, B](opt: Option[A])(fun:A=>B): Option[B] = opt match {
//        case Some(a) => Some(fun(a))
//        case _ => None()
//
//      }
      //currying method
      def map[A](opt: Option[A])(fun:A=>Boolean): Option[Boolean] = opt match {
        case Some(a) => Some(fun(a))
        case _ => None()

      }

      //currying method
      def map2[A](opt: Option[A])(opt2: Option[A])(fun:(A,A)=>A): Option[A] = opt match {
        case Some(a) if !isEmpty(opt2) => opt2 match {
          case Some(b) => Some(fun(a,b))
        }
        case _ => None()
      }
      //different types in input and output
      def map3[A,B,C](opt: Option[A])(opt2: Option[B])(fun:(A,B)=>C): Option[C] = opt match {
        case Some(a) => opt2 match {
          case Some(b) => Some(fun(a,b))
        }
        case _ => None()
      }

      def isEmpty[A](opt: Option[A]): Boolean = opt match {
        case None() => true
        case _ => false
      }

      def getOrElse[A, B >: A](opt: Option[A], orElse: B): B = opt match {
        case Some(a) => a
        case _ => orElse
      }

      def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match {
        case Some(a) => f(a)
        case _ => None()
      }
    }

    import Option._
    val s1: Option[Int] = Some(1)
    val s2: Option[Int] = Some(2)
    val s3: Option[Int] = None()

    //filter
    assertEquals(Some(5), filter(Some(5))(_ > 2))
    assertEquals( None(), filter(Some(5))(_ > 8) )

    //map
    assertEquals(Some(true), map(Some(5))(_ > 2) )// Some(true)
    assertEquals(None(), map(None[Int])(_ > 2) )// None

    //map2
    assertEquals(Some(3), map2(Some(1))(Some(2))(_+_))
    assertEquals(Some(3), map2(Some(5))(Some(2))(_-_))
    assertEquals(Some(6), map2(Some(3))(Some(2))(_*_))

    //map3
    assertEquals(Some(6), map3(Some(3))(Some(2))(_*_))
    assertEquals(Some(true), map3(Some(3))(Some(3))(_==_))
    assertEquals(Some(false), map3(Some(3))(Some("no"))(_==_))
    assertEquals(Some("3no"), map3(Some(3))(Some("no"))(_+_))
    assertEquals(Some("auto"), map3(Some("au"))(Some("to"))(_+_))
  }


}
