package bhapps.menus.vertical.verticalmenu.models

import android.graphics.drawable.Drawable
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuType

class VerticalMenuItem(
    var item_type: Int,
    _id: Int,
    _group: String,
    _title: String,
    _icon: Drawable?,
    _type: VerticalMenuType,
    _parent: Boolean,
    _parent_id: Int,
    _active: Boolean,
    _show_badge: Boolean,
    _badge_label: String,
    _activity: String,
    _fragment: String,
    _keyValueHashMap: HashMap<String, Any>,
    _key: String,
    _stringAsKeyValue: String,
    _intAsKeyValue: Int,
    _doubleAsKeyValue: Double,
    _longAsKeyValue: Long,
    _booleanAsKeyValue: Boolean = false,
    _anyAsKeyValue: Any,
    _dialog: String,
    _is_first_item: Boolean = false,
    _is_last_item: Boolean = false
) {

    var id = -1
    var group: String
    var icon: Drawable?
    var title: String
    var vertical_menu_type: String
    var is_parent: Boolean = false
    var parent_id: Int
    var active: Boolean = false
    var show_badge: Boolean = false
    var badge_label = ""
    var activity: String
    var fragment: String
    var keyValueHashMap: HashMap<String, Any>
    var key: String?
    var stringAsKeyValue: String?
    var intAsKeyValue: Int?
    var doubleAsKeyValue: Double?
    var longAsKeyValue: Long?
    var booleanAsKeyValue: Boolean? = false
    var anyAsKeyValue: Any?
    var dialog: String
    var is_first_item: Boolean =  false
    var is_last_item: Boolean = false

    init {
        id = _id
        group = _group
        title = _title
        icon = _icon
        vertical_menu_type = _type.toString()
        is_parent = _parent
        parent_id = _parent_id
        active = _active
        show_badge = _show_badge
        badge_label = _badge_label
        activity = _activity
        fragment = _fragment
        keyValueHashMap =_keyValueHashMap
        key = _key
        stringAsKeyValue = _stringAsKeyValue
        intAsKeyValue = _intAsKeyValue
        doubleAsKeyValue = _doubleAsKeyValue
        longAsKeyValue = _longAsKeyValue
        booleanAsKeyValue = _booleanAsKeyValue
        anyAsKeyValue = _anyAsKeyValue
        dialog = _dialog
        is_first_item = _is_first_item
        is_last_item =_is_last_item
    }
}
