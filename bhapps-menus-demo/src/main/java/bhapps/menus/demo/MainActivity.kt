package bhapps.menus.demo

import android.content.res.Configuration
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
            VerticalMenuHelper.generateVerticalMenuFromJsonFile(
                this,
                "bhapps_menus_menu_vertical_menu_config_icons_only.json"
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
            VerticalMenuHelper.generateVerticalMenuFromJsonFile(
                this,
                "bhapps_menus_menu_vertical_menu_config.json"
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
            VerticalMenuHelper.generateVerticalMenuFromJsonFile(
                this,
                "bhapps_menus_menu_vertical_menu_config_icons_only.json"
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
            VerticalMenuHelper.generateVerticalMenuFromJsonFile(
                this,
                "bhapps_menus_menu_vertical_menu_config_icons_only.json"
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
            VerticalMenuHelper.generateVerticalMenuFromJsonFile(
                this,
                "bhapps_menus_menu_vertical_menu_config_icons_only.json"
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
