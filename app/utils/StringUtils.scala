package utils

/**
 * The Class StringUtils.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 4:44 PM
 *
 */
object StringUtils {
  /**
   * Convert vietnamese unicode string to ascii string.
   *
   * @param st vietnamese unicode string.
   * @return ascii string.
   */
  def convertNonAscii(st: String): String = {
    var _st = st.replaceAll("[eéèẽẻẹ]", "e")
    _st = _st.replaceAll("[êếềễểệ]", "e")
    _st = _st.replaceAll("[uúùũủụ]", "u")
    _st = _st.replaceAll("[ưứừữửự]", "u")
    _st = _st.replaceAll("[iíìĩỉị]", "i")
    _st = _st.replaceAll("[aáàãảạ]", "a")
    _st = _st.replaceAll("[ăắằẵẳặ]", "a")
    _st = _st.replaceAll("[âấầẫẩậ]", "a")
    _st = _st.replaceAll("[oóòõỏọ]", "o")
    _st = _st.replaceAll("[ơớờỡởợ]", "o")
    _st = _st.replaceAll("[ôốồỗổộ]", "o")
    _st = _st.replaceAll("[EÉÈẼẺẸ]", "E")
    _st = _st.replaceAll("[ÊẾỀỄỂỆ]", "E")
    _st = _st.replaceAll("[UÚÙŨỦỤ]", "U")
    _st = _st.replaceAll("[ƯỨỪỮỬỰ]", "U")
    _st = _st.replaceAll("[IÍÌĨỈỊ]", "I")
    _st = _st.replaceAll("[AÁÀÃẢẠ]", "A")
    _st = _st.replaceAll("[ĂẮẰẴẲẶ]", "A")
    _st = _st.replaceAll("[ÂẤẦẪẨẬ]", "A")
    _st = _st.replaceAll("[OÓÒÕỎỌ]", "O")
    _st = _st.replaceAll("[ÔỐỒỖỔỘ]", "O")
    _st = _st.replaceAll("[ƠỚỜỠỞỢ]", "O")
    _st = _st.replaceAll("[ỹýỷỵ]", "y")
    _st = _st.replaceAll("[đ]", "d")
    _st = _st.replaceAll("[Đ]", "D")
    _st
  }

}
