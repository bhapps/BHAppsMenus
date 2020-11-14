package bhapps.menus.horizontal.horizontalmenu.models

enum class HorizontalMenuType(val value: Int) {
    PARENT(1),
    PARENTWITHCHILDREN(2),
    CHILD(3),
    DIVIDER(4),
    TITLE(5)
}