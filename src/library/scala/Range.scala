/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2007, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id: $


package scala

import Predef._

/** <p>
 *    The <code>Range</code> class represents integer values in range
 *    <code>[start;end)</code> with non-zero step value <code>step</code>.
 *    For example:
 *  </p><pre>
 *     <b>val</b> r1 = Iterator.range(0, 10)
 *     <b>val</b> r2 = Iterator.range(r1.start, r1.end, r1.step + 1)
 *     println(r2.length) // = 5
 *  </pre>
 *
 *  @author  Stephane Micheloud
 *  @version 1.0, 01/05/2007
 */
class Range(val start: Int, val end: Int, val step: Int) extends BufferedIterator[Int] {
  assert(step != 0)
  private var i = start

  def hasNext: Boolean = if (step > 0) i < end else i > end

  def next(): Int =
    if (hasNext) { val j = i; i += step; j }
    else throw new NoSuchElementException("next on empty iterator")

  def head: Int =
    if (hasNext) i
    else throw new NoSuchElementException("head on empty iterator")

  def length: Int =
    1 + (Math.abs(start - end) - 1) / Math.abs(step)

  def contains(x: Int): Boolean =
    Iterator.range(0, length) exists (i => x == start + i * step)
}