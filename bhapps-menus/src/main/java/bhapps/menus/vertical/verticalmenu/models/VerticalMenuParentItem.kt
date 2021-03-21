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
    var activity: String = "",
    var fragment: String = "",
    var key: String = "",
    var value: Any = "",
    var dialog: String = "",
    var is_first_item: Boolean = false,
    var is_last_item: Boolean = false,
    var children: ArrayList<VerticalMenuChildItem>? = null
) {
    override fun toString() = id.toString()
}
