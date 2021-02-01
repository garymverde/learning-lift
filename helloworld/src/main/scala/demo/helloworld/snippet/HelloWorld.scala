package demo.helloworld
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import demo.helloworld.lib._
import Helpers._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
  lazy val who = Props.get("foo.name")        // Should be checking the box to ensure that the value is found

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString)
  def neighbor = "#neighbor *" #>  who
  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

