package controllers.element

import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}
import play.api.mvc.Controller
import play.api.db.slick._
import play.api.Play.current

/**
 * The Class TransactionElement.
 *
 * @author Nguyen Duc Dung
 * @since 1/10/14 8:57 PM
 *
 */
trait TransactionElement extends StackableController {
  self: Controller =>

  private case object SessionKey extends RequestAttributeKey[Session]

  override def cleanupOnFailed[A](request: RequestWithAttributes[A], e: Throwable) = {
    try {
      currentSession(request).map(_session => {
        try {
          _session.conn.rollback()
        } finally {
          _session.close()
        }
      })
    } finally {
      super.cleanupOnFailed(request, e)
    }
  }

  override def cleanupOnSucceeded[A](request: RequestWithAttributes[A]) = {
    try {
      currentSession(request).map(_session => {
        try {
          _session.conn.commit()
        } finally {
          _session.close()
        }
      })
    } finally {
      super.cleanupOnSucceeded(request)
    }
  }

  /**
   * Return current session store in a request
   * @param req
   * @return
   */
  def currentSession(implicit req: RequestWithAttributes[_]) = req.get(SessionKey)

  /**
   * Create a session only when it needed.
   * @param req
   * @return
   */
  implicit def getOrCreate(implicit req: RequestWithAttributes[_]): Session = req.get(SessionKey).getOrElse {
    val _session = DB.createSession()
    _session.conn.setAutoCommit(false)
    req.set(SessionKey, _session)
    _session
  }

}
