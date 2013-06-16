package utils

import android.view.View.OnClickListener
import android.view.View

object Helpers {
  implicit def function2OnClickListener(handler: View => Unit) = new OnClickListener() {
    override def onClick(source: View) = handler(source)
  }
}