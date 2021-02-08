package bootstrap.liftweb

import demo.lifttravel.model._
import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import net.liftweb.http.js.jquery._
import net.liftweb.mapper.{DB, DefaultConnectionIdentifier, Schemifier, StandardDBVendor}
import net.liftweb.http.{LiftRules, S}


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {

    DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
    LiftRules.unloadHooks.append(
      () => DBVendor.closeAllConnections_!())
    S.addAround(DB.buildLoanWrapper)

    Schemifier.schemify(true, Schemifier.infoF _, Auction, Bid, Customer,
      Order, OrderAuction, Supplier)

    // where to search snippet
    LiftRules.addToPackages("demo.lifttravel")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index",
      Menu.i("Auctions") / "auctions/index"
    )
    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries:_*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))
  }

  object DBVendor extends StandardDBVendor(
    Props.get("db.class").openOr("org.h2.Driver"),
    Props.get("db.url").openOr("jdbc:h2:./database/chapter_3"),
    Props.get("db.user"),
    Props.get("db.pass"))
}
