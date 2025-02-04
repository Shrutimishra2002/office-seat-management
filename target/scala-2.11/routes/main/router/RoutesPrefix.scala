
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/shruti_sm1540_sc418/Documents/OfficeSeatManagement/conf/routes
// @DATE:Tue Feb 04 11:37:14 IST 2025


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
