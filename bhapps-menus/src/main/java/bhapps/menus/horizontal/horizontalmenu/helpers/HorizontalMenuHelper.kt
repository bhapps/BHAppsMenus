package bhapps.menus.horizontal.horizontalmenu.helpers

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import bhapps.menus.R
import bhapps.menus.horizontal.horizontalmenu.adapters.HorizontalMenuAdapter
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuConfig.HORIZONTALMENU_BUILD_MENU_JSON_FILE
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuConfig.HORIZONTALMENU_BUILD_MENU_USING
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuItem
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuParentItem
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuType
import bhapps.menus.vertical.verticalmenu.adapters.VerticalMenuAdapter
import com.google.gson.stream.JsonReader
import java.util.*

public object HorizontalMenuHelper {

    public fun generateHorizontalMenuFromList(list: List<HorizontalMenuParentItem>): List<HorizontalMenuItem> {
        val items = ArrayList<HorizontalMenuItem>()
        if(list.isNotEmpty()) {
            try {

                for (horizontalMenuParentItem in list) {
                    val group = horizontalMenuParentItem.group
                    items.add(
                        HorizontalMenuItem(
                            horizontalMenuParentItem.horizontal_menu_type.value,
                            horizontalMenuParentItem.id,
                            horizontalMenuParentItem.group,
                            horizontalMenuParentItem.title,
                            horizontalMenuParentItem.icon,
                            horizontalMenuParentItem.horizontal_menu_type,
                            horizontalMenuParentItem.parent,
                            -1,
                            horizontalMenuParentItem.active,
                            horizontalMenuParentItem.show_badge,
                            horizontalMenuParentItem.badge_label,
                            "",
                            "",
                            "",
                            false,
                            false
                        )
                    )
                    if(horizontalMenuParentItem.children !=null) {
                        for (horizontalMenuChildItem in horizontalMenuParentItem.children!!) {
                            items.add(
                                HorizontalMenuItem(
                                    horizontalMenuChildItem.horizontal_menu_type.value,
                                    horizontalMenuChildItem.id,
                                    group,
                                    horizontalMenuChildItem.title,
                                    horizontalMenuChildItem.icon,
                                    horizontalMenuChildItem.horizontal_menu_type,
                                    false,
                                    horizontalMenuParentItem.id,
                                    horizontalMenuChildItem.active,
                                    horizontalMenuChildItem.show_badge,
                                    horizontalMenuChildItem.badge_label,
                                    "",
                                    "",
                                    "",
                                    horizontalMenuChildItem.is_first_item,
                                    horizontalMenuChildItem.is_last_item
                                )
                            )
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.e("HorizontalMenuHelper", "HorizontalMenuHelper.generateHorizontalMenuFromList(): " + ex.printStackTrace().toString())
            }
        }

        return items
    }

    public fun generateHorizontalMenuFromJsonFile(context: Context, file: String): List<HorizontalMenuItem> {
        val items = ArrayList<HorizontalMenuItem>()
        if(file.isNotEmpty()) {
            try {

                context.assets.open(file).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject()

                            var id: String? = null
                            var group: String? = null
                            var title: String? = null
                            var icon: String? = null
                            var typeFromString: HorizontalMenuType? = null
                            var parent: Boolean = false
                            var active: Boolean = false
                            var show_badge: Boolean = false
                            var badge_label: String? = null

                            var activity: String? = null
                            var fragment: String? = null
                            var dialog: String? = null

                            while (jsonReader.hasNext()) {

                                val name = jsonReader.nextName()
                                if (name == "id") {
                                    id = jsonReader.nextString()
                                } else if (name == "group") {
                                    group = jsonReader.nextString()
                                } else if (name == "title") {
                                    title = jsonReader.nextString()
                                } else if (name == "icon") {
                                    icon = jsonReader.nextString()
                                } else if (name == "type") {
                                    var getType = jsonReader.nextInt()
                                    if (getType == 0) {
                                        typeFromString = HorizontalMenuType.PARENT
                                    } else if (getType == 1) {
                                        typeFromString = HorizontalMenuType.PARENTWITHCHILDREN
                                    } else if (getType == 2) {
                                        typeFromString = HorizontalMenuType.CHILD
                                    } else if (getType == 3) {
                                        typeFromString = HorizontalMenuType.DIVIDER
                                    } else if (getType == 4) {
                                        typeFromString = HorizontalMenuType.TITLE
                                    }
                                } else if (name == "parent") {
                                    parent = jsonReader.nextBoolean()
                                } else if (name == "active") {
                                    active = jsonReader.nextBoolean()
                                } else if (name == "show_badge") {
                                    show_badge = jsonReader.nextBoolean()
                                } else if (name == "badge_label") {
                                    badge_label = jsonReader.nextString()
                                } else if (name == "activity") {
                                    activity = jsonReader.nextString()
                                } else if (name == "fragment") {
                                    fragment = jsonReader.nextString()
                                } else if (name == "dialog") {
                                    dialog = jsonReader.nextString()
                                } else {
                                    jsonReader.skipValue()
                                }

                            }

                            var iconFromString = context.resources.getDrawable(
                                icon!!.toInt()
                            )

                            items.add(
                                HorizontalMenuItem(
                                    typeFromString!!.value,
                                    Integer.parseInt(id.toString()),
                                    group!!,
                                    title!!,
                                    iconFromString,
                                    typeFromString,
                                    parent,
                                    -1,
                                    active,
                                    show_badge,
                                    badge_label!!,
                                    activity!!,
                                    fragment!!,
                                    dialog!!
                                )
                            )
                            jsonReader.endObject()
                        }
                        jsonReader.endArray();

                    }
                }

            } catch (ex: Exception) {
                Log.e("error", ex.message.toString())
            }
        }

        return items
    }

    public fun generateHorizontalMenuFromFile(context: Context): List<HorizontalMenuItem> {
        val items = ArrayList<HorizontalMenuItem>()
        if(HORIZONTALMENU_BUILD_MENU_USING.contains("json")){
            try {

                context.assets.open(HORIZONTALMENU_BUILD_MENU_JSON_FILE).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->

                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject()

                            var id: String? = null
                            var group: String? = null
                            var title: String? = null
                            var icon: String? = null
                            var type: Int = 0
                            var typeFromString: HorizontalMenuType? = null
                            var parent: Boolean = false
                            var active: Boolean = false
                            var show_badge: Boolean = false
                            var badge_label: String? = null

                            var activity: String? = null
                            var fragment: String? = null
                            var dialog: String? = null

                            while (jsonReader.hasNext()) {

                                val name = jsonReader.nextName()
                                if (name == "id") {
                                    id = jsonReader.nextString()
                                } else if (name == "group") {
                                    group = jsonReader.nextString()
                                } else if (name == "title") {
                                    title = jsonReader.nextString()
                                } else if (name == "icon") {
                                    icon = jsonReader.nextString()
                                } else if (name == "type") {
                                    var getType = jsonReader.nextInt()
                                    type = jsonReader.nextInt()
                                    if (getType == 0) {
                                        typeFromString = HorizontalMenuType.PARENT
                                    } else if (getType == 1) {
                                        typeFromString = HorizontalMenuType.PARENTWITHCHILDREN
                                    } else if (getType == 2) {
                                        typeFromString = HorizontalMenuType.CHILD
                                    } else if (getType == 3) {
                                        typeFromString = HorizontalMenuType.DIVIDER
                                    } else if (getType == 4) {
                                        typeFromString = HorizontalMenuType.TITLE
                                    }
                                } else if (name == "parent") {
                                    parent = jsonReader.nextBoolean()
                                } else if (name == "active") {
                                    active = jsonReader.nextBoolean()
                                } else if (name == "show_badge") {
                                    show_badge = jsonReader.nextBoolean()
                                } else if (name == "badge_label") {
                                    badge_label = jsonReader.nextString()
                                } else if (name == "activity") {
                                    activity = jsonReader.nextString()
                                } else if (name == "fragment") {
                                    fragment = jsonReader.nextString()
                                } else if (name == "dialog") {
                                    dialog = jsonReader.nextString()
                                } else {
                                    jsonReader.skipValue()
                                }

                            }


                            var iconFromString = context.resources.getDrawable(
                                icon!!.toInt()
                            )

                            items.add(
                                HorizontalMenuItem(
                                    type,
                                    Integer.parseInt(id.toString()),
                                    group!!,
                                    title!!,
                                    iconFromString,
                                    typeFromString!!,
                                    parent,
                                    -1,
                                    active,
                                    show_badge,
                                    badge_label!!,
                                    activity!!,
                                    fragment!!,
                                    dialog!!
                                )
                            )
                            jsonReader.endObject()
                        }
                        jsonReader.endArray();

                    }
                }

            } catch (exception: Exception) {
                Log.e("exception", exception.message.toString())
            }
        }

        return items
    }

    public fun navigateToAction(activity: Activity, fragment: Fragment, addToBackStack: Boolean) {
        if(fragment !=null){

        }
    }

    public fun setMenuItemTitle(title: String, adapter: HorizontalMenuAdapter, group: String, id: Int) {
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
        adapter: HorizontalMenuAdapter,
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
        adapter: HorizontalMenuAdapter
    ) {
        if(adapter!!.itemCount > 0) {
            adapter!!.items.forEach { it ->
                it.active = false
            }
            adapter.notifyDataSetChanged()
        }
    }

    public fun setBadgeStateAndBadgeLabel(context: Context, adapter: HorizontalMenuAdapter, group: String, id: Int, badgeValue: String) {

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

    public fun removeBadgeStateAndBadgeLabel(context: Context, adapter: HorizontalMenuAdapter, group: String, id: Int) {
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