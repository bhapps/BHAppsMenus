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
    var activity: String? = null,
    var fragment: String? = null,
    var keyValueHashMap: HashMap<String, Any>? = null,
    var key: String? = null,
    var stringAsKeyValue: String? = null,
    var intAsKeyValue: Int? = 0,
    var doubleAsKeyValue: Double? = 0.0,
    var longAsKeyValue: Long? = 0,
    var booleanAsKeyValue: Boolean? = false,
    var anyAsKeyValue: Any? = null,
    var dialog: String? = null,
    var is_first_item: Boolean? = false,
    var is_last_item: Boolean? = false
) {
    override fun toString() = id.toString()
}