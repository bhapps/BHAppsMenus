package bhapps.menus.horizontal.horizontalmenu.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import bhapps.menus.R
import bhapps.menus.horizontal.horizontalmenu.extensions.getIntToDp
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuConfig.HORIZONTALMENU_CHILDITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuConfig.HORIZONTALMENU_PARENTITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuHelper.drawBadgeWithColors
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuHelper.removeBadgeStateAndBadgeLabel
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuHelper.setActiveState
import bhapps.menus.horizontal.horizontalmenu.helpers.HorizontalMenuHelper.setBadgeLabelAsPerLimit
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuItem
import bhapps.menus.horizontal.horizontalmenu.views.BadgeConstraintView

class HorizontalMenuAdapter(
    context: Context,
    items: List<HorizontalMenuItem>,
    onItemClickListener: OnItemClickListener
) : HorizontalMenuExpandableAdapter<HorizontalMenuItem>(context) {
    
    val horizontalMenuAdapter: HorizontalMenuAdapter = this
    val activity: Activity? = null
    val context: Context
    var onItemClickListener: OnItemClickListener? = null
    var getInactiveTextColour: Int = 0
    var getInactiveTextColourIsSet: Boolean = false

    var horizontalMenuItemsUISettings = HashMap<String, Any?>()

    init {
        this.context = context
        this.onItemClickListener = onItemClickListener
        if (items != null) {
            this.items = items
        }
    }

    companion object {
        var onItemClickListener: OnItemClickListener? = null
    }

    //region ParentOnlyMenuTypeViewHolder
    inner class ParentOnlyMenuTypeViewHolder(var view: View) :
        ViewHolder(view) {
        var bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_parent_item_layout: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_parent_item_parent_title: TextView
        var bhapps_menus_menu_horizontal_menu_parent_item_parent_icon: ImageView
        var bhapps_menus_menu_horizontal_menu_parent_item_parent_badge: BadgeConstraintView
        var bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label: TextView
        var bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon: ImageView
        var bhapps_menus_menu_horizontal_menu_parent_item_indicator: View
        var bhapps_menus_menu_horizontal_menu_parent_item_divider: View
        fun bind(position: Int) {

            if(horizontalMenuItemsUISettings["horizontal_menu_height"] !=null){
                if(horizontalMenuItemsUISettings["horizontal_menu_height"] as Int != 0){
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_height"] as Int
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_item_layout.setBackgroundColor(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int
                )
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] !=null) {
                bhapps_menus_menu_horizontal_menu_parent_item_layout.background =
                   horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] as Drawable
            }

            //region set padding
            if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int > 0){
                //setPadding(int left, int top, int right, int bottom)
                bhapps_menus_menu_horizontal_menu_parent_item_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int
                )
            }else{
                //setPadding(int left, int top, int right, int bottom)

                bhapps_menus_menu_horizontal_menu_parent_item_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_bottom"] as Int
                )
            }
            //endregion set padding

            //region set margin
            if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int > 0){
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int
                )
                bhapps_menus_menu_horizontal_menu_parent_item_layout.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params
            }else{
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_bottom"] as Int
                )
                bhapps_menus_menu_horizontal_menu_parent_item_layout.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_layout_layout_params
            }
            //endregion set margin

            //region set icon
            if (horizontalMenuItemsUISettings["horizontal_menu_show_icons"] as Boolean && horizontalMenuItemsUISettings["horizontal_menu_show_parent_icons"] as Boolean) {
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.visibility = View.VISIBLE

                    try {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setImageDrawable(visibleItems!![position].icon)
                    }catch (exception: Exception) {
                        Log.e("setImageDrawable", "setImageDrawable.exception: " + exception.printStackTrace())
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                        )
                    }

                    val bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_width"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_width"] as Int
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_height"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.height = horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_height"] as Int
                    }

                    //region set padding
                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int > 0){
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int
                        )
                    }else{
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_bottom"] as Int
                        )
                    }
                    //endregion set padding

                    //region set margin
                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int > 0){
                        //setMargins(int left, int top, int right, int bottom)

                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id

                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params
                    }else{
                        //setMargins(int left, int top, int right, int bottom)

                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id

                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_bottom"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params
                    }
                    //endregion set margin

                } else {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.visibility = View.GONE
                }

            } else {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.visibility = View.GONE
            }
            //endregion set icon

            //region set title
            bhapps_menus_menu_horizontal_menu_parent_item_parent_title.text =
                visibleItems!![position]!!.title

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_capitalized"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.isAllCaps =
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_capitalized"] as Boolean
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_size"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.textSize =
                    bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_size"] as Int)).toFloat()
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTextColor(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int
                )

                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int
                    getInactiveTextColourIsSet = true
                }
            }else{
                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.currentTextColor
                    getInactiveTextColourIsSet = true
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_bold"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.BOLD)
            }else{
                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.NORMAL)
            }
            //endregion set title

            //region set badge
            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_visible"] as Boolean) {
                if (
                    !visibleItems!![position].badge_label.isNullOrBlank() ||
                    !visibleItems!![position].badge_label.isNullOrEmpty() &&
                    visibleItems!![position].show_badge
                ) {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.visibility = View.VISIBLE

                        //region set padding
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int > 0){
                            //setPadding(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int
                            )
                        }else{
                            //setPadding(int left, int top, int right, int bottom)
                            //set default padding if values not passed
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.setPadding(
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_left"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_top"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_right"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_bottom"] as Int
                            )
                        }
                        //endregion set padding

                        //region set margin
                        var bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int > 0){
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] as Boolean) {
                                bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.id
                            }else{
                                bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.startToEnd = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id
                            }

                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int
                            )
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] as Boolean) {
                                bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.id
                            }else{
                                bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.startToEnd = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id
                            }

                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_bottom"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_width"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_width"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.width = getIntToDp(context, HORIZONTALMENU_PARENTITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_height"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.height = horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_height"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.height = getIntToDp(context, HORIZONTALMENU_PARENTITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params
                        //endregion set margin

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_size"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.textSize =
                                bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_size"] as Int)).toFloat()
                        }

                        bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.text = visibleItems!![position].badge_label
                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.setTextColor(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_color"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_bold"] as Boolean) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.setTypeface(null, Typeface.BOLD)
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.setTypeface(null, Typeface.NORMAL)
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_visible"] as Boolean) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.visibility = View.VISIBLE
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.text = setBadgeLabelAsPerLimit(view.context, visibleItems!![position].badge_label)
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label.visibility = View.GONE
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_background_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.setBackground(
                                drawBadgeWithColors(
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_background_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_thickness"] as Int
                                )
                            )
                        }
                } else {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.visibility = View.GONE
                }
            } else {
                bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.visibility = View.GONE
            }
            //endregion set badge

            //region set navigation_icon
            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.VISIBLE

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_width"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_width"] as Int
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_height"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_height"] as Int
                }
                
                //region set padding
                if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] as Int > 0){
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setPadding(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] as Int
                    )
                }else{
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_bottom"] as Int
                    )
                }
                //endregion set padding

                //region set margin
                if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] as Int > 0){
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.startToEnd = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.setMargins(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params
                }else{
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.startToEnd = bhapps_menus_menu_horizontal_menu_parent_item_parent_title.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params.setMargins(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_left"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_top"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_right"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_bottom"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon_layout_params
                }
                //endregion set margin

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setImageDrawable(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_drawable"] as Drawable
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_color"] as Int
                    )
                }


            }else{
                bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.GONE
            }
            //endregion set navigation_icon

            //region set divider
            if (horizontalMenuItemsUISettings["horizontal_menu_show_parent_divider"] as Boolean) {
                if (horizontalMenuItemsUISettings["horizontal_menu_divider_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_item_divider.visibility = View.VISIBLE

                    //region set padding
                    if(horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int > 0){
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int
                        )
                    }else{
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_divider_padding_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_padding_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_padding_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_padding_bottom"] as Int
                        )
                    }
                    //endregion set padding

                    //region set margin
                    if(horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int > 0){
                        //setMargins(int left, int top, int right, int bottom)
                        val bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params =
                            ConstraintLayout.LayoutParams(
                                1,
                                ConstraintLayout.LayoutParams.MATCH_PARENT
                            )

                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params
                    }else{
                        //setMargins(int left, int top, int right, int bottom)
                        val bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params =
                            ConstraintLayout.LayoutParams(
                                1,
                                ConstraintLayout.LayoutParams.MATCH_PARENT
                            )

                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                        bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_bottom"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_divider_layout_params
                    }
                    //endregion set margin

                    if (horizontalMenuItemsUISettings["horizontal_menu_divider_width"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_divider_width"] as Int
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_divider_height"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_divider.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_divider_height"] as Int
                    }

                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_divider.visibility = View.GONE
                }
            }else{
                bhapps_menus_menu_horizontal_menu_parent_item_divider.visibility = View.GONE
            }
            //endregion set divider

            //region set indicator
            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_is_visible"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_item_indicator.visibility = View.VISIBLE
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_indicator.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int

                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] !=null) {
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] as Int == 2) {

                            var layoutParamsForIndicatorPosition = ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int
                            )

                            layoutParamsForIndicatorPosition.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            bhapps_menus_menu_horizontal_menu_parent_item_indicator.layoutParams = layoutParamsForIndicatorPosition

                        }
                    }

                }
            }else{
                bhapps_menus_menu_horizontal_menu_parent_item_indicator.visibility = View.GONE
            }
            //endregion set indicator

            //region set setOnClickListener
            view.setOnClickListener { view ->
                onItemClickListener!!.onItemClick(view, visibleItems!![position]!!.id, position, visibleItems!![position]!!)

                setActiveState(
                    context,
                    horizontalMenuAdapter,
                    visibleItems!![position]!!.group,
                    visibleItems!![position]!!.id
                )

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_cleared_on_active"] as Boolean) {
                    removeBadgeStateAndBadgeLabel(
                        context,
                        horizontalMenuAdapter,
                        visibleItems!![position]!!.group,
                        visibleItems!![position]!!.id
                    )
                }
                
            }
            //endregion set setOnClickListener

            //region set active-inactive states
            if (visibleItems!![position]!!.active) {
                //region set active state
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.background =
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_drawable"] as Drawable
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_indicator.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_active_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_active_tint_color"] as Int
                    )
                }else{
                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                        )
                    }
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_is_bold"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.BOLD)
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.NORMAL)
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTextColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] as Boolean) {
                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_hidden_when_active"] as Boolean) {
                        bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility =
                            View.GONE
                    } else {
                        bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility =
                            View.VISIBLE
                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_navigation_icon_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setColorFilter(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_navigation_icon_color"] as Int
                            )
                        }
                    }
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.GONE
                }
                //endregion set active state
            } else {
                //region set inactive state
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int
                    )
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.setBackgroundColor(
                        context.resources.getColor(android.R.color.transparent)
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_item_layout.background =
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] as Drawable
                }

                bhapps_menus_menu_horizontal_menu_parent_item_indicator.setBackgroundColor(
                    context.resources.getColor(android.R.color.transparent)
                )

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                    )
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.setColorFilter(0)
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_bold"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.BOLD)
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTypeface(null, Typeface.NORMAL)
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_item_parent_title.setTextColor(
                        getInactiveTextColour
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.VISIBLE
                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_color"] as Int
                        )
                    }
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.GONE
                }
                //endregion set inactive state
            }
            //endregion set active-inactive states

            if (horizontalMenuItemsUISettings["horizontal_menu_show_icons_only"] as Boolean || horizontalMenuItemsUISettings["horizontal_menu_is_responsive_menu"] as Boolean) {

                bhapps_menus_menu_horizontal_menu_parent_item_parent_title.visibility = View.GONE
                bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon.visibility = View.GONE

                val bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                )

                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                bhapps_menus_menu_horizontal_menu_parent_item_parent_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_icon_layout_params

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_visible"] as Boolean) {
                    if (visibleItems!![position].badge_label != null) {
                        if (visibleItems!![position].show_badge) {
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.visibility = View.VISIBLE

                            val bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params =
                                ConstraintLayout.LayoutParams(
                                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )

                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout.id

                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_bottom"] as Int
                            )

                            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge.layoutParams = bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_layout_params

                        }
                    }
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_is_visible"] as Boolean) {
                    var horizontal_menu_parent_items_indicator_thickness = 2
                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int != 0) {
                        horizontal_menu_parent_items_indicator_thickness = horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int
                    }

                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] !=null) {
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] as Int == 2) {
                            //show indicator left
                            var layoutParamsForIndicatorPosition = ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                                getIntToDp(context, horizontal_menu_parent_items_indicator_thickness)
                            )

                            layoutParamsForIndicatorPosition.startToStart = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            bhapps_menus_menu_horizontal_menu_parent_item_indicator.layoutParams = layoutParamsForIndicatorPosition

                        }else{
                            //show indicator right
                            val layoutParamsForIndicatorPosition =
                                ConstraintLayout.LayoutParams(
                                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                                    getIntToDp(context, horizontal_menu_parent_items_indicator_thickness)
                                )

                            layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                            layoutParamsForIndicatorPosition.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id

                            bhapps_menus_menu_horizontal_menu_parent_item_indicator.layoutParams = layoutParamsForIndicatorPosition
                        }
                    }else{
                        //show indicator right
                        val layoutParamsForIndicatorPosition =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                                getIntToDp(context, horizontal_menu_parent_items_indicator_thickness)
                            )

                        layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id
                        layoutParamsForIndicatorPosition.endToEnd = bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper.id

                        bhapps_menus_menu_horizontal_menu_parent_item_indicator.layoutParams = layoutParamsForIndicatorPosition
                    }

                }

                if(horizontalMenuItemsUISettings["horizontal_menu_is_responsive_menu"] as Boolean) {

                }

            }
        }

        init {
            bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_layout_wrapper) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_parent_item_layout =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_layout) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_parent_item_parent_title =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_parent_title) as TextView
            bhapps_menus_menu_horizontal_menu_parent_item_parent_icon =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_parent_icon) as ImageView
            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_parent_badge) as BadgeConstraintView
            bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_parent_badge_label) as TextView
            bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_item_navigation_icon) as ImageView
            bhapps_menus_menu_horizontal_menu_parent_item_indicator =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_parent_item_indicator) as View
            bhapps_menus_menu_horizontal_menu_parent_item_divider =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_parent_item_divider) as View
        }
    }
    //endregion ParentOnlyMenuTypeViewHolder

    //region ParentWithChildItemsMenuTypeViewHolder
    inner class ParentWithChildItemsMenuTypeViewHolder(var view: View) :
        HeaderViewHolder(
            view,
            view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow) as ImageView
        ) {
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_title: TextView
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon: ImageView
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge: BadgeConstraintView
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label: TextView
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow: ImageView
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator: View
        var bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider: View
        override fun bind(position: Int) {
            super.bind(position)

            if(horizontalMenuItemsUISettings["horizontal_menu_height"] !=null){
                if(horizontalMenuItemsUISettings["horizontal_menu_height"] as Int != 0){
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_height"] as Int
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setBackgroundColor(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int
                )
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] !=null) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.background =
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] as Drawable
            }

            //region set padding
            if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int > 0){
                //setPadding(int left, int top, int right, int bottom)
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] as Int
                )
            }else{
                //setPadding(int left, int top, int right, int bottom)
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_bottom"] as Int
                )
            }
            //endregion set padding

            //region set margin
            if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int > 0){
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] as Int
                )
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params
            }else{
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_bottom"] as Int
                )
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_layout_params
            }
            //endregion set margin

            //region set icon
            if (horizontalMenuItemsUISettings["horizontal_menu_show_icons"] as Boolean && horizontalMenuItemsUISettings["horizontal_menu_show_parent_icons"] as Boolean) {
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.visibility = View.VISIBLE

                    try {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setImageDrawable(visibleItems!![position].icon)
                    }catch (exception: Exception) {
                        Log.e("setImageDrawable", "setImageDrawable.exception: " + exception.printStackTrace())
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                        )
                    }else{
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setColorFilter(0)
                    }

                    val bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_width"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_width"] as Int
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_height"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.height = horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_height"] as Int
                    }

                    //region set padding
                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int > 0){
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] as Int
                        )
                    }else{
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_bottom"] as Int
                        )
                    }
                    //endregion set padding

                    //region set margin
                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int > 0){
                        //setMargins(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params
                    }else{
                        //setMargins(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_bottom"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon_layout_params
                    }
                    //endregion set margin

                } else {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.visibility = View.GONE
                }

            } else {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.visibility = View.GONE
            }
            //endregion set icon

            //region set title
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.text =
                visibleItems!![position]!!.title

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_capitalized"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.isAllCaps =
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_capitalized"] as Boolean
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_size"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.textSize =
                    bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_size"] as Int)).toFloat()
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTextColor(
                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int
                )
                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int
                    getInactiveTextColourIsSet = true
                }
            }else{
                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.currentTextColor
                    getInactiveTextColourIsSet = true
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_bold"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.BOLD)
            }else{
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.NORMAL)
            }
            //endregion set title

            //region set badge
            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_visible"] as Boolean) {
                if (
                    !visibleItems!![position].badge_label.isNullOrBlank() ||
                    !visibleItems!![position].badge_label.isNullOrEmpty() &&
                    visibleItems!![position].show_badge
                ) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.visibility = View.VISIBLE

                        //region set padding
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int > 0){
                            //setPadding(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] as Int
                            )
                        }else{
                            //setPadding(int left, int top, int right, int bottom)
                            //set default padding if values not passed
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_bottom"] as Int
                            )
                        }
                        //endregion set padding

                        //region set margin
                        val bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int > 0){
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] as Int
                            )
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.endToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.setMargins(
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_left"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_top"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_right"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_bottom"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_width"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_width"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.width = getIntToDp(context, HORIZONTALMENU_PARENTITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_height"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.height = horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_height"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params.height = getIntToDp(context, HORIZONTALMENU_PARENTITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_layout_params
                        //endregion set margin

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_size"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.textSize =
                                bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_size"] as Int)).toFloat()
                        }

                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.text = visibleItems!![position].badge_label
                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.setTextColor(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_color"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_bold"] as Boolean) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.setTypeface(null, Typeface.BOLD)
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.setTypeface(null, Typeface.NORMAL)
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_visible"] as Boolean) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.visibility = View.VISIBLE
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.text = setBadgeLabelAsPerLimit(view.context, visibleItems!![position].badge_label)
                        }else{
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label.visibility = View.GONE
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_background_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.setBackground(
                                drawBadgeWithColors(
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_background_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_thickness"] as Int
                                )
                            )
                        }
                } else {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.visibility = View.GONE
                }
            } else {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge.visibility = View.GONE
            }
            //endregion set badge

            //region set collapse_icon
            if (horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_is_visible"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.visibility = View.VISIBLE

                if (horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_width"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_width"] as Int
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_height"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_height"] as Int
                }

                //region set padding
                if(horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] as Int > 0){
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.setPadding(
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] as Int
                    )
                }else{
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_bottom"] as Int
                    )
                }
                //endregion set padding

                //region set margin
                if(horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] as Int > 0){
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id

                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.setMargins(
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params
                }else{
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id

                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_bottom"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow_layout_params
                }
                //endregion set margin

                if (horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.setImageDrawable(
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_drawable"] as Drawable
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_color"] as Int
                    )
                }


            }else{
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow.visibility = View.GONE
            }
            //endregion set collapse_icon

            //region set divider
            if (horizontalMenuItemsUISettings["horizontal_menu_show_parent_divider"] as Boolean) {
                if (horizontalMenuItemsUISettings["horizontal_menu_divider_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.visibility = View.VISIBLE

                    //region set padding
                    if(horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int > 0){
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] as Int
                        )
                    }else{
                        //setPadding(int left, int top, int right, int bottom)
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.setPadding(
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_padding_bottom"] as Int
                        )
                    }
                    //endregion set padding

                    //region set margin
                    if(horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] as Int > 0){
                        //setMargins(int left, int top, int right, int bottom)
                        val bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                1
                            )

                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id

                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_divider_margin_bottom"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params
                    }else{
                        //setMargins(int left, int top, int right, int bottom)
                        val bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                1
                            )

                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.startToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.id

                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_divider_margin_bottom"] as Int
                        )
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.layoutParams = bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider_layout_params
                    }
                    //endregion set margin

                    if (horizontalMenuItemsUISettings["horizontal_menu_divider_width"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_divider_width"] as Int
                    }

                    if (horizontalMenuItemsUISettings["horizontal_menu_divider_height"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_divider_height"] as Int
                    }

                }else{
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.visibility = View.GONE
                }
            }else{
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider.visibility = View.GONE
            }
            //endregion set divider

            //region set indicator
            if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_is_visible"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.visibility = View.VISIBLE
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int

                    if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] !=null) {
                        if(horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] as Int == 2) {

                            var layoutParamsForIndicatorPosition = ConstraintLayout.LayoutParams(
                                horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] as Int,
                                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                            )

                            layoutParamsForIndicatorPosition.startToStart = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                            layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                            layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper.id
                            bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.layoutParams = layoutParamsForIndicatorPosition

                        }
                    }

                }
            }else{
                bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.visibility = View.GONE
            }
            //endregion set indicator
            
            if (visibleItems!![position]!!.active) {

                //region set active state
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.background =
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_drawable"] as Drawable
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_active_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_active_tint_color"] as Int
                    )
                }else{
                    if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                        )
                    }
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_is_bold"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.BOLD)
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.NORMAL)
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTextColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_cleared_on_active"] as Boolean) {
                    //todo: add clear badge on active
                }

                //endregion set active state

            } else {

                //region set inactive state
                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] as Int
                    )
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.setBackgroundColor(
                        context.resources.getColor(android.R.color.transparent)
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout.background =
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] as Drawable
                }

                bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator.setBackgroundColor(
                    context.resources.getColor(android.R.color.transparent)
                )

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_bold"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.BOLD)
                }else{
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTypeface(null, Typeface.NORMAL)
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_parent_with_child_items_title.setTextColor(
                        getInactiveTextColour
                    )
                }
                //endregion set inactive state
            }

        }

        init {
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout_wrapper) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_title =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_title) as TextView
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_icon) as ImageView
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge) as BadgeConstraintView
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_badge_label) as TextView
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_arrow) as ImageView
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_indicator) as View
            bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_parent_with_child_items_divider) as View
        }

    }
    //endregion ParentWithChildItemsMenuTypeViewHolder

    //region ChildMenuTypeViewHolder
    inner class ChildMenuTypeViewHolder(var view: View) :
        ViewHolder(view) {
        var bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_child_items_layout: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_child_items_title: TextView
        var bhapps_menus_menu_horizontal_menu_child_items_icon: ImageView
        var bhapps_menus_menu_horizontal_menu_child_items_badge: BadgeConstraintView
        var bhapps_menus_menu_horizontal_menu_child_items_badge_label: TextView
        var bhapps_menus_menu_horizontal_menu_child_items_divider: View
        var bhapps_menus_menu_horizontal_menu_child_items_indicator: View
        fun bind(position: Int) {
            
            if(horizontalMenuItemsUISettings["horizontal_menu_height"] !=null){
                if(horizontalMenuItemsUISettings["horizontal_menu_height"] as Int != 0){
                    bhapps_menus_menu_horizontal_menu_child_items_layout.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_height"] as Int
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_background_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_child_items_layout.setBackgroundColor(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_background_color"] as Int
                )
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_background_drawable"] !=null) {
                bhapps_menus_menu_horizontal_menu_child_items_layout.background =
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_background_drawable"] as Drawable
            }

            //region set padding
            if(horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] as Int > 0){
                //setPadding(int left, int top, int right, int bottom)
                bhapps_menus_menu_horizontal_menu_child_items_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] as Int
                )
            }else{
                //setPadding(int left, int top, int right, int bottom)
                bhapps_menus_menu_horizontal_menu_child_items_layout.setPadding(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_bottom"] as Int
                )
            }
            //endregion set padding

            //region set margin
            if(horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] as Int > 0){
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] as Int
                )
                bhapps_menus_menu_horizontal_menu_child_items_layout.layoutParams = bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params
            }else{
                //setMargins(int left, int top, int right, int bottom)
                val bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params =
                    ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params.setMargins(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_left"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_top"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_right"] as Int,
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_bottom"] as Int
                )
                bhapps_menus_menu_horizontal_menu_child_items_layout.layoutParams = bhapps_menus_menu_horizontal_menu_child_items_layout_layout_params
            }
            //endregion set margin

            //region set icon
            if (horizontalMenuItemsUISettings["horizontal_menu_show_icons"] !=null && horizontalMenuItemsUISettings["horizontal_menu_show_child_icons"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_show_icons"] as Boolean && horizontalMenuItemsUISettings["horizontal_menu_show_child_icons"] as Boolean) {
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_is_visible"] as Boolean) {
                        bhapps_menus_menu_horizontal_menu_child_items_icon.visibility = View.VISIBLE

                        try {
                            bhapps_menus_menu_horizontal_menu_child_items_icon.setImageDrawable(visibleItems!![position].icon)
                        }catch (exception: Exception) {
                            Log.e("setImageDrawable", "setImageDrawable.exception: " + exception.printStackTrace())
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_icon.setColorFilter(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int
                            )
                        } else {
                            bhapps_menus_menu_horizontal_menu_child_items_icon.setColorFilter(0)
                        }

                        val bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )


                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_width"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.width =
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_width"] as Int
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_height"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.height =
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_height"] as Int
                        }

                        //region set padding
                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] as Int > 0) {
                            //setPadding(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_child_items_icon.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] as Int
                            )
                        } else {
                            //setPadding(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_child_items_icon.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_bottom"] as Int
                            )
                        }
                        //endregion set padding

                        //region set margin
                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] as Int > 0) {
                            //setMargins(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.topToTop =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.startToStart =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.bottomToBottom =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.endToStart =
                                bhapps_menus_menu_horizontal_menu_child_items_title.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] as Int
                            )
                            bhapps_menus_menu_horizontal_menu_child_items_icon.layoutParams =
                                bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params
                        } else {
                            //setMargins(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.topToTop =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.startToStart =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.bottomToBottom =
                                bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.endToStart =
                                bhapps_menus_menu_horizontal_menu_child_items_title.id
                            bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_bottom"] as Int
                            )
                            bhapps_menus_menu_horizontal_menu_child_items_icon.layoutParams =
                                bhapps_menus_menu_horizontal_menu_child_items_icon_layout_params
                        }
                        //endregion set margin

                    } else {
                        bhapps_menus_menu_horizontal_menu_child_items_icon.visibility = View.GONE
                    }

                } else {
                    bhapps_menus_menu_horizontal_menu_child_items_icon.visibility = View.GONE
                }
            }
            //endregion set icon

            //region set title
            bhapps_menus_menu_horizontal_menu_child_items_title.text = visibleItems!![position]!!.title

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_capitalized"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_capitalized"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_child_items_title.isAllCaps =
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_capitalized"] as Boolean
                }else{
                    bhapps_menus_menu_horizontal_menu_child_items_title.isAllCaps =
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_capitalized"] as Boolean
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_size"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_child_items_title.textSize =
                    bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_size"] as Int)).toFloat()
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_child_items_title.setTextColor(
                    horizontalMenuItemsUISettings["horizontal_menu_child_items_text_color"] as Int
                )
                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] as Int
                    getInactiveTextColourIsSet = true
                }
            }else{
                if(!getInactiveTextColourIsSet){
                    getInactiveTextColour = bhapps_menus_menu_horizontal_menu_child_items_title.currentTextColor
                    getInactiveTextColourIsSet = true
                }
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_bold"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_bold"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                        null,
                        Typeface.BOLD
                    )
                } else {
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                        null,
                        Typeface.NORMAL
                    )
                }
            }else{
                bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                    null,
                    Typeface.NORMAL
                )
            }
            //endregion set title

            //region set badge
            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_visible"] as Boolean) {
                if (
                    !visibleItems!![position].badge_label.isNullOrBlank() ||
                    !visibleItems!![position].badge_label.isNullOrEmpty() &&
                     visibleItems!![position].show_badge
                ) {
                        bhapps_menus_menu_horizontal_menu_child_items_badge.visibility = View.VISIBLE

                        //region set padding
                        if(horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] as Int > 0){
                            //setPadding(int left, int top, int right, int bottom)
                            bhapps_menus_menu_horizontal_menu_child_items_badge.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] as Int
                            )
                        }else{
                            //setPadding(int left, int top, int right, int bottom)
                            //set default padding if values not passed
                            bhapps_menus_menu_horizontal_menu_child_items_badge.setPadding(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_bottom"] as Int
                            )
                        }
                        //endregion set padding

                        //region set margin
                        val bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params =
                            ConstraintLayout.LayoutParams(
                                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )
                        if(horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] as Int > 0){
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] as Int
                            )
                            bhapps_menus_menu_horizontal_menu_child_items_badge.layoutParams = bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params
                        }else{
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.topToTop = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.bottomToBottom = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.endToEnd = bhapps_menus_menu_horizontal_menu_child_items_layout.id
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.setMargins(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_left"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_top"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_right"] as Int,
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_bottom"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_width"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.width = horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_width"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.width = getIntToDp(context, HORIZONTALMENU_CHILDITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_height"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.height = horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_height"] as Int
                        }else{
                            bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params.height = getIntToDp(context, HORIZONTALMENU_CHILDITEMS_BADGE_DEFAULT_WIDTHHEIGHT_DP)
                        }

                        bhapps_menus_menu_horizontal_menu_child_items_badge.layoutParams = bhapps_menus_menu_horizontal_menu_child_items_badge_layout_params
                        //endregion set margin

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_size"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_badge_label.textSize =
                                bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_size"] as Int)).toFloat()
                        }

                        bhapps_menus_menu_horizontal_menu_child_items_badge_label.text = visibleItems!![position].badge_label
                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_badge_label.setTextColor(
                                horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_color"] as Int
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_bold"] !=null) {
                            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_bold"] as Boolean) {
                                bhapps_menus_menu_horizontal_menu_child_items_badge_label.setTypeface(
                                    null,
                                    Typeface.BOLD
                                )
                            } else {
                                bhapps_menus_menu_horizontal_menu_child_items_badge_label.setTypeface(
                                    null,
                                    Typeface.NORMAL
                                )
                            }
                        }else{
                            bhapps_menus_menu_horizontal_menu_child_items_badge_label.setTypeface(
                                null,
                                Typeface.BOLD
                            )
                        }

                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_visible"] !=null) {
                            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_visible"] as Boolean) {
                                bhapps_menus_menu_horizontal_menu_child_items_badge_label.visibility = View.VISIBLE
                                bhapps_menus_menu_horizontal_menu_child_items_badge_label.text = setBadgeLabelAsPerLimit(view.context, visibleItems!![position].badge_label)
                            } else {
                                bhapps_menus_menu_horizontal_menu_child_items_badge_label.visibility = View.GONE
                            }
                        }else{
                            bhapps_menus_menu_horizontal_menu_child_items_badge_label.visibility = View.VISIBLE
                            bhapps_menus_menu_horizontal_menu_child_items_badge_label.text = setBadgeLabelAsPerLimit(view.context, visibleItems!![position].badge_label)
                        }


                        if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_background_color"] as Int != 0) {
                            bhapps_menus_menu_horizontal_menu_child_items_badge.setBackground(
                                drawBadgeWithColors(
                                    horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_background_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_border_color"] as Int,
                                    horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_border_thickness"] as Int
                                )
                            )
                        }

                } else {
                    bhapps_menus_menu_horizontal_menu_child_items_badge.visibility = View.GONE
                }
            } else {
                bhapps_menus_menu_horizontal_menu_child_items_badge.visibility = View.GONE
            }
            //endregion set badge

            //region set indicator
            if (horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_is_visible"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_is_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_child_items_indicator.visibility = View.VISIBLE
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_thickness"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_child_items_indicator.layoutParams.width = horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_thickness"] as Int
                        if(horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_position"] !=null) {
                            if(horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_position"] as Int == 2) {

                                var layoutParamsForIndicatorPosition = ConstraintLayout.LayoutParams(
                                    horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_thickness"] as Int,
                                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                                )

                                layoutParamsForIndicatorPosition.startToStart = bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper.id
                                layoutParamsForIndicatorPosition.topToTop = bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper.id
                                layoutParamsForIndicatorPosition.bottomToBottom = bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper.id
                                bhapps_menus_menu_horizontal_menu_child_items_indicator.layoutParams = layoutParamsForIndicatorPosition

                            }
                        }

                    }
                }else{
                    bhapps_menus_menu_horizontal_menu_child_items_indicator.visibility = View.GONE
                }
            }else{
                bhapps_menus_menu_horizontal_menu_child_items_indicator.visibility = View.GONE
            }
            //endregion set indicator

            //todo: add divider attr/app/horizontalMenuItemsUISettings values to show divider, colour, thickness, padding, margin on each child
            if(visibleItems!![position]!!.is_last_item) {
                bhapps_menus_menu_horizontal_menu_child_items_divider.visibility = View.VISIBLE
            }else{
                bhapps_menus_menu_horizontal_menu_child_items_divider.visibility = View.GONE
            }
            
            view.setOnClickListener { view ->
                onItemClickListener!!.onItemClick(view, visibleItems!![position]!!.id, position, visibleItems!![position]!!)
                
                setActiveState(
                    context,
                    horizontalMenuAdapter,
                    visibleItems!![position]!!.group,
                    visibleItems!![position]!!.id
                )

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_cleared_on_active"] as Boolean) {
                    removeBadgeStateAndBadgeLabel(
                        context,
                        horizontalMenuAdapter,
                        visibleItems!![position]!!.group,
                        visibleItems!![position]!!.id
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_cleared_on_active"] as Boolean) {
                    removeBadgeStateAndBadgeLabel(
                        context,
                        horizontalMenuAdapter,
                        visibleItems!![position]!!.group,
                        visibleItems!![position]!!.parent_id
                    )
                }
                
            }

            if (visibleItems!![position]!!.active) {

                //region set active state
                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_child_items_layout.background =
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_drawable"] as Drawable
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_indicator.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_active_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_active_tint_color"] as Int
                    )
                }else{
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_child_items_icon.setColorFilter(
                            horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int
                        )
                    }
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_is_bold"] !=null) {
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_is_bold"] as Boolean) {
                        bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                            null,
                            Typeface.BOLD
                        )
                    } else {
                        bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                            null,
                            Typeface.NORMAL
                        )
                    }
                }else{
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                        null,
                        Typeface.NORMAL
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTextColor(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_cleared_on_active"] !=null) {
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_cleared_on_active"] as Boolean) {
                        //todo: add clear badge on active
                    }
                }

                //endregion set active state

            } else {

                //region set inactive state
                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_background_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_layout.setBackgroundColor(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_background_color"] as Int
                    )
                }else{
                    bhapps_menus_menu_horizontal_menu_child_items_layout.setBackgroundColor(
                        context.resources.getColor(android.R.color.transparent)
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_background_drawable"] != null) {
                    bhapps_menus_menu_horizontal_menu_child_items_layout.background =
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_background_drawable"] as Drawable
                }

                bhapps_menus_menu_horizontal_menu_child_items_indicator.setBackgroundColor(
                    context.resources.getColor(android.R.color.transparent)
                )

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_icon.setColorFilter(
                        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] as Int
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_bold"] !=null) {
                    if (horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_bold"] as Boolean) {
                        bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                            null,
                            Typeface.BOLD
                        )
                    } else {
                        bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                            null,
                            Typeface.NORMAL
                        )
                    }
                }else{
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTypeface(
                        null,
                        Typeface.NORMAL
                    )
                }

                if (horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_color"] as Int != 0) {
                    bhapps_menus_menu_horizontal_menu_child_items_title.setTextColor(
                        getInactiveTextColour
                    )
                }
                //endregion set inactive state

            }
            
        }

        init {
            bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_layout_wrapper) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_child_items_layout =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_layout) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_child_items_title =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_title) as TextView
            bhapps_menus_menu_horizontal_menu_child_items_icon =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_icon) as ImageView
            bhapps_menus_menu_horizontal_menu_child_items_badge =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_badge) as BadgeConstraintView
            bhapps_menus_menu_horizontal_menu_child_items_badge_label =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_child_items_badge_label) as TextView
            bhapps_menus_menu_horizontal_menu_child_items_divider =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_child_items_divider) as View
            bhapps_menus_menu_horizontal_menu_child_items_indicator =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_child_items_indicator) as View
        }
    }
    //endregion ChildMenuTypeViewHolder

    //region DividerViewHolder
    inner class DividerViewHolder(var view: View) :
        ViewHolder(view) {
        var bhapps_menus_menu_horizontal_menu_divider_title: TextView
        fun bind(position: Int) {
            if (visibleItems!![position]!!.title.isEmpty()) {
                bhapps_menus_menu_horizontal_menu_divider_title.visibility = View.GONE
            } else {
                bhapps_menus_menu_horizontal_menu_divider_title.visibility = View.VISIBLE
                bhapps_menus_menu_horizontal_menu_divider_title.text = visibleItems!![position]!!.title
            }
        }

        init {
            bhapps_menus_menu_horizontal_menu_divider_title =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_divider_title) as TextView
        }
    }
    //endregion DividerViewHolder

    //region TitleViewHolder
    inner class TitleViewHolder(var view: View) :
        ViewHolder(view) {
        var bhapps_menus_menu_horizontal_menu_title_item_layout_wrapper: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_title_item_layout: ConstraintLayout
        var bhapps_menus_menu_horizontal_menu_title_item_parent_title: TextView
        var bhapps_menus_menu_horizontal_menu_title_item_divider: View
        fun bind(position: Int) {

            //region set padding
            if(horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] as Int > 0) {
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_title_item_layout.setPadding(
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] as Int
                    )
                } else {
                    //setPadding(int left, int top, int right, int bottom)
                    bhapps_menus_menu_horizontal_menu_title_item_layout.setPadding(
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_left"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_top"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_right"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_bottom"] as Int
                    )
                }
            }
            //endregion set padding

            //region set margin
            if(horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] !=null) {
                if (horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] as Int > 0) {
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )
                    bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params.setMargins(
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] as Int,
                        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_title_item_layout.layoutParams =
                        bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params
                } else {
                    //setMargins(int left, int top, int right, int bottom)
                    val bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )
                    bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params.setMargins(
                            horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_left"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_top"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_right"] as Int,
                            horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_bottom"] as Int
                    )
                    bhapps_menus_menu_horizontal_menu_title_item_layout.layoutParams =
                        bhapps_menus_menu_horizontal_menu_title_item_layout_layout_params
                }
            }
            //endregion set margin

            //region set title
            bhapps_menus_menu_horizontal_menu_title_item_parent_title.text =
                visibleItems!![position]!!.title

            if (horizontalMenuItemsUISettings["horizontal_menu_title_items_text_is_capitalized"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_title_item_parent_title.isAllCaps =
                    horizontalMenuItemsUISettings["horizontal_menu_title_items_text_is_capitalized"] as Boolean
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_title_items_text_size"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_title_item_parent_title.textSize =
                    bhapps.menus.helpers.Helpers.getIntToDp(view.context, (horizontalMenuItemsUISettings["horizontal_menu_title_items_text_size"] as Int)).toFloat()
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_title_items_text_color"] as Int != 0) {
                bhapps_menus_menu_horizontal_menu_title_item_parent_title.setTextColor(
                    horizontalMenuItemsUISettings["horizontal_menu_title_items_text_color"] as Int
                )
            }

            if (horizontalMenuItemsUISettings["horizontal_menu_title_items_text_is_bold"] as Boolean) {
                bhapps_menus_menu_horizontal_menu_title_item_parent_title.setTypeface(null, Typeface.BOLD)
            }else{
                bhapps_menus_menu_horizontal_menu_title_item_parent_title.setTypeface(null, Typeface.NORMAL)
            }
            //endregion set title

            //region set divider
            if (horizontalMenuItemsUISettings["horizontal_menu_show_parent_divider"] as Boolean) {
                if (horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_visible"] as Boolean) {
                    bhapps_menus_menu_horizontal_menu_title_item_divider.visibility = View.VISIBLE

                    if (horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_thickness"] as Int != 0) {
                        bhapps_menus_menu_horizontal_menu_title_item_divider.layoutParams.height = horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_thickness"] as Int
                    }

                }else{
                    bhapps_menus_menu_horizontal_menu_title_item_divider.visibility = View.GONE
                }
            }else{
                bhapps_menus_menu_horizontal_menu_title_item_divider.visibility = View.GONE
            }
            //endregion set divider

            //region set setOnClickListener
            view.setOnClickListener { view ->
                //onItemClickListener!!.onItemClick(view, visibleItems!![position]!!.id)
            }
            //endregion set setOnClickListener

            //region set active-inactive states
            if (visibleItems!![position]!!.active) {

            }
            //endregion set active-inactive states
        }

        init {
            bhapps_menus_menu_horizontal_menu_title_item_layout_wrapper =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_title_item_layout_wrapper) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_title_item_layout =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_title_item_layout) as ConstraintLayout
            bhapps_menus_menu_horizontal_menu_title_item_parent_title =
                view.findViewById<View>(R.id.bhapps_menus_menu_horizontal_menu_title_item_parent_title) as TextView
            bhapps_menus_menu_horizontal_menu_title_item_divider =
                view.findViewById(R.id.bhapps_menus_menu_horizontal_menu_title_item_divider) as View
        }
    }
    //endregion TitleViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val divider = View(context)
        divider.layoutParams = LinearLayout.LayoutParams(
            2,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        divider.setBackgroundColor(
            context.resources.getColor(R.color.bhapps_menus_menu_horizontal_menu_divider_color)
        )
        return if (viewType == PARENT) {
            ParentOnlyMenuTypeViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_parent_item_layout,
                    parent
                )
            )
        } else if (viewType == PARENTWITHCHILDREN) {
            ParentWithChildItemsMenuTypeViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_parent_with_child_items_layout,
                    parent
                )
            )
        } else if (viewType == CHILD) {
            ChildMenuTypeViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_child_item_layout,
                    parent
                )
            )
        } else if (viewType == DIVIDER) {
            DividerViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_divider_layout,
                    parent
                )
            )
        } else if (viewType == TITLE) {
            TitleViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_title_item_layout,
                    parent
                )
            )
        } else {
            ParentOnlyMenuTypeViewHolder(
                inflate(
                    R.layout.bhapps_menus_menu_horizontal_menu_parent_item_layout,
                    parent
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == PARENT) {
            (holder as ParentOnlyMenuTypeViewHolder).bind(position)
        } else if (viewType == PARENTWITHCHILDREN) {
            (holder as ParentWithChildItemsMenuTypeViewHolder).bind(position)
        } else if (viewType == CHILD) {
            (holder as ChildMenuTypeViewHolder).bind(position)
        } else if (viewType == DIVIDER) {
            (holder as DividerViewHolder).bind(position)
        } else if (viewType == TITLE) {
            (holder as TitleViewHolder).bind(position)
        } else {
            (holder as ParentOnlyMenuTypeViewHolder).bind(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, itemId: Int, position: Int, horizontalMenuItem: HorizontalMenuItem)
    }
}