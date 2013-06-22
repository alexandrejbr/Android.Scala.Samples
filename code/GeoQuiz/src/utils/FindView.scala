package utils

import android.app.Activity

trait FindView extends Activity {
  def findView[T](id: Int): T = {
    findViewById(id).asInstanceOf[T]
  }
}