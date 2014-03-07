package utils

/**
  * The Class StringToIntConverter.
  *
  * @author Nguyen Duc Dung
  * @since 6/5/13 11:28 PM
  *
  */
object String2Int {
   def unapply(s : String) : Option[Int] = try {
     Some(s.toInt)
   } catch {
     case _ : java.lang.NumberFormatException => None
   }
 }
