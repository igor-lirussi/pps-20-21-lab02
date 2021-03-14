package u02

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class Part4Test {

  @Test def testGeometricShapes() {

    sealed trait Shape
    case class Rectangle (side1:Double, side2:Double) extends Shape
    case class Square (side:Double) extends Shape
    case class Circle (radius:Double) extends Shape

    val rect = Rectangle(2,3)
    val square = Square(4)
    val circle = Circle(5)
    assertEquals(Rectangle(2,3),rect)
    assertEquals(Square(4), square)
    assertEquals(Circle(5), circle)

    def perimeter(shape: Shape): Double = shape match {
      case Rectangle(side1, side2) => (side1 + side2)*2
      case Circle(radius) => radius*2*Math.PI
      case Square(side) => side *4
    }


    assertEquals(perimeter(Rectangle(2,3)),perimeter(rect))
    assertEquals(perimeter(Square(4)), perimeter(square))
    assertEquals(perimeter(Circle(5)), perimeter(circle))
    assertEquals(10,perimeter(rect))
    assertEquals(16, perimeter(square))

    def area(shape:Shape) : Double = shape match {
      case Rectangle(side1, side2) => (side1 * side2)
      case Circle(radius) => radius*radius*Math.PI
      case Square(side) => side * side
    }


    assertEquals(area(Rectangle(2,3)),area(rect))
    assertEquals(area(Square(4)), area(square))
    assertEquals(area(Circle(5)), area(circle))
    assertEquals(6,area(rect))
    assertEquals(16, area(square))


  }


}
