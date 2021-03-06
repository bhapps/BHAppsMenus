package bhapps.menus.vertical.verticalmenu.models

import android.graphics.drawable.Drawable
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuType

class VerticalMenuChildItem(
    var id: Int,
    var group: String = "",
    var title: String = "",
    var icon: Drawable? = null,
    var vertical_menu_type: VerticalMenuType = VerticalMenuType.CHILD,
    var parent: Boolean = true,
    var parent_id: Int = -1,
    var active: Boolean = false,
    var show_badge: Boolean = true,
    var badge_label: String = "",
    var activity: String = "",
    var fragment: String = "",
    var key: String = "",
    var value: Any = "",
    var dialog: String = "",
    var is_first_item: Boolean = false,
    var is_last_item: Boolean = false
) {
    override fun toString() = id.toString()
}