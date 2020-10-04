package bhapps.menus.vertical.verticalmenu.models

import android.graphics.drawable.Drawable

class VerticalMenuParentItem(
    var id: Int,
    var group: String = "",
    var title: String = "",
    var icon: Drawable? = null,
    var vertical_menu_type: VerticalMenuType = VerticalMenuType.PARENT,
    var parent: Boolean = true,
    var active: Boolean = false,
    var show_badge: Boolean = true,
    var badge_label: String = "",
    var children: ArrayList<VerticalMenuChildItem>? = null
) {
    override fun toString() = id.toString()
}
