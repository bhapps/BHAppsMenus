package bhapps.menus.vertical.verticalmenu.models

enum class VerticalMenuType(val value: Int) {
    PARENT(1),
    PARENTWITHCHILDREN(2),
    CHILD(3),
    DIVIDER(4),
    TITLE(5)
}