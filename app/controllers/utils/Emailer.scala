package controllers.utils

import play.api.Play
import org.apache.commons.mail.{DefaultAuthenticator, SimpleEmail}

/**
 * The Class Emailer.
 *
 * @author Nguyen Duc Dung
 * @since 9/6/13, 7:36 AM
 *
 */
object Emailer {

  lazy val emailUsername = Play.current.configuration.getString("email.username").get
  lazy val emailPassword = Play.current.configuration.getString("email.password").get
  lazy val emailSmtp = Play.current.configuration.getString("email.smtp").get
  lazy val emailPort = Play.current.configuration.getInt("email.port").get

  def send(content: String, title: String, toEmail: String) {
    val sender = new SimpleEmail()
    sender.setHostName(emailSmtp)
    sender.setSmtpPort(emailPort)
    sender.setAuthenticator(new DefaultAuthenticator(emailUsername, emailPassword))
    sender.setSSLOnConnect(true)
    sender.setFrom(emailUsername, "MyFeedy")
    sender.setSubject(title)
    sender.setMsg(content)
    sender.addTo(toEmail)
    sender.send()
  }

}
