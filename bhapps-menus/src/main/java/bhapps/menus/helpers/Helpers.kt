package bhapps.menus.helpers

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View

object Helpers {

    fun View.dpToPx(dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    /*
       *
       * getIntToDp(context, int)
       * use to change passed int to dp for providing width, height dp values
       * bhapps.utilitools.ui.kotlin.helpers.UIHelpers.getIntToDp(context, int)
       * returns int
   */

    fun getIntToDp(context: Context, changeToDP: Int): Int {

        var IntToDp = changeToDP
        val r = context.resources
        IntToDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            changeToDP.toFloat(),
            r.displayMetrics
        ).toInt()

        return IntToDp

    }

    /*
       *
       * getDpToPx(context, float)
       * use to change passed float to px for providing width, height px values
       * bhapps.utilitools.ui.kotlin.helpers.UIHelpers.getDpToPx(context, float)
       * returns float
   */

    fun getDpToPx(context: Context, dp: Float): Float {
        val metrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        return dp * (metrics.densityDpi / 160f)
    }

}