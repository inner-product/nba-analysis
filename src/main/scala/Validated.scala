package io.innerproduct
package nba

sealed trait Validated[E, A] {
  import Validated._

  /**
    * Combines this [[Validation]] with that. If both have failed we accumulate
    * errors. If either one has failed with fail with its errors. Otherwise we
    * succeed with a tuple of values.
    */
  def zip[B](that: Validated[E, B]): Validated[E, (A, B)] =
    ???

  def map[B](f: A => B): Validated[E, B] =
    this match {
      case Success(value)  => Success(f(value))
      case Failure(errors) => Failure(errors)
    }
}
object Validated {
  final case class Success[E, A](value: A) extends Validated[E, A]
  final case class Failure[E, A](errors: List[E]) extends Validated[E, A]

  def success[E, A](value: A): Validated[E, A] = Success(value)
  def failure[E, A](errors: List[E]): Validated[E, A] = Failure(errors)
}
