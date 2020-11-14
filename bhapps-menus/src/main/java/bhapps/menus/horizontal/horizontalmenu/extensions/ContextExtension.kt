package bhapps.menus.horizontal.horizontalmenu.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import bhapps.menus.R
import bhapps.menus.horizontal.horizontalmenu.annotations.DpAnnotation

internal fun getIntToDp(context: Context, changeToDP: Int): Int {

  var IntToDp = changeToDP
  val r = context.resources
  IntToDp = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    changeToDP.toFloat(),
    r.displayMetrics
  ).toInt()

  return IntToDp

}

internal fun Context.dp2Px(@DpAnnotation dp: Int): Int {
  val scale = resources.displayMetrics.density
  return (dp * scale).toInt()
}

internal fun Context.dp2Px(@DpAnnotation dp: Float): Float {
  val scale = resources.displayMetrics.density
  return dp * scale
}

internal fun Context.accentColor(): Int {
  val colorAttr: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    R.attr.colorAccent
  } else {
    resources.getIdentifier("colorAccent", "attr", packageName)
  }
  val outValue = TypedValue()
  theme.resolveAttribute(colorAttr, outValue, true)
  return outValue.data
}

internal fun Context.contextColor(@ColorRes resource: Int): Int {
  return ContextCompat.getColor(this, resource)
}

internal fun Context.resourceDrawable(@DrawableRes resource: Int): Drawable? {
  return ResourcesCompat.getDrawable(resources, resource, null)
}

internal fun Context.dimen(@DimenRes dimenRes: Int): Int {
  return resources.getDimensionPixelSize(dimenRes)
}
