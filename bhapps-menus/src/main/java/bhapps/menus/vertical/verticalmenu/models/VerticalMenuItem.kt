package bhapps.menus.vertical.verticalmenu.models

class VerticalMenuItem(
    var item_type: Int,
    _id: Int,
    _group: String,
    _title: String,
    _icon: Int,
    _type: VerticalMenuType,
    _parent: Boolean,
    _active: Boolean,
    _show_badge: Boolean,
    _badge_label: String,
    _activity: String,
    _fragment: String,
    _dialog: String
) {

    var id = -1
    var group: String
    var icon = -1
    var title: String
    var vertical_menu_type: String
    var is_parent = false
    var active = false
    var show_badge = false
    var badge_label = ""
    var activity: String
    var fragment: String
    var dialog: String

    init {
        id = _id
        group = _group
        title = _title
        icon = _icon
        vertical_menu_type = _type.toString()
        is_parent = _parent
        active = _active
        show_badge = _show_badge
        badge_label = _badge_label
        activity = _activity
        fragment = _fragment
        dialog = _dialog
    }
}
