package bhapps.menus.vertical.verticalmenu

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import bhapps.menus.R
import bhapps.menus.databinding.BhappsMenusMenuVerticalMenuLayoutBinding
import bhapps.menus.vertical.verticalmenu.adapters.VerticalMenuAdapter
import bhapps.menus.vertical.verticalmenu.annotations.DpAnnotation
import bhapps.menus.vertical.verticalmenu.config.INDEX_UNSELECTED
import bhapps.menus.vertical.verticalmenu.extensions.getIntToDp
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuHelper.dpToPx
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuItem


class VerticalMenu
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
       : ConstraintLayout(context, attrs, defStyleAttr)
{

    private val bindingForBhappsMenusMenuVerticalMenuLayout: BhappsMenusMenuVerticalMenuLayoutBinding =
        BhappsMenusMenuVerticalMenuLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var bhappsMenusMenuVerticalMenuLayout = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuLayout
    var bhappsMenusMenuVerticalMenuLogo = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuLogo
    var bhappsMenusMenuVerticalMenuWidthHolder = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuWidthHolder
    var bhappsMenusMenuVerticalMenuRecycleView = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuRecycleView
    var bhappsMenusMenuVerticalMenuShimmerLayout = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuShimmerLayout
    var bhappsMenusMenuVerticalMenuBorder = bindingForBhappsMenusMenuVerticalMenuLayout.bhappsMenusMenuVerticalMenuBorder

    //region get/set values
    //region get/set ScreenOrientation values
    var currentScreenOrientation: Int = 0
    var currentScreenOrientationIsPortrait: Boolean = false
    var currentScreenOrientationListener: ScreenOrientationListener? = null
    fun getScreenOrientation(): Int {
        return currentScreenOrientation
    }

    fun setScreenOrientation(_currentScreenOrientation: Int) {
        currentScreenOrientation = _currentScreenOrientation
        currentScreenOrientationListener?.onChange()
    }

    fun getScreenOrientationListener(): ScreenOrientationListener? {
        return currentScreenOrientationListener
    }

    fun setScreenOrientationListener(_currentScreenOrientationListener: ScreenOrientationListener) {
        currentScreenOrientationListener = _currentScreenOrientationListener
    }

    interface ScreenOrientationListener {
        fun onChange()
    }
    //endregion get/set ScreenOrientation values

    var verticalMenuAdapter: VerticalMenuAdapter? = null
    private var verticalMenuItems: List<VerticalMenuItem> = mutableListOf()
    fun setVerticalMenuItems(_verticalMenuItems: List<VerticalMenuItem>) {
        verticalMenuItems = _verticalMenuItems
    }

    var selectedIndex: Int = INDEX_UNSELECTED
    var verticalMenuItemsUISettings = HashMap<String, Any?>()

    //region get/set vertical_menu values
    var _vertical_menu_is_responsive_menu: Boolean = false
    var _vertical_menu_width: Int = 0
    var _vertical_menu_max_width: Int = 0
    var _vertical_menu_padding: Int = 0
    var _vertical_menu_padding_left: Int = 0
    var _vertical_menu_padding_right: Int = 0
    var _vertical_menu_padding_top: Int = 0
    var _vertical_menu_padding_bottom: Int = 0
    var _vertical_menu_margin: Int = 0
    var _vertical_menu_margin_left: Int = 0
    var _vertical_menu_margin_right: Int = 0
    var _vertical_menu_margin_top: Int = 0
    var _vertical_menu_margin_bottom: Int = 0
    var _vertical_menu_alignment_position: Int = 0
    var _vertical_menu_enable_nested_scrolling: Boolean = false
    var _vertical_menu_active_state_on_left: Boolean = false

    var _vertical_menu_show_scroll_bar: Boolean =  false
    var _vertical_menu_show_icons: Boolean = true
    var _vertical_menu_show_parent_icons: Boolean = true
    var _vertical_menu_show_child_icons: Boolean = true
    var _vertical_menu_show_parent_divider: Boolean = true
    var _vertical_menu_show_icons_only: Boolean = false

    var _vertical_menu_border_is_visible: Boolean = false
    var _vertical_menu_border_thickness: Int = 0
    var _vertical_menu_border_color: Int = 0
    var _vertical_menu_border_position: Int = 0
    var _vertical_menu_show_shimmer_animation: Boolean = false
    var _vertical_menu_show_shimmer_animation_duration: Int = 0
    var _vertical_menu_show_animation_in: Boolean = false
    var _vertical_menu_show_animation_in_type: Int = 0
    var _vertical_menu_show_animation_in_duration: Int = 0

    var _vertical_menu_divider_color: Int = 0
    var _vertical_menu_divider_width: Int = 0
    var _vertical_menu_divider_height: Int = 1
    var _vertical_menu_divider_padding: Int = 0
    var _vertical_menu_divider_padding_left: Int = 0
    var _vertical_menu_divider_padding_right: Int = 0
    var _vertical_menu_divider_padding_top: Int = 0
    var _vertical_menu_divider_padding_bottom: Int = 0
    var _vertical_menu_divider_margin: Int = 0
    var _vertical_menu_divider_margin_left: Int = 0
    var _vertical_menu_divider_margin_right: Int = 0
    var _vertical_menu_divider_margin_top: Int = 0
    var _vertical_menu_divider_margin_bottom: Int = 0
    var _vertical_menu_divider_is_visible: Boolean = true

    var _vertical_menu_logo_width: Int = 0
    var _vertical_menu_logo_height: Int = 0
    var _vertical_menu_logo_padding: Int = 0
    var _vertical_menu_logo_padding_left: Int = 0
    var _vertical_menu_logo_padding_right: Int = 0
    var _vertical_menu_logo_padding_top: Int = 0
    var _vertical_menu_logo_padding_bottom: Int = 0
    var _vertical_menu_logo_margin: Int = 0
    var _vertical_menu_logo_margin_left: Int = 0
    var _vertical_menu_logo_margin_right: Int = 0
    var _vertical_menu_logo_margin_top: Int = 0
    var _vertical_menu_logo_margin_bottom: Int = 0
    var _vertical_menu_logo_drawable: Drawable? = null
    var _vertical_menu_logo_is_visible: Boolean = false
    var _vertical_menu_logo_position: Int = 1

    //endregion get/set vertical_menu values

    //region get/set vertical_menu_parent values
    var _vertical_menu_parent_items_width: Int = 0
    var _vertical_menu_parent_items_height: Int = 0
    var _vertical_menu_parent_items_padding: Int = 0
    var _vertical_menu_parent_items_padding_left: Int = 12
    var _vertical_menu_parent_items_padding_right: Int = 12
    var _vertical_menu_parent_items_padding_top: Int = 12
    var _vertical_menu_parent_items_padding_bottom: Int = 12
    var _vertical_menu_parent_items_margin: Int = 0
    var _vertical_menu_parent_items_margin_left: Int = 0
    var _vertical_menu_parent_items_margin_right: Int = 0
    var _vertical_menu_parent_items_margin_top: Int = 0
    var _vertical_menu_parent_items_margin_bottom: Int = 0
    var _vertical_menu_parent_items_background_color: Int = 0
    var _vertical_menu_parent_items_active_background_color: Int = 0
    var _vertical_menu_parent_items_background_drawable: Drawable? = null
    var _vertical_menu_parent_items_active_background_drawable: Drawable? = null
    var _vertical_menu_parent_items_text_color: Int = 0
    var _vertical_menu_parent_items_text_size: Int = 0
    var _vertical_menu_parent_items_text_is_capitalized: Boolean = false
    var _vertical_menu_parent_items_text_is_bold: Boolean = false
    var _vertical_menu_parent_items_text_is_visible: Boolean = true
    var _vertical_menu_parent_items_indicator_is_visible: Boolean = true
    var _vertical_menu_parent_items_indicator_thickness: Int = 0
    var _vertical_menu_parent_items_indicator_color: Int = 0
    var _vertical_menu_parent_items_indicator_position: Int = 0
    var _vertical_menu_parent_items_active_padding: Int = 0
    var _vertical_menu_parent_items_active_padding_left: Int = 0
    var _vertical_menu_parent_items_active_padding_right: Int = 0
    var _vertical_menu_parent_items_active_padding_top: Int = 0
    var _vertical_menu_parent_items_active_padding_bottom: Int = 0
    var _vertical_menu_parent_items_active_margin: Int = 0
    var _vertical_menu_parent_items_active_margin_left: Int = 0
    var _vertical_menu_parent_items_active_margin_right: Int = 0
    var _vertical_menu_parent_items_active_margin_top: Int = 0
    var _vertical_menu_parent_items_active_margin_bottom: Int = 0
    var _vertical_menu_parent_items_active_text_color: Int = 0
    var _vertical_menu_parent_items_active_text_size: Int = 0
    var _vertical_menu_parent_items_active_text_is_bold: Boolean = false

    var _vertical_menu_parent_items_icon_width: Int = 0
    var _vertical_menu_parent_items_icon_height: Int = 0
    var _vertical_menu_parent_items_icon_padding: Int = 0
    var _vertical_menu_parent_items_icon_padding_left: Int = 0
    var _vertical_menu_parent_items_icon_padding_right: Int = 0
    var _vertical_menu_parent_items_icon_padding_top: Int = 0
    var _vertical_menu_parent_items_icon_padding_bottom: Int = 0
    var _vertical_menu_parent_items_icon_margin: Int = 0
    var _vertical_menu_parent_items_icon_margin_left: Int = 0
    var _vertical_menu_parent_items_icon_margin_right: Int = 0
    var _vertical_menu_parent_items_icon_margin_top: Int = 0
    var _vertical_menu_parent_items_icon_margin_bottom: Int = 0
    var _vertical_menu_parent_items_icon_tint_color: Int = 0
    var _vertical_menu_parent_items_icon_is_visible: Boolean = true
    var _vertical_menu_parent_items_icon_active_tint_color: Int = 0

    var _vertical_menu_parent_items_badge_width: Int = 0
    var _vertical_menu_parent_items_badge_height: Int = 0
    var _vertical_menu_parent_items_badge_padding: Int = 0
    var _vertical_menu_parent_items_badge_padding_left: Int = 0
    var _vertical_menu_parent_items_badge_padding_right: Int = 0
    var _vertical_menu_parent_items_badge_padding_top: Int = 0
    var _vertical_menu_parent_items_badge_padding_bottom: Int = 0
    var _vertical_menu_parent_items_badge_margin: Int = 0
    var _vertical_menu_parent_items_badge_margin_left: Int = 0
    var _vertical_menu_parent_items_badge_margin_right: Int = 0
    var _vertical_menu_parent_items_badge_margin_top: Int = 0
    var _vertical_menu_parent_items_badge_margin_bottom: Int = 0
    var _vertical_menu_parent_items_badge_background_color: Int = 0
    var _vertical_menu_parent_items_badge_border_color: Int = 0
    var _vertical_menu_parent_items_badge_border_thickness: Int = 0
    var _vertical_menu_parent_items_badge_text_color: Int = 0
    var _vertical_menu_parent_items_badge_text_size: Int = 0
    var _vertical_menu_parent_items_badge_text_is_bold: Boolean = false
    var _vertical_menu_parent_items_badge_text_is_visible: Boolean =  true
    var _vertical_menu_parent_items_badge_is_visible: Boolean = true
    var _vertical_menu_parent_items_badge_is_cleared_on_active: Boolean =  false

    var _vertical_menu_collapse_type: Int = 0
    var _vertical_menu_collapse_others: Boolean =  true
    var _vertical_menu_collapse_expand_delay: Int = 0
    var _vertical_menu_collapse_icon_rotate: Int = 0
    var _vertical_menu_collapse_icon_rotate_duration: Int = 0
    var _vertical_menu_collapse_icon_width: Int = 0
    var _vertical_menu_collapse_icon_height: Int = 0
    var _vertical_menu_collapse_icon_padding: Int = 0
    var _vertical_menu_collapse_icon_padding_left: Int = 0
    var _vertical_menu_collapse_icon_padding_right: Int = 0
    var _vertical_menu_collapse_icon_padding_top: Int = 0
    var _vertical_menu_collapse_icon_padding_bottom: Int = 0
    var _vertical_menu_collapse_icon_margin: Int = 0
    var _vertical_menu_collapse_icon_margin_left: Int = 0
    var _vertical_menu_collapse_icon_margin_right: Int = 0
    var _vertical_menu_collapse_icon_margin_top: Int = 0
    var _vertical_menu_collapse_icon_margin_bottom: Int = 0
    var _vertical_menu_collapse_icon_color: Int = 0
    var _vertical_menu_collapse_icon_drawable: Drawable? = null
    var _vertical_menu_collapse_icon_is_visible: Boolean =  true

    var _vertical_menu_parent_items_navigation_icon_width: Int = 0
    var _vertical_menu_parent_items_navigation_icon_height: Int = 0
    var _vertical_menu_parent_items_navigation_icon_padding: Int = 0
    var _vertical_menu_parent_items_navigation_icon_padding_left: Int = 0
    var _vertical_menu_parent_items_navigation_icon_padding_right: Int = 0
    var _vertical_menu_parent_items_navigation_icon_padding_top: Int = 0
    var _vertical_menu_parent_items_navigation_icon_padding_bottom: Int = 0
    var _vertical_menu_parent_items_navigation_icon_margin: Int = 0
    var _vertical_menu_parent_items_navigation_icon_margin_left: Int = 0
    var _vertical_menu_parent_items_navigation_icon_margin_right: Int = 0
    var _vertical_menu_parent_items_navigation_icon_margin_top: Int = 0
    var _vertical_menu_parent_items_navigation_icon_margin_bottom: Int = 0
    var _vertical_menu_parent_items_navigation_icon_color: Int = 0
    var _vertical_menu_parent_items_navigation_icon_drawable: Drawable? = null
    var _vertical_menu_parent_items_navigation_icon_is_visible: Boolean =  true
    var _vertical_menu_parent_items_navigation_icon_is_hidden_when_active: Boolean =  true
    var _vertical_menu_parent_items_active_navigation_icon_color: Int = 0

    //endregion get/set vertical_menu_parent values
    
    //region get/set vertical_menu_title values
    var _vertical_menu_title_items_width: Int =  0
    var _vertical_menu_title_items_height: Int =  0
    var _vertical_menu_title_items_padding: Int =  0
    var _vertical_menu_title_items_padding_left: Int =  0
    var _vertical_menu_title_items_padding_right: Int =  0
    var _vertical_menu_title_items_padding_top: Int =  0
    var _vertical_menu_title_items_padding_bottom: Int =  0
    var _vertical_menu_title_items_margin: Int =  0
    var _vertical_menu_title_items_margin_left: Int =  0
    var _vertical_menu_title_items_margin_right: Int =  0
    var _vertical_menu_title_items_margin_top: Int =  0
    var _vertical_menu_title_items_margin_bottom: Int =  0
    var _vertical_menu_title_items_background_color: Int =  0
    var _vertical_menu_title_items_background_drawable: Int =  0
    var _vertical_menu_title_items_text_color: Int =  0
    var _vertical_menu_title_items_text_size: Int =  0
    var _vertical_menu_title_items_text_is_capitalized: Boolean =  false
    var _vertical_menu_title_items_text_is_bold: Boolean =  false
    var _vertical_menu_title_items_divider_visible: Boolean =  true
    var _vertical_menu_title_items_divider_thickness: Int =  0
    var _vertical_menu_title_items_divider_color: Int =  0
    //endregion get/set vertical_menu_title values

    //region get/set vertical_menu_child values
    var _vertical_menu_child_items_width: Int = 0
    var _vertical_menu_child_items_height: Int = 0
    var _vertical_menu_child_items_padding: Int = 0
    var _vertical_menu_child_items_padding_left: Int = 0
    var _vertical_menu_child_items_padding_right: Int = 0
    var _vertical_menu_child_items_padding_top: Int = 0
    var _vertical_menu_child_items_padding_bottom: Int = 0
    var _vertical_menu_child_items_margin: Int = 0
    var _vertical_menu_child_items_margin_left: Int = 0
    var _vertical_menu_child_items_margin_right: Int = 0
    var _vertical_menu_child_items_margin_top: Int = 0
    var _vertical_menu_child_items_margin_bottom: Int = 0
    var _vertical_menu_child_items_background_color: Int = 0
    var _vertical_menu_child_items_active_background_color: Int = 0
    var _vertical_menu_child_items_text_color: Int = 0
    var _vertical_menu_child_items_text_size: Int = 0
    var _vertical_menu_child_items_text_is_capitalized: Boolean = true
    var _vertical_menu_child_items_text_is_bold: Boolean = true
    var _vertical_menu_child_items_text_is_visible: Boolean = true
    var _vertical_menu_child_items_indicator_is_visible: Boolean = true
    var _vertical_menu_child_items_indicator_thickness: Int = 0
    var _vertical_menu_child_items_indicator_color: Int = 0
    var _vertical_menu_child_items_indicator_position: Int = 0
    var _vertical_menu_child_items_active_padding: Int = 0
    var _vertical_menu_child_items_active_padding_left: Int = 0
    var _vertical_menu_child_items_active_padding_right: Int = 0
    var _vertical_menu_child_items_active_padding_top: Int = 0
    var _vertical_menu_child_items_active_padding_bottom: Int = 0
    var _vertical_menu_child_items_active_margin: Int = 0
    var _vertical_menu_child_items_active_margin_left: Int = 0
    var _vertical_menu_child_items_active_margin_right: Int = 0
    var _vertical_menu_child_items_active_margin_top: Int = 0
    var _vertical_menu_child_items_active_margin_bottom: Int = 0
    var _vertical_menu_child_items_active_text_color: Int = 0
    var _vertical_menu_child_items_active_text_size: Int = 0
    var _vertical_menu_child_items_active_text_is_bold: Boolean = true
    var _vertical_menu_child_items_icon_width: Int = 0
    var _vertical_menu_child_items_icon_height: Int = 0
    var _vertical_menu_child_items_icon_padding: Int = 0
    var _vertical_menu_child_items_icon_padding_left: Int = 0
    var _vertical_menu_child_items_icon_padding_right: Int = 0
    var _vertical_menu_child_items_icon_padding_top: Int = 0
    var _vertical_menu_child_items_icon_padding_bottom: Int = 0
    var _vertical_menu_child_items_icon_margin: Int = 0
    var _vertical_menu_child_items_icon_margin_left: Int = 0
    var _vertical_menu_child_items_icon_margin_right: Int = 0
    var _vertical_menu_child_items_icon_margin_top: Int = 0
    var _vertical_menu_child_items_icon_margin_bottom: Int = 0
    var _vertical_menu_child_items_icon_tint_color: Int = 0
    var _vertical_menu_child_items_icon_is_visible: Boolean = true
    var _vertical_menu_child_items_icon_active_tint_color: Int = 0
    var _vertical_menu_child_items_badge_width: Int = 0
    var _vertical_menu_child_items_badge_height: Int = 0
    var _vertical_menu_child_items_badge_padding: Int = 0
    var _vertical_menu_child_items_badge_padding_left: Int = 0
    var _vertical_menu_child_items_badge_padding_right: Int = 0
    var _vertical_menu_child_items_badge_padding_top: Int = 0
    var _vertical_menu_child_items_badge_padding_bottom: Int = 0
    var _vertical_menu_child_items_badge_margin: Int = 0
    var _vertical_menu_child_items_badge_margin_left: Int = 0
    var _vertical_menu_child_items_badge_margin_right: Int = 0
    var _vertical_menu_child_items_badge_margin_top: Int = 0
    var _vertical_menu_child_items_badge_margin_bottom: Int = 0
    var _vertical_menu_child_items_badge_background_color: Int = 0
    var _vertical_menu_child_items_badge_border_color: Int = 0
    var _vertical_menu_child_items_badge_border_thickness: Int = 0
    var _vertical_menu_child_items_badge_text_color: Int = 0
    var _vertical_menu_child_items_badge_text_size: Int = 0
    var _vertical_menu_child_items_badge_text_is_bold: Boolean = true
    var _vertical_menu_child_items_badge_is_visible: Boolean = true
    var _vertical_menu_child_items_badge_is_cleared_on_active: Boolean = true
    var _vertical_menu_child_items_background_drawable: Drawable? = null
    var _vertical_menu_child_items_active_background_drawable: Drawable? = null
    var _vertical_menu_child_items_badge_text_is_visible: Boolean = true
    var _vertical_menu_child_items_when_active_show_parent_active_state: Boolean = true
    //endregion get/set vertical_menu_child values
    
    //region get/set vertical_menu values
    var vertical_menu_is_responsive_menu: Boolean
        get() = _vertical_menu_is_responsive_menu
        set(value) { _vertical_menu_is_responsive_menu = value
    }

    var vertical_menu_width: Int @Px
        get() = _vertical_menu_width
        set(@DpAnnotation value) { _vertical_menu_width = dpToPx(value)
    }
    var vertical_menu_max_width: Int @Px 
        get() = _vertical_menu_max_width
        set(@DpAnnotation value) { _vertical_menu_max_width = dpToPx(value)
    }
    var vertical_menu_padding: Int @Px 
        get() = _vertical_menu_padding
        set(@DpAnnotation value) { _vertical_menu_padding = dpToPx(value)
    }
    var vertical_menu_padding_left: Int @Px 
        get() = _vertical_menu_padding_left
        set(@DpAnnotation value) { _vertical_menu_padding_left = dpToPx(value)
    }
    var vertical_menu_padding_right: Int @Px 
        get() = _vertical_menu_padding_right
        set(@DpAnnotation value) { _vertical_menu_padding_right = dpToPx(value)
    }
    var vertical_menu_padding_top: Int @Px 
        get() = _vertical_menu_padding_top
        set(@DpAnnotation value) { _vertical_menu_padding_top = dpToPx(value)
    }
    var vertical_menu_padding_bottom: Int @Px 
        get() = _vertical_menu_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_padding_bottom = dpToPx(value)
    }
    var vertical_menu_margin: Int @Px 
        get() = _vertical_menu_margin
        set(@DpAnnotation value) { _vertical_menu_margin = dpToPx(value)
    }
    var vertical_menu_margin_left: Int @Px 
        get() = _vertical_menu_margin_left
        set(@DpAnnotation value) { _vertical_menu_margin_left = dpToPx(value)
    }
    var vertical_menu_margin_right: Int @Px 
        get() = _vertical_menu_margin_right
        set(@DpAnnotation value) { _vertical_menu_margin_right = dpToPx(value)
    }
    var vertical_menu_margin_top: Int @Px 
        get() = _vertical_menu_margin_top
        set(@DpAnnotation value) { _vertical_menu_margin_top = dpToPx(value)
    }
    var vertical_menu_margin_bottom: Int @Px 
        get() = _vertical_menu_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_margin_bottom = dpToPx(value)
    }
    var vertical_menu_alignment_position: Int
        get() = _vertical_menu_alignment_position
        set(value) { _vertical_menu_alignment_position = value
    }
    var vertical_menu_enable_nested_scrolling: Boolean 
        get() = _vertical_menu_enable_nested_scrolling
        set(value) { _vertical_menu_enable_nested_scrolling = value
    }
    var vertical_menu_show_scroll_bar: Boolean
        get() = _vertical_menu_show_scroll_bar
        set(value) { _vertical_menu_show_scroll_bar = value
    }
    var vertical_menu_active_state_on_left: Boolean 
        get() = _vertical_menu_active_state_on_left
        set(value) { _vertical_menu_active_state_on_left = value
    }
    var vertical_menu_show_icons: Boolean 
        get() = _vertical_menu_show_icons
        set(value) { _vertical_menu_show_icons = value
    }
    var vertical_menu_show_parent_icons: Boolean 
        get() = _vertical_menu_show_parent_icons
        set(value) { _vertical_menu_show_parent_icons = value
    }
    var vertical_menu_show_child_icons: Boolean 
        get() = _vertical_menu_show_child_icons
        set(value) { _vertical_menu_show_child_icons = value
    }
    var vertical_menu_show_parent_divider: Boolean 
        get() = _vertical_menu_show_parent_divider
        set(value) { _vertical_menu_show_parent_divider = value
    }
    var vertical_menu_show_icons_only: Boolean 
        get() = _vertical_menu_show_icons_only
        set(value) { _vertical_menu_show_icons_only = value
    }
    var vertical_menu_collapse_type: Int 
        get() = _vertical_menu_collapse_type
        set(value) { _vertical_menu_collapse_type = value
     }
    var vertical_menu_collapse_others: Boolean 
        get() = _vertical_menu_collapse_others
        set(value) { _vertical_menu_collapse_others = value
     }
    var vertical_menu_collapse_expand_delay: Int 
        get() = _vertical_menu_collapse_expand_delay
        set(value) { _vertical_menu_collapse_expand_delay = value
     }
    var vertical_menu_collapse_icon_rotate: Int 
        get() = _vertical_menu_collapse_icon_rotate
        set(value) { _vertical_menu_collapse_icon_rotate = value
     }
    var vertical_menu_collapse_icon_rotate_duration: Int 
        get() = _vertical_menu_collapse_icon_rotate_duration
        set(value) { _vertical_menu_collapse_icon_rotate_duration = value
     }
    var vertical_menu_collapse_icon_width: Int @Px 
        get() = _vertical_menu_collapse_icon_width
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_width = dpToPx(value)
     }
    var vertical_menu_collapse_icon_height: Int @Px 
        get() = _vertical_menu_collapse_icon_height
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_height = dpToPx(value)
     }
    var vertical_menu_collapse_icon_padding: Int @Px 
        get() = _vertical_menu_collapse_icon_padding
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_padding = dpToPx(value)
     }
    var vertical_menu_collapse_icon_padding_left: Int @Px 
        get() = _vertical_menu_collapse_icon_padding_left
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_padding_left = dpToPx(value)
     }
    var vertical_menu_collapse_icon_padding_right: Int @Px 
        get() = _vertical_menu_collapse_icon_padding_right
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_padding_right = dpToPx(value)
     }
    var vertical_menu_collapse_icon_padding_top: Int @Px 
        get() = _vertical_menu_collapse_icon_padding_top
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_padding_top = dpToPx(value)
     }
    var vertical_menu_collapse_icon_padding_bottom: Int @Px 
        get() = _vertical_menu_collapse_icon_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_padding_bottom = dpToPx(value)
     }
    var vertical_menu_collapse_icon_margin: Int @Px 
        get() = _vertical_menu_collapse_icon_margin
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_margin = dpToPx(value)
     }
    var vertical_menu_collapse_icon_margin_left: Int @Px 
        get() = _vertical_menu_collapse_icon_margin_left
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_margin_left = dpToPx(value)
     }
    var vertical_menu_collapse_icon_margin_right: Int @Px 
        get() = _vertical_menu_collapse_icon_margin_right
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_margin_right = dpToPx(value)
     }
    var vertical_menu_collapse_icon_margin_top: Int @Px 
        get() = _vertical_menu_collapse_icon_margin_top
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_margin_top = dpToPx(value)
     }
    var vertical_menu_collapse_icon_margin_bottom: Int @Px 
        get() = _vertical_menu_collapse_icon_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_collapse_icon_margin_bottom = dpToPx(value)
     }
    var vertical_menu_collapse_icon_color: Int @ColorInt 
        get() = _vertical_menu_collapse_icon_color
        set(@ColorInt value) { _vertical_menu_collapse_icon_color = value
     }
    var vertical_menu_collapse_icon_drawable: Drawable?
        get() = _vertical_menu_collapse_icon_drawable
        set(value) { _vertical_menu_collapse_icon_drawable = value
     }
    var vertical_menu_collapse_icon_is_visible: Boolean 
        get() = _vertical_menu_collapse_icon_is_visible
        set(value) { _vertical_menu_collapse_icon_is_visible = value
     }
    var vertical_menu_border_is_visible: Boolean 
        get() = _vertical_menu_border_is_visible
        set(value) { _vertical_menu_border_is_visible = value
    }
    var vertical_menu_border_thickness: Int @Px 
        get() = _vertical_menu_border_thickness
        set(@DpAnnotation value) { _vertical_menu_border_thickness = dpToPx(value)
    }
    var vertical_menu_border_color: Int @ColorInt 
        get() = _vertical_menu_border_color
        set(@ColorInt value) { _vertical_menu_border_color = value
    }
    var vertical_menu_border_position: Int 
        get() = _vertical_menu_border_position
        set(value) { _vertical_menu_border_position = value
    }
    var vertical_menu_show_shimmer_animation: Boolean
        get() = _vertical_menu_show_shimmer_animation
        set(value) { _vertical_menu_show_shimmer_animation = value
    }
    var vertical_menu_show_shimmer_animation_duration: Int
        get() = _vertical_menu_show_shimmer_animation_duration
        set(value) { _vertical_menu_show_shimmer_animation_duration = value
    }
    var vertical_menu_show_animation_in: Boolean
        get() = _vertical_menu_show_animation_in
        set(value) { _vertical_menu_show_animation_in = value
    }
    var vertical_menu_show_animation_in_type: Int
        get() = _vertical_menu_show_animation_in_type
        set(value) { _vertical_menu_show_animation_in_type = value
    }
    var vertical_menu_show_animation_in_duration: Int
        get() = _vertical_menu_show_animation_in_duration
        set(value) { _vertical_menu_show_animation_in_duration = value
    }
    var vertical_menu_divider_color: Int @ColorInt
        get() = _vertical_menu_divider_color
        set(@ColorInt value) { _vertical_menu_divider_color = value
    }
    var vertical_menu_divider_width: Int @Px
        get() = _vertical_menu_divider_width
        set(@DpAnnotation value) { _vertical_menu_divider_width = dpToPx(value)
    }
    var vertical_menu_divider_height: Int @Px
        get() = _vertical_menu_divider_height
        set(@DpAnnotation value) { _vertical_menu_divider_height = dpToPx(value)
    }
    var vertical_menu_divider_padding: Int @Px
        get() = _vertical_menu_divider_padding
        set(@DpAnnotation value) { _vertical_menu_divider_padding = dpToPx(value)
    }
    var vertical_menu_divider_padding_left: Int @Px
        get() = _vertical_menu_divider_padding_left
        set(@DpAnnotation value) { _vertical_menu_divider_padding_left = dpToPx(value)
    }
    var vertical_menu_divider_padding_right: Int @Px
        get() = _vertical_menu_divider_padding_right
        set(@DpAnnotation value) { _vertical_menu_divider_padding_right = dpToPx(value)
    }
    var vertical_menu_divider_padding_top: Int @Px
        get() = _vertical_menu_divider_padding_top
        set(@DpAnnotation value) { _vertical_menu_divider_padding_top = dpToPx(value)
    }
    var vertical_menu_divider_padding_bottom: Int @Px
        get() = _vertical_menu_divider_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_divider_padding_bottom = dpToPx(value)
    }
    var vertical_menu_divider_margin: Int @Px
        get() = _vertical_menu_divider_margin
        set(@DpAnnotation value) { _vertical_menu_divider_margin = dpToPx(value)
    }
    var vertical_menu_divider_margin_left: Int @Px
        get() = _vertical_menu_divider_margin_left
        set(@DpAnnotation value) { _vertical_menu_divider_margin_left = dpToPx(value)
    }
    var vertical_menu_divider_margin_right: Int @Px
        get() = _vertical_menu_divider_margin_right
        set(@DpAnnotation value) { _vertical_menu_divider_margin_right = dpToPx(value)
    }
    var vertical_menu_divider_margin_top: Int @Px
        get() = _vertical_menu_divider_margin_top
        set(@DpAnnotation value) { _vertical_menu_divider_margin_top = dpToPx(value)
    }
    var vertical_menu_divider_margin_bottom: Int @Px
        get() = _vertical_menu_divider_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_divider_margin_bottom = dpToPx(value)
    }
    var vertical_menu_divider_is_visible: Boolean
        get() = _vertical_menu_divider_is_visible
        set(value) { _vertical_menu_divider_is_visible =  value
    }
    var vertical_menu_logo_width: Int 
        get() = _vertical_menu_logo_width
        set(@DpAnnotation value) { 	_vertical_menu_logo_width = dpToPx(value)
    }
    var vertical_menu_logo_height: Int 
        get() = _vertical_menu_logo_height
        set(@DpAnnotation value) { 	_vertical_menu_logo_height = dpToPx(value)
    }
    var vertical_menu_logo_padding: Int 
        get() = _vertical_menu_logo_padding
        set(@DpAnnotation value) { 	_vertical_menu_logo_padding = dpToPx(value)
    }
    var vertical_menu_logo_padding_left: Int 
        get() = _vertical_menu_logo_padding_left
        set(@DpAnnotation value) { 	_vertical_menu_logo_padding_left = dpToPx(value)
    }
    var vertical_menu_logo_padding_right: Int 
        get() = _vertical_menu_logo_padding_right
        set(@DpAnnotation value) { 	_vertical_menu_logo_padding_right = dpToPx(value)
    }
    var vertical_menu_logo_padding_top: Int 
        get() = _vertical_menu_logo_padding_top
        set(@DpAnnotation value) { 	_vertical_menu_logo_padding_top = dpToPx(value)
    }
    var vertical_menu_logo_padding_bottom: Int 
        get() = _vertical_menu_logo_padding_bottom
        set(@DpAnnotation value) { 	_vertical_menu_logo_padding_bottom = dpToPx(value)
    }
    var vertical_menu_logo_margin: Int 
        get() = _vertical_menu_logo_margin
        set(@DpAnnotation value) { 	_vertical_menu_logo_margin = dpToPx(value)
    }
    var vertical_menu_logo_margin_left: Int 
        get() = _vertical_menu_logo_margin_left
        set(@DpAnnotation value) { 	_vertical_menu_logo_margin_left = dpToPx(value)
    }
    var vertical_menu_logo_margin_right: Int 
        get() = _vertical_menu_logo_margin_right
        set(@DpAnnotation value) { 	_vertical_menu_logo_margin_right = dpToPx(value)
    }
    var vertical_menu_logo_margin_top: Int 
        get() = _vertical_menu_logo_margin_top
        set(@DpAnnotation value) { 	_vertical_menu_logo_margin_top = dpToPx(value)
    }
    var vertical_menu_logo_margin_bottom: Int 
        get() = _vertical_menu_logo_margin_bottom
        set(@DpAnnotation value) { 	_vertical_menu_logo_margin_bottom = dpToPx(value)
    }
    var vertical_menu_logo_drawable: Drawable?
        get() = _vertical_menu_logo_drawable
        set(value) { _vertical_menu_logo_drawable = value	
    }
    var vertical_menu_logo_is_visible: Boolean 
        get() = _vertical_menu_logo_is_visible
        set(value) { _vertical_menu_logo_is_visible =  value
    }
    var vertical_menu_logo_position: Int 
        get() = _vertical_menu_logo_position
        set(value) { _vertical_menu_logo_position =  value	
    }
    //endregion get/set vertical_menu values

    //region get/set vertical_menu_parent values
    var vertical_menu_parent_items_width: Int @Px 
        get() = _vertical_menu_parent_items_width 
        set(@DpAnnotation value) { _vertical_menu_parent_items_width  = dpToPx(value)
    }
    var vertical_menu_parent_items_height: Int @Px 
        get() = _vertical_menu_parent_items_height 
        set(@DpAnnotation value) { _vertical_menu_parent_items_height = dpToPx(value)
    }
    var vertical_menu_parent_items_padding: Int @Px 
        get() = _vertical_menu_parent_items_padding 
        set(@DpAnnotation value) { _vertical_menu_parent_items_padding = dpToPx(value)
    }
    var vertical_menu_parent_items_padding_left: Int @Px 
        get() = _vertical_menu_parent_items_padding_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_padding_left = dpToPx(value)
    }
    var vertical_menu_parent_items_padding_right: Int @Px 
        get() = _vertical_menu_parent_items_padding_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_padding_right = dpToPx(value)
    }
    var vertical_menu_parent_items_padding_top: Int @Px 
        get() = _vertical_menu_parent_items_padding_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_padding_top = dpToPx(value)
    }
    var vertical_menu_parent_items_padding_bottom: Int @Px 
        get() = _vertical_menu_parent_items_padding_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_padding_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_margin: Int @Px 
        get() = _vertical_menu_parent_items_margin 
        set(@DpAnnotation value) { _vertical_menu_parent_items_margin = dpToPx(value)
    }
    var vertical_menu_parent_items_margin_left: Int @Px 
        get() = _vertical_menu_parent_items_margin_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_margin_left = dpToPx(value)
    }
    var vertical_menu_parent_items_margin_right: Int @Px 
        get() = _vertical_menu_parent_items_margin_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_margin_right = dpToPx(value)
    }
    var vertical_menu_parent_items_margin_top: Int @Px 
        get() = _vertical_menu_parent_items_margin_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_margin_top = dpToPx(value)
    }
    var vertical_menu_parent_items_margin_bottom: Int @Px 
        get() = _vertical_menu_parent_items_margin_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_margin_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_background_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_background_color 
        set(@ColorInt value) { _vertical_menu_parent_items_background_color = value
    }
    var vertical_menu_parent_items_active_background_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_active_background_color 
        set(@ColorInt value) { _vertical_menu_parent_items_active_background_color = value
    }
    var vertical_menu_parent_items_background_drawable: Drawable?
        get() = _vertical_menu_parent_items_background_drawable
        set(value) { _vertical_menu_parent_items_background_drawable = value
    }
    var vertical_menu_parent_items_active_background_drawable: Drawable?
        get() = _vertical_menu_parent_items_active_background_drawable
        set(value) { _vertical_menu_parent_items_active_background_drawable = value
    }
    var vertical_menu_parent_items_text_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_text_color 
        set(@ColorInt value) { _vertical_menu_parent_items_text_color = value
    }
    var vertical_menu_parent_items_text_size: Int @Px 
        get() = _vertical_menu_parent_items_text_size 
        set(@DpAnnotation value) { _vertical_menu_parent_items_text_size = dpToPx(value)
    }
    var vertical_menu_parent_items_text_is_capitalized: Boolean 
        get() = _vertical_menu_parent_items_text_is_capitalized 
        set(value) { _vertical_menu_parent_items_text_is_capitalized = value
    }
    var vertical_menu_parent_items_text_is_bold: Boolean 
        get() = _vertical_menu_parent_items_text_is_bold 
        set(value) { _vertical_menu_parent_items_text_is_bold = value
    }
    var vertical_menu_parent_items_text_is_visible: Boolean 
        get() = _vertical_menu_parent_items_text_is_visible 
        set(value) { _vertical_menu_parent_items_text_is_visible = value
    }
    var vertical_menu_parent_items_indicator_is_visible: Boolean 
        get() = _vertical_menu_parent_items_indicator_is_visible 
        set(value) { _vertical_menu_parent_items_indicator_is_visible = value
    }
    var vertical_menu_parent_items_indicator_thickness: Int @Px 
        get() = _vertical_menu_parent_items_indicator_thickness 
        set(@DpAnnotation value) { _vertical_menu_parent_items_indicator_thickness = dpToPx(value)
    }
    var vertical_menu_parent_items_indicator_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_indicator_color 
        set(@ColorInt value) { _vertical_menu_parent_items_indicator_color = value
    }
    var vertical_menu_parent_items_indicator_position: Int
        get() = _vertical_menu_parent_items_indicator_position 
        set(value) { _vertical_menu_parent_items_indicator_position = value
    }
    var vertical_menu_parent_items_active_padding: Int @Px 
        get() = _vertical_menu_parent_items_active_padding 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_padding = dpToPx(value)
    }
    var vertical_menu_parent_items_active_padding_left: Int @Px 
        get() = _vertical_menu_parent_items_active_padding_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_padding_left = dpToPx(value)
    }
    var vertical_menu_parent_items_active_padding_right: Int @Px 
        get() = _vertical_menu_parent_items_active_padding_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_padding_right = dpToPx(value)
    }
    var vertical_menu_parent_items_active_padding_top: Int @Px 
        get() = _vertical_menu_parent_items_active_padding_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_padding_top = dpToPx(value)
    }
    var vertical_menu_parent_items_active_padding_bottom: Int @Px 
        get() = _vertical_menu_parent_items_active_padding_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_padding_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_active_margin: Int @Px 
        get() = _vertical_menu_parent_items_active_margin 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_margin = dpToPx(value)
    }
    var vertical_menu_parent_items_active_margin_left: Int @Px 
        get() = _vertical_menu_parent_items_active_margin_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_margin_left = dpToPx(value)
    }
    var vertical_menu_parent_items_active_margin_right: Int @Px 
        get() = _vertical_menu_parent_items_active_margin_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_margin_right = dpToPx(value)
    }
    var vertical_menu_parent_items_active_margin_top: Int @Px 
        get() = _vertical_menu_parent_items_active_margin_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_margin_top = dpToPx(value)
    }
    var vertical_menu_parent_items_active_margin_bottom: Int @Px 
        get() = _vertical_menu_parent_items_active_margin_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_margin_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_active_text_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_active_text_color 
        set(@ColorInt value) { _vertical_menu_parent_items_active_text_color = value
    }
    var vertical_menu_parent_items_active_text_size: Int @Px 
        get() = _vertical_menu_parent_items_active_text_size 
        set(@DpAnnotation value) { _vertical_menu_parent_items_active_text_size = dpToPx(value)
    }
    var vertical_menu_parent_items_active_text_is_bold: Boolean 
        get() = _vertical_menu_parent_items_active_text_is_bold 
        set(value) { _vertical_menu_parent_items_active_text_is_bold = value
    }
    var vertical_menu_parent_items_icon_width: Int @Px 
        get() = _vertical_menu_parent_items_icon_width 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_width = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_height: Int @Px 
        get() = _vertical_menu_parent_items_icon_height 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_height = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_padding: Int @Px 
        get() = _vertical_menu_parent_items_icon_padding 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_padding = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_padding_left: Int @Px 
        get() = _vertical_menu_parent_items_icon_padding_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_padding_left = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_padding_right: Int @Px 
        get() = _vertical_menu_parent_items_icon_padding_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_padding_right = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_padding_top: Int @Px 
        get() = _vertical_menu_parent_items_icon_padding_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_padding_top = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_padding_bottom: Int @Px 
        get() = _vertical_menu_parent_items_icon_padding_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_padding_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_margin: Int @Px 
        get() = _vertical_menu_parent_items_icon_margin 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_margin = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_margin_left: Int @Px 
        get() = _vertical_menu_parent_items_icon_margin_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_margin_left = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_margin_right: Int @Px 
        get() = _vertical_menu_parent_items_icon_margin_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_margin_right = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_margin_top: Int @Px 
        get() = _vertical_menu_parent_items_icon_margin_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_margin_top = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_margin_bottom: Int @Px 
        get() = _vertical_menu_parent_items_icon_margin_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_icon_margin_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_icon_tint_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_icon_tint_color 
        set(@ColorInt value) { _vertical_menu_parent_items_icon_tint_color = value
    }
    var vertical_menu_parent_items_icon_is_visible: Boolean 
        get() = _vertical_menu_parent_items_icon_is_visible 
        set(value) { _vertical_menu_parent_items_icon_is_visible = value
    }
    var vertical_menu_parent_items_icon_active_tint_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_icon_active_tint_color 
        set(@ColorInt value) { _vertical_menu_parent_items_icon_active_tint_color = value
    }
    var vertical_menu_parent_items_badge_width: Int @Px 
        get() = _vertical_menu_parent_items_badge_width 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_width = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_height: Int @Px 
        get() = _vertical_menu_parent_items_badge_height 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_height = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_padding: Int @Px 
        get() = _vertical_menu_parent_items_badge_padding 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_padding = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_padding_left: Int @Px 
        get() = _vertical_menu_parent_items_badge_padding_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_padding_left = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_padding_right: Int @Px 
        get() = _vertical_menu_parent_items_badge_padding_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_padding_right = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_padding_top: Int @Px 
        get() = _vertical_menu_parent_items_badge_padding_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_padding_top = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_padding_bottom: Int @Px 
        get() = _vertical_menu_parent_items_badge_padding_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_padding_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_margin: Int @Px 
        get() = _vertical_menu_parent_items_badge_margin 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_margin = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_margin_left: Int @Px 
        get() = _vertical_menu_parent_items_badge_margin_left 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_margin_left = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_margin_right: Int @Px 
        get() = _vertical_menu_parent_items_badge_margin_right 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_margin_right = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_margin_top: Int @Px 
        get() = _vertical_menu_parent_items_badge_margin_top 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_margin_top = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_margin_bottom: Int @Px 
        get() = _vertical_menu_parent_items_badge_margin_bottom 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_margin_bottom = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_background_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_badge_background_color 
        set(@ColorInt value) { _vertical_menu_parent_items_badge_background_color = value
    }
    var vertical_menu_parent_items_badge_border_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_badge_border_color 
        set(@ColorInt value) { _vertical_menu_parent_items_badge_border_color = value
    }
    var vertical_menu_parent_items_badge_border_thickness: Int @Px
        get() = _vertical_menu_parent_items_badge_border_thickness 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_border_thickness = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_text_color: Int @ColorInt 
        get() = _vertical_menu_parent_items_badge_text_color 
        set(@ColorInt value) { _vertical_menu_parent_items_badge_text_color = value
    }
    var vertical_menu_parent_items_badge_text_size: Int @Px 
        get() = _vertical_menu_parent_items_badge_text_size 
        set(@DpAnnotation value) { _vertical_menu_parent_items_badge_text_size = dpToPx(value)
    }
    var vertical_menu_parent_items_badge_text_is_bold: Boolean 
        get() = _vertical_menu_parent_items_badge_text_is_bold 
        set(value) { _vertical_menu_parent_items_badge_text_is_bold = value
    }
    var vertical_menu_parent_items_badge_text_is_visible: Boolean
        get() = _vertical_menu_parent_items_badge_text_is_visible
        set(value) { _vertical_menu_parent_items_badge_text_is_visible = value
    }
    var vertical_menu_parent_items_badge_is_visible: Boolean 
        get() = _vertical_menu_parent_items_badge_is_visible 
        set(value) { _vertical_menu_parent_items_badge_is_visible = value
    }
    var vertical_menu_parent_items_badge_is_cleared_on_active: Boolean 
        get() = _vertical_menu_parent_items_badge_is_cleared_on_active 
        set(value) { _vertical_menu_parent_items_badge_is_cleared_on_active = value 
    }
    var vertical_menu_parent_items_navigation_icon_width: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_width	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_width = dpToPx(value)
    }
    var vertical_menu_parent_items_navigation_icon_height: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_height	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_height = dpToPx(value)
    }
    var vertical_menu_parent_items_navigation_icon_padding: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_padding	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_padding = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_padding_left: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_padding_left	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_padding_left = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_padding_right: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_padding_right	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_padding_right = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_padding_top: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_padding_top	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_padding_top = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_padding_bottom: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_padding_bottom	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_padding_bottom = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_margin: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_margin	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_margin = dpToPx(value)
    }
    var vertical_menu_parent_items_navigation_icon_margin_left: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_margin_left	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_margin_left = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_margin_right: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_margin_right	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_margin_right = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_margin_top: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_margin_top	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_margin_top = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_margin_bottom: Int @Px 
        get() = _vertical_menu_parent_items_navigation_icon_margin_bottom	
        set(@DpAnnotation value) { _vertical_menu_parent_items_navigation_icon_margin_bottom = dpToPx(
            value
        )
    }
    var vertical_menu_parent_items_navigation_icon_color: Int @ColorInt 
        get() =	_vertical_menu_parent_items_navigation_icon_color	
        set(@ColorInt value) { _vertical_menu_parent_items_navigation_icon_color = value
    }
    var vertical_menu_parent_items_navigation_icon_drawable: Drawable?
        get() = _vertical_menu_parent_items_navigation_icon_drawable	
        set(value) { _vertical_menu_parent_items_navigation_icon_drawable = value
    }
    var vertical_menu_parent_items_navigation_icon_is_visible: Boolean 
        get() = _vertical_menu_parent_items_navigation_icon_is_visible	
        set(value) { _vertical_menu_parent_items_navigation_icon_is_visible = value
    }
    var vertical_menu_parent_items_navigation_icon_is_hidden_when_active: Boolean 
        get() = _vertical_menu_parent_items_navigation_icon_is_hidden_when_active	
        set(value) { _vertical_menu_parent_items_navigation_icon_is_hidden_when_active = value
    }
    var vertical_menu_parent_items_active_navigation_icon_color: Int @ColorInt 
        get() =	_vertical_menu_parent_items_active_navigation_icon_color	
        set(@ColorInt value) { _vertical_menu_parent_items_active_navigation_icon_color = value
    }

    //endregion get/set vertical_menu_parent values

    //region get/set vertical_menu_title values
    var vertical_menu_title_items_width: Int @Px
    get() = _vertical_menu_title_items_width
        set(@DpAnnotation value) { _vertical_menu_title_items_width = dpToPx(value)
        }
    var vertical_menu_title_items_height: Int @Px
    get() = _vertical_menu_title_items_height
        set(@DpAnnotation value) { _vertical_menu_title_items_height = dpToPx(value)
        }
    var vertical_menu_title_items_padding: Int @Px
    get() = _vertical_menu_title_items_padding
        set(@DpAnnotation value) { _vertical_menu_title_items_padding = dpToPx(value)
        }
    var vertical_menu_title_items_padding_left: Int @Px
    get() = _vertical_menu_title_items_padding_left
        set(@DpAnnotation value) { _vertical_menu_title_items_padding_left = dpToPx(value)
        }
    var vertical_menu_title_items_padding_right: Int @Px
    get() = _vertical_menu_title_items_padding_right
        set(@DpAnnotation value) { _vertical_menu_title_items_padding_right = dpToPx(value)
        }
    var vertical_menu_title_items_padding_top: Int @Px
    get() = _vertical_menu_title_items_padding_top
        set(@DpAnnotation value) { _vertical_menu_title_items_padding_top = dpToPx(value)
        }
    var vertical_menu_title_items_padding_bottom: Int @Px
    get() = _vertical_menu_title_items_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_title_items_padding_bottom = dpToPx(value)
        }
    var vertical_menu_title_items_margin: Int @Px
    get() = _vertical_menu_title_items_margin
        set(@DpAnnotation value) { _vertical_menu_title_items_margin = dpToPx(value)
        }
    var vertical_menu_title_items_margin_left: Int @Px
    get() = _vertical_menu_title_items_margin_left
        set(@DpAnnotation value) { _vertical_menu_title_items_margin_left = dpToPx(value)
        }
    var vertical_menu_title_items_margin_right: Int @Px
    get() = _vertical_menu_title_items_margin_right
        set(@DpAnnotation value) { _vertical_menu_title_items_margin_right = dpToPx(value)
        }
    var vertical_menu_title_items_margin_top: Int @Px
    get() = _vertical_menu_title_items_margin_top
        set(@DpAnnotation value) { _vertical_menu_title_items_margin_top = dpToPx(value)
        }
    var vertical_menu_title_items_margin_bottom: Int @Px
    get() = _vertical_menu_title_items_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_title_items_margin_bottom = dpToPx(value)
        }
    var vertical_menu_title_items_background_color: Int @ColorInt
    get() =_vertical_menu_title_items_background_color
        set(@ColorInt value) { _vertical_menu_title_items_background_color =  value
        }
    var vertical_menu_title_items_background_drawable: Int
        get() = _vertical_menu_title_items_background_drawable
        set(value) { _vertical_menu_title_items_background_drawable =  value
        }
    var vertical_menu_title_items_text_color: Int @ColorInt
    get() =	_vertical_menu_title_items_text_color
        set(@ColorInt value) { _vertical_menu_title_items_text_color =  value
        }
    var vertical_menu_title_items_text_size: Int @Px
    get() = _vertical_menu_title_items_text_size
        set(@DpAnnotation value) { _vertical_menu_title_items_text_size = dpToPx(value)
        }
    var vertical_menu_title_items_text_is_capitalized: Boolean
        get() = _vertical_menu_title_items_text_is_capitalized
        set(value) { _vertical_menu_title_items_text_is_capitalized =  value
        }
    var vertical_menu_title_items_text_is_bold: Boolean
        get() = _vertical_menu_title_items_text_is_bold
        set(value) { _vertical_menu_title_items_text_is_bold =  value
        }
    var vertical_menu_title_items_divider_visible: Boolean
        get() = _vertical_menu_title_items_divider_visible
        set(value) { _vertical_menu_title_items_divider_visible =  value
        }
    var vertical_menu_title_items_divider_thickness: Int @Px
    get() = _vertical_menu_title_items_divider_thickness
        set(@DpAnnotation value) { _vertical_menu_title_items_divider_thickness = dpToPx(value)
        }
    var vertical_menu_title_items_divider_color: Int @ColorInt
    get() =	_vertical_menu_title_items_divider_color
        set(@ColorInt value) { _vertical_menu_title_items_divider_color =  value
        }

    //endregion get/set vertical_menu_title values

    //region get/set vertical_menu_child values
    var vertical_menu_child_items_width: Int @Px 
        get() = _vertical_menu_child_items_width
        set(@DpAnnotation value) { _vertical_menu_child_items_width = dpToPx(value)
    }
    var vertical_menu_child_items_height: Int @Px 
        get() = _vertical_menu_child_items_height
        set(@DpAnnotation value) { _vertical_menu_child_items_height = dpToPx(value)
    }
    var vertical_menu_child_items_padding: Int @Px 
        get() = _vertical_menu_child_items_padding
        set(@DpAnnotation value) { _vertical_menu_child_items_padding = dpToPx(value)
    }
    var vertical_menu_child_items_padding_left: Int @Px 
        get() = _vertical_menu_child_items_padding_left
        set(@DpAnnotation value) { _vertical_menu_child_items_padding_left = dpToPx(value)
    }
    var vertical_menu_child_items_padding_right: Int @Px 
        get() = _vertical_menu_child_items_padding_right
        set(@DpAnnotation value) { _vertical_menu_child_items_padding_right = dpToPx(value)
    }
    var vertical_menu_child_items_padding_top: Int @Px 
        get() = _vertical_menu_child_items_padding_top
        set(@DpAnnotation value) { _vertical_menu_child_items_padding_top = dpToPx(value)
    }
    var vertical_menu_child_items_padding_bottom: Int @Px 
        get() = _vertical_menu_child_items_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_padding_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_margin: Int @Px 
        get() = _vertical_menu_child_items_margin
        set(@DpAnnotation value) { _vertical_menu_child_items_margin = dpToPx(value)
    }
    var vertical_menu_child_items_margin_left: Int @Px 
        get() = _vertical_menu_child_items_margin_left
        set(@DpAnnotation value) { _vertical_menu_child_items_margin_left = dpToPx(value)
    }
    var vertical_menu_child_items_margin_right: Int @Px 
        get() = _vertical_menu_child_items_margin_right
        set(@DpAnnotation value) { _vertical_menu_child_items_margin_right = dpToPx(value)
    }
    var vertical_menu_child_items_margin_top: Int @Px 
        get() = _vertical_menu_child_items_margin_top
        set(@DpAnnotation value) { _vertical_menu_child_items_margin_top = dpToPx(value)
    }
    var vertical_menu_child_items_margin_bottom: Int @Px 
        get() = _vertical_menu_child_items_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_margin_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_background_color: Int @ColorInt
        get() =	_vertical_menu_child_items_background_color
        set(@ColorInt value) { _vertical_menu_child_items_background_color =  value
    }
    var vertical_menu_child_items_active_background_color: Int @ColorInt
        get() = _vertical_menu_child_items_active_background_color
        set(@ColorInt value) { _vertical_menu_child_items_active_background_color =  value
    }
    var vertical_menu_child_items_text_color: Int @ColorInt
        get() =	_vertical_menu_child_items_text_color
        set(@ColorInt value) { _vertical_menu_child_items_text_color =  value
    }
    var vertical_menu_child_items_text_size: Int @Px 
        get() = _vertical_menu_child_items_text_size
        set(@DpAnnotation value) { _vertical_menu_child_items_text_size = dpToPx(value)
    }
    var vertical_menu_child_items_text_is_capitalized: Boolean 
        get() = _vertical_menu_child_items_text_is_capitalized
        set(value) { _vertical_menu_child_items_text_is_capitalized =  value
    }
    var vertical_menu_child_items_text_is_bold: Boolean 
        get() = _vertical_menu_child_items_text_is_bold
        set(value) { _vertical_menu_child_items_text_is_bold =  value
    }
    var vertical_menu_child_items_text_is_visible: Boolean 
        get() = _vertical_menu_child_items_text_is_visible
        set(value) { _vertical_menu_child_items_text_is_visible =  value
    }
    var vertical_menu_child_items_indicator_is_visible: Boolean 
        get() = _vertical_menu_child_items_indicator_is_visible
        set(value) { _vertical_menu_child_items_indicator_is_visible =  value
    }
    var vertical_menu_child_items_indicator_thickness: Int @Px 
        get() = _vertical_menu_child_items_indicator_thickness
        set(@DpAnnotation value) { _vertical_menu_child_items_indicator_thickness = dpToPx(value)
    }
    var vertical_menu_child_items_indicator_color: Int @ColorInt
        get() = _vertical_menu_child_items_indicator_color
        set(@ColorInt value) { _vertical_menu_child_items_indicator_color =  value
    }
    var vertical_menu_child_items_indicator_position: Int 
        get() = _vertical_menu_child_items_indicator_position
        set(value) { _vertical_menu_child_items_indicator_position =  value
    }
    var vertical_menu_child_items_active_padding: Int @Px 
        get() = _vertical_menu_child_items_active_padding
        set(@DpAnnotation value) { _vertical_menu_child_items_active_padding = dpToPx(value)
    }
    var vertical_menu_child_items_active_padding_left: Int @Px 
        get() = _vertical_menu_child_items_active_padding_left
        set(@DpAnnotation value) { _vertical_menu_child_items_active_padding_left = dpToPx(value)
    }
    var vertical_menu_child_items_active_padding_right: Int @Px 
        get() = _vertical_menu_child_items_active_padding_right
        set(@DpAnnotation value) { _vertical_menu_child_items_active_padding_right = dpToPx(value)
    }
    var vertical_menu_child_items_active_padding_top: Int @Px 
        get() = _vertical_menu_child_items_active_padding_top
        set(@DpAnnotation value) { _vertical_menu_child_items_active_padding_top = dpToPx(value)
    }
    var vertical_menu_child_items_active_padding_bottom: Int @Px 
        get() = _vertical_menu_child_items_active_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_active_padding_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_active_margin: Int @Px 
        get() = _vertical_menu_child_items_active_margin
        set(@DpAnnotation value) { _vertical_menu_child_items_active_margin = dpToPx(value)
    }
    var vertical_menu_child_items_active_margin_left: Int @Px 
        get() = _vertical_menu_child_items_active_margin_left
        set(@DpAnnotation value) { _vertical_menu_child_items_active_margin_left = dpToPx(value)
    }
    var vertical_menu_child_items_active_margin_right: Int @Px 
        get() = _vertical_menu_child_items_active_margin_right
        set(@DpAnnotation value) { _vertical_menu_child_items_active_margin_right = dpToPx(value)
    }
    var vertical_menu_child_items_active_margin_top: Int @Px 
        get() = _vertical_menu_child_items_active_margin_top
        set(@DpAnnotation value) { _vertical_menu_child_items_active_margin_top = dpToPx(value)
    }
    var vertical_menu_child_items_active_margin_bottom: Int @Px 
        get() = _vertical_menu_child_items_active_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_active_margin_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_active_text_color: Int @ColorInt
        get() =	_vertical_menu_child_items_active_text_color
        set(@ColorInt value) { _vertical_menu_child_items_active_text_color =  value
    }
    var vertical_menu_child_items_active_text_size: Int @Px 
        get() = _vertical_menu_child_items_active_text_size
        set(@DpAnnotation value) { _vertical_menu_child_items_active_text_size = dpToPx(value)
    }
    var vertical_menu_child_items_active_text_is_bold: Boolean 
        get() = _vertical_menu_child_items_active_text_is_bold
        set(value) { _vertical_menu_child_items_active_text_is_bold =  value
    }
    var vertical_menu_child_items_icon_width: Int @Px 
        get() = _vertical_menu_child_items_icon_width
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_width = dpToPx(value)
    }
    var vertical_menu_child_items_icon_height: Int @Px 
        get() = _vertical_menu_child_items_icon_height
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_height = dpToPx(value)
    }
    var vertical_menu_child_items_icon_padding: Int @Px 
        get() = _vertical_menu_child_items_icon_padding
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_padding = dpToPx(value)
    }
    var vertical_menu_child_items_icon_padding_left: Int @Px 
        get() = _vertical_menu_child_items_icon_padding_left
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_padding_left = dpToPx(value)
    }
    var vertical_menu_child_items_icon_padding_right: Int @Px 
        get() = _vertical_menu_child_items_icon_padding_right
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_padding_right = dpToPx(value)
    }
    var vertical_menu_child_items_icon_padding_top: Int @Px 
        get() = _vertical_menu_child_items_icon_padding_top
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_padding_top = dpToPx(value)
    }
    var vertical_menu_child_items_icon_padding_bottom: Int @Px 
        get() = _vertical_menu_child_items_icon_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_padding_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_icon_margin: Int @Px 
        get() = _vertical_menu_child_items_icon_margin
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_margin = dpToPx(value)
    }
    var vertical_menu_child_items_icon_margin_left: Int @Px 
        get() = _vertical_menu_child_items_icon_margin_left
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_margin_left = dpToPx(value)
    }
    var vertical_menu_child_items_icon_margin_right: Int @Px 
        get() = _vertical_menu_child_items_icon_margin_right
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_margin_right = dpToPx(value)
    }
    var vertical_menu_child_items_icon_margin_top: Int @Px 
        get() = _vertical_menu_child_items_icon_margin_top
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_margin_top = dpToPx(value)
    }
    var vertical_menu_child_items_icon_margin_bottom: Int @Px 
        get() = _vertical_menu_child_items_icon_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_icon_margin_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_icon_tint_color: Int @ColorInt
        get() = _vertical_menu_child_items_icon_tint_color
        set(@ColorInt value) { _vertical_menu_child_items_icon_tint_color =  value
    }
    var vertical_menu_child_items_icon_is_visible: Boolean 
        get() = _vertical_menu_child_items_icon_is_visible
        set(value) { _vertical_menu_child_items_icon_is_visible =  value
    }
    var vertical_menu_child_items_icon_active_tint_color: Int @ColorInt
        get() =	_vertical_menu_child_items_icon_active_tint_color
        set(@ColorInt value) { _vertical_menu_child_items_icon_active_tint_color =  value
    }
    var vertical_menu_child_items_badge_width: Int @Px 
        get() = _vertical_menu_child_items_badge_width
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_width = dpToPx(value)
    }
    var vertical_menu_child_items_badge_height: Int @Px 
        get() = _vertical_menu_child_items_badge_height
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_height = dpToPx(value)
    }
    var vertical_menu_child_items_badge_padding: Int @Px 
        get() = _vertical_menu_child_items_badge_padding
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_padding = dpToPx(value)
    }
    var vertical_menu_child_items_badge_padding_left: Int @Px 
        get() = _vertical_menu_child_items_badge_padding_left
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_padding_left = dpToPx(value)
    }
    var vertical_menu_child_items_badge_padding_right: Int @Px 
        get() = _vertical_menu_child_items_badge_padding_right
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_padding_right = dpToPx(value)
    }
    var vertical_menu_child_items_badge_padding_top: Int @Px 
        get() = _vertical_menu_child_items_badge_padding_top
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_padding_top = dpToPx(value)
    }
    var vertical_menu_child_items_badge_padding_bottom: Int @Px 
        get() = _vertical_menu_child_items_badge_padding_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_padding_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_badge_margin: Int @Px 
        get() = _vertical_menu_child_items_badge_margin
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_margin = dpToPx(value)
    }
    var vertical_menu_child_items_badge_margin_left: Int @Px 
        get() = _vertical_menu_child_items_badge_margin_left
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_margin_left = dpToPx(value)
    }
    var vertical_menu_child_items_badge_margin_right: Int @Px 
        get() = _vertical_menu_child_items_badge_margin_right
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_margin_right = dpToPx(value)
    }
    var vertical_menu_child_items_badge_margin_top: Int @Px 
        get() = _vertical_menu_child_items_badge_margin_top
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_margin_top = dpToPx(value)
    }
    var vertical_menu_child_items_badge_margin_bottom: Int @Px 
        get() = _vertical_menu_child_items_badge_margin_bottom
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_margin_bottom = dpToPx(value)
    }
    var vertical_menu_child_items_badge_background_color: Int @ColorInt
        get() =	_vertical_menu_child_items_badge_background_color
        set(@ColorInt value) { _vertical_menu_child_items_badge_background_color =  value
    }
    var vertical_menu_child_items_badge_border_color: Int @ColorInt
        get() =	_vertical_menu_child_items_badge_border_color
        set(@ColorInt value) { _vertical_menu_child_items_badge_border_color =  value
    }
    var vertical_menu_child_items_badge_border_thickness: Int @Px 
        get() = _vertical_menu_child_items_badge_border_thickness
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_border_thickness = dpToPx(value)
    }
    var vertical_menu_child_items_badge_text_color: Int @ColorInt
        get() = _vertical_menu_child_items_badge_text_color
        set(@ColorInt value) { _vertical_menu_child_items_badge_text_color =  value
    }
    var vertical_menu_child_items_badge_text_size: Int @Px 
        get() = _vertical_menu_child_items_badge_text_size
        set(@DpAnnotation value) { _vertical_menu_child_items_badge_text_size = dpToPx(value)
    }
    var vertical_menu_child_items_badge_text_is_bold: Boolean 
        get() = _vertical_menu_child_items_badge_text_is_bold
        set(value) { _vertical_menu_child_items_badge_text_is_bold =  value
    }
    var vertical_menu_child_items_badge_is_visible: Boolean 
        get() = _vertical_menu_child_items_badge_is_visible
        set(value) { _vertical_menu_child_items_badge_is_visible =  value
    }
    var vertical_menu_child_items_badge_is_cleared_on_active: Boolean 
        get() = _vertical_menu_child_items_badge_is_cleared_on_active
        set(value) { _vertical_menu_child_items_badge_is_cleared_on_active =  value
    }
    var vertical_menu_child_items_background_drawable: Drawable?
        get() = _vertical_menu_child_items_background_drawable
        set(value) { _vertical_menu_child_items_background_drawable =  value
    }
    var vertical_menu_child_items_active_background_drawable: Drawable?
        get() = _vertical_menu_child_items_active_background_drawable
        set(value) { _vertical_menu_child_items_active_background_drawable =  value
    }
    var vertical_menu_child_items_badge_text_is_visible: Boolean 
        get() = _vertical_menu_child_items_badge_text_is_visible
        set(value) { _vertical_menu_child_items_badge_text_is_visible =  value
    }
    var vertical_menu_child_items_when_active_show_parent_active_state: Boolean 
        get() = _vertical_menu_child_items_when_active_show_parent_active_state
        set(value) { _vertical_menu_child_items_when_active_show_parent_active_state =  value
    }
    //endregion get/set vertical_menu_child values

    //endregion get/set values

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu,
            defStyleAttr,
            0
        )
        try {
           setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    //region setTypeArray
    private fun setTypeArray(typedArray: TypedArray) {

        //region get/set vertical_menu values
        this.vertical_menu_is_responsive_menu = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_is_responsive_menu,
            this._vertical_menu_is_responsive_menu
        )


        this._vertical_menu_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_width,
            this._vertical_menu_width
        )
        this._vertical_menu_max_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_max_width,
            this._vertical_menu_max_width
        )
        this._vertical_menu_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_padding,
            this._vertical_menu_padding
        )
        this._vertical_menu_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_padding_left,
            this._vertical_menu_padding_left
        )
        this._vertical_menu_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_padding_right,
            this._vertical_menu_padding_right
        )
        this._vertical_menu_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_padding_top,
            this._vertical_menu_padding_top
        )
        this._vertical_menu_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_padding_bottom,
            this._vertical_menu_padding_bottom
        )
        this._vertical_menu_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_margin,
            this._vertical_menu_margin
        )
        this._vertical_menu_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_margin_left,
            this._vertical_menu_margin_left
        )
        this._vertical_menu_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_margin_right,
            this._vertical_menu_margin_right
        )
        this._vertical_menu_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_margin_top,
            this._vertical_menu_margin_top
        )
        this._vertical_menu_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_margin_bottom,
            this._vertical_menu_margin_bottom
        )
        this._vertical_menu_alignment_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_alignment_position,
            this._vertical_menu_alignment_position
        )
        this._vertical_menu_enable_nested_scrolling = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_enable_nested_scrolling,
            this._vertical_menu_enable_nested_scrolling
        )
        this._vertical_menu_show_scroll_bar = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_scroll_bar,
            this._vertical_menu_show_scroll_bar
        )
        this._vertical_menu_active_state_on_left = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_active_state_on_left,
            this._vertical_menu_active_state_on_left
        )
        this._vertical_menu_show_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_icons,
            this._vertical_menu_show_icons
        )
        this._vertical_menu_show_parent_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_parent_icons,
            this._vertical_menu_show_parent_icons
        )
        this._vertical_menu_show_child_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_child_icons,
            this._vertical_menu_show_child_icons
        )
        this._vertical_menu_show_parent_divider = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_parent_divider,
            this._vertical_menu_show_parent_divider
        )
        this._vertical_menu_show_icons_only = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_icons_only,
            this._vertical_menu_show_icons_only
        )
        this._vertical_menu_collapse_type = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_type,
            this._vertical_menu_collapse_type
        )
        this._vertical_menu_collapse_others = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_others,
            this._vertical_menu_collapse_others
        )
        this._vertical_menu_collapse_expand_delay = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_expand_delay,
            this._vertical_menu_collapse_expand_delay
        )
        this._vertical_menu_collapse_icon_rotate = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_rotate,
            this._vertical_menu_collapse_icon_rotate
        )
        this._vertical_menu_collapse_icon_rotate_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_rotate_duration,
            this._vertical_menu_collapse_icon_rotate_duration
        )
        this._vertical_menu_collapse_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_width,
            this._vertical_menu_collapse_icon_width
        )
        this._vertical_menu_collapse_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_height,
            this._vertical_menu_collapse_icon_height
        )
        this._vertical_menu_collapse_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_padding,
            this._vertical_menu_collapse_icon_padding
        )
        this._vertical_menu_collapse_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_padding_left,
            this._vertical_menu_collapse_icon_padding_left
        )
        this._vertical_menu_collapse_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_padding_right,
            this._vertical_menu_collapse_icon_padding_right
        )
        this._vertical_menu_collapse_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_padding_top,
            this._vertical_menu_collapse_icon_padding_top
        )
        this._vertical_menu_collapse_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_padding_bottom,
            this._vertical_menu_collapse_icon_padding_bottom
        )
        this._vertical_menu_collapse_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_margin,
            this._vertical_menu_collapse_icon_margin
        )
        this._vertical_menu_collapse_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_margin_left,
            this._vertical_menu_collapse_icon_margin_left
        )
        this._vertical_menu_collapse_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_margin_right,
            this._vertical_menu_collapse_icon_margin_right
        )
        this._vertical_menu_collapse_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_margin_top,
            this._vertical_menu_collapse_icon_margin_top
        )
        this._vertical_menu_collapse_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_margin_bottom,
            this._vertical_menu_collapse_icon_margin_bottom
        )
        this._vertical_menu_collapse_icon_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_color,
            this._vertical_menu_collapse_icon_color
        )
        this._vertical_menu_collapse_icon_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_drawable
        )
        this._vertical_menu_collapse_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_collapse_icon_is_visible,
            this._vertical_menu_collapse_icon_is_visible
        )
        this._vertical_menu_border_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_border_is_visible,
            this._vertical_menu_border_is_visible
        )
        this._vertical_menu_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_border_thickness,
            this._vertical_menu_border_thickness
        )
        this._vertical_menu_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_border_color,
            this._vertical_menu_border_color
        )
        this._vertical_menu_border_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_border_position,
            this._vertical_menu_border_position
        )
        this._vertical_menu_show_shimmer_animation = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_shimmer_animation,
            this._vertical_menu_show_shimmer_animation
        )
        this._vertical_menu_show_shimmer_animation_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_shimmer_animation_duration,
            this._vertical_menu_show_shimmer_animation_duration
        )
        this._vertical_menu_show_animation_in = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_animation_in,
            this._vertical_menu_show_animation_in
        )
        this._vertical_menu_show_animation_in_type = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_animation_in_type,
            this._vertical_menu_show_animation_in_type
        )
        this._vertical_menu_show_animation_in_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_show_animation_in_duration,
            this._vertical_menu_show_animation_in_duration
        )
        this._vertical_menu_divider_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_color,
            this._vertical_menu_divider_color
        )
        this._vertical_menu_divider_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_width,
            this._vertical_menu_divider_width
        )
        this._vertical_menu_divider_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_height,
            this._vertical_menu_divider_height
        )
        this._vertical_menu_divider_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_padding,
            this._vertical_menu_divider_padding
        )
        this._vertical_menu_divider_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_padding_left,
            this._vertical_menu_divider_padding_left
        )
        this._vertical_menu_divider_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_padding_right,
            this._vertical_menu_divider_padding_right
        )
        this._vertical_menu_divider_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_padding_top,
            this._vertical_menu_divider_padding_top
        )
        this._vertical_menu_divider_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_padding_bottom,
            this._vertical_menu_divider_padding_bottom
        )
        this._vertical_menu_divider_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_margin,
            this._vertical_menu_divider_margin
        )
        this._vertical_menu_divider_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_margin_left,
            this._vertical_menu_divider_margin_left
        )
        this._vertical_menu_divider_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_margin_right,
            this._vertical_menu_divider_margin_right
        )
        this._vertical_menu_divider_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_margin_top,
            this._vertical_menu_divider_margin_top
        )
        this._vertical_menu_divider_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_margin_bottom,
            this._vertical_menu_divider_margin_bottom
        )
        this._vertical_menu_divider_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_divider_is_visible,
            this._vertical_menu_divider_is_visible
        )
        this._vertical_menu_logo_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_width, 
            this._vertical_menu_logo_width
        )
        this._vertical_menu_logo_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_height, 
            this._vertical_menu_logo_height
        )
        this._vertical_menu_logo_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_padding, 
            this._vertical_menu_logo_padding
        )
        this._vertical_menu_logo_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_padding_left, 
            this._vertical_menu_logo_padding_left
        )
        this._vertical_menu_logo_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_padding_right, 
            this._vertical_menu_logo_padding_right
        )
        this._vertical_menu_logo_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_padding_top, 
            this._vertical_menu_logo_padding_top
        )
        this._vertical_menu_logo_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_padding_bottom, 
            this._vertical_menu_logo_padding_bottom
        )
        this._vertical_menu_logo_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_margin, 
            this._vertical_menu_logo_margin
        )
        this._vertical_menu_logo_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_margin_left, 
            this._vertical_menu_logo_margin_left
        )
        this._vertical_menu_logo_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_margin_right, 
            this._vertical_menu_logo_margin_right
        )
        this._vertical_menu_logo_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_margin_top, 
            this._vertical_menu_logo_margin_top
        )
        this._vertical_menu_logo_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_margin_bottom, 
            this._vertical_menu_logo_margin_bottom
        )
        this._vertical_menu_logo_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_drawable
        )
        this._vertical_menu_logo_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_is_visible, 
            this._vertical_menu_logo_is_visible
        )
        this._vertical_menu_logo_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_logo_position, 
            this._vertical_menu_logo_position
        )
        //endregion get/set vertical_menu values

        //region get/set vertical_menu_parent values
        this._vertical_menu_parent_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_width,
            this._vertical_menu_parent_items_width
        )
        this._vertical_menu_parent_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_height,
            this._vertical_menu_parent_items_height
        )
        this._vertical_menu_parent_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_padding,
            this._vertical_menu_parent_items_padding
        )
        this._vertical_menu_parent_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_padding_left,
            this._vertical_menu_parent_items_padding_left
        )
        this._vertical_menu_parent_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_padding_right,
            this._vertical_menu_parent_items_padding_right
        )
        this._vertical_menu_parent_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_padding_top,
            this._vertical_menu_parent_items_padding_top
        )
        this._vertical_menu_parent_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_padding_bottom,
            this._vertical_menu_parent_items_padding_bottom
        )
        this._vertical_menu_parent_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_margin,
            this._vertical_menu_parent_items_margin
        )
        this._vertical_menu_parent_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_margin_left,
            this._vertical_menu_parent_items_margin_left
        )
        this._vertical_menu_parent_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_margin_right,
            this._vertical_menu_parent_items_margin_right
        )
        this._vertical_menu_parent_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_margin_top,
            this._vertical_menu_parent_items_margin_top
        )
        this._vertical_menu_parent_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_margin_bottom,
            this._vertical_menu_parent_items_margin_bottom
        )
        this._vertical_menu_parent_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_background_color,
            this._vertical_menu_parent_items_background_color
        )
        this._vertical_menu_parent_items_active_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_background_color,
            this._vertical_menu_parent_items_active_background_color
        )
        this._vertical_menu_parent_items_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_background_drawable
        )
        this._vertical_menu_parent_items_active_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_background_drawable
        )
        this._vertical_menu_parent_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_text_color,
            this._vertical_menu_parent_items_text_color
        )
        this._vertical_menu_parent_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_text_size,
            this._vertical_menu_parent_items_text_size
        )
        this._vertical_menu_parent_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_text_is_capitalized,
            this._vertical_menu_parent_items_text_is_capitalized
        )
        this._vertical_menu_parent_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_text_is_bold,
            this._vertical_menu_parent_items_text_is_bold
        )
        this._vertical_menu_parent_items_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_text_is_visible,
            this._vertical_menu_parent_items_text_is_visible
        )
        this._vertical_menu_parent_items_indicator_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_indicator_is_visible,
            this._vertical_menu_parent_items_indicator_is_visible
        )
        this._vertical_menu_parent_items_indicator_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_indicator_thickness,
            this._vertical_menu_parent_items_indicator_thickness
        )
        this._vertical_menu_parent_items_indicator_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_indicator_color,
            this._vertical_menu_parent_items_indicator_color
        )
        this._vertical_menu_parent_items_indicator_position = typedArray.getInteger(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_indicator_position,
            this._vertical_menu_parent_items_indicator_position
        )
        this._vertical_menu_parent_items_active_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_padding,
            this._vertical_menu_parent_items_active_padding
        )
        this._vertical_menu_parent_items_active_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_padding_left,
            this._vertical_menu_parent_items_active_padding_left
        )
        this._vertical_menu_parent_items_active_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_padding_right,
            this._vertical_menu_parent_items_active_padding_right
        )
        this._vertical_menu_parent_items_active_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_padding_top,
            this._vertical_menu_parent_items_active_padding_top
        )
        this._vertical_menu_parent_items_active_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_padding_bottom,
            this._vertical_menu_parent_items_active_padding_bottom
        )
        this._vertical_menu_parent_items_active_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_margin,
            this._vertical_menu_parent_items_active_margin
        )
        this._vertical_menu_parent_items_active_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_margin_left,
            this._vertical_menu_parent_items_active_margin_left
        )
        this._vertical_menu_parent_items_active_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_margin_right,
            this._vertical_menu_parent_items_active_margin_right
        )
        this._vertical_menu_parent_items_active_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_margin_top,
            this._vertical_menu_parent_items_active_margin_top
        )
        this._vertical_menu_parent_items_active_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_margin_bottom,
            this._vertical_menu_parent_items_active_margin_bottom
        )
        this._vertical_menu_parent_items_active_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_text_color,
            this._vertical_menu_parent_items_active_text_color
        )
        this._vertical_menu_parent_items_active_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_text_size,
            this._vertical_menu_parent_items_active_text_size
        )
        this._vertical_menu_parent_items_active_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_text_is_bold,
            this._vertical_menu_parent_items_active_text_is_bold
        )
        this._vertical_menu_parent_items_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_width,
            this._vertical_menu_parent_items_icon_width
        )
        this._vertical_menu_parent_items_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_height,
            this._vertical_menu_parent_items_icon_height
        )
        this._vertical_menu_parent_items_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_padding,
            this._vertical_menu_parent_items_icon_padding
        )
        this._vertical_menu_parent_items_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_padding_left,
            this._vertical_menu_parent_items_icon_padding_left
        )
        this._vertical_menu_parent_items_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_padding_right,
            this._vertical_menu_parent_items_icon_padding_right
        )
        this._vertical_menu_parent_items_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_padding_top,
            this._vertical_menu_parent_items_icon_padding_top
        )
        this._vertical_menu_parent_items_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_padding_bottom,
            this._vertical_menu_parent_items_icon_padding_bottom
        )
        this._vertical_menu_parent_items_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_margin,
            this._vertical_menu_parent_items_icon_margin
        )
        this._vertical_menu_parent_items_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_margin_left,
            this._vertical_menu_parent_items_icon_margin_left
        )
        this._vertical_menu_parent_items_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_margin_right,
            this._vertical_menu_parent_items_icon_margin_right
        )
        this._vertical_menu_parent_items_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_margin_top,
            this._vertical_menu_parent_items_icon_margin_top
        )
        this._vertical_menu_parent_items_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_margin_bottom,
            this._vertical_menu_parent_items_icon_margin_bottom
        )
        this._vertical_menu_parent_items_icon_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_tint_color,
            this._vertical_menu_parent_items_icon_tint_color
        )
        this._vertical_menu_parent_items_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_is_visible,
            this._vertical_menu_parent_items_icon_is_visible
        )
        this._vertical_menu_parent_items_icon_active_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_icon_active_tint_color,
            this._vertical_menu_parent_items_icon_active_tint_color
        )
        this._vertical_menu_parent_items_badge_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_width,
            this._vertical_menu_parent_items_badge_width
        )
        this._vertical_menu_parent_items_badge_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_height,
            this._vertical_menu_parent_items_badge_height
        )
        this._vertical_menu_parent_items_badge_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_padding,
            this._vertical_menu_parent_items_badge_padding
        )
        this._vertical_menu_parent_items_badge_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_padding_left,
            this._vertical_menu_parent_items_badge_padding_left
        )
        this._vertical_menu_parent_items_badge_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_padding_right,
            this._vertical_menu_parent_items_badge_padding_right
        )
        this._vertical_menu_parent_items_badge_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_padding_top,
            this._vertical_menu_parent_items_badge_padding_top
        )
        this._vertical_menu_parent_items_badge_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_padding_bottom,
            this._vertical_menu_parent_items_badge_padding_bottom
        )
        this._vertical_menu_parent_items_badge_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_margin,
            this._vertical_menu_parent_items_badge_margin
        )
        this._vertical_menu_parent_items_badge_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_margin_left,
            this._vertical_menu_parent_items_badge_margin_left
        )
        this._vertical_menu_parent_items_badge_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_margin_right,
            this._vertical_menu_parent_items_badge_margin_right
        )
        this._vertical_menu_parent_items_badge_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_margin_top,
            this._vertical_menu_parent_items_badge_margin_top
        )
        this._vertical_menu_parent_items_badge_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_margin_bottom,
            this._vertical_menu_parent_items_badge_margin_bottom
        )
        this._vertical_menu_parent_items_badge_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_background_color,
            this._vertical_menu_parent_items_badge_background_color
        )
        this._vertical_menu_parent_items_badge_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_border_color,
            this._vertical_menu_parent_items_badge_border_color
        )
        this._vertical_menu_parent_items_badge_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_border_thickness,
            this._vertical_menu_parent_items_badge_border_thickness
        )
        this._vertical_menu_parent_items_badge_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_text_color,
            this._vertical_menu_parent_items_badge_text_color
        )
        this._vertical_menu_parent_items_badge_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_text_size,
            this._vertical_menu_parent_items_badge_text_size
        )
        this._vertical_menu_parent_items_badge_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_text_is_bold,
            this._vertical_menu_parent_items_badge_text_is_bold
        )
        this._vertical_menu_parent_items_badge_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_text_is_visible,
            this._vertical_menu_parent_items_badge_text_is_visible
        )
        this._vertical_menu_parent_items_badge_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_is_visible,
            this._vertical_menu_parent_items_badge_is_visible
        )
        this._vertical_menu_parent_items_badge_is_cleared_on_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_badge_is_cleared_on_active,
            this._vertical_menu_parent_items_badge_is_cleared_on_active
        )

        this._vertical_menu_parent_items_navigation_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_width,
            this._vertical_menu_parent_items_navigation_icon_width
        )
        this._vertical_menu_parent_items_navigation_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_height,
            this._vertical_menu_parent_items_navigation_icon_height
        )
        this._vertical_menu_parent_items_navigation_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_padding,
            this._vertical_menu_parent_items_navigation_icon_padding
        )
        this._vertical_menu_parent_items_navigation_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_padding_left,
            this._vertical_menu_parent_items_navigation_icon_padding_left
        )
        this._vertical_menu_parent_items_navigation_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_padding_right,
            this._vertical_menu_parent_items_navigation_icon_padding_right
        )
        this._vertical_menu_parent_items_navigation_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_padding_top,
            this._vertical_menu_parent_items_navigation_icon_padding_top
        )
        this._vertical_menu_parent_items_navigation_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_padding_bottom,
            this._vertical_menu_parent_items_navigation_icon_padding_bottom
        )
        this._vertical_menu_parent_items_navigation_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_margin,
            this._vertical_menu_parent_items_navigation_icon_margin
        )
        this._vertical_menu_parent_items_navigation_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_margin_left,
            this._vertical_menu_parent_items_navigation_icon_margin_left
        )
        this._vertical_menu_parent_items_navigation_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_margin_right,
            this._vertical_menu_parent_items_navigation_icon_margin_right
        )
        this._vertical_menu_parent_items_navigation_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_margin_top,
            this._vertical_menu_parent_items_navigation_icon_margin_top
        )
        this._vertical_menu_parent_items_navigation_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_margin_bottom,
            this._vertical_menu_parent_items_navigation_icon_margin_bottom
        )
        this._vertical_menu_parent_items_navigation_icon_color = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_color,
            this._vertical_menu_parent_items_navigation_icon_color
        )
        this._vertical_menu_parent_items_navigation_icon_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_drawable
        )
        this._vertical_menu_parent_items_navigation_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_is_visible,
            this._vertical_menu_parent_items_navigation_icon_is_visible
        )
        this._vertical_menu_parent_items_navigation_icon_is_hidden_when_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_navigation_icon_is_hidden_when_active,
            this._vertical_menu_parent_items_navigation_icon_is_hidden_when_active
        )
        this._vertical_menu_parent_items_active_navigation_icon_color = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_parent_items_active_navigation_icon_color,
            this._vertical_menu_parent_items_active_navigation_icon_color
        )

        //endregion get/set vertical_menu_parent values

        //region get/set vertical_menu_title values
        this._vertical_menu_title_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_width,
            this._vertical_menu_title_items_width
        )
        this._vertical_menu_title_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_height,
            this._vertical_menu_title_items_height
        )
        this._vertical_menu_title_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_padding,
            this._vertical_menu_title_items_padding
        )
        this._vertical_menu_title_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_padding_left,
            this._vertical_menu_title_items_padding_left
        )
        this._vertical_menu_title_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_padding_right,
            this._vertical_menu_title_items_padding_right
        )
        this._vertical_menu_title_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_padding_top,
            this._vertical_menu_title_items_padding_top
        )
        this._vertical_menu_title_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_padding_bottom,
            this._vertical_menu_title_items_padding_bottom
        )
        this._vertical_menu_title_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_margin,
            this._vertical_menu_title_items_margin
        )
        this._vertical_menu_title_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_margin_left,
            this._vertical_menu_title_items_margin_left
        )
        this._vertical_menu_title_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_margin_right,
            this._vertical_menu_title_items_margin_right
        )
        this._vertical_menu_title_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_margin_top,
            this._vertical_menu_title_items_margin_top
        )
        this._vertical_menu_title_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_margin_bottom,
            this._vertical_menu_title_items_margin_bottom
        )
        this._vertical_menu_title_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_background_color,
            this._vertical_menu_title_items_background_color
        )
        this._vertical_menu_title_items_background_drawable = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_background_drawable,
            this._vertical_menu_title_items_background_drawable
        )
        this._vertical_menu_title_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_text_color,
            this._vertical_menu_title_items_text_color
        )
        this._vertical_menu_title_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_text_size,
            this._vertical_menu_title_items_text_size
        )
        this._vertical_menu_title_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_text_is_capitalized,
            this._vertical_menu_title_items_text_is_capitalized
        )
        this._vertical_menu_title_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_text_is_bold,
            this._vertical_menu_title_items_text_is_bold
        )
        this._vertical_menu_title_items_divider_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_divider_visible,
            this._vertical_menu_title_items_divider_visible
        )
        this._vertical_menu_title_items_divider_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_divider_thickness,
            this._vertical_menu_title_items_divider_thickness
        )
        this._vertical_menu_title_items_divider_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_title_items_divider_color,
            this._vertical_menu_title_items_divider_color
        )
        //endregion get/set vertical_menu_title values

        //region get/set vertical_menu_child values
        this._vertical_menu_child_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_width,
            this._vertical_menu_child_items_width
        )
        this._vertical_menu_child_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_height,
            this._vertical_menu_child_items_height
        )
        this._vertical_menu_child_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_padding,
            this._vertical_menu_child_items_padding
        )
        this._vertical_menu_child_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_padding_left,
            this._vertical_menu_child_items_padding_left
        )
        this._vertical_menu_child_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_padding_right,
            this._vertical_menu_child_items_padding_right
        )
        this._vertical_menu_child_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_padding_top,
            this._vertical_menu_child_items_padding_top
        )
        this._vertical_menu_child_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_padding_bottom,
            this._vertical_menu_child_items_padding_bottom
        )
        this._vertical_menu_child_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_margin,
            this._vertical_menu_child_items_margin
        )
        this._vertical_menu_child_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_margin_left,
            this._vertical_menu_child_items_margin_left
        )
        this._vertical_menu_child_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_margin_right,
            this._vertical_menu_child_items_margin_right
        )
        this._vertical_menu_child_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_margin_top,
            this._vertical_menu_child_items_margin_top
        )
        this._vertical_menu_child_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_margin_bottom,
            this._vertical_menu_child_items_margin_bottom
        )
        this._vertical_menu_child_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_background_color,
            this._vertical_menu_child_items_background_color
        )
        this._vertical_menu_child_items_active_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_background_color,
            this._vertical_menu_child_items_active_background_color
        )
        this._vertical_menu_child_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_text_color,
            this._vertical_menu_child_items_text_color
        )
        this._vertical_menu_child_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_text_size,
            this._vertical_menu_child_items_text_size
        )
        this._vertical_menu_child_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_text_is_capitalized,
            this._vertical_menu_child_items_text_is_capitalized
        )
        this._vertical_menu_child_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_text_is_bold,
            this._vertical_menu_child_items_text_is_bold
        )
        this._vertical_menu_child_items_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_text_is_visible,
            this._vertical_menu_child_items_text_is_visible
        )
        this._vertical_menu_child_items_indicator_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_indicator_is_visible,
            this._vertical_menu_child_items_indicator_is_visible
        )
        this._vertical_menu_child_items_indicator_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_indicator_thickness,
            this._vertical_menu_child_items_indicator_thickness
        )
        this._vertical_menu_child_items_indicator_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_indicator_color,
            this._vertical_menu_child_items_indicator_color
        )
        this._vertical_menu_child_items_indicator_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_indicator_position,
            this._vertical_menu_child_items_indicator_position
        )
        this._vertical_menu_child_items_active_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_padding,
            this._vertical_menu_child_items_active_padding
        )
        this._vertical_menu_child_items_active_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_padding_left,
            this._vertical_menu_child_items_active_padding_left
        )
        this._vertical_menu_child_items_active_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_padding_right,
            this._vertical_menu_child_items_active_padding_right
        )
        this._vertical_menu_child_items_active_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_padding_top,
            this._vertical_menu_child_items_active_padding_top
        )
        this._vertical_menu_child_items_active_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_padding_bottom,
            this._vertical_menu_child_items_active_padding_bottom
        )
        this._vertical_menu_child_items_active_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_margin,
            this._vertical_menu_child_items_active_margin
        )
        this._vertical_menu_child_items_active_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_margin_left,
            this._vertical_menu_child_items_active_margin_left
        )
        this._vertical_menu_child_items_active_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_margin_right,
            this._vertical_menu_child_items_active_margin_right
        )
        this._vertical_menu_child_items_active_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_margin_top,
            this._vertical_menu_child_items_active_margin_top
        )
        this._vertical_menu_child_items_active_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_margin_bottom,
            this._vertical_menu_child_items_active_margin_bottom
        )
        this._vertical_menu_child_items_active_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_text_color,
            this._vertical_menu_child_items_active_text_color
        )
        this._vertical_menu_child_items_active_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_text_size,
            this._vertical_menu_child_items_active_text_size
        )
        this._vertical_menu_child_items_active_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_text_is_bold,
            this._vertical_menu_child_items_active_text_is_bold
        )
        this._vertical_menu_child_items_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_width,
            this._vertical_menu_child_items_icon_width
        )
        this._vertical_menu_child_items_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_height,
            this._vertical_menu_child_items_icon_height
        )
        this._vertical_menu_child_items_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_padding,
            this._vertical_menu_child_items_icon_padding
        )
        this._vertical_menu_child_items_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_padding_left,
            this._vertical_menu_child_items_icon_padding_left
        )
        this._vertical_menu_child_items_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_padding_right,
            this._vertical_menu_child_items_icon_padding_right
        )
        this._vertical_menu_child_items_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_padding_top,
            this._vertical_menu_child_items_icon_padding_top
        )
        this._vertical_menu_child_items_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_padding_bottom,
            this._vertical_menu_child_items_icon_padding_bottom
        )
        this._vertical_menu_child_items_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_margin,
            this._vertical_menu_child_items_icon_margin
        )
        this._vertical_menu_child_items_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_margin_left,
            this._vertical_menu_child_items_icon_margin_left
        )
        this._vertical_menu_child_items_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_margin_right,
            this._vertical_menu_child_items_icon_margin_right
        )
        this._vertical_menu_child_items_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_margin_top,
            this._vertical_menu_child_items_icon_margin_top
        )
        this._vertical_menu_child_items_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_margin_bottom,
            this._vertical_menu_child_items_icon_margin_bottom
        )
        this._vertical_menu_child_items_icon_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_tint_color,
            this._vertical_menu_child_items_icon_tint_color
        )
        this._vertical_menu_child_items_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_is_visible,
            this._vertical_menu_child_items_icon_is_visible
        )
        this._vertical_menu_child_items_icon_active_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_icon_active_tint_color,
            this._vertical_menu_child_items_icon_active_tint_color
        )
        this._vertical_menu_child_items_badge_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_width,
            this._vertical_menu_child_items_badge_width
        )
        this._vertical_menu_child_items_badge_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_height,
            this._vertical_menu_child_items_badge_height
        )
        this._vertical_menu_child_items_badge_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_padding,
            this._vertical_menu_child_items_badge_padding
        )
        this._vertical_menu_child_items_badge_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_padding_left,
            this._vertical_menu_child_items_badge_padding_left
        )
        this._vertical_menu_child_items_badge_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_padding_right,
            this._vertical_menu_child_items_badge_padding_right
        )
        this._vertical_menu_child_items_badge_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_padding_top,
            this._vertical_menu_child_items_badge_padding_top
        )
        this._vertical_menu_child_items_badge_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_padding_bottom,
            this._vertical_menu_child_items_badge_padding_bottom
        )
        this._vertical_menu_child_items_badge_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_margin,
            this._vertical_menu_child_items_badge_margin
        )
        this._vertical_menu_child_items_badge_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_margin_left,
            this._vertical_menu_child_items_badge_margin_left
        )
        this._vertical_menu_child_items_badge_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_margin_right,
            this._vertical_menu_child_items_badge_margin_right
        )
        this._vertical_menu_child_items_badge_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_margin_top,
            this._vertical_menu_child_items_badge_margin_top
        )
        this._vertical_menu_child_items_badge_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_margin_bottom,
            this._vertical_menu_child_items_badge_margin_bottom
        )
        this._vertical_menu_child_items_badge_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_background_color,
            this._vertical_menu_child_items_badge_background_color
        )
        this._vertical_menu_child_items_badge_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_border_color,
            this._vertical_menu_child_items_badge_border_color
        )
        this._vertical_menu_child_items_badge_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_border_thickness,
            this._vertical_menu_child_items_badge_border_thickness
        )
        this._vertical_menu_child_items_badge_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_text_color,
            this._vertical_menu_child_items_badge_text_color
        )
        this._vertical_menu_child_items_badge_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_text_size,
            this._vertical_menu_child_items_badge_text_size
        )
        this._vertical_menu_child_items_badge_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_text_is_bold,
            this._vertical_menu_child_items_badge_text_is_bold
        )
        this._vertical_menu_child_items_badge_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_is_visible,
            this._vertical_menu_child_items_badge_is_visible
        )
        this._vertical_menu_child_items_badge_is_cleared_on_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_is_cleared_on_active,
            this._vertical_menu_child_items_badge_is_cleared_on_active
        )
        this._vertical_menu_child_items_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_background_drawable
        )
        this._vertical_menu_child_items_active_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_active_background_drawable
        )
        this._vertical_menu_child_items_badge_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_badge_text_is_visible,
            this._vertical_menu_child_items_badge_text_is_visible
        )
        this._vertical_menu_child_items_when_active_show_parent_active_state = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Vertical_VerticalMenu_vertical_menu_child_items_when_active_show_parent_active_state,
            this._vertical_menu_child_items_when_active_show_parent_active_state
        )
        //endregion get/set vertical_menu_child values

    }
    //endregion setTypeArray
    
    public fun refresh() {
        initializeVerticalMenu()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        post {
        }
    }

    fun setVerticalMenuItemsUISettings() {

        verticalMenuItemsUISettings["vertical_menu_is_responsive_menu"] = currentScreenOrientationIsPortrait

        if(this@VerticalMenu.vertical_menu_width > 0){
            if(this@VerticalMenu.vertical_menu_border_thickness > 0 && this@VerticalMenu.vertical_menu_border_is_visible){
                verticalMenuItemsUISettings["vertical_menu_width"] = this@VerticalMenu.vertical_menu_width + this@VerticalMenu.vertical_menu_border_thickness
            }else{
                verticalMenuItemsUISettings["vertical_menu_width"] = this@VerticalMenu.vertical_menu_width
            }
        }else{

            if(this@VerticalMenu.vertical_menu_border_thickness > 0 && this@VerticalMenu.vertical_menu_border_is_visible){
                if(this@VerticalMenu.vertical_menu_show_icons_only) {
                    verticalMenuItemsUISettings["vertical_menu_width"] =
                        getIntToDp(context, 45) + this@VerticalMenu.vertical_menu_border_thickness
                }else{
                    verticalMenuItemsUISettings["vertical_menu_width"] =
                        getIntToDp(context, 200) + this@VerticalMenu.vertical_menu_border_thickness
                }
            }else{
                if(this@VerticalMenu.vertical_menu_show_icons_only) {
                    verticalMenuItemsUISettings["vertical_menu_width"] =
                        getIntToDp(context, 45)
                }else{
                    verticalMenuItemsUISettings["vertical_menu_width"] =
                        getIntToDp(context, 200) + this@VerticalMenu.vertical_menu_border_thickness
                }
            }
        }

        verticalMenuItemsUISettings["vertical_menu_active_state_on_left"] = this@VerticalMenu.vertical_menu_active_state_on_left
        verticalMenuItemsUISettings["vertical_menu_show_icons"] = this@VerticalMenu.vertical_menu_show_icons
        verticalMenuItemsUISettings["vertical_menu_show_parent_icons"] = this@VerticalMenu.vertical_menu_show_parent_icons
        verticalMenuItemsUISettings["vertical_menu_show_child_icons"] = this@VerticalMenu.vertical_menu_show_child_icons
        verticalMenuItemsUISettings["vertical_menu_show_parent_divider"] = this@VerticalMenu.vertical_menu_show_parent_divider
        verticalMenuItemsUISettings["vertical_menu_show_icons_only"] = this@VerticalMenu.vertical_menu_show_icons_only

        verticalMenuItemsUISettings["vertical_menu_divider_color"] = this@VerticalMenu.vertical_menu_divider_color
        verticalMenuItemsUISettings["vertical_menu_divider_width"] = this@VerticalMenu.vertical_menu_divider_width
        verticalMenuItemsUISettings["vertical_menu_divider_height"] = this@VerticalMenu.vertical_menu_divider_height
        verticalMenuItemsUISettings["vertical_menu_divider_padding"] = this@VerticalMenu.vertical_menu_divider_padding
        verticalMenuItemsUISettings["vertical_menu_divider_padding_left"] = this@VerticalMenu.vertical_menu_divider_padding_left
        verticalMenuItemsUISettings["vertical_menu_divider_padding_right"] = this@VerticalMenu.vertical_menu_divider_padding_right
        verticalMenuItemsUISettings["vertical_menu_divider_padding_top"] = this@VerticalMenu.vertical_menu_divider_padding_top
        verticalMenuItemsUISettings["vertical_menu_divider_padding_bottom"] = this@VerticalMenu.vertical_menu_divider_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_divider_margin"] = this@VerticalMenu.vertical_menu_divider_margin
        verticalMenuItemsUISettings["vertical_menu_divider_margin_left"] = this@VerticalMenu.vertical_menu_divider_margin_left
        verticalMenuItemsUISettings["vertical_menu_divider_margin_right"] = this@VerticalMenu.vertical_menu_divider_margin_right
        verticalMenuItemsUISettings["vertical_menu_divider_margin_top"] = this@VerticalMenu.vertical_menu_divider_margin_top
        verticalMenuItemsUISettings["vertical_menu_divider_margin_bottom"] = this@VerticalMenu.vertical_menu_divider_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_divider_is_visible"] = this@VerticalMenu.vertical_menu_divider_is_visible

        verticalMenuItemsUISettings["vertical_menu_logo_width"] = this@VerticalMenu.vertical_menu_logo_width
        verticalMenuItemsUISettings["vertical_menu_logo_height"] = this@VerticalMenu.vertical_menu_logo_height
        verticalMenuItemsUISettings["vertical_menu_logo_padding"] = this@VerticalMenu.vertical_menu_logo_padding
        verticalMenuItemsUISettings["vertical_menu_logo_padding_left"] = this@VerticalMenu.vertical_menu_logo_padding_left
        verticalMenuItemsUISettings["vertical_menu_logo_padding_right"] = this@VerticalMenu.vertical_menu_logo_padding_right
        verticalMenuItemsUISettings["vertical_menu_logo_padding_top"] = this@VerticalMenu.vertical_menu_logo_padding_top
        verticalMenuItemsUISettings["vertical_menu_logo_padding_bottom"] = this@VerticalMenu.vertical_menu_logo_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_logo_margin"] = this@VerticalMenu.vertical_menu_logo_margin
        verticalMenuItemsUISettings["vertical_menu_logo_margin_left"] = this@VerticalMenu.vertical_menu_logo_margin_left
        verticalMenuItemsUISettings["vertical_menu_logo_margin_right"] = this@VerticalMenu.vertical_menu_logo_margin_right
        verticalMenuItemsUISettings["vertical_menu_logo_margin_top"] = this@VerticalMenu.vertical_menu_logo_margin_top
        verticalMenuItemsUISettings["vertical_menu_logo_margin_bottom"] = this@VerticalMenu.vertical_menu_logo_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_logo_drawable"] = this@VerticalMenu.vertical_menu_logo_drawable
        verticalMenuItemsUISettings["vertical_menu_logo_is_visible"] = this@VerticalMenu.vertical_menu_logo_is_visible
        verticalMenuItemsUISettings["vertical_menu_logo_position"] = this@VerticalMenu.vertical_menu_logo_position
        
        verticalMenuItemsUISettings["vertical_menu_parent_items_width"] = this@VerticalMenu.vertical_menu_parent_items_width
        verticalMenuItemsUISettings["vertical_menu_parent_items_height"] = this@VerticalMenu.vertical_menu_parent_items_height
        verticalMenuItemsUISettings["vertical_menu_parent_items_padding"] = this@VerticalMenu.vertical_menu_parent_items_padding
        verticalMenuItemsUISettings["vertical_menu_parent_items_padding_left"] = this@VerticalMenu.vertical_menu_parent_items_padding_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_padding_right"] = this@VerticalMenu.vertical_menu_parent_items_padding_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_padding_top"] = this@VerticalMenu.vertical_menu_parent_items_padding_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_padding_bottom"] = this@VerticalMenu.vertical_menu_parent_items_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_margin"] = this@VerticalMenu.vertical_menu_parent_items_margin
        verticalMenuItemsUISettings["vertical_menu_parent_items_margin_left"] = this@VerticalMenu.vertical_menu_parent_items_margin_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_margin_right"] = this@VerticalMenu.vertical_menu_parent_items_margin_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_margin_top"] = this@VerticalMenu.vertical_menu_parent_items_margin_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_margin_bottom"] = this@VerticalMenu.vertical_menu_parent_items_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_background_color"] = this@VerticalMenu.vertical_menu_parent_items_background_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_background_color"] = this@VerticalMenu.vertical_menu_parent_items_active_background_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_background_drawable"] = this@VerticalMenu.vertical_menu_parent_items_background_drawable
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_background_drawable"] = this@VerticalMenu.vertical_menu_parent_items_active_background_drawable
        verticalMenuItemsUISettings["vertical_menu_parent_items_text_color"] = this@VerticalMenu.vertical_menu_parent_items_text_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_text_size"] = this@VerticalMenu.vertical_menu_parent_items_text_size
        verticalMenuItemsUISettings["vertical_menu_parent_items_text_is_capitalized"] = this@VerticalMenu.vertical_menu_parent_items_text_is_capitalized
        verticalMenuItemsUISettings["vertical_menu_parent_items_text_is_bold"] = this@VerticalMenu.vertical_menu_parent_items_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_parent_items_text_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_text_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_indicator_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_indicator_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_indicator_thickness"] = this@VerticalMenu.vertical_menu_parent_items_indicator_thickness
        verticalMenuItemsUISettings["vertical_menu_parent_items_indicator_color"] = this@VerticalMenu.vertical_menu_parent_items_indicator_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_indicator_position"] = this@VerticalMenu.vertical_menu_parent_items_indicator_position
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_padding"] = this@VerticalMenu.vertical_menu_parent_items_active_padding
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_padding_left"] = this@VerticalMenu.vertical_menu_parent_items_active_padding_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_padding_right"] = this@VerticalMenu.vertical_menu_parent_items_active_padding_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_padding_top"] = this@VerticalMenu.vertical_menu_parent_items_active_padding_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_padding_bottom"] = this@VerticalMenu.vertical_menu_parent_items_active_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_margin"] = this@VerticalMenu.vertical_menu_parent_items_active_margin
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_margin_left"] = this@VerticalMenu.vertical_menu_parent_items_active_margin_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_margin_right"] = this@VerticalMenu.vertical_menu_parent_items_active_margin_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_margin_top"] = this@VerticalMenu.vertical_menu_parent_items_active_margin_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_margin_bottom"] = this@VerticalMenu.vertical_menu_parent_items_active_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_text_color"] = this@VerticalMenu.vertical_menu_parent_items_active_text_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_text_size"] = this@VerticalMenu.vertical_menu_parent_items_active_text_size
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_text_is_bold"] = this@VerticalMenu.vertical_menu_parent_items_active_text_is_bold

        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_width"] = this@VerticalMenu.vertical_menu_parent_items_icon_width
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_height"] = this@VerticalMenu.vertical_menu_parent_items_icon_height
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_padding"] = this@VerticalMenu.vertical_menu_parent_items_icon_padding
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_padding_left"] = this@VerticalMenu.vertical_menu_parent_items_icon_padding_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_padding_right"] = this@VerticalMenu.vertical_menu_parent_items_icon_padding_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_padding_top"] = this@VerticalMenu.vertical_menu_parent_items_icon_padding_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_padding_bottom"] = this@VerticalMenu.vertical_menu_parent_items_icon_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_margin"] = this@VerticalMenu.vertical_menu_parent_items_icon_margin
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_margin_left"] = this@VerticalMenu.vertical_menu_parent_items_icon_margin_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_margin_right"] = this@VerticalMenu.vertical_menu_parent_items_icon_margin_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_margin_top"] = this@VerticalMenu.vertical_menu_parent_items_icon_margin_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_margin_bottom"] = this@VerticalMenu.vertical_menu_parent_items_icon_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_tint_color"] = this@VerticalMenu.vertical_menu_parent_items_icon_tint_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_icon_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_icon_active_tint_color"] = this@VerticalMenu.vertical_menu_parent_items_icon_active_tint_color
        
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_width"] = this@VerticalMenu.vertical_menu_parent_items_badge_width
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_height"] = this@VerticalMenu.vertical_menu_parent_items_badge_height
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_padding"] = this@VerticalMenu.vertical_menu_parent_items_badge_padding
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_padding_left"] = this@VerticalMenu.vertical_menu_parent_items_badge_padding_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_padding_right"] = this@VerticalMenu.vertical_menu_parent_items_badge_padding_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_padding_top"] = this@VerticalMenu.vertical_menu_parent_items_badge_padding_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_padding_bottom"] = this@VerticalMenu.vertical_menu_parent_items_badge_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_margin"] = this@VerticalMenu.vertical_menu_parent_items_badge_margin
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_margin_left"] = this@VerticalMenu.vertical_menu_parent_items_badge_margin_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_margin_right"] = this@VerticalMenu.vertical_menu_parent_items_badge_margin_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_margin_top"] = this@VerticalMenu.vertical_menu_parent_items_badge_margin_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_margin_bottom"] = this@VerticalMenu.vertical_menu_parent_items_badge_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_background_color"] = this@VerticalMenu.vertical_menu_parent_items_badge_background_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_border_color"] = this@VerticalMenu.vertical_menu_parent_items_badge_border_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_border_thickness"] = this@VerticalMenu.vertical_menu_parent_items_badge_border_thickness
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_text_color"] = this@VerticalMenu.vertical_menu_parent_items_badge_text_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_text_size"] = this@VerticalMenu.vertical_menu_parent_items_badge_text_size
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_text_is_bold"] = this@VerticalMenu.vertical_menu_parent_items_badge_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_text_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_badge_text_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_badge_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_badge_is_cleared_on_active"] = this@VerticalMenu.vertical_menu_parent_items_badge_is_cleared_on_active

        verticalMenuItemsUISettings["vertical_menu_collapse_type"] = this@VerticalMenu.vertical_menu_collapse_type
        verticalMenuItemsUISettings["vertical_menu_collapse_others"] = this@VerticalMenu.vertical_menu_collapse_others
        verticalMenuItemsUISettings["vertical_menu_collapse_expand_delay"] = this@VerticalMenu.vertical_menu_collapse_expand_delay
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_rotate"] = this@VerticalMenu.vertical_menu_collapse_icon_rotate
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_rotate_duration"] = this@VerticalMenu.vertical_menu_collapse_icon_rotate_duration
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_width"] = this@VerticalMenu.vertical_menu_collapse_icon_width
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_height"] = this@VerticalMenu.vertical_menu_collapse_icon_height
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_padding"] = this@VerticalMenu.vertical_menu_collapse_icon_padding
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_padding_left"] = this@VerticalMenu.vertical_menu_collapse_icon_padding_left
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_padding_right"] = this@VerticalMenu.vertical_menu_collapse_icon_padding_right
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_padding_top"] = this@VerticalMenu.vertical_menu_collapse_icon_padding_top
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_padding_bottom"] = this@VerticalMenu.vertical_menu_collapse_icon_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_margin"] = this@VerticalMenu.vertical_menu_collapse_icon_margin
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_margin_left"] = this@VerticalMenu.vertical_menu_collapse_icon_margin_left
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_margin_right"] = this@VerticalMenu.vertical_menu_collapse_icon_margin_right
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_margin_top"] = this@VerticalMenu.vertical_menu_collapse_icon_margin_top
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_margin_bottom"] = this@VerticalMenu.vertical_menu_collapse_icon_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_color"] = this@VerticalMenu.vertical_menu_collapse_icon_color
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_drawable"] = this@VerticalMenu.vertical_menu_collapse_icon_drawable
        verticalMenuItemsUISettings["vertical_menu_collapse_icon_is_visible"] = this@VerticalMenu.vertical_menu_collapse_icon_is_visible

        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_width"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_width
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_height"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_height
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_padding"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_padding
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_padding_left"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_padding_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_padding_right"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_padding_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_padding_top"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_padding_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_padding_bottom"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_margin"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_margin
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_margin_left"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_margin_left
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_margin_right"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_margin_right
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_margin_top"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_margin_top
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_margin_bottom"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_color"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_color
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_drawable"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_drawable
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_is_visible"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_is_visible
        verticalMenuItemsUISettings["vertical_menu_parent_items_navigation_icon_is_hidden_when_active"] = this@VerticalMenu.vertical_menu_parent_items_navigation_icon_is_hidden_when_active
        verticalMenuItemsUISettings["vertical_menu_parent_items_active_navigation_icon_color"] = this@VerticalMenu.vertical_menu_parent_items_active_navigation_icon_color

        verticalMenuItemsUISettings["vertical_menu_title_items_width"] = this@VerticalMenu.vertical_menu_title_items_width
        verticalMenuItemsUISettings["vertical_menu_title_items_height"] = this@VerticalMenu.vertical_menu_title_items_height
        verticalMenuItemsUISettings["vertical_menu_title_items_padding"] = this@VerticalMenu.vertical_menu_title_items_padding
        verticalMenuItemsUISettings["vertical_menu_title_items_padding_left"] = this@VerticalMenu.vertical_menu_title_items_padding_left
        verticalMenuItemsUISettings["vertical_menu_title_items_padding_right"] = this@VerticalMenu.vertical_menu_title_items_padding_right
        verticalMenuItemsUISettings["vertical_menu_title_items_padding_top"] = this@VerticalMenu.vertical_menu_title_items_padding_top
        verticalMenuItemsUISettings["vertical_menu_title_items_padding_bottom"] = this@VerticalMenu.vertical_menu_title_items_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_title_items_margin"] = this@VerticalMenu.vertical_menu_title_items_margin
        verticalMenuItemsUISettings["vertical_menu_title_items_margin_left"] = this@VerticalMenu.vertical_menu_title_items_margin_left
        verticalMenuItemsUISettings["vertical_menu_title_items_margin_right"] = this@VerticalMenu.vertical_menu_title_items_margin_right
        verticalMenuItemsUISettings["vertical_menu_title_items_margin_top"] = this@VerticalMenu.vertical_menu_title_items_margin_top
        verticalMenuItemsUISettings["vertical_menu_title_items_margin_bottom"] = this@VerticalMenu.vertical_menu_title_items_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_title_items_background_color"] = this@VerticalMenu.vertical_menu_title_items_background_color
        verticalMenuItemsUISettings["vertical_menu_title_items_background_drawable"] = this@VerticalMenu.vertical_menu_title_items_background_drawable
        verticalMenuItemsUISettings["vertical_menu_title_items_text_color"] = this@VerticalMenu.vertical_menu_title_items_text_color
        verticalMenuItemsUISettings["vertical_menu_title_items_text_size"] = this@VerticalMenu.vertical_menu_title_items_text_size
        verticalMenuItemsUISettings["vertical_menu_title_items_text_is_capitalized"] = this@VerticalMenu.vertical_menu_title_items_text_is_capitalized
        verticalMenuItemsUISettings["vertical_menu_title_items_text_is_bold"] = this@VerticalMenu.vertical_menu_title_items_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_title_items_divider_visible"] = this@VerticalMenu.vertical_menu_title_items_divider_visible
        verticalMenuItemsUISettings["vertical_menu_title_items_divider_thickness"] = this@VerticalMenu.vertical_menu_title_items_divider_thickness
        verticalMenuItemsUISettings["vertical_menu_title_items_divider_color"] = this@VerticalMenu.vertical_menu_title_items_divider_color

        verticalMenuItemsUISettings["vertical_menu_child_items_width"] = this@VerticalMenu.vertical_menu_child_items_width
        verticalMenuItemsUISettings["vertical_menu_child_items_height"] = this@VerticalMenu.vertical_menu_child_items_height
        verticalMenuItemsUISettings["vertical_menu_child_items_padding"] = this@VerticalMenu.vertical_menu_child_items_padding
        verticalMenuItemsUISettings["vertical_menu_child_items_padding_left"] = this@VerticalMenu.vertical_menu_child_items_padding_left
        verticalMenuItemsUISettings["vertical_menu_child_items_padding_right"] = this@VerticalMenu.vertical_menu_child_items_padding_right
        verticalMenuItemsUISettings["vertical_menu_child_items_padding_top"] = this@VerticalMenu.vertical_menu_child_items_padding_top
        verticalMenuItemsUISettings["vertical_menu_child_items_padding_bottom"] = this@VerticalMenu.vertical_menu_child_items_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_margin"] = this@VerticalMenu.vertical_menu_child_items_margin
        verticalMenuItemsUISettings["vertical_menu_child_items_margin_left"] = this@VerticalMenu.vertical_menu_child_items_margin_left
        verticalMenuItemsUISettings["vertical_menu_child_items_margin_right"] = this@VerticalMenu.vertical_menu_child_items_margin_right
        verticalMenuItemsUISettings["vertical_menu_child_items_margin_top"] = this@VerticalMenu.vertical_menu_child_items_margin_top
        verticalMenuItemsUISettings["vertical_menu_child_items_margin_bottom"] = this@VerticalMenu.vertical_menu_child_items_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_background_color"] = this@VerticalMenu.vertical_menu_child_items_background_color
        verticalMenuItemsUISettings["vertical_menu_child_items_active_background_color"] = this@VerticalMenu.vertical_menu_child_items_active_background_color
        verticalMenuItemsUISettings["vertical_menu_child_items_text_color"] = this@VerticalMenu.vertical_menu_child_items_text_color
        verticalMenuItemsUISettings["vertical_menu_child_items_text_size"] = this@VerticalMenu.vertical_menu_child_items_text_size
        verticalMenuItemsUISettings["vertical_menu_child_items_text_is_capitalized"] = this@VerticalMenu.vertical_menu_child_items_text_is_capitalized
        verticalMenuItemsUISettings["vertical_menu_child_items_text_is_bold"] = this@VerticalMenu.vertical_menu_child_items_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_child_items_text_is_visible"] = this@VerticalMenu.vertical_menu_child_items_text_is_visible
        verticalMenuItemsUISettings["vertical_menu_child_items_indicator_is_visible"] = this@VerticalMenu.vertical_menu_child_items_indicator_is_visible
        verticalMenuItemsUISettings["vertical_menu_child_items_indicator_thickness"] = this@VerticalMenu.vertical_menu_child_items_indicator_thickness
        verticalMenuItemsUISettings["vertical_menu_child_items_indicator_color"] = this@VerticalMenu.vertical_menu_child_items_indicator_color
        verticalMenuItemsUISettings["vertical_menu_child_items_indicator_position"] = this@VerticalMenu.vertical_menu_child_items_indicator_position
        verticalMenuItemsUISettings["vertical_menu_child_items_active_padding"] = this@VerticalMenu.vertical_menu_child_items_active_padding
        verticalMenuItemsUISettings["vertical_menu_child_items_active_padding_left"] = this@VerticalMenu.vertical_menu_child_items_active_padding_left
        verticalMenuItemsUISettings["vertical_menu_child_items_active_padding_right"] = this@VerticalMenu.vertical_menu_child_items_active_padding_right
        verticalMenuItemsUISettings["vertical_menu_child_items_active_padding_top"] = this@VerticalMenu.vertical_menu_child_items_active_padding_top
        verticalMenuItemsUISettings["vertical_menu_child_items_active_padding_bottom"] = this@VerticalMenu.vertical_menu_child_items_active_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_active_margin"] = this@VerticalMenu.vertical_menu_child_items_active_margin
        verticalMenuItemsUISettings["vertical_menu_child_items_active_margin_left"] = this@VerticalMenu.vertical_menu_child_items_active_margin_left
        verticalMenuItemsUISettings["vertical_menu_child_items_active_margin_right"] = this@VerticalMenu.vertical_menu_child_items_active_margin_right
        verticalMenuItemsUISettings["vertical_menu_child_items_active_margin_top"] = this@VerticalMenu.vertical_menu_child_items_active_margin_top
        verticalMenuItemsUISettings["vertical_menu_child_items_active_margin_bottom"] = this@VerticalMenu.vertical_menu_child_items_active_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_active_text_color"] = this@VerticalMenu.vertical_menu_child_items_active_text_color
        verticalMenuItemsUISettings["vertical_menu_child_items_active_text_size"] = this@VerticalMenu.vertical_menu_child_items_active_text_size
        verticalMenuItemsUISettings["vertical_menu_child_items_active_text_is_bold"] = this@VerticalMenu.vertical_menu_child_items_active_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_width"] = this@VerticalMenu.vertical_menu_child_items_icon_width
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_height"] = this@VerticalMenu.vertical_menu_child_items_icon_height
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_padding"] = this@VerticalMenu.vertical_menu_child_items_icon_padding
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_padding_left"] = this@VerticalMenu.vertical_menu_child_items_icon_padding_left
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_padding_right"] = this@VerticalMenu.vertical_menu_child_items_icon_padding_right
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_padding_top"] = this@VerticalMenu.vertical_menu_child_items_icon_padding_top
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_padding_bottom"] = this@VerticalMenu.vertical_menu_child_items_icon_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_margin"] = this@VerticalMenu.vertical_menu_child_items_icon_margin
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_margin_left"] = this@VerticalMenu.vertical_menu_child_items_icon_margin_left
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_margin_right"] = this@VerticalMenu.vertical_menu_child_items_icon_margin_right
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_margin_top"] = this@VerticalMenu.vertical_menu_child_items_icon_margin_top
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_margin_bottom"] = this@VerticalMenu.vertical_menu_child_items_icon_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_tint_color"] = this@VerticalMenu.vertical_menu_child_items_icon_tint_color
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_is_visible"] = this@VerticalMenu.vertical_menu_child_items_icon_is_visible
        verticalMenuItemsUISettings["vertical_menu_child_items_icon_active_tint_color"] = this@VerticalMenu.vertical_menu_child_items_icon_active_tint_color
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_width"] = this@VerticalMenu.vertical_menu_child_items_badge_width
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_height"] = this@VerticalMenu.vertical_menu_child_items_badge_height
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_padding"] = this@VerticalMenu.vertical_menu_child_items_badge_padding
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_padding_left"] = this@VerticalMenu.vertical_menu_child_items_badge_padding_left
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_padding_right"] = this@VerticalMenu.vertical_menu_child_items_badge_padding_right
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_padding_top"] = this@VerticalMenu.vertical_menu_child_items_badge_padding_top
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_padding_bottom"] = this@VerticalMenu.vertical_menu_child_items_badge_padding_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_margin"] = this@VerticalMenu.vertical_menu_child_items_badge_margin
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_margin_left"] = this@VerticalMenu.vertical_menu_child_items_badge_margin_left
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_margin_right"] = this@VerticalMenu.vertical_menu_child_items_badge_margin_right
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_margin_top"] = this@VerticalMenu.vertical_menu_child_items_badge_margin_top
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_margin_bottom"] = this@VerticalMenu.vertical_menu_child_items_badge_margin_bottom
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_background_color"] = this@VerticalMenu.vertical_menu_child_items_badge_background_color
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_border_color"] = this@VerticalMenu.vertical_menu_child_items_badge_border_color
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_border_thickness"] = this@VerticalMenu.vertical_menu_child_items_badge_border_thickness
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_text_color"] = this@VerticalMenu.vertical_menu_child_items_badge_text_color
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_text_size"] = this@VerticalMenu.vertical_menu_child_items_badge_text_size
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_text_is_bold"] = this@VerticalMenu.vertical_menu_child_items_badge_text_is_bold
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_is_visible"] = this@VerticalMenu.vertical_menu_child_items_badge_is_visible
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_is_cleared_on_active"] = this@VerticalMenu.vertical_menu_child_items_badge_is_cleared_on_active
        verticalMenuItemsUISettings["vertical_menu_child_items_background_drawable"] = this@VerticalMenu.vertical_menu_child_items_background_drawable
        verticalMenuItemsUISettings["vertical_menu_child_items_active_background_drawable"] = this@VerticalMenu.vertical_menu_child_items_active_background_drawable
        verticalMenuItemsUISettings["vertical_menu_child_items_badge_text_is_visible"] = this@VerticalMenu.vertical_menu_child_items_badge_text_is_visible
        verticalMenuItemsUISettings["vertical_menu_child_items_when_active_show_parent_active_state"] = this@VerticalMenu.vertical_menu_child_items_when_active_show_parent_active_state

    }

    fun buildVerticalMenu(_verticalMenuAdapter: VerticalMenuAdapter) {
        verticalMenuAdapter = _verticalMenuAdapter
        initializeVerticalMenu()
    }

    private fun initializeVerticalMenu() {

        /*
        this@VerticalMenu.setScreenOrientationListener(object : ScreenOrientationListener {
            override fun onChange() {
                //Log.e("VerticalMenu", "VerticalMenu.ScreenOrientationListener.OnChange: " + currentScreenOrientation)
            }
        })
        */

        if(this@VerticalMenu.vertical_menu_is_responsive_menu) {
            currentScreenOrientation = this@VerticalMenu.resources.configuration.orientation

            if (this@VerticalMenu.resources.configuration.orientation == 1) {
                currentScreenOrientationIsPortrait = true
            } else {
                currentScreenOrientationIsPortrait = false
            }

        }

        var setWidth = 200
        var setResponsiveWidth = 54
        var setBorderThckiness = 0
        if(this@VerticalMenu.vertical_menu_width > 0){

            if(this@VerticalMenu.vertical_menu_border_thickness > 0 && this@VerticalMenu.vertical_menu_border_is_visible){
                setBorderThckiness = this@VerticalMenu.vertical_menu_border_thickness
                bhappsMenusMenuVerticalMenuLayout.layoutParams.width = this@VerticalMenu.vertical_menu_width + this@VerticalMenu.vertical_menu_border_thickness
                setWidth = this@VerticalMenu.vertical_menu_width + this@VerticalMenu.vertical_menu_border_thickness
            }else{
                bhappsMenusMenuVerticalMenuLayout.layoutParams.width = this@VerticalMenu.vertical_menu_width
                setWidth = this@VerticalMenu.vertical_menu_width
            }

            bhappsMenusMenuVerticalMenuWidthHolder.layoutParams.width = this@VerticalMenu.vertical_menu_width
            bhappsMenusMenuVerticalMenuRecycleView.layoutParams.width = this@VerticalMenu.vertical_menu_width
            bhappsMenusMenuVerticalMenuShimmerLayout.layoutParams.width = this@VerticalMenu.vertical_menu_width
        }else{

            var getIntToDp = getIntToDp(context, 200)
            if(this@VerticalMenu.vertical_menu_show_icons_only) {
                getIntToDp = getIntToDp(context, 45)
            }
            if(this@VerticalMenu.vertical_menu_border_thickness > 0 && this@VerticalMenu.vertical_menu_border_is_visible){
                setBorderThckiness = this@VerticalMenu.vertical_menu_border_thickness
                bhappsMenusMenuVerticalMenuLayout.layoutParams.width = getIntToDp + this@VerticalMenu.vertical_menu_border_thickness
                setWidth = getIntToDp + this@VerticalMenu.vertical_menu_border_thickness

            }else{
                bhappsMenusMenuVerticalMenuLayout.layoutParams.width = getIntToDp
                setWidth = getIntToDp
            }

            bhappsMenusMenuVerticalMenuWidthHolder.layoutParams.width = getIntToDp
            bhappsMenusMenuVerticalMenuRecycleView.layoutParams.width = getIntToDp
            bhappsMenusMenuVerticalMenuShimmerLayout.layoutParams.width = getIntToDp

        }

        if(this@VerticalMenu.vertical_menu_padding > 0){
            //setPadding(int left, int top, int right, int bottom)
            bhappsMenusMenuVerticalMenuLayout.setPadding(
                this@VerticalMenu.vertical_menu_padding,
                this@VerticalMenu.vertical_menu_padding,
                this@VerticalMenu.vertical_menu_padding,
                this@VerticalMenu.vertical_menu_padding
            )
        }else{
            //setPadding(int left, int top, int right, int bottom)
            bhappsMenusMenuVerticalMenuLayout.setPadding(
                this@VerticalMenu.vertical_menu_padding_left,
                this@VerticalMenu.vertical_menu_padding_top,
                this@VerticalMenu.vertical_menu_padding_right,
                this@VerticalMenu.vertical_menu_padding_bottom
            )
        }

        if(this@VerticalMenu.vertical_menu_margin > 0){
            //setMargins(int left, int top, int right, int bottom)
            var layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.setMargins(
                this@VerticalMenu.vertical_menu_margin,
                this@VerticalMenu.vertical_menu_margin,
                this@VerticalMenu.vertical_menu_margin,
                this@VerticalMenu.vertical_menu_margin
            )
            bhappsMenusMenuVerticalMenuLayout.layoutParams = layoutParams
        }else{
            //setMargins(int left, int top, int right, int bottom)
            var layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.setMargins(
                this@VerticalMenu.vertical_menu_margin_left,
                this@VerticalMenu.vertical_menu_margin_top,
                this@VerticalMenu.vertical_menu_margin_right,
                this@VerticalMenu.vertical_menu_margin_bottom
            )
            bhappsMenusMenuVerticalMenuLayout.layoutParams = layoutParams
        }

        if(currentScreenOrientationIsPortrait){
            bhappsMenusMenuVerticalMenuLayout.layoutParams.width = setResponsiveWidth + setBorderThckiness
            bhappsMenusMenuVerticalMenuWidthHolder.layoutParams.width = setResponsiveWidth
            bhappsMenusMenuVerticalMenuRecycleView.layoutParams.width = setResponsiveWidth
            bhappsMenusMenuVerticalMenuShimmerLayout.layoutParams.width = setResponsiveWidth
        }

        if(this@VerticalMenu.vertical_menu_alignment_position !=null) {
            var layoutParamsForRecycleView = ConstraintLayout.LayoutParams(
                setWidth,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            if (this@VerticalMenu.vertical_menu_alignment_position == 1) {
                //top align
                layoutParamsForRecycleView.topToBottom = bhappsMenusMenuVerticalMenuLogo.id
                layoutParamsForRecycleView.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                bhappsMenusMenuVerticalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            } else if (this@VerticalMenu.vertical_menu_alignment_position == 2) {
                //center align
                layoutParamsForRecycleView.topToBottom = bhappsMenusMenuVerticalMenuLogo.id
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                bhappsMenusMenuVerticalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            } else if (this@VerticalMenu.vertical_menu_alignment_position == 3) {
                //bottom align
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                bhappsMenusMenuVerticalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            }else{
                var layoutParamsForRecycleView = ConstraintLayout.LayoutParams(
                    setWidth,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParamsForRecycleView.topToBottom = bhappsMenusMenuVerticalMenuLogo.id
                layoutParamsForRecycleView.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                bhappsMenusMenuVerticalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            }
        }

        if(this@VerticalMenu.vertical_menu_show_shimmer_animation){
            bhappsMenusMenuVerticalMenuShimmerLayout.visibility = View.VISIBLE
            bhappsMenusMenuVerticalMenuShimmerLayout.startShimmer()
            if(this@VerticalMenu.vertical_menu_show_shimmer_animation_duration != 0){
                Handler().postDelayed({
                    bhappsMenusMenuVerticalMenuShimmerLayout.visibility = View.GONE
                    bhappsMenusMenuVerticalMenuShimmerLayout.stopShimmer()
                    bhappsMenusMenuVerticalMenuRecycleView.visibility = View.VISIBLE
                }, this@VerticalMenu.vertical_menu_show_shimmer_animation_duration.toLong())
            }else{
                Handler().postDelayed({
                    bhappsMenusMenuVerticalMenuShimmerLayout.visibility = View.GONE
                    bhappsMenusMenuVerticalMenuShimmerLayout.stopShimmer()
                    bhappsMenusMenuVerticalMenuRecycleView.visibility = View.VISIBLE
                }, 1200)
            }
        }else{
            bhappsMenusMenuVerticalMenuShimmerLayout.visibility = View.GONE
            bhappsMenusMenuVerticalMenuShimmerLayout.stopShimmer()
            bhappsMenusMenuVerticalMenuRecycleView.visibility = View.VISIBLE
        }
        
        if(!this@VerticalMenu.vertical_menu_logo_is_visible){
            bhappsMenusMenuVerticalMenuLogo.visibility = View.GONE
        }else{
            if(this@VerticalMenu.vertical_menu_logo_drawable !=null){
                bhappsMenusMenuVerticalMenuLogo.visibility = View.VISIBLE

                if(this@VerticalMenu.vertical_menu_logo_width !=null){
                    bhappsMenusMenuVerticalMenuLogo.layoutParams.width = this@VerticalMenu.vertical_menu_logo_width
                }

                if(this@VerticalMenu.vertical_menu_logo_height !=null) {
                    bhappsMenusMenuVerticalMenuLogo.layoutParams.height = this@VerticalMenu.vertical_menu_logo_height
                }

                bhappsMenusMenuVerticalMenuLogo.setImageDrawable(this@VerticalMenu.vertical_menu_logo_drawable)

                if(this@VerticalMenu.vertical_menu_logo_padding > 0){
                    //setPadding(int left, int top, int right, int bottom)
                    bhappsMenusMenuVerticalMenuLogo.setPadding(
                        this@VerticalMenu.vertical_menu_logo_padding,
                        this@VerticalMenu.vertical_menu_logo_padding,
                        this@VerticalMenu.vertical_menu_logo_padding,
                        this@VerticalMenu.vertical_menu_logo_padding
                    )
                }else{
                    //setPadding(int left, int top, int right, int bottom)
                    bhappsMenusMenuVerticalMenuLogo.setPadding(
                        this@VerticalMenu.vertical_menu_logo_padding_left,
                        this@VerticalMenu.vertical_menu_logo_padding_top,
                        this@VerticalMenu.vertical_menu_logo_padding_right,
                        this@VerticalMenu.vertical_menu_logo_padding_bottom
                    )
                }

                var bhappsMenusMenuVerticalMenuLogoLayoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                if(this@VerticalMenu.vertical_menu_logo_margin > 0){
                    bhappsMenusMenuVerticalMenuLogoLayoutParams.setMargins(
                        this@VerticalMenu.vertical_menu_logo_margin,
                        this@VerticalMenu.vertical_menu_logo_margin,
                        this@VerticalMenu.vertical_menu_logo_margin,
                        this@VerticalMenu.vertical_menu_logo_margin
                    )
                }else{
                    bhappsMenusMenuVerticalMenuLogoLayoutParams.setMargins(
                        this@VerticalMenu.vertical_menu_logo_margin_left,
                        this@VerticalMenu.vertical_menu_logo_margin_top,
                        this@VerticalMenu.vertical_menu_logo_margin_right,
                        this@VerticalMenu.vertical_menu_logo_margin_bottom
                    )
                }

                if(this@VerticalMenu.vertical_menu_logo_position !=null){
                    if(this@VerticalMenu.vertical_menu_logo_position == 1){
                        //left align
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.topToTop = bhappsMenusMenuVerticalMenuLayout.id

                    }else if(this@VerticalMenu.vertical_menu_logo_position == 2){
                        //right align
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.topToTop = bhappsMenusMenuVerticalMenuLayout.id

                    }else if(this@VerticalMenu.vertical_menu_logo_position == 3){
                        //center align
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.endToEnd = bhappsMenusMenuVerticalMenuLayout.id
                        bhappsMenusMenuVerticalMenuLogoLayoutParams.topToTop = bhappsMenusMenuVerticalMenuLayout.id
                    }
                }

                if(this@VerticalMenu.vertical_menu_logo_width !=null){
                    bhappsMenusMenuVerticalMenuLogoLayoutParams.width = this@VerticalMenu.vertical_menu_logo_width
                }

                if(this@VerticalMenu.vertical_menu_logo_height !=null) {
                    bhappsMenusMenuVerticalMenuLogoLayoutParams.height = this@VerticalMenu.vertical_menu_logo_height
                }

                bhappsMenusMenuVerticalMenuLogo.layoutParams = bhappsMenusMenuVerticalMenuLogoLayoutParams
                
            }else{
                bhappsMenusMenuVerticalMenuLogo.visibility = View.GONE
            }
        }

        if(!this@VerticalMenu.vertical_menu_border_is_visible){
            bhappsMenusMenuVerticalMenuBorder.visibility = View.GONE
        }else{
            if(this@VerticalMenu.vertical_menu_border_color != 0){
                bhappsMenusMenuVerticalMenuBorder.setBackgroundColor(this@VerticalMenu.vertical_menu_border_color)
            }

            if(this@VerticalMenu.vertical_menu_border_thickness > 0){
                bhappsMenusMenuVerticalMenuBorder.layoutParams.width = this@VerticalMenu.vertical_menu_border_thickness
                if(this@VerticalMenu.vertical_menu_border_position !=null) {
                    if(this@VerticalMenu.vertical_menu_border_position == 2) {

                        var layoutParamsForBorderPosition = ConstraintLayout.LayoutParams(
                            this@VerticalMenu.vertical_menu_border_thickness,
                            ConstraintLayout.LayoutParams.MATCH_PARENT
                        )

                        layoutParamsForBorderPosition.startToStart = bhappsMenusMenuVerticalMenuLayout.id
                        layoutParamsForBorderPosition.topToTop = bhappsMenusMenuVerticalMenuLayout.id
                        layoutParamsForBorderPosition.bottomToBottom = bhappsMenusMenuVerticalMenuLayout.id

                        bhappsMenusMenuVerticalMenuBorder.layoutParams = layoutParamsForBorderPosition

                    }
                }

            }

        }

        bhappsMenusMenuVerticalMenuRecycleView.adapter = null

        if(this@VerticalMenu.vertical_menu_collapse_type == 1){
            verticalMenuAdapter!!.mode = 1
        }else{
            verticalMenuAdapter!!.mode = 0
        }

        setVerticalMenuItemsUISettings()
        verticalMenuAdapter!!.verticalMenuItemsUISettings = verticalMenuItemsUISettings

        bhappsMenusMenuVerticalMenuRecycleView!!.isVerticalScrollBarEnabled = this@VerticalMenu.vertical_menu_show_scroll_bar
        bhappsMenusMenuVerticalMenuRecycleView!!.isNestedScrollingEnabled = this@VerticalMenu.vertical_menu_enable_nested_scrolling
        bhappsMenusMenuVerticalMenuRecycleView!!.layoutManager = LinearLayoutManager(context)
        bhappsMenusMenuVerticalMenuRecycleView!!.adapter = verticalMenuAdapter

    }

}