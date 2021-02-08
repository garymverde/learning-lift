package demo.lifttravel.model

import net.liftweb.common.{Full,Box,Empty,Failure}
import net.liftweb.mapper._
import net.liftweb.sitemap.Loc._
import scala.xml.{NodeSeq,Node}

object Customer extends Customer
  with KeyedMetaMapper[Long, Customer]
  with MetaMegaProtoUser[Customer]{

}

class Customer extends MegaProtoUser[Customer] with CreatedUpdated{
  def getSingleton = Customer
}
