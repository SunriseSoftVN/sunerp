package services

import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import config.TestId

/**
 * The Class TextGenerator.
 *
 * @author Nguyen Duc Dung
 * @since 4/14/14 9:40 AM
 *
 */
abstract class TextGenerator {
  def welcomeText: String
}

class WelcomeTextGenerator(implicit val bindingModule: BindingModule) extends TextGenerator with Injectable {
  val _welcomeTest = inject[String](TestId)
  override def welcomeText: String = _welcomeTest
}