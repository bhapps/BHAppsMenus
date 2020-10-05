package bhapps.menus.demo

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import bhapps.menus.vertical.verticalmenu.VerticalMenu
import bhapps.menus.vertical.verticalmenu.adapters.VerticalMenuAdapter
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuHelper
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuHelper.setActiveState
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuHelper.setBadgeStateAndBadgeLabel
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuChildItem
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuParentItem
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuType
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var bhapps_menus_menu_vertical_menu_icons_only: VerticalMenu
    lateinit var bhapps_menus_menu_vertical_menu_icons_only_adapter: VerticalMenuAdapter

    lateinit var bhapps_menus_menu_vertical_menu: VerticalMenu
    lateinit var bhapps_menus_menu_vertical_menu_adapter: VerticalMenuAdapter

    lateinit var bhapps_menus_menu_vertical_menu_with_active_chip_background: VerticalMenu
    lateinit var bhapps_menus_menu_vertical_menu_with_active_chip_background_adapter: VerticalMenuAdapter

    lateinit var bhapps_menus_menu_vertical_menu_with_active_gradient_background: VerticalMenu
    lateinit var bhapps_menus_menu_vertical_menu_with_active_gradient_background_adapter: VerticalMenuAdapter

    lateinit var bhapps_menus_menu_vertical_menu_icons_only_right: VerticalMenu
    lateinit var bhapps_menus_menu_vertical_menu_icons_only_right_adapter: VerticalMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buildVerticalMenu()
    }

    fun buildVerticalMenu() {

        //region bhapps_menus_menu_vertical_menu_icons_only
        bhapps_menus_menu_vertical_menu_icons_only = findViewById(R.id.bhapps_menus_menu_vertical_menu_icons_only)
        bhapps_menus_menu_vertical_menu_icons_only_adapter = VerticalMenuAdapter(
            this,
            VerticalMenuHelper.generateVerticalMenuFromList(
                buildMenuWithParentsOnlyFromList()
            ),
            object : VerticalMenuAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, itemId: Int) {
                    onVerticalMenuIconsOnlyMenuItemSelected(
                        view,
                        itemId
                    )
                }
            }
        )

        bhapps_menus_menu_vertical_menu_icons_only.buildVerticalMenu(
            bhapps_menus_menu_vertical_menu_icons_only_adapter
        )

        setBadgeStateAndBadgeLabel(
            this,
            bhapps_menus_menu_vertical_menu_icons_only_adapter,
            "dashboard",
            100,
            "22"
        )
        setActiveState(this, bhapps_menus_menu_vertical_menu_icons_only_adapter, "dashboard", 100)
        //endregion bhapps_menus_menu_vertical_menu_icons_only

        //region bhapps_menus_menu_vertical_menu
        bhapps_menus_menu_vertical_menu = findViewById(R.id.bhapps_menus_menu_vertical_menu)
        bhapps_menus_menu_vertical_menu_adapter = VerticalMenuAdapter(
            this,
            VerticalMenuHelper.generateVerticalMenuFromList(
                buildMenuWithMultipleTypesFromList()
            ),
            object : VerticalMenuAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, itemId: Int) {
                    onVerticalMenuItemSelected(
                        view,
                        itemId
                    )
                }
            }
        )

        bhapps_menus_menu_vertical_menu.buildVerticalMenu(
            bhapps_menus_menu_vertical_menu_adapter
        )

        setBadgeStateAndBadgeLabel(
            this,
            bhapps_menus_menu_vertical_menu_adapter,
            "dashboard",
            100,
            "22"
        )
        //endregion bhapps_menus_menu_vertical_menu

        //region bhapps_menus_menu_vertical_menu_with_active_chip_background
        bhapps_menus_menu_vertical_menu_with_active_chip_background = findViewById(
            R.id.bhapps_menus_menu_vertical_menu_with_active_chip_background
        )
        bhapps_menus_menu_vertical_menu_with_active_chip_background_adapter = VerticalMenuAdapter(
            this,
            VerticalMenuHelper.generateVerticalMenuFromList(
                buildMenuWithParentsOnlyFromList()
            ),
            object : VerticalMenuAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, itemId: Int) {
                    onVerticalMenuIconsOnlyMenuItemSelected(
                        view,
                        itemId
                    )
                }
            }
        )

        bhapps_menus_menu_vertical_menu_with_active_chip_background.buildVerticalMenu(
            bhapps_menus_menu_vertical_menu_with_active_chip_background_adapter
        )

        setBadgeStateAndBadgeLabel(
            this,
            bhapps_menus_menu_vertical_menu_with_active_chip_background_adapter,
            "dashboard",
            100,
            "22"
        )
        setActiveState(
            this,
            bhapps_menus_menu_vertical_menu_with_active_chip_background_adapter,
            "dashboard",
            100
        )
        //endregion bhapps_menus_menu_vertical_menu_with_active_chip_background

        //region bhapps_menus_menu_vertical_menu_with_active_gradient_background
        bhapps_menus_menu_vertical_menu_with_active_gradient_background = findViewById(
            R.id.bhapps_menus_menu_vertical_menu_with_active_gradient_background
        )
        bhapps_menus_menu_vertical_menu_with_active_gradient_background_adapter = VerticalMenuAdapter(
            this,
            VerticalMenuHelper.generateVerticalMenuFromList(
                buildMenuWithParentsOnlyFromList()
            ),
            object : VerticalMenuAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, itemId: Int) {
                    onVerticalMenuIconsOnlyMenuItemSelected(
                        view,
                        itemId
                    )
                }
            }
        )

        bhapps_menus_menu_vertical_menu_with_active_gradient_background.buildVerticalMenu(
            bhapps_menus_menu_vertical_menu_with_active_gradient_background_adapter
        )

        setBadgeStateAndBadgeLabel(
            this,
            bhapps_menus_menu_vertical_menu_with_active_gradient_background_adapter,
            "dashboard",
            100,
            "22"
        )
        setActiveState(
            this,
            bhapps_menus_menu_vertical_menu_with_active_gradient_background_adapter,
            "dashboard",
            100
        )
        //endregion bhapps_menus_menu_vertical_menu_with_active_gradient_background

        //region bhapps_menus_menu_vertical_menu_icons_only_right
        bhapps_menus_menu_vertical_menu_icons_only_right = findViewById(R.id.bhapps_menus_menu_vertical_menu_icons_only_right)
        bhapps_menus_menu_vertical_menu_icons_only_right_adapter = VerticalMenuAdapter(
            this,
            VerticalMenuHelper.generateVerticalMenuFromList(
                buildMenuWithParentsOnlyFromList()
            ),
            object : VerticalMenuAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, itemId: Int) {
                    onVerticalMenuIconsOnlyMenuItemSelected(
                        view,
                        itemId
                    )
                }
            }
        )

        bhapps_menus_menu_vertical_menu_icons_only_right.buildVerticalMenu(
            bhapps_menus_menu_vertical_menu_icons_only_right_adapter
        )

        setBadgeStateAndBadgeLabel(
            this,
            bhapps_menus_menu_vertical_menu_icons_only_right_adapter,
            "dashboard",
            100,
            "22"
        )
        //endregion bhapps_menus_menu_vertical_menu_icons_only_right

        //region selected_bhapps_menus_menu_vertical_menu
        val selected_bhapps_menus_menu_vertical_menu: TextView = findViewById(R.id.selected_bhapps_menus_menu_vertical_menu)
        selected_bhapps_menus_menu_vertical_menu.setOnClickListener {
            bhapps_menus_menu_vertical_menu_icons_only.refresh()
            bhapps_menus_menu_vertical_menu.refresh()
        }
        //endregion selected_bhapps_menus_menu_vertical_menu

    }
    
    fun buildMenuWithMultipleTypesFromList(): List<VerticalMenuParentItem> {
        var list = ArrayList<VerticalMenuParentItem>()

        var verticalMenuParentItem0 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem0Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem0.id = Random.nextInt(1, 999)
        verticalMenuParentItem0.group = verticalMenuParentItem0Group
        verticalMenuParentItem0.title = "Dashboard"
        verticalMenuParentItem0.vertical_menu_type = VerticalMenuType.TITLE
        verticalMenuParentItem0.parent = true
        verticalMenuParentItem0.active = false
        list.add(verticalMenuParentItem0)

        var verticalMenuParentItem1 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem1Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem1.id = Random.nextInt(1, 999)
        verticalMenuParentItem1.group = verticalMenuParentItem1Group
        verticalMenuParentItem1.icon = this.getDrawable(R.drawable.data_usage_icon)
        verticalMenuParentItem1.title = "Sales"
        verticalMenuParentItem1.vertical_menu_type = VerticalMenuType.PARENTWITHCHILDREN
        verticalMenuParentItem1.parent = true
        verticalMenuParentItem1.active = false
        verticalMenuParentItem1.show_badge = true
        verticalMenuParentItem1.badge_label = "5"

        //add children
        var verticalMenuParentItem1ChildList = ArrayList<VerticalMenuChildItem>()
        
        var verticalMenuParentItem1Child1 = VerticalMenuChildItem(Random.nextInt(1, 999))
        verticalMenuParentItem1Child1.id = Random.nextInt(1, 999)
        verticalMenuParentItem1Child1.group = verticalMenuParentItem1Group
        verticalMenuParentItem1Child1.icon = this.getDrawable(R.drawable.poll_box_outline_icon)
        verticalMenuParentItem1Child1.title = "Orders"
        verticalMenuParentItem1Child1.vertical_menu_type = VerticalMenuType.CHILD
        verticalMenuParentItem1Child1.parent = false
        verticalMenuParentItem1Child1.parent_id = verticalMenuParentItem1.id
        verticalMenuParentItem1Child1.active = false
        verticalMenuParentItem1Child1.show_badge = true
        verticalMenuParentItem1Child1.badge_label = "22"
        verticalMenuParentItem1Child1.is_first_item = true
        verticalMenuParentItem1ChildList.add(0, verticalMenuParentItem1Child1)

        var verticalMenuParentItem1Child2 = VerticalMenuChildItem(Random.nextInt(1, 999))
        verticalMenuParentItem1Child2.id = Random.nextInt(1, 999)
        verticalMenuParentItem1Child2.group = verticalMenuParentItem1Group
        verticalMenuParentItem1Child2.icon = this.getDrawable(R.drawable.poll_box_outline_icon)
        verticalMenuParentItem1Child2.title = "Tax"
        verticalMenuParentItem1Child2.vertical_menu_type = VerticalMenuType.CHILD
        verticalMenuParentItem1Child2.parent = false
        verticalMenuParentItem1Child1.parent_id = verticalMenuParentItem1.id
        verticalMenuParentItem1Child2.active = false
        verticalMenuParentItem1Child2.show_badge = true
        verticalMenuParentItem1Child2.badge_label = "2"
        verticalMenuParentItem1Child1.is_last_item = true
        verticalMenuParentItem1ChildList.add(0, verticalMenuParentItem1Child2)

        verticalMenuParentItem1.children = verticalMenuParentItem1ChildList
        list.add(verticalMenuParentItem1)

        var verticalMenuParentItem2 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem2Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem2.id = Random.nextInt(1, 999)
        verticalMenuParentItem2.group = verticalMenuParentItem2Group
        verticalMenuParentItem2.icon = this.getDrawable(R.drawable.shield_sync_outline_icon)
        verticalMenuParentItem2.title = "Settings"
        verticalMenuParentItem2.vertical_menu_type = VerticalMenuType.PARENT
        verticalMenuParentItem2.parent = true
        verticalMenuParentItem2.active = false
        verticalMenuParentItem2.show_badge = true
        verticalMenuParentItem2.badge_label = ""
        list.add(verticalMenuParentItem2)

        Log.e("buildMenuFromList", "list.count: " + list.count())
        return list
        
    }

    fun buildMenuWithParentsOnlyFromList(): List<VerticalMenuParentItem> {
        var list = ArrayList<VerticalMenuParentItem>()

        var verticalMenuParentItem0 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem0Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem0.id = Random.nextInt(1, 999)
        verticalMenuParentItem0.group = verticalMenuParentItem0Group
        verticalMenuParentItem0.icon = this.getDrawable(R.drawable.dashboard_icon)
        verticalMenuParentItem0.title = "Dashboard"
        verticalMenuParentItem0.vertical_menu_type = VerticalMenuType.PARENT
        verticalMenuParentItem0.parent = true
        verticalMenuParentItem0.active = true
        verticalMenuParentItem0.show_badge = true
        verticalMenuParentItem0.badge_label = "102"
        list.add(verticalMenuParentItem0)

        var verticalMenuParentItem1 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem1Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem1.id = Random.nextInt(1, 999)
        verticalMenuParentItem1.group = verticalMenuParentItem1Group
        verticalMenuParentItem1.icon = this.getDrawable(R.drawable.data_usage_icon)
        verticalMenuParentItem1.title = "Analytics"
        verticalMenuParentItem1.vertical_menu_type = VerticalMenuType.PARENT
        verticalMenuParentItem1.parent = true
        verticalMenuParentItem1.active = false
        verticalMenuParentItem1.show_badge = true
        verticalMenuParentItem1.badge_label = "5"
        list.add(verticalMenuParentItem1)

        var verticalMenuParentItem2 = VerticalMenuParentItem(Random.nextInt(1, 999))
        var verticalMenuParentItem2Group = Random.nextInt(1, 999).toString()
        verticalMenuParentItem2.id = Random.nextInt(1, 999)
        verticalMenuParentItem2.group = verticalMenuParentItem2Group
        verticalMenuParentItem2.icon = this.getDrawable(R.drawable.shield_sync_outline_icon)
        verticalMenuParentItem2.title = "Settings"
        verticalMenuParentItem2.vertical_menu_type = VerticalMenuType.PARENT
        verticalMenuParentItem2.parent = true
        verticalMenuParentItem2.active = false
        verticalMenuParentItem2.show_badge = true
        verticalMenuParentItem2.badge_label = ""
        list.add(verticalMenuParentItem2)

        Log.e("buildMenuFromList", "list.count: " + list.count())
        return list

    }

    fun onVerticalMenuIconsOnlyMenuItemSelected(view: View?, itemId: Int) {
        var selected_bhapps_menus_menu_vertical_menu: TextView = findViewById(R.id.selected_bhapps_menus_menu_vertical_menu)
        selected_bhapps_menus_menu_vertical_menu.text =
            "Selected: bhapps_menus_menu_vertical_menu_icons_only\n" +
            "Id: " + itemId

    }

    fun onVerticalMenuItemSelected(view: View?, itemId: Int) {
        var selected_bhapps_menus_menu_vertical_menu: TextView = findViewById(R.id.selected_bhapps_menus_menu_vertical_menu)
        selected_bhapps_menus_menu_vertical_menu.text =
            "Selected: bhapps_menus_menu_vertical_menu\n" +
            "Id: " + itemId
    }

}
