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

  }


}
