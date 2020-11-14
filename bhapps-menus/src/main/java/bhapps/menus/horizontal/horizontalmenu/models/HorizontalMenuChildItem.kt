package bhapps.menus.horizontal.horizontalmenu.models

import android.graphics.drawable.Drawable
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuType

class HorizontalMenuChildItem(
    var id: Int,
    var group: String = "",
    var title: String = "",
    var icon: Drawable? = null,
    var horizontal_menu_type: HorizontalMenuType = HorizontalMenuType.CHILD,
    var parent: Boolean = true,
    var parent_id: Int = -1,
    var active: Boolean = false,
    var show_badge: Boolean = true,
    var badge_label: String = "",
    var is_first_item: Boolean = false,
    var is_last_item: Boolean = false
) {
    override fun toString() = id.toString()
}