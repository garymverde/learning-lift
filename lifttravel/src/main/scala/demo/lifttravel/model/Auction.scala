package demo.lifttravel.model

import net.liftweb.common.{Full,Box,Empty,Failure}
import net.liftweb.sitemap.Loc._
import scala.xml.NodeSeq
import net.liftweb.mapper._

object Auction extends Auction
  with LongKeyedMetaMapper[Auction]
  with CRUDify[Long,Auction]{

}

class Auction extends LongKeyedMapper[Auction] with IdPK with CreatedUpdated {
  def getSingleton = Auction

  // fields
  object name extends MappedString(this, 150)

  object description extends MappedText(this)

  object endsAt extends MappedDateTime(this)

  object outboundOn extends MappedDateTime(this)

  object inboundOn extends MappedDateTime(this)

  object flyingFrom extends MappedString(this, 100)

  object isClosed extends MappedBoolean(this) {
    override def defaultValue = false
  }

  object startingAmount extends MappedDouble(this)

  // relationships
  object supplier extends LongMappedMapper(this, Supplier){
    override def dbColumnName = "supplier_id"
    override def validSelectValues =
      Full(Supplier.findMap(OrderBy(Supplier.name, Ascending)){
        case s: Supplier => Full(s.id.is -> s.name.is)
      })
  }
}