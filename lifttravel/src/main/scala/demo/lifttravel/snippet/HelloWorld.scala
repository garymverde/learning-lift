package demo.lifttravel
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import demo.lifttravel.lib._
import Helpers._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
  lazy val who = Props.get("foo.name")        // Should be checking the box to ensure that the value is found

  // replace the contents of the element with id "time" with the date
  def howdy = <span>Welcome to lift-travel at {new _root_.java.util.Date}. Mode is {Props.mode}</span>
  def oldHowdy = "#time *" #> date.map(_.toString)
  def neighbor = "#neighbor *" #>  who
  def render = "*" #> <strong>hello world!</strong>
  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

