package bhapps.menus.vertical.verticalmenu.helpers

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import bhapps.menus.R
import bhapps.menus.vertical.verticalmenu.adapters.VerticalMenuAdapter
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_BUILD_MENU_JSON_FILE
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_BUILD_MENU_USING
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuItem
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuParentItem
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuType
import com.google.gson.stream.JsonReader
import java.util.*

public object VerticalMenuHelper {

    fun View.dpToPx(dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    public fun generateVerticalMenuFromList(list: List<VerticalMenuParentItem>): List<VerticalMenuItem> {
        val items = ArrayList<VerticalMenuItem>()
        if(list.isNotEmpty()) {
            try {

                for (verticalMenuParentItem in list) {
                    val group = verticalMenuParentItem.group

                    /*
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
                    _dialog: String,
                    _is_first_item: Boolean = false,
                    _is_last_item: Boolean = false
                    */

                    items.add(
                        VerticalMenuItem(
                            verticalMenuParentItem.vertical_menu_type.value,
                            verticalMenuParentItem.id,
                            verticalMenuParentItem.group,
                            verticalMenuParentItem.title,
                            verticalMenuParentItem.icon,
                            verticalMenuParentItem.vertical_menu_type,
                            verticalMenuParentItem.parent,
                            -1,
                            verticalMenuParentItem.active,
                            verticalMenuParentItem.show_badge,
                            verticalMenuParentItem.badge_label,
                            verticalMenuParentItem.activity!!,
                            verticalMenuParentItem.fragment!!,
                            verticalMenuParentItem.key!!,
                            verticalMenuParentItem.value!!,
                            verticalMenuParentItem.dialog!!,
                            verticalMenuParentItem.is_first_item!!,
                            verticalMenuParentItem.is_last_item!!
                        )
                    )
                    if(verticalMenuParentItem.children !=null) {
                        for (verticalMenuChildItem in verticalMenuParentItem.children!!) {
                            items.add(
                                VerticalMenuItem(
                                    verticalMenuChildItem.vertical_menu_type.value,
                                    verticalMenuChildItem.id,
                                    group,
                                    verticalMenuChildItem.title,
                                    verticalMenuChildItem.icon,
                                    verticalMenuChildItem.vertical_menu_type,
                                    false,
                                    verticalMenuParentItem.id,
                                    verticalMenuChildItem.active,
                                    verticalMenuChildItem.show_badge,
                                    verticalMenuChildItem.badge_label,
                                    verticalMenuChildItem.activity!!,
                                    verticalMenuChildItem.fragment!!,
                                    verticalMenuChildItem.key!!,
                                    verticalMenuChildItem.value!!,
                                    verticalMenuChildItem.dialog!!,
                                    verticalMenuChildItem.is_first_item!!,
                                    verticalMenuChildItem.is_last_item!!
                                )
                            )
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.e("VerticalMenuHelper", "VerticalMenuHelper.generateVerticalMenuFromList(): " + ex.message)
            }
        }

        return items
    }

    public fun navigateToAction(activity: Activity, fragment: Fragment, addToBackStack: Boolean) {
        if(fragment !=null){

        }
    }

    public fun setMenuItemTitle(title: String, adapter: VerticalMenuAdapter, group: String, id: Int) {
        if(group !=null){
            if(id !=null){
                if(adapter!!.itemCount > 0) {
                    adapter!!.items.forEach { it ->
                        if (it != null) {
                            if(it.group == group){
                                if(it.id == id){
                                    it.title = title
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    public fun setActiveState(
        context: Context,
        adapter: VerticalMenuAdapter,
        group: String,
        id: Int
    ) {
        if(group !=null){
            if(id !=null){
                if(adapter!!.itemCount > 0) {
                    adapter!!.items.forEach { it ->
                        if (it != null) {
                            if(it.group == group){
                                if(it.id == id){
                                    it.active = true
                                }else{
                                    it.active = false
                                }
                                if(it.is_parent){
                                    it.active = true
                                }
                            }else{
                                it.active = false
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    public fun setNoActiveStates(
        adapter: VerticalMenuAdapter
    ) {
        if(adapter!!.itemCount > 0) {
            adapter!!.items.forEach { it ->
                it.active = false
            }
            adapter.notifyDataSetChanged()
        }
    }

    public fun setBadgeStateAndBadgeLabel(context: Context, adapter: VerticalMenuAdapter, group: String, id: Int, badgeValue: String) {

        if(group !=null){
            if(id !=null){
                if(adapter!!.itemCount > 0) {
                    adapter!!.items.forEach { it ->
                        if (it != null) {
                            if(it.group == group){
                                if(it.id == id){
                                    it.show_badge = true
                                    it.badge_label = setBadgeLabelAsPerLimit(context, badgeValue)
                                }
                                if(it.is_parent){
                                    it.show_badge = true
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    public fun removeBadgeStateAndBadgeLabel(context: Context, adapter: VerticalMenuAdapter, group: String, id: Int) {
        if(group !=null){
            if(id !=null){
                if(adapter!!.itemCount > 0) {
                    adapter!!.items.forEach { it ->
                        if (it != null) {
                            if(it.group == group){
                                if(it.id == id){
                                    it.show_badge = false
                                    it.badge_label = ""
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    public fun setBadgeLabelAsPerLimit(context: Context, badgeValue: String): String {

        var result = ""

        /*
            function to display badge label text on a specific Bottom Navigation View Menu Item
            if more than 2 characters (i.e: more than 100), the label will display R.strings.bhapps_menus_badge_label_max_text text

            calls requires:
                String Value
        */

        if (badgeValue.length == 2 || badgeValue.length == 1 || badgeValue.length == 0) {
            result = badgeValue
        }else{
            result = context.getString(R.string.bhapps_menus_badge_label_max_text)
        }

        return result

    }

    /*
    * https://stackoverflow.com/a/55888151/1423608
    */
    fun drawBadgeWithColors(badgeBackgroundColor: Int, badgeBorderColor: Int, badgeBorderThickness: Int): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        shape.setColor(badgeBackgroundColor)
        if(badgeBorderThickness != 0) {
            shape.setStroke(badgeBorderThickness, badgeBorderColor)
        }
        return shape
    }

}