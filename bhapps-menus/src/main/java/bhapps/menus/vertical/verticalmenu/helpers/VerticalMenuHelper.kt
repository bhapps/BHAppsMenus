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
                    items.add(
                        VerticalMenuItem(
                            verticalMenuParentItem.vertical_menu_type.value,
                            verticalMenuParentItem.id,
                            verticalMenuParentItem.group,
                            verticalMenuParentItem.title,
                            verticalMenuParentItem.icon,
                            verticalMenuParentItem.vertical_menu_type,
                            verticalMenuParentItem.parent,
                            verticalMenuParentItem.active,
                            verticalMenuParentItem.show_badge,
                            verticalMenuParentItem.badge_label,
                            "",
                            "",
                            ""
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
                                    verticalMenuChildItem.active,
                                    verticalMenuChildItem.show_badge,
                                    verticalMenuChildItem.badge_label,
                                    "",
                                    "",
                                    ""
                                )
                            )
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.e("VerticalMenuHelper", "VerticalMenuHelper.generateVerticalMenuFromList(): " + ex.printStackTrace().toString())
            }
        }

        return items
    }

    public fun generateVerticalMenuFromJsonFile(context: Context, file: String): List<VerticalMenuItem> {
        val items = ArrayList<VerticalMenuItem>()
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
                            var typeFromString: VerticalMenuType? = null
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
                                        typeFromString = VerticalMenuType.PARENT
                                    } else if (getType == 1) {
                                        typeFromString = VerticalMenuType.PARENTWITHCHILDREN
                                    } else if (getType == 2) {
                                        typeFromString = VerticalMenuType.CHILD
                                    } else if (getType == 3) {
                                        typeFromString = VerticalMenuType.DIVIDER
                                    } else if (getType == 4) {
                                        typeFromString = VerticalMenuType.TITLE
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
                                VerticalMenuItem(
                                    typeFromString!!.value,
                                    Integer.parseInt(id.toString()),
                                    group!!,
                                    title!!,
                                    iconFromString,
                                    typeFromString,
                                    parent,
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

    public fun generateVerticalMenuFromFile(context: Context): List<VerticalMenuItem> {
        val items = ArrayList<VerticalMenuItem>()
        if(VERTICALMENU_BUILD_MENU_USING.contains("json")){
            try {

                context.assets.open(VERTICALMENU_BUILD_MENU_JSON_FILE).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->

                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject()

                            var id: String? = null
                            var group: String? = null
                            var title: String? = null
                            var icon: String? = null
                            var type: Int = 0
                            var typeFromString: VerticalMenuType? = null
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
                                        typeFromString = VerticalMenuType.PARENT
                                    } else if (getType == 1) {
                                        typeFromString = VerticalMenuType.PARENTWITHCHILDREN
                                    } else if (getType == 2) {
                                        typeFromString = VerticalMenuType.CHILD
                                    } else if (getType == 3) {
                                        typeFromString = VerticalMenuType.DIVIDER
                                    } else if (getType == 4) {
                                        typeFromString = VerticalMenuType.TITLE
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
                                VerticalMenuItem(
                                    type,
                                    Integer.parseInt(id.toString()),
                                    group!!,
                                    title!!,
                                    iconFromString,
                                    typeFromString!!,
                                    parent,
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