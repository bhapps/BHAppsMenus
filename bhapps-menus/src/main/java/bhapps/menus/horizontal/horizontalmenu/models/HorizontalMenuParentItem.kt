package bhapps.menus.horizontal.horizontalmenu.models

import android.graphics.drawable.Drawable
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuChildItem
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuType

class HorizontalMenuParentItem(
    var id: Int,
    var group: String = "",
    var title: String = "",
    var icon: Drawable? = null,
    var horizontal_menu_type: HorizontalMenuType = HorizontalMenuType.PARENT,
    var parent: Boolean = true,
    var active: Boolean = false,
    var show_badge: Boolean = true,
    var badge_label: String = "",
    var children: ArrayList<HorizontalMenuChildItem>? = null
) {
    override fun toString() = id.toString()
}
