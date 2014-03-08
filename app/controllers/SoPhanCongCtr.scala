package controllers

import controllers.element.{MainTemplate, BaseCtr}
import models.sunerp.{SoPhanCong, SoPhanCongs}
import models.core.AbstractQuery
import play.api.libs.json.Writes

/**
 * The Class SoPhanCongCtr.
 *
 * @author Nguyen Duc Dung
 * @since 3/8/14 4:20 PM
 *
 */
object SoPhanCongCtr extends BaseCtr[SoPhanCong, SoPhanCongs] with MainTemplate {
  override def editForm = SoPhanCongs.editForm
  override implicit val jsonWrite: Writes[SoPhanCong] = SoPhanCongs.soPhanCongJsonFormat
  override val dao: AbstractQuery[SoPhanCong, SoPhanCongs] = SoPhanCongs
  override val domainName: String = "soPhanCong"
}
