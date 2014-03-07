package utils

import org.apache.commons.io.IOUtils
import collection.JavaConversions._

/**
 * The Class FileUtil.
 *
 * @author Nguyen Duc Dung
 * @since 12/27/12 1:31 AM
 *
 */
object FileUtil {

  def getResourceAsStream(fileName: String) = getClass.getClassLoader.getResourceAsStream(fileName)

  def getTextFile(fileName: String) = {
    val input = getResourceAsStream(fileName)
    val sb = new StringBuilder
    val src = IOUtils.readLines(input)
    src.foreach(st => {
      sb.append(st)
      sb.append("\n")
    })
    sb.toString()
  }

  def getEmailContent(emailName: String) = getTextFile("email/" + emailName + ".txt")
}
