package bhapps.menus.horizontal.horizontalmenu

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
import bhapps.menus.databinding.BhappsMenusMenuHorizontalMenuLayoutBinding
import bhapps.menus.horizontal.horizontalmenu.adapters.HorizontalMenuAdapter
import bhapps.menus.horizontal.horizontalmenu.annotations.DpAnnotation
import bhapps.menus.horizontal.horizontalmenu.config.DEFAULT_HEIGHT
import bhapps.menus.horizontal.horizontalmenu.config.INDEX_UNSELECTED
import bhapps.menus.horizontal.horizontalmenu.extensions.getIntToDp
import bhapps.menus.helpers.Helpers.dpToPx
import bhapps.menus.horizontal.horizontalmenu.models.HorizontalMenuItem

class HorizontalMenu
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
       : ConstraintLayout(context, attrs, defStyleAttr)
{

    private val bindingForBhappsMenusMenuHorizontalMenuLayout: BhappsMenusMenuHorizontalMenuLayoutBinding =
        BhappsMenusMenuHorizontalMenuLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var bhappsMenusMenuHorizontalMenuLayout = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuLayout
    var bhappsMenusMenuHorizontalMenuLogo = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuLogo
    var bhappsMenusMenuHorizontalMenuWidthHolder = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuWidthHolder
    var bhappsMenusMenuHorizontalMenuRecycleView = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuRecycleView
    var bhappsMenusMenuHorizontalMenuShimmerLayout = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuShimmerLayout
    var bhappsMenusMenuHorizontalMenuBorder = bindingForBhappsMenusMenuHorizontalMenuLayout.bhappsMenusMenuHorizontalMenuBorder

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

    var horizontalMenuAdapter: HorizontalMenuAdapter? = null
    private var horizontalMenuItems: List<HorizontalMenuItem> = mutableListOf()
    fun setHorizontalMenuItems(_horizontalMenuItems: List<HorizontalMenuItem>) {
        horizontalMenuItems = _horizontalMenuItems
    }

    var selectedIndex: Int = INDEX_UNSELECTED
    var horizontalMenuItemsUISettings = HashMap<String, Any?>()

    //region get/set horizontal_menu values
    var _horizontal_menu_is_responsive_menu: Boolean = false
    var _horizontal_menu_height: Int = 0
    var horizontal_menu_max_height: Int = 0
    var _horizontal_menu_padding: Int = 0
    var _horizontal_menu_padding_left: Int = 0
    var _horizontal_menu_padding_right: Int = 0
    var _horizontal_menu_padding_top: Int = 0
    var _horizontal_menu_padding_bottom: Int = 0
    var _horizontal_menu_margin: Int = 0
    var _horizontal_menu_margin_left: Int = 0
    var _horizontal_menu_margin_right: Int = 0
    var _horizontal_menu_margin_top: Int = 0
    var _horizontal_menu_margin_bottom: Int = 0
    var _horizontal_menu_alignment_position: Int = 0
    var _horizontal_menu_enable_nested_scrolling: Boolean = false
    var _horizontal_menu_active_state_on_left: Boolean = false

    var _horizontal_menu_show_scroll_bar: Boolean =  false
    var _horizontal_menu_show_icons: Boolean = true
    var _horizontal_menu_show_parent_icons: Boolean = true
    var _horizontal_menu_show_child_icons: Boolean = true
    var _horizontal_menu_show_parent_divider: Boolean = true
    var _horizontal_menu_show_icons_only: Boolean = false

    var _horizontal_menu_border_is_visible: Boolean = false
    var _horizontal_menu_border_thickness: Int = 0
    var _horizontal_menu_border_color: Int = 0
    var _horizontal_menu_border_position: Int = 0
    var _horizontal_menu_show_shimmer_animation: Boolean = false
    var _horizontal_menu_show_shimmer_animation_duration: Int = 0
    var _horizontal_menu_show_animation_in: Boolean = false
    var _horizontal_menu_show_animation_in_type: Int = 0
    var _horizontal_menu_show_animation_in_duration: Int = 0

    var _horizontal_menu_divider_color: Int = 0
    var _horizontal_menu_divider_width: Int = 0
    var _horizontal_menu_divider_height: Int = 1
    var _horizontal_menu_divider_padding: Int = 0
    var _horizontal_menu_divider_padding_left: Int = 0
    var _horizontal_menu_divider_padding_right: Int = 0
    var _horizontal_menu_divider_padding_top: Int = 0
    var _horizontal_menu_divider_padding_bottom: Int = 0
    var _horizontal_menu_divider_margin: Int = 0
    var _horizontal_menu_divider_margin_left: Int = 0
    var _horizontal_menu_divider_margin_right: Int = 0
    var _horizontal_menu_divider_margin_top: Int = 0
    var _horizontal_menu_divider_margin_bottom: Int = 0
    var _horizontal_menu_divider_is_visible: Boolean = true

    var _horizontal_menu_logo_width: Int = 0
    var _horizontal_menu_logo_height: Int = 0
    var _horizontal_menu_logo_padding: Int = 0
    var _horizontal_menu_logo_padding_left: Int = 0
    var _horizontal_menu_logo_padding_right: Int = 0
    var _horizontal_menu_logo_padding_top: Int = 0
    var _horizontal_menu_logo_padding_bottom: Int = 0
    var _horizontal_menu_logo_margin: Int = 0
    var _horizontal_menu_logo_margin_left: Int = 0
    var _horizontal_menu_logo_margin_right: Int = 0
    var _horizontal_menu_logo_margin_top: Int = 0
    var _horizontal_menu_logo_margin_bottom: Int = 0
    var _horizontal_menu_logo_drawable: Drawable? = null
    var _horizontal_menu_logo_is_visible: Boolean = false
    var _horizontal_menu_logo_position: Int = 1

    //endregion get/set horizontal_menu values

    //region get/set horizontal_menu_parent values
    var _horizontal_menu_parent_items_width: Int = 0
    var _horizontal_menu_parent_items_height: Int = 0
    var _horizontal_menu_parent_items_padding: Int = 0
    var _horizontal_menu_parent_items_padding_left: Int = 12
    var _horizontal_menu_parent_items_padding_right: Int = 12
    var _horizontal_menu_parent_items_padding_top: Int = 12
    var _horizontal_menu_parent_items_padding_bottom: Int = 12
    var _horizontal_menu_parent_items_margin: Int = 0
    var _horizontal_menu_parent_items_margin_left: Int = 0
    var _horizontal_menu_parent_items_margin_right: Int = 0
    var _horizontal_menu_parent_items_margin_top: Int = 0
    var _horizontal_menu_parent_items_margin_bottom: Int = 0
    var _horizontal_menu_parent_items_background_color: Int = 0
    var _horizontal_menu_parent_items_active_background_color: Int = 0
    var _horizontal_menu_parent_items_background_drawable: Drawable? = null
    var _horizontal_menu_parent_items_active_background_drawable: Drawable? = null
    var _horizontal_menu_parent_items_text_color: Int = 0
    var _horizontal_menu_parent_items_text_size: Int = 0
    var _horizontal_menu_parent_items_text_is_capitalized: Boolean = false
    var _horizontal_menu_parent_items_text_is_bold: Boolean = false
    var _horizontal_menu_parent_items_text_is_visible: Boolean = true
    var _horizontal_menu_parent_items_indicator_is_visible: Boolean = true
    var _horizontal_menu_parent_items_indicator_thickness: Int = 0
    var _horizontal_menu_parent_items_indicator_color: Int = 0
    var _horizontal_menu_parent_items_indicator_position: Int = 0
    var _horizontal_menu_parent_items_active_padding: Int = 0
    var _horizontal_menu_parent_items_active_padding_left: Int = 0
    var _horizontal_menu_parent_items_active_padding_right: Int = 0
    var _horizontal_menu_parent_items_active_padding_top: Int = 0
    var _horizontal_menu_parent_items_active_padding_bottom: Int = 0
    var _horizontal_menu_parent_items_active_margin: Int = 0
    var _horizontal_menu_parent_items_active_margin_left: Int = 0
    var _horizontal_menu_parent_items_active_margin_right: Int = 0
    var _horizontal_menu_parent_items_active_margin_top: Int = 0
    var _horizontal_menu_parent_items_active_margin_bottom: Int = 0
    var _horizontal_menu_parent_items_active_text_color: Int = 0
    var _horizontal_menu_parent_items_active_text_size: Int = 0
    var _horizontal_menu_parent_items_active_text_is_bold: Boolean = false

    var _horizontal_menu_parent_items_icon_width: Int = 0
    var _horizontal_menu_parent_items_icon_height: Int = 0
    var _horizontal_menu_parent_items_icon_padding: Int = 0
    var _horizontal_menu_parent_items_icon_padding_left: Int = 0
    var _horizontal_menu_parent_items_icon_padding_right: Int = 0
    var _horizontal_menu_parent_items_icon_padding_top: Int = 0
    var _horizontal_menu_parent_items_icon_padding_bottom: Int = 0
    var _horizontal_menu_parent_items_icon_margin: Int = 0
    var _horizontal_menu_parent_items_icon_margin_left: Int = 0
    var _horizontal_menu_parent_items_icon_margin_right: Int = 0
    var _horizontal_menu_parent_items_icon_margin_top: Int = 0
    var _horizontal_menu_parent_items_icon_margin_bottom: Int = 0
    var _horizontal_menu_parent_items_icon_tint_color: Int = 0
    var _horizontal_menu_parent_items_icon_is_visible: Boolean = true
    var _horizontal_menu_parent_items_icon_active_tint_color: Int = 0

    var _horizontal_menu_parent_items_badge_width: Int = 0
    var _horizontal_menu_parent_items_badge_height: Int = 0
    var _horizontal_menu_parent_items_badge_padding: Int = 0
    var _horizontal_menu_parent_items_badge_padding_left: Int = 0
    var _horizontal_menu_parent_items_badge_padding_right: Int = 0
    var _horizontal_menu_parent_items_badge_padding_top: Int = 0
    var _horizontal_menu_parent_items_badge_padding_bottom: Int = 0
    var _horizontal_menu_parent_items_badge_margin: Int = 0
    var _horizontal_menu_parent_items_badge_margin_left: Int = 0
    var _horizontal_menu_parent_items_badge_margin_right: Int = 0
    var _horizontal_menu_parent_items_badge_margin_top: Int = 0
    var _horizontal_menu_parent_items_badge_margin_bottom: Int = 0
    var _horizontal_menu_parent_items_badge_background_color: Int = 0
    var _horizontal_menu_parent_items_badge_border_color: Int = 0
    var _horizontal_menu_parent_items_badge_border_thickness: Int = 0
    var _horizontal_menu_parent_items_badge_text_color: Int = 0
    var _horizontal_menu_parent_items_badge_text_size: Int = 0
    var _horizontal_menu_parent_items_badge_text_is_bold: Boolean = false
    var _horizontal_menu_parent_items_badge_text_is_visible: Boolean =  true
    var _horizontal_menu_parent_items_badge_is_visible: Boolean = true
    var _horizontal_menu_parent_items_badge_is_cleared_on_active: Boolean =  false

    var _horizontal_menu_collapse_type: Int = 0
    var _horizontal_menu_collapse_others: Boolean =  true
    var _horizontal_menu_collapse_expand_delay: Int = 0
    var _horizontal_menu_collapse_icon_rotate: Int = 0
    var _horizontal_menu_collapse_icon_rotate_duration: Int = 0
    var _horizontal_menu_collapse_icon_width: Int = 0
    var _horizontal_menu_collapse_icon_height: Int = 0
    var _horizontal_menu_collapse_icon_padding: Int = 0
    var _horizontal_menu_collapse_icon_padding_left: Int = 0
    var _horizontal_menu_collapse_icon_padding_right: Int = 0
    var _horizontal_menu_collapse_icon_padding_top: Int = 0
    var _horizontal_menu_collapse_icon_padding_bottom: Int = 0
    var _horizontal_menu_collapse_icon_margin: Int = 0
    var _horizontal_menu_collapse_icon_margin_left: Int = 0
    var _horizontal_menu_collapse_icon_margin_right: Int = 0
    var _horizontal_menu_collapse_icon_margin_top: Int = 0
    var _horizontal_menu_collapse_icon_margin_bottom: Int = 0
    var _horizontal_menu_collapse_icon_color: Int = 0
    var _horizontal_menu_collapse_icon_drawable: Drawable? = null
    var _horizontal_menu_collapse_icon_is_visible: Boolean =  true

    var _horizontal_menu_parent_items_navigation_icon_width: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_height: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_padding: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_padding_left: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_padding_right: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_padding_top: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_padding_bottom: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_margin: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_margin_left: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_margin_right: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_margin_top: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_margin_bottom: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_color: Int = 0
    var _horizontal_menu_parent_items_navigation_icon_drawable: Drawable? = null
    var _horizontal_menu_parent_items_navigation_icon_is_visible: Boolean =  true
    var _horizontal_menu_parent_items_navigation_icon_is_hidden_when_active: Boolean =  true
    var _horizontal_menu_parent_items_active_navigation_icon_color: Int = 0

    //endregion get/set horizontal_menu_parent values
    
    //region get/set horizontal_menu_title values
    var _horizontal_menu_title_items_width: Int =  0
    var _horizontal_menu_title_items_height: Int =  0
    var _horizontal_menu_title_items_padding: Int =  0
    var _horizontal_menu_title_items_padding_left: Int =  0
    var _horizontal_menu_title_items_padding_right: Int =  0
    var _horizontal_menu_title_items_padding_top: Int =  0
    var _horizontal_menu_title_items_padding_bottom: Int =  0
    var _horizontal_menu_title_items_margin: Int =  0
    var _horizontal_menu_title_items_margin_left: Int =  0
    var _horizontal_menu_title_items_margin_right: Int =  0
    var _horizontal_menu_title_items_margin_top: Int =  0
    var _horizontal_menu_title_items_margin_bottom: Int =  0
    var _horizontal_menu_title_items_background_color: Int =  0
    var _horizontal_menu_title_items_background_drawable: Int =  0
    var _horizontal_menu_title_items_text_color: Int =  0
    var _horizontal_menu_title_items_text_size: Int =  0
    var _horizontal_menu_title_items_text_is_capitalized: Boolean =  false
    var _horizontal_menu_title_items_text_is_bold: Boolean =  false
    var _horizontal_menu_title_items_divider_visible: Boolean =  true
    var _horizontal_menu_title_items_divider_thickness: Int =  0
    var _horizontal_menu_title_items_divider_color: Int =  0
    //endregion get/set horizontal_menu_title values

    //region get/set horizontal_menu_child values
    var _horizontal_menu_child_items_width: Int = 0
    var _horizontal_menu_child_items_height: Int = 0
    var _horizontal_menu_child_items_padding: Int = 0
    var _horizontal_menu_child_items_padding_left: Int = 0
    var _horizontal_menu_child_items_padding_right: Int = 0
    var _horizontal_menu_child_items_padding_top: Int = 0
    var _horizontal_menu_child_items_padding_bottom: Int = 0
    var _horizontal_menu_child_items_margin: Int = 0
    var _horizontal_menu_child_items_margin_left: Int = 0
    var _horizontal_menu_child_items_margin_right: Int = 0
    var _horizontal_menu_child_items_margin_top: Int = 0
    var _horizontal_menu_child_items_margin_bottom: Int = 0
    var _horizontal_menu_child_items_background_color: Int = 0
    var _horizontal_menu_child_items_active_background_color: Int = 0
    var _horizontal_menu_child_items_text_color: Int = 0
    var _horizontal_menu_child_items_text_size: Int = 0
    var _horizontal_menu_child_items_text_is_capitalized: Boolean = true
    var _horizontal_menu_child_items_text_is_bold: Boolean = true
    var _horizontal_menu_child_items_text_is_visible: Boolean = true
    var _horizontal_menu_child_items_indicator_is_visible: Boolean = true
    var _horizontal_menu_child_items_indicator_thickness: Int = 0
    var _horizontal_menu_child_items_indicator_color: Int = 0
    var _horizontal_menu_child_items_indicator_position: Int = 0
    var _horizontal_menu_child_items_active_padding: Int = 0
    var _horizontal_menu_child_items_active_padding_left: Int = 0
    var _horizontal_menu_child_items_active_padding_right: Int = 0
    var _horizontal_menu_child_items_active_padding_top: Int = 0
    var _horizontal_menu_child_items_active_padding_bottom: Int = 0
    var _horizontal_menu_child_items_active_margin: Int = 0
    var _horizontal_menu_child_items_active_margin_left: Int = 0
    var _horizontal_menu_child_items_active_margin_right: Int = 0
    var _horizontal_menu_child_items_active_margin_top: Int = 0
    var _horizontal_menu_child_items_active_margin_bottom: Int = 0
    var _horizontal_menu_child_items_active_text_color: Int = 0
    var _horizontal_menu_child_items_active_text_size: Int = 0
    var _horizontal_menu_child_items_active_text_is_bold: Boolean = true
    var _horizontal_menu_child_items_icon_width: Int = 0
    var _horizontal_menu_child_items_icon_height: Int = 0
    var _horizontal_menu_child_items_icon_padding: Int = 0
    var _horizontal_menu_child_items_icon_padding_left: Int = 0
    var _horizontal_menu_child_items_icon_padding_right: Int = 0
    var _horizontal_menu_child_items_icon_padding_top: Int = 0
    var _horizontal_menu_child_items_icon_padding_bottom: Int = 0
    var _horizontal_menu_child_items_icon_margin: Int = 0
    var _horizontal_menu_child_items_icon_margin_left: Int = 0
    var _horizontal_menu_child_items_icon_margin_right: Int = 0
    var _horizontal_menu_child_items_icon_margin_top: Int = 0
    var _horizontal_menu_child_items_icon_margin_bottom: Int = 0
    var _horizontal_menu_child_items_icon_tint_color: Int = 0
    var _horizontal_menu_child_items_icon_is_visible: Boolean = true
    var _horizontal_menu_child_items_icon_active_tint_color: Int = 0
    var _horizontal_menu_child_items_badge_width: Int = 0
    var _horizontal_menu_child_items_badge_height: Int = 0
    var _horizontal_menu_child_items_badge_padding: Int = 0
    var _horizontal_menu_child_items_badge_padding_left: Int = 0
    var _horizontal_menu_child_items_badge_padding_right: Int = 0
    var _horizontal_menu_child_items_badge_padding_top: Int = 0
    var _horizontal_menu_child_items_badge_padding_bottom: Int = 0
    var _horizontal_menu_child_items_badge_margin: Int = 0
    var _horizontal_menu_child_items_badge_margin_left: Int = 0
    var _horizontal_menu_child_items_badge_margin_right: Int = 0
    var _horizontal_menu_child_items_badge_margin_top: Int = 0
    var _horizontal_menu_child_items_badge_margin_bottom: Int = 0
    var _horizontal_menu_child_items_badge_background_color: Int = 0
    var _horizontal_menu_child_items_badge_border_color: Int = 0
    var _horizontal_menu_child_items_badge_border_thickness: Int = 0
    var _horizontal_menu_child_items_badge_text_color: Int = 0
    var _horizontal_menu_child_items_badge_text_size: Int = 0
    var _horizontal_menu_child_items_badge_text_is_bold: Boolean = true
    var _horizontal_menu_child_items_badge_is_visible: Boolean = true
    var _horizontal_menu_child_items_badge_is_cleared_on_active: Boolean = true
    var _horizontal_menu_child_items_background_drawable: Drawable? = null
    var _horizontal_menu_child_items_active_background_drawable: Drawable? = null
    var _horizontal_menu_child_items_badge_text_is_visible: Boolean = true
    var _horizontal_menu_child_items_when_active_show_parent_active_state: Boolean = true
    //endregion get/set horizontal_menu_child values
    
    //region get/set horizontal_menu values
    var horizontal_menu_is_responsive_menu: Boolean
        get() = _horizontal_menu_is_responsive_menu
        set(value) { _horizontal_menu_is_responsive_menu = value
    }

    var horizontal_menu_height: Int @Px
        get() = _horizontal_menu_height
        set(@DpAnnotation value) { _horizontal_menu_height = dpToPx(value)
    }
    var horizontal_menu_max_width: Int @Px 
        get() = horizontal_menu_max_height
        set(@DpAnnotation value) { horizontal_menu_max_height = dpToPx(value)
    }
    var horizontal_menu_padding: Int @Px 
        get() = _horizontal_menu_padding
        set(@DpAnnotation value) { _horizontal_menu_padding = dpToPx(value)
    }
    var horizontal_menu_padding_left: Int @Px 
        get() = _horizontal_menu_padding_left
        set(@DpAnnotation value) { _horizontal_menu_padding_left = dpToPx(value)
    }
    var horizontal_menu_padding_right: Int @Px 
        get() = _horizontal_menu_padding_right
        set(@DpAnnotation value) { _horizontal_menu_padding_right = dpToPx(value)
    }
    var horizontal_menu_padding_top: Int @Px 
        get() = _horizontal_menu_padding_top
        set(@DpAnnotation value) { _horizontal_menu_padding_top = dpToPx(value)
    }
    var horizontal_menu_padding_bottom: Int @Px 
        get() = _horizontal_menu_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_margin: Int @Px 
        get() = _horizontal_menu_margin
        set(@DpAnnotation value) { _horizontal_menu_margin = dpToPx(value)
    }
    var horizontal_menu_margin_left: Int @Px 
        get() = _horizontal_menu_margin_left
        set(@DpAnnotation value) { _horizontal_menu_margin_left = dpToPx(value)
    }
    var horizontal_menu_margin_right: Int @Px 
        get() = _horizontal_menu_margin_right
        set(@DpAnnotation value) { _horizontal_menu_margin_right = dpToPx(value)
    }
    var horizontal_menu_margin_top: Int @Px 
        get() = _horizontal_menu_margin_top
        set(@DpAnnotation value) { _horizontal_menu_margin_top = dpToPx(value)
    }
    var horizontal_menu_margin_bottom: Int @Px 
        get() = _horizontal_menu_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_alignment_position: Int
        get() = _horizontal_menu_alignment_position
        set(value) { _horizontal_menu_alignment_position = value
    }
    var horizontal_menu_enable_nested_scrolling: Boolean 
        get() = _horizontal_menu_enable_nested_scrolling
        set(value) { _horizontal_menu_enable_nested_scrolling = value
    }
    var horizontal_menu_show_scroll_bar: Boolean
        get() = _horizontal_menu_show_scroll_bar
        set(value) { _horizontal_menu_show_scroll_bar = value
    }
    var horizontal_menu_active_state_on_left: Boolean 
        get() = _horizontal_menu_active_state_on_left
        set(value) { _horizontal_menu_active_state_on_left = value
    }
    var horizontal_menu_show_icons: Boolean 
        get() = _horizontal_menu_show_icons
        set(value) { _horizontal_menu_show_icons = value
    }
    var horizontal_menu_show_parent_icons: Boolean 
        get() = _horizontal_menu_show_parent_icons
        set(value) { _horizontal_menu_show_parent_icons = value
    }
    var horizontal_menu_show_child_icons: Boolean 
        get() = _horizontal_menu_show_child_icons
        set(value) { _horizontal_menu_show_child_icons = value
    }
    var horizontal_menu_show_parent_divider: Boolean 
        get() = _horizontal_menu_show_parent_divider
        set(value) { _horizontal_menu_show_parent_divider = value
    }
    var horizontal_menu_show_icons_only: Boolean 
        get() = _horizontal_menu_show_icons_only
        set(value) { _horizontal_menu_show_icons_only = value
    }
    var horizontal_menu_collapse_type: Int 
        get() = _horizontal_menu_collapse_type
        set(value) { _horizontal_menu_collapse_type = value
     }
    var horizontal_menu_collapse_others: Boolean 
        get() = _horizontal_menu_collapse_others
        set(value) { _horizontal_menu_collapse_others = value
     }
    var horizontal_menu_collapse_expand_delay: Int 
        get() = _horizontal_menu_collapse_expand_delay
        set(value) { _horizontal_menu_collapse_expand_delay = value
     }
    var horizontal_menu_collapse_icon_rotate: Int 
        get() = _horizontal_menu_collapse_icon_rotate
        set(value) { _horizontal_menu_collapse_icon_rotate = value
     }
    var horizontal_menu_collapse_icon_rotate_duration: Int 
        get() = _horizontal_menu_collapse_icon_rotate_duration
        set(value) { _horizontal_menu_collapse_icon_rotate_duration = value
     }
    var horizontal_menu_collapse_icon_width: Int @Px 
        get() = _horizontal_menu_collapse_icon_width
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_width = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_height: Int @Px 
        get() = _horizontal_menu_collapse_icon_height
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_height = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_padding: Int @Px 
        get() = _horizontal_menu_collapse_icon_padding
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_padding = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_padding_left: Int @Px 
        get() = _horizontal_menu_collapse_icon_padding_left
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_padding_left = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_padding_right: Int @Px 
        get() = _horizontal_menu_collapse_icon_padding_right
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_padding_right = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_padding_top: Int @Px 
        get() = _horizontal_menu_collapse_icon_padding_top
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_padding_top = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_padding_bottom: Int @Px 
        get() = _horizontal_menu_collapse_icon_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_padding_bottom = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_margin: Int @Px 
        get() = _horizontal_menu_collapse_icon_margin
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_margin = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_margin_left: Int @Px 
        get() = _horizontal_menu_collapse_icon_margin_left
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_margin_left = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_margin_right: Int @Px 
        get() = _horizontal_menu_collapse_icon_margin_right
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_margin_right = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_margin_top: Int @Px 
        get() = _horizontal_menu_collapse_icon_margin_top
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_margin_top = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_margin_bottom: Int @Px 
        get() = _horizontal_menu_collapse_icon_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_collapse_icon_margin_bottom = dpToPx(value)
     }
    var horizontal_menu_collapse_icon_color: Int @ColorInt 
        get() = _horizontal_menu_collapse_icon_color
        set(@ColorInt value) { _horizontal_menu_collapse_icon_color = value
     }
    var horizontal_menu_collapse_icon_drawable: Drawable?
        get() = _horizontal_menu_collapse_icon_drawable
        set(value) { _horizontal_menu_collapse_icon_drawable = value
     }
    var horizontal_menu_collapse_icon_is_visible: Boolean 
        get() = _horizontal_menu_collapse_icon_is_visible
        set(value) { _horizontal_menu_collapse_icon_is_visible = value
     }
    var horizontal_menu_border_is_visible: Boolean 
        get() = _horizontal_menu_border_is_visible
        set(value) { _horizontal_menu_border_is_visible = value
    }
    var horizontal_menu_border_thickness: Int @Px 
        get() = _horizontal_menu_border_thickness
        set(@DpAnnotation value) { _horizontal_menu_border_thickness = dpToPx(value)
    }
    var horizontal_menu_border_color: Int @ColorInt 
        get() = _horizontal_menu_border_color
        set(@ColorInt value) { _horizontal_menu_border_color = value
    }
    var horizontal_menu_border_position: Int 
        get() = _horizontal_menu_border_position
        set(value) { _horizontal_menu_border_position = value
    }
    var horizontal_menu_show_shimmer_animation: Boolean
        get() = _horizontal_menu_show_shimmer_animation
        set(value) { _horizontal_menu_show_shimmer_animation = value
    }
    var horizontal_menu_show_shimmer_animation_duration: Int
        get() = _horizontal_menu_show_shimmer_animation_duration
        set(value) { _horizontal_menu_show_shimmer_animation_duration = value
    }
    var horizontal_menu_show_animation_in: Boolean
        get() = _horizontal_menu_show_animation_in
        set(value) { _horizontal_menu_show_animation_in = value
    }
    var horizontal_menu_show_animation_in_type: Int
        get() = _horizontal_menu_show_animation_in_type
        set(value) { _horizontal_menu_show_animation_in_type = value
    }
    var horizontal_menu_show_animation_in_duration: Int
        get() = _horizontal_menu_show_animation_in_duration
        set(value) { _horizontal_menu_show_animation_in_duration = value
    }
    var horizontal_menu_divider_color: Int @ColorInt
        get() = _horizontal_menu_divider_color
        set(@ColorInt value) { _horizontal_menu_divider_color = value
    }
    var horizontal_menu_divider_width: Int @Px
        get() = _horizontal_menu_divider_width
        set(@DpAnnotation value) { _horizontal_menu_divider_width = dpToPx(value)
    }
    var horizontal_menu_divider_height: Int @Px
        get() = _horizontal_menu_divider_height
        set(@DpAnnotation value) { _horizontal_menu_divider_height = dpToPx(value)
    }
    var horizontal_menu_divider_padding: Int @Px
        get() = _horizontal_menu_divider_padding
        set(@DpAnnotation value) { _horizontal_menu_divider_padding = dpToPx(value)
    }
    var horizontal_menu_divider_padding_left: Int @Px
        get() = _horizontal_menu_divider_padding_left
        set(@DpAnnotation value) { _horizontal_menu_divider_padding_left = dpToPx(value)
    }
    var horizontal_menu_divider_padding_right: Int @Px
        get() = _horizontal_menu_divider_padding_right
        set(@DpAnnotation value) { _horizontal_menu_divider_padding_right = dpToPx(value)
    }
    var horizontal_menu_divider_padding_top: Int @Px
        get() = _horizontal_menu_divider_padding_top
        set(@DpAnnotation value) { _horizontal_menu_divider_padding_top = dpToPx(value)
    }
    var horizontal_menu_divider_padding_bottom: Int @Px
        get() = _horizontal_menu_divider_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_divider_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_divider_margin: Int @Px
        get() = _horizontal_menu_divider_margin
        set(@DpAnnotation value) { _horizontal_menu_divider_margin = dpToPx(value)
    }
    var horizontal_menu_divider_margin_left: Int @Px
        get() = _horizontal_menu_divider_margin_left
        set(@DpAnnotation value) { _horizontal_menu_divider_margin_left = dpToPx(value)
    }
    var horizontal_menu_divider_margin_right: Int @Px
        get() = _horizontal_menu_divider_margin_right
        set(@DpAnnotation value) { _horizontal_menu_divider_margin_right = dpToPx(value)
    }
    var horizontal_menu_divider_margin_top: Int @Px
        get() = _horizontal_menu_divider_margin_top
        set(@DpAnnotation value) { _horizontal_menu_divider_margin_top = dpToPx(value)
    }
    var horizontal_menu_divider_margin_bottom: Int @Px
        get() = _horizontal_menu_divider_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_divider_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_divider_is_visible: Boolean
        get() = _horizontal_menu_divider_is_visible
        set(value) { _horizontal_menu_divider_is_visible =  value
    }
    var horizontal_menu_logo_width: Int 
        get() = _horizontal_menu_logo_width
        set(@DpAnnotation value) { 	_horizontal_menu_logo_width = dpToPx(value)
    }
    var horizontal_menu_logo_height: Int 
        get() = _horizontal_menu_logo_height
        set(@DpAnnotation value) { 	_horizontal_menu_logo_height = dpToPx(value)
    }
    var horizontal_menu_logo_padding: Int 
        get() = _horizontal_menu_logo_padding
        set(@DpAnnotation value) { 	_horizontal_menu_logo_padding = dpToPx(value)
    }
    var horizontal_menu_logo_padding_left: Int 
        get() = _horizontal_menu_logo_padding_left
        set(@DpAnnotation value) { 	_horizontal_menu_logo_padding_left = dpToPx(value)
    }
    var horizontal_menu_logo_padding_right: Int 
        get() = _horizontal_menu_logo_padding_right
        set(@DpAnnotation value) { 	_horizontal_menu_logo_padding_right = dpToPx(value)
    }
    var horizontal_menu_logo_padding_top: Int 
        get() = _horizontal_menu_logo_padding_top
        set(@DpAnnotation value) { 	_horizontal_menu_logo_padding_top = dpToPx(value)
    }
    var horizontal_menu_logo_padding_bottom: Int 
        get() = _horizontal_menu_logo_padding_bottom
        set(@DpAnnotation value) { 	_horizontal_menu_logo_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_logo_margin: Int 
        get() = _horizontal_menu_logo_margin
        set(@DpAnnotation value) { 	_horizontal_menu_logo_margin = dpToPx(value)
    }
    var horizontal_menu_logo_margin_left: Int 
        get() = _horizontal_menu_logo_margin_left
        set(@DpAnnotation value) { 	_horizontal_menu_logo_margin_left = dpToPx(value)
    }
    var horizontal_menu_logo_margin_right: Int 
        get() = _horizontal_menu_logo_margin_right
        set(@DpAnnotation value) { 	_horizontal_menu_logo_margin_right = dpToPx(value)
    }
    var horizontal_menu_logo_margin_top: Int 
        get() = _horizontal_menu_logo_margin_top
        set(@DpAnnotation value) { 	_horizontal_menu_logo_margin_top = dpToPx(value)
    }
    var horizontal_menu_logo_margin_bottom: Int 
        get() = _horizontal_menu_logo_margin_bottom
        set(@DpAnnotation value) { 	_horizontal_menu_logo_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_logo_drawable: Drawable?
        get() = _horizontal_menu_logo_drawable
        set(value) { _horizontal_menu_logo_drawable = value	
    }
    var horizontal_menu_logo_is_visible: Boolean 
        get() = _horizontal_menu_logo_is_visible
        set(value) { _horizontal_menu_logo_is_visible =  value
    }
    var horizontal_menu_logo_position: Int 
        get() = _horizontal_menu_logo_position
        set(value) { _horizontal_menu_logo_position =  value	
    }
    //endregion get/set horizontal_menu values

    //region get/set horizontal_menu_parent values
    var horizontal_menu_parent_items_width: Int @Px 
        get() = _horizontal_menu_parent_items_width 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_width  = dpToPx(value)
    }
    var horizontal_menu_parent_items_height: Int @Px 
        get() = _horizontal_menu_parent_items_height 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_height = dpToPx(value)
    }
    var horizontal_menu_parent_items_padding: Int @Px 
        get() = _horizontal_menu_parent_items_padding 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_padding = dpToPx(value)
    }
    var horizontal_menu_parent_items_padding_left: Int @Px 
        get() = _horizontal_menu_parent_items_padding_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_padding_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_padding_right: Int @Px 
        get() = _horizontal_menu_parent_items_padding_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_padding_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_padding_top: Int @Px 
        get() = _horizontal_menu_parent_items_padding_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_padding_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_padding_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_padding_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_margin: Int @Px 
        get() = _horizontal_menu_parent_items_margin 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_margin = dpToPx(value)
    }
    var horizontal_menu_parent_items_margin_left: Int @Px 
        get() = _horizontal_menu_parent_items_margin_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_margin_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_margin_right: Int @Px 
        get() = _horizontal_menu_parent_items_margin_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_margin_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_margin_top: Int @Px 
        get() = _horizontal_menu_parent_items_margin_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_margin_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_margin_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_margin_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_background_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_background_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_background_color = value
    }
    var horizontal_menu_parent_items_active_background_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_active_background_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_active_background_color = value
    }
    var horizontal_menu_parent_items_background_drawable: Drawable?
        get() = _horizontal_menu_parent_items_background_drawable
        set(value) { _horizontal_menu_parent_items_background_drawable = value
    }
    var horizontal_menu_parent_items_active_background_drawable: Drawable?
        get() = _horizontal_menu_parent_items_active_background_drawable
        set(value) { _horizontal_menu_parent_items_active_background_drawable = value
    }
    var horizontal_menu_parent_items_text_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_text_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_text_color = value
    }
    var horizontal_menu_parent_items_text_size: Int @Px 
        get() = _horizontal_menu_parent_items_text_size 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_text_size = dpToPx(value)
    }
    var horizontal_menu_parent_items_text_is_capitalized: Boolean 
        get() = _horizontal_menu_parent_items_text_is_capitalized 
        set(value) { _horizontal_menu_parent_items_text_is_capitalized = value
    }
    var horizontal_menu_parent_items_text_is_bold: Boolean 
        get() = _horizontal_menu_parent_items_text_is_bold 
        set(value) { _horizontal_menu_parent_items_text_is_bold = value
    }
    var horizontal_menu_parent_items_text_is_visible: Boolean 
        get() = _horizontal_menu_parent_items_text_is_visible 
        set(value) { _horizontal_menu_parent_items_text_is_visible = value
    }
    var horizontal_menu_parent_items_indicator_is_visible: Boolean 
        get() = _horizontal_menu_parent_items_indicator_is_visible 
        set(value) { _horizontal_menu_parent_items_indicator_is_visible = value
    }
    var horizontal_menu_parent_items_indicator_thickness: Int @Px 
        get() = _horizontal_menu_parent_items_indicator_thickness 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_indicator_thickness = dpToPx(value)
    }
    var horizontal_menu_parent_items_indicator_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_indicator_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_indicator_color = value
    }
    var horizontal_menu_parent_items_indicator_position: Int
        get() = _horizontal_menu_parent_items_indicator_position 
        set(value) { _horizontal_menu_parent_items_indicator_position = value
    }
    var horizontal_menu_parent_items_active_padding: Int @Px 
        get() = _horizontal_menu_parent_items_active_padding 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_padding = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_padding_left: Int @Px 
        get() = _horizontal_menu_parent_items_active_padding_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_padding_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_padding_right: Int @Px 
        get() = _horizontal_menu_parent_items_active_padding_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_padding_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_padding_top: Int @Px 
        get() = _horizontal_menu_parent_items_active_padding_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_padding_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_padding_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_active_padding_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_margin: Int @Px 
        get() = _horizontal_menu_parent_items_active_margin 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_margin = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_margin_left: Int @Px 
        get() = _horizontal_menu_parent_items_active_margin_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_margin_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_margin_right: Int @Px 
        get() = _horizontal_menu_parent_items_active_margin_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_margin_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_margin_top: Int @Px 
        get() = _horizontal_menu_parent_items_active_margin_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_margin_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_margin_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_active_margin_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_text_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_active_text_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_active_text_color = value
    }
    var horizontal_menu_parent_items_active_text_size: Int @Px 
        get() = _horizontal_menu_parent_items_active_text_size 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_active_text_size = dpToPx(value)
    }
    var horizontal_menu_parent_items_active_text_is_bold: Boolean 
        get() = _horizontal_menu_parent_items_active_text_is_bold 
        set(value) { _horizontal_menu_parent_items_active_text_is_bold = value
    }
    var horizontal_menu_parent_items_icon_width: Int @Px 
        get() = _horizontal_menu_parent_items_icon_width 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_width = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_height: Int @Px 
        get() = _horizontal_menu_parent_items_icon_height 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_height = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_padding: Int @Px 
        get() = _horizontal_menu_parent_items_icon_padding 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_padding = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_padding_left: Int @Px 
        get() = _horizontal_menu_parent_items_icon_padding_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_padding_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_padding_right: Int @Px 
        get() = _horizontal_menu_parent_items_icon_padding_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_padding_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_padding_top: Int @Px 
        get() = _horizontal_menu_parent_items_icon_padding_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_padding_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_padding_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_icon_padding_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_margin: Int @Px 
        get() = _horizontal_menu_parent_items_icon_margin 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_margin = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_margin_left: Int @Px 
        get() = _horizontal_menu_parent_items_icon_margin_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_margin_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_margin_right: Int @Px 
        get() = _horizontal_menu_parent_items_icon_margin_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_margin_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_margin_top: Int @Px 
        get() = _horizontal_menu_parent_items_icon_margin_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_margin_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_margin_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_icon_margin_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_icon_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_icon_tint_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_icon_tint_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_icon_tint_color = value
    }
    var horizontal_menu_parent_items_icon_is_visible: Boolean 
        get() = _horizontal_menu_parent_items_icon_is_visible 
        set(value) { _horizontal_menu_parent_items_icon_is_visible = value
    }
    var horizontal_menu_parent_items_icon_active_tint_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_icon_active_tint_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_icon_active_tint_color = value
    }
    var horizontal_menu_parent_items_badge_width: Int @Px 
        get() = _horizontal_menu_parent_items_badge_width 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_width = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_height: Int @Px 
        get() = _horizontal_menu_parent_items_badge_height 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_height = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_padding: Int @Px 
        get() = _horizontal_menu_parent_items_badge_padding 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_padding = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_padding_left: Int @Px 
        get() = _horizontal_menu_parent_items_badge_padding_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_padding_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_padding_right: Int @Px 
        get() = _horizontal_menu_parent_items_badge_padding_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_padding_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_padding_top: Int @Px 
        get() = _horizontal_menu_parent_items_badge_padding_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_padding_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_padding_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_badge_padding_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_margin: Int @Px 
        get() = _horizontal_menu_parent_items_badge_margin 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_margin = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_margin_left: Int @Px 
        get() = _horizontal_menu_parent_items_badge_margin_left 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_margin_left = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_margin_right: Int @Px 
        get() = _horizontal_menu_parent_items_badge_margin_right 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_margin_right = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_margin_top: Int @Px 
        get() = _horizontal_menu_parent_items_badge_margin_top 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_margin_top = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_margin_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_badge_margin_bottom 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_background_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_badge_background_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_badge_background_color = value
    }
    var horizontal_menu_parent_items_badge_border_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_badge_border_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_badge_border_color = value
    }
    var horizontal_menu_parent_items_badge_border_thickness: Int @Px
        get() = _horizontal_menu_parent_items_badge_border_thickness 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_border_thickness = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_text_color: Int @ColorInt 
        get() = _horizontal_menu_parent_items_badge_text_color 
        set(@ColorInt value) { _horizontal_menu_parent_items_badge_text_color = value
    }
    var horizontal_menu_parent_items_badge_text_size: Int @Px 
        get() = _horizontal_menu_parent_items_badge_text_size 
        set(@DpAnnotation value) { _horizontal_menu_parent_items_badge_text_size = dpToPx(value)
    }
    var horizontal_menu_parent_items_badge_text_is_bold: Boolean 
        get() = _horizontal_menu_parent_items_badge_text_is_bold 
        set(value) { _horizontal_menu_parent_items_badge_text_is_bold = value
    }
    var horizontal_menu_parent_items_badge_text_is_visible: Boolean
        get() = _horizontal_menu_parent_items_badge_text_is_visible
        set(value) { _horizontal_menu_parent_items_badge_text_is_visible = value
    }
    var horizontal_menu_parent_items_badge_is_visible: Boolean 
        get() = _horizontal_menu_parent_items_badge_is_visible 
        set(value) { _horizontal_menu_parent_items_badge_is_visible = value
    }
    var horizontal_menu_parent_items_badge_is_cleared_on_active: Boolean 
        get() = _horizontal_menu_parent_items_badge_is_cleared_on_active 
        set(value) { _horizontal_menu_parent_items_badge_is_cleared_on_active = value 
    }
    var horizontal_menu_parent_items_navigation_icon_width: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_width	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_width = dpToPx(value)
    }
    var horizontal_menu_parent_items_navigation_icon_height: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_height	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_height = dpToPx(value)
    }
    var horizontal_menu_parent_items_navigation_icon_padding: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_padding	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_padding = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_padding_left: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_padding_left	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_padding_left = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_padding_right: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_padding_right	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_padding_right = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_padding_top: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_padding_top	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_padding_top = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_padding_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_padding_bottom	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_padding_bottom = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_margin: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_margin	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_margin = dpToPx(value)
    }
    var horizontal_menu_parent_items_navigation_icon_margin_left: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_margin_left	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_margin_left = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_margin_right: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_margin_right	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_margin_right = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_margin_top: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_margin_top	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_margin_top = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_margin_bottom: Int @Px 
        get() = _horizontal_menu_parent_items_navigation_icon_margin_bottom	
        set(@DpAnnotation value) { _horizontal_menu_parent_items_navigation_icon_margin_bottom = dpToPx(
            value
        )
    }
    var horizontal_menu_parent_items_navigation_icon_color: Int @ColorInt 
        get() =	_horizontal_menu_parent_items_navigation_icon_color	
        set(@ColorInt value) { _horizontal_menu_parent_items_navigation_icon_color = value
    }
    var horizontal_menu_parent_items_navigation_icon_drawable: Drawable?
        get() = _horizontal_menu_parent_items_navigation_icon_drawable	
        set(value) { _horizontal_menu_parent_items_navigation_icon_drawable = value
    }
    var horizontal_menu_parent_items_navigation_icon_is_visible: Boolean 
        get() = _horizontal_menu_parent_items_navigation_icon_is_visible	
        set(value) { _horizontal_menu_parent_items_navigation_icon_is_visible = value
    }
    var horizontal_menu_parent_items_navigation_icon_is_hidden_when_active: Boolean 
        get() = _horizontal_menu_parent_items_navigation_icon_is_hidden_when_active	
        set(value) { _horizontal_menu_parent_items_navigation_icon_is_hidden_when_active = value
    }
    var horizontal_menu_parent_items_active_navigation_icon_color: Int @ColorInt 
        get() =	_horizontal_menu_parent_items_active_navigation_icon_color	
        set(@ColorInt value) { _horizontal_menu_parent_items_active_navigation_icon_color = value
    }

    //endregion get/set horizontal_menu_parent values

    //region get/set horizontal_menu_title values
    var horizontal_menu_title_items_width: Int @Px
    get() = _horizontal_menu_title_items_width
        set(@DpAnnotation value) { _horizontal_menu_title_items_width = dpToPx(value)
        }
    var horizontal_menu_title_items_height: Int @Px
    get() = _horizontal_menu_title_items_height
        set(@DpAnnotation value) { _horizontal_menu_title_items_height = dpToPx(value)
        }
    var horizontal_menu_title_items_padding: Int @Px
    get() = _horizontal_menu_title_items_padding
        set(@DpAnnotation value) { _horizontal_menu_title_items_padding = dpToPx(value)
        }
    var horizontal_menu_title_items_padding_left: Int @Px
    get() = _horizontal_menu_title_items_padding_left
        set(@DpAnnotation value) { _horizontal_menu_title_items_padding_left = dpToPx(value)
        }
    var horizontal_menu_title_items_padding_right: Int @Px
    get() = _horizontal_menu_title_items_padding_right
        set(@DpAnnotation value) { _horizontal_menu_title_items_padding_right = dpToPx(value)
        }
    var horizontal_menu_title_items_padding_top: Int @Px
    get() = _horizontal_menu_title_items_padding_top
        set(@DpAnnotation value) { _horizontal_menu_title_items_padding_top = dpToPx(value)
        }
    var horizontal_menu_title_items_padding_bottom: Int @Px
    get() = _horizontal_menu_title_items_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_title_items_padding_bottom = dpToPx(value)
        }
    var horizontal_menu_title_items_margin: Int @Px
    get() = _horizontal_menu_title_items_margin
        set(@DpAnnotation value) { _horizontal_menu_title_items_margin = dpToPx(value)
        }
    var horizontal_menu_title_items_margin_left: Int @Px
    get() = _horizontal_menu_title_items_margin_left
        set(@DpAnnotation value) { _horizontal_menu_title_items_margin_left = dpToPx(value)
        }
    var horizontal_menu_title_items_margin_right: Int @Px
    get() = _horizontal_menu_title_items_margin_right
        set(@DpAnnotation value) { _horizontal_menu_title_items_margin_right = dpToPx(value)
        }
    var horizontal_menu_title_items_margin_top: Int @Px
    get() = _horizontal_menu_title_items_margin_top
        set(@DpAnnotation value) { _horizontal_menu_title_items_margin_top = dpToPx(value)
        }
    var horizontal_menu_title_items_margin_bottom: Int @Px
    get() = _horizontal_menu_title_items_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_title_items_margin_bottom = dpToPx(value)
        }
    var horizontal_menu_title_items_background_color: Int @ColorInt
    get() =_horizontal_menu_title_items_background_color
        set(@ColorInt value) { _horizontal_menu_title_items_background_color =  value
        }
    var horizontal_menu_title_items_background_drawable: Int
        get() = _horizontal_menu_title_items_background_drawable
        set(value) { _horizontal_menu_title_items_background_drawable =  value
        }
    var horizontal_menu_title_items_text_color: Int @ColorInt
    get() =	_horizontal_menu_title_items_text_color
        set(@ColorInt value) { _horizontal_menu_title_items_text_color =  value
        }
    var horizontal_menu_title_items_text_size: Int @Px
    get() = _horizontal_menu_title_items_text_size
        set(@DpAnnotation value) { _horizontal_menu_title_items_text_size = dpToPx(value)
        }
    var horizontal_menu_title_items_text_is_capitalized: Boolean
        get() = _horizontal_menu_title_items_text_is_capitalized
        set(value) { _horizontal_menu_title_items_text_is_capitalized =  value
        }
    var horizontal_menu_title_items_text_is_bold: Boolean
        get() = _horizontal_menu_title_items_text_is_bold
        set(value) { _horizontal_menu_title_items_text_is_bold =  value
        }
    var horizontal_menu_title_items_divider_visible: Boolean
        get() = _horizontal_menu_title_items_divider_visible
        set(value) { _horizontal_menu_title_items_divider_visible =  value
        }
    var horizontal_menu_title_items_divider_thickness: Int @Px
    get() = _horizontal_menu_title_items_divider_thickness
        set(@DpAnnotation value) { _horizontal_menu_title_items_divider_thickness = dpToPx(value)
        }
    var horizontal_menu_title_items_divider_color: Int @ColorInt
    get() =	_horizontal_menu_title_items_divider_color
        set(@ColorInt value) { _horizontal_menu_title_items_divider_color =  value
        }

    //endregion get/set horizontal_menu_title values

    //region get/set horizontal_menu_child values
    var horizontal_menu_child_items_width: Int @Px 
        get() = _horizontal_menu_child_items_width
        set(@DpAnnotation value) { _horizontal_menu_child_items_width = dpToPx(value)
    }
    var horizontal_menu_child_items_height: Int @Px 
        get() = _horizontal_menu_child_items_height
        set(@DpAnnotation value) { _horizontal_menu_child_items_height = dpToPx(value)
    }
    var horizontal_menu_child_items_padding: Int @Px 
        get() = _horizontal_menu_child_items_padding
        set(@DpAnnotation value) { _horizontal_menu_child_items_padding = dpToPx(value)
    }
    var horizontal_menu_child_items_padding_left: Int @Px 
        get() = _horizontal_menu_child_items_padding_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_padding_left = dpToPx(value)
    }
    var horizontal_menu_child_items_padding_right: Int @Px 
        get() = _horizontal_menu_child_items_padding_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_padding_right = dpToPx(value)
    }
    var horizontal_menu_child_items_padding_top: Int @Px 
        get() = _horizontal_menu_child_items_padding_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_padding_top = dpToPx(value)
    }
    var horizontal_menu_child_items_padding_bottom: Int @Px 
        get() = _horizontal_menu_child_items_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_margin: Int @Px 
        get() = _horizontal_menu_child_items_margin
        set(@DpAnnotation value) { _horizontal_menu_child_items_margin = dpToPx(value)
    }
    var horizontal_menu_child_items_margin_left: Int @Px 
        get() = _horizontal_menu_child_items_margin_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_margin_left = dpToPx(value)
    }
    var horizontal_menu_child_items_margin_right: Int @Px 
        get() = _horizontal_menu_child_items_margin_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_margin_right = dpToPx(value)
    }
    var horizontal_menu_child_items_margin_top: Int @Px 
        get() = _horizontal_menu_child_items_margin_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_margin_top = dpToPx(value)
    }
    var horizontal_menu_child_items_margin_bottom: Int @Px 
        get() = _horizontal_menu_child_items_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_background_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_background_color
        set(@ColorInt value) { _horizontal_menu_child_items_background_color =  value
    }
    var horizontal_menu_child_items_active_background_color: Int @ColorInt
        get() = _horizontal_menu_child_items_active_background_color
        set(@ColorInt value) { _horizontal_menu_child_items_active_background_color =  value
    }
    var horizontal_menu_child_items_text_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_text_color
        set(@ColorInt value) { _horizontal_menu_child_items_text_color =  value
    }
    var horizontal_menu_child_items_text_size: Int @Px 
        get() = _horizontal_menu_child_items_text_size
        set(@DpAnnotation value) { _horizontal_menu_child_items_text_size = dpToPx(value)
    }
    var horizontal_menu_child_items_text_is_capitalized: Boolean 
        get() = _horizontal_menu_child_items_text_is_capitalized
        set(value) { _horizontal_menu_child_items_text_is_capitalized =  value
    }
    var horizontal_menu_child_items_text_is_bold: Boolean 
        get() = _horizontal_menu_child_items_text_is_bold
        set(value) { _horizontal_menu_child_items_text_is_bold =  value
    }
    var horizontal_menu_child_items_text_is_visible: Boolean 
        get() = _horizontal_menu_child_items_text_is_visible
        set(value) { _horizontal_menu_child_items_text_is_visible =  value
    }
    var horizontal_menu_child_items_indicator_is_visible: Boolean 
        get() = _horizontal_menu_child_items_indicator_is_visible
        set(value) { _horizontal_menu_child_items_indicator_is_visible =  value
    }
    var horizontal_menu_child_items_indicator_thickness: Int @Px 
        get() = _horizontal_menu_child_items_indicator_thickness
        set(@DpAnnotation value) { _horizontal_menu_child_items_indicator_thickness = dpToPx(value)
    }
    var horizontal_menu_child_items_indicator_color: Int @ColorInt
        get() = _horizontal_menu_child_items_indicator_color
        set(@ColorInt value) { _horizontal_menu_child_items_indicator_color =  value
    }
    var horizontal_menu_child_items_indicator_position: Int 
        get() = _horizontal_menu_child_items_indicator_position
        set(value) { _horizontal_menu_child_items_indicator_position =  value
    }
    var horizontal_menu_child_items_active_padding: Int @Px 
        get() = _horizontal_menu_child_items_active_padding
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_padding = dpToPx(value)
    }
    var horizontal_menu_child_items_active_padding_left: Int @Px 
        get() = _horizontal_menu_child_items_active_padding_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_padding_left = dpToPx(value)
    }
    var horizontal_menu_child_items_active_padding_right: Int @Px 
        get() = _horizontal_menu_child_items_active_padding_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_padding_right = dpToPx(value)
    }
    var horizontal_menu_child_items_active_padding_top: Int @Px 
        get() = _horizontal_menu_child_items_active_padding_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_padding_top = dpToPx(value)
    }
    var horizontal_menu_child_items_active_padding_bottom: Int @Px 
        get() = _horizontal_menu_child_items_active_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_active_margin: Int @Px 
        get() = _horizontal_menu_child_items_active_margin
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_margin = dpToPx(value)
    }
    var horizontal_menu_child_items_active_margin_left: Int @Px 
        get() = _horizontal_menu_child_items_active_margin_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_margin_left = dpToPx(value)
    }
    var horizontal_menu_child_items_active_margin_right: Int @Px 
        get() = _horizontal_menu_child_items_active_margin_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_margin_right = dpToPx(value)
    }
    var horizontal_menu_child_items_active_margin_top: Int @Px 
        get() = _horizontal_menu_child_items_active_margin_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_margin_top = dpToPx(value)
    }
    var horizontal_menu_child_items_active_margin_bottom: Int @Px 
        get() = _horizontal_menu_child_items_active_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_active_text_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_active_text_color
        set(@ColorInt value) { _horizontal_menu_child_items_active_text_color =  value
    }
    var horizontal_menu_child_items_active_text_size: Int @Px 
        get() = _horizontal_menu_child_items_active_text_size
        set(@DpAnnotation value) { _horizontal_menu_child_items_active_text_size = dpToPx(value)
    }
    var horizontal_menu_child_items_active_text_is_bold: Boolean 
        get() = _horizontal_menu_child_items_active_text_is_bold
        set(value) { _horizontal_menu_child_items_active_text_is_bold =  value
    }
    var horizontal_menu_child_items_icon_width: Int @Px 
        get() = _horizontal_menu_child_items_icon_width
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_width = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_height: Int @Px 
        get() = _horizontal_menu_child_items_icon_height
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_height = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_padding: Int @Px 
        get() = _horizontal_menu_child_items_icon_padding
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_padding = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_padding_left: Int @Px 
        get() = _horizontal_menu_child_items_icon_padding_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_padding_left = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_padding_right: Int @Px 
        get() = _horizontal_menu_child_items_icon_padding_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_padding_right = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_padding_top: Int @Px 
        get() = _horizontal_menu_child_items_icon_padding_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_padding_top = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_padding_bottom: Int @Px 
        get() = _horizontal_menu_child_items_icon_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_margin: Int @Px 
        get() = _horizontal_menu_child_items_icon_margin
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_margin = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_margin_left: Int @Px 
        get() = _horizontal_menu_child_items_icon_margin_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_margin_left = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_margin_right: Int @Px 
        get() = _horizontal_menu_child_items_icon_margin_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_margin_right = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_margin_top: Int @Px 
        get() = _horizontal_menu_child_items_icon_margin_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_margin_top = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_margin_bottom: Int @Px 
        get() = _horizontal_menu_child_items_icon_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_icon_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_icon_tint_color: Int @ColorInt
        get() = _horizontal_menu_child_items_icon_tint_color
        set(@ColorInt value) { _horizontal_menu_child_items_icon_tint_color =  value
    }
    var horizontal_menu_child_items_icon_is_visible: Boolean 
        get() = _horizontal_menu_child_items_icon_is_visible
        set(value) { _horizontal_menu_child_items_icon_is_visible =  value
    }
    var horizontal_menu_child_items_icon_active_tint_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_icon_active_tint_color
        set(@ColorInt value) { _horizontal_menu_child_items_icon_active_tint_color =  value
    }
    var horizontal_menu_child_items_badge_width: Int @Px 
        get() = _horizontal_menu_child_items_badge_width
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_width = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_height: Int @Px 
        get() = _horizontal_menu_child_items_badge_height
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_height = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_padding: Int @Px 
        get() = _horizontal_menu_child_items_badge_padding
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_padding = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_padding_left: Int @Px 
        get() = _horizontal_menu_child_items_badge_padding_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_padding_left = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_padding_right: Int @Px 
        get() = _horizontal_menu_child_items_badge_padding_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_padding_right = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_padding_top: Int @Px 
        get() = _horizontal_menu_child_items_badge_padding_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_padding_top = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_padding_bottom: Int @Px 
        get() = _horizontal_menu_child_items_badge_padding_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_padding_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_margin: Int @Px 
        get() = _horizontal_menu_child_items_badge_margin
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_margin = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_margin_left: Int @Px 
        get() = _horizontal_menu_child_items_badge_margin_left
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_margin_left = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_margin_right: Int @Px 
        get() = _horizontal_menu_child_items_badge_margin_right
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_margin_right = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_margin_top: Int @Px 
        get() = _horizontal_menu_child_items_badge_margin_top
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_margin_top = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_margin_bottom: Int @Px 
        get() = _horizontal_menu_child_items_badge_margin_bottom
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_margin_bottom = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_background_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_badge_background_color
        set(@ColorInt value) { _horizontal_menu_child_items_badge_background_color =  value
    }
    var horizontal_menu_child_items_badge_border_color: Int @ColorInt
        get() =	_horizontal_menu_child_items_badge_border_color
        set(@ColorInt value) { _horizontal_menu_child_items_badge_border_color =  value
    }
    var horizontal_menu_child_items_badge_border_thickness: Int @Px 
        get() = _horizontal_menu_child_items_badge_border_thickness
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_border_thickness = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_text_color: Int @ColorInt
        get() = _horizontal_menu_child_items_badge_text_color
        set(@ColorInt value) { _horizontal_menu_child_items_badge_text_color =  value
    }
    var horizontal_menu_child_items_badge_text_size: Int @Px 
        get() = _horizontal_menu_child_items_badge_text_size
        set(@DpAnnotation value) { _horizontal_menu_child_items_badge_text_size = dpToPx(value)
    }
    var horizontal_menu_child_items_badge_text_is_bold: Boolean 
        get() = _horizontal_menu_child_items_badge_text_is_bold
        set(value) { _horizontal_menu_child_items_badge_text_is_bold =  value
    }
    var horizontal_menu_child_items_badge_is_visible: Boolean 
        get() = _horizontal_menu_child_items_badge_is_visible
        set(value) { _horizontal_menu_child_items_badge_is_visible =  value
    }
    var horizontal_menu_child_items_badge_is_cleared_on_active: Boolean 
        get() = _horizontal_menu_child_items_badge_is_cleared_on_active
        set(value) { _horizontal_menu_child_items_badge_is_cleared_on_active =  value
    }
    var horizontal_menu_child_items_background_drawable: Drawable?
        get() = _horizontal_menu_child_items_background_drawable
        set(value) { _horizontal_menu_child_items_background_drawable =  value
    }
    var horizontal_menu_child_items_active_background_drawable: Drawable?
        get() = _horizontal_menu_child_items_active_background_drawable
        set(value) { _horizontal_menu_child_items_active_background_drawable =  value
    }
    var horizontal_menu_child_items_badge_text_is_visible: Boolean 
        get() = _horizontal_menu_child_items_badge_text_is_visible
        set(value) { _horizontal_menu_child_items_badge_text_is_visible =  value
    }
    var horizontal_menu_child_items_when_active_show_parent_active_state: Boolean 
        get() = _horizontal_menu_child_items_when_active_show_parent_active_state
        set(value) { _horizontal_menu_child_items_when_active_show_parent_active_state =  value
    }
    //endregion get/set horizontal_menu_child values

    //endregion get/set values

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu,
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
        //region get/set horizontal_menu values
        this.horizontal_menu_is_responsive_menu = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_is_responsive_menu,
            this._horizontal_menu_is_responsive_menu
        )

        this._horizontal_menu_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_height,
            this._horizontal_menu_height
        )
        this.horizontal_menu_max_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_max_height,
            this.horizontal_menu_max_height
        )
        this._horizontal_menu_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_padding,
            this._horizontal_menu_padding
        )
        this._horizontal_menu_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_padding_left,
            this._horizontal_menu_padding_left
        )
        this._horizontal_menu_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_padding_right,
            this._horizontal_menu_padding_right
        )
        this._horizontal_menu_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_padding_top,
            this._horizontal_menu_padding_top
        )
        this._horizontal_menu_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_padding_bottom,
            this._horizontal_menu_padding_bottom
        )
        this._horizontal_menu_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_margin,
            this._horizontal_menu_margin
        )
        this._horizontal_menu_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_margin_left,
            this._horizontal_menu_margin_left
        )
        this._horizontal_menu_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_margin_right,
            this._horizontal_menu_margin_right
        )
        this._horizontal_menu_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_margin_top,
            this._horizontal_menu_margin_top
        )
        this._horizontal_menu_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_margin_bottom,
            this._horizontal_menu_margin_bottom
        )
        this._horizontal_menu_alignment_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_alignment_position,
            this._horizontal_menu_alignment_position
        )
        this._horizontal_menu_enable_nested_scrolling = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_enable_nested_scrolling,
            this._horizontal_menu_enable_nested_scrolling
        )
        this._horizontal_menu_show_scroll_bar = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_scroll_bar,
            this._horizontal_menu_show_scroll_bar
        )
        this._horizontal_menu_active_state_on_left = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_active_state_on_left,
            this._horizontal_menu_active_state_on_left
        )
        this._horizontal_menu_show_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_icons,
            this._horizontal_menu_show_icons
        )
        this._horizontal_menu_show_parent_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_parent_icons,
            this._horizontal_menu_show_parent_icons
        )
        this._horizontal_menu_show_child_icons = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_child_icons,
            this._horizontal_menu_show_child_icons
        )
        this._horizontal_menu_show_parent_divider = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_parent_divider,
            this._horizontal_menu_show_parent_divider
        )
        this._horizontal_menu_show_icons_only = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_icons_only,
            this._horizontal_menu_show_icons_only
        )
        this._horizontal_menu_collapse_type = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_type,
            this._horizontal_menu_collapse_type
        )
        this._horizontal_menu_collapse_others = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_others,
            this._horizontal_menu_collapse_others
        )
        this._horizontal_menu_collapse_expand_delay = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_expand_delay,
            this._horizontal_menu_collapse_expand_delay
        )
        this._horizontal_menu_collapse_icon_rotate = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_rotate,
            this._horizontal_menu_collapse_icon_rotate
        )
        this._horizontal_menu_collapse_icon_rotate_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_rotate_duration,
            this._horizontal_menu_collapse_icon_rotate_duration
        )
        this._horizontal_menu_collapse_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_width,
            this._horizontal_menu_collapse_icon_width
        )
        this._horizontal_menu_collapse_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_height,
            this._horizontal_menu_collapse_icon_height
        )
        this._horizontal_menu_collapse_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_padding,
            this._horizontal_menu_collapse_icon_padding
        )
        this._horizontal_menu_collapse_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_padding_left,
            this._horizontal_menu_collapse_icon_padding_left
        )
        this._horizontal_menu_collapse_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_padding_right,
            this._horizontal_menu_collapse_icon_padding_right
        )
        this._horizontal_menu_collapse_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_padding_top,
            this._horizontal_menu_collapse_icon_padding_top
        )
        this._horizontal_menu_collapse_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_padding_bottom,
            this._horizontal_menu_collapse_icon_padding_bottom
        )
        this._horizontal_menu_collapse_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_margin,
            this._horizontal_menu_collapse_icon_margin
        )
        this._horizontal_menu_collapse_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_margin_left,
            this._horizontal_menu_collapse_icon_margin_left
        )
        this._horizontal_menu_collapse_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_margin_right,
            this._horizontal_menu_collapse_icon_margin_right
        )
        this._horizontal_menu_collapse_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_margin_top,
            this._horizontal_menu_collapse_icon_margin_top
        )
        this._horizontal_menu_collapse_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_margin_bottom,
            this._horizontal_menu_collapse_icon_margin_bottom
        )
        this._horizontal_menu_collapse_icon_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_color,
            this._horizontal_menu_collapse_icon_color
        )
        this._horizontal_menu_collapse_icon_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_drawable
        )
        this._horizontal_menu_collapse_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_collapse_icon_is_visible,
            this._horizontal_menu_collapse_icon_is_visible
        )
        this._horizontal_menu_border_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_border_is_visible,
            this._horizontal_menu_border_is_visible
        )
        this._horizontal_menu_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_border_thickness,
            this._horizontal_menu_border_thickness
        )
        this._horizontal_menu_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_border_color,
            this._horizontal_menu_border_color
        )
        this._horizontal_menu_border_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_border_position,
            this._horizontal_menu_border_position
        )
        this._horizontal_menu_show_shimmer_animation = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_shimmer_animation,
            this._horizontal_menu_show_shimmer_animation
        )
        this._horizontal_menu_show_shimmer_animation_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_shimmer_animation_duration,
            this._horizontal_menu_show_shimmer_animation_duration
        )
        this._horizontal_menu_show_animation_in = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_animation_in,
            this._horizontal_menu_show_animation_in
        )
        this._horizontal_menu_show_animation_in_type = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_animation_in_type,
            this._horizontal_menu_show_animation_in_type
        )
        this._horizontal_menu_show_animation_in_duration = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_show_animation_in_duration,
            this._horizontal_menu_show_animation_in_duration
        )
        this._horizontal_menu_divider_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_color,
            this._horizontal_menu_divider_color
        )
        this._horizontal_menu_divider_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_width,
            this._horizontal_menu_divider_width
        )
        this._horizontal_menu_divider_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_height,
            this._horizontal_menu_divider_height
        )
        this._horizontal_menu_divider_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_padding,
            this._horizontal_menu_divider_padding
        )
        this._horizontal_menu_divider_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_padding_left,
            this._horizontal_menu_divider_padding_left
        )
        this._horizontal_menu_divider_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_padding_right,
            this._horizontal_menu_divider_padding_right
        )
        this._horizontal_menu_divider_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_padding_top,
            this._horizontal_menu_divider_padding_top
        )
        this._horizontal_menu_divider_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_padding_bottom,
            this._horizontal_menu_divider_padding_bottom
        )
        this._horizontal_menu_divider_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_margin,
            this._horizontal_menu_divider_margin
        )
        this._horizontal_menu_divider_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_margin_left,
            this._horizontal_menu_divider_margin_left
        )
        this._horizontal_menu_divider_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_margin_right,
            this._horizontal_menu_divider_margin_right
        )
        this._horizontal_menu_divider_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_margin_top,
            this._horizontal_menu_divider_margin_top
        )
        this._horizontal_menu_divider_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_margin_bottom,
            this._horizontal_menu_divider_margin_bottom
        )
        this._horizontal_menu_divider_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_divider_is_visible,
            this._horizontal_menu_divider_is_visible
        )
        this._horizontal_menu_logo_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_width, 
            this._horizontal_menu_logo_width
        )
        this._horizontal_menu_logo_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_height, 
            this._horizontal_menu_logo_height
        )
        this._horizontal_menu_logo_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_padding, 
            this._horizontal_menu_logo_padding
        )
        this._horizontal_menu_logo_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_padding_left, 
            this._horizontal_menu_logo_padding_left
        )
        this._horizontal_menu_logo_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_padding_right, 
            this._horizontal_menu_logo_padding_right
        )
        this._horizontal_menu_logo_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_padding_top, 
            this._horizontal_menu_logo_padding_top
        )
        this._horizontal_menu_logo_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_padding_bottom, 
            this._horizontal_menu_logo_padding_bottom
        )
        this._horizontal_menu_logo_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_margin, 
            this._horizontal_menu_logo_margin
        )
        this._horizontal_menu_logo_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_margin_left, 
            this._horizontal_menu_logo_margin_left
        )
        this._horizontal_menu_logo_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_margin_right, 
            this._horizontal_menu_logo_margin_right
        )
        this._horizontal_menu_logo_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_margin_top, 
            this._horizontal_menu_logo_margin_top
        )
        this._horizontal_menu_logo_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_margin_bottom, 
            this._horizontal_menu_logo_margin_bottom
        )
        this._horizontal_menu_logo_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_drawable
        )
        this._horizontal_menu_logo_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_is_visible, 
            this._horizontal_menu_logo_is_visible
        )
        this._horizontal_menu_logo_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_logo_position, 
            this._horizontal_menu_logo_position
        )
        //endregion get/set horizontal_menu values

        //region get/set horizontal_menu_parent values
        this._horizontal_menu_parent_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_width,
            this._horizontal_menu_parent_items_width
        )
        this._horizontal_menu_parent_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_height,
            this._horizontal_menu_parent_items_height
        )
        this._horizontal_menu_parent_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_padding,
            this._horizontal_menu_parent_items_padding
        )
        this._horizontal_menu_parent_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_padding_left,
            this._horizontal_menu_parent_items_padding_left
        )
        this._horizontal_menu_parent_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_padding_right,
            this._horizontal_menu_parent_items_padding_right
        )
        this._horizontal_menu_parent_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_padding_top,
            this._horizontal_menu_parent_items_padding_top
        )
        this._horizontal_menu_parent_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_padding_bottom,
            this._horizontal_menu_parent_items_padding_bottom
        )
        this._horizontal_menu_parent_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_margin,
            this._horizontal_menu_parent_items_margin
        )
        this._horizontal_menu_parent_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_margin_left,
            this._horizontal_menu_parent_items_margin_left
        )
        this._horizontal_menu_parent_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_margin_right,
            this._horizontal_menu_parent_items_margin_right
        )
        this._horizontal_menu_parent_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_margin_top,
            this._horizontal_menu_parent_items_margin_top
        )
        this._horizontal_menu_parent_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_margin_bottom,
            this._horizontal_menu_parent_items_margin_bottom
        )
        this._horizontal_menu_parent_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_background_color,
            this._horizontal_menu_parent_items_background_color
        )
        this._horizontal_menu_parent_items_active_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_background_color,
            this._horizontal_menu_parent_items_active_background_color
        )
        this._horizontal_menu_parent_items_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_background_drawable
        )
        this._horizontal_menu_parent_items_active_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_background_drawable
        )
        this._horizontal_menu_parent_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_text_color,
            this._horizontal_menu_parent_items_text_color
        )
        this._horizontal_menu_parent_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_text_size,
            this._horizontal_menu_parent_items_text_size
        )
        this._horizontal_menu_parent_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_text_is_capitalized,
            this._horizontal_menu_parent_items_text_is_capitalized
        )
        this._horizontal_menu_parent_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_text_is_bold,
            this._horizontal_menu_parent_items_text_is_bold
        )
        this._horizontal_menu_parent_items_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_text_is_visible,
            this._horizontal_menu_parent_items_text_is_visible
        )
        this._horizontal_menu_parent_items_indicator_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_indicator_is_visible,
            this._horizontal_menu_parent_items_indicator_is_visible
        )
        this._horizontal_menu_parent_items_indicator_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_indicator_thickness,
            this._horizontal_menu_parent_items_indicator_thickness
        )
        this._horizontal_menu_parent_items_indicator_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_indicator_color,
            this._horizontal_menu_parent_items_indicator_color
        )
        this._horizontal_menu_parent_items_indicator_position = typedArray.getInteger(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_indicator_position,
            this._horizontal_menu_parent_items_indicator_position
        )
        this._horizontal_menu_parent_items_active_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_padding,
            this._horizontal_menu_parent_items_active_padding
        )
        this._horizontal_menu_parent_items_active_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_padding_left,
            this._horizontal_menu_parent_items_active_padding_left
        )
        this._horizontal_menu_parent_items_active_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_padding_right,
            this._horizontal_menu_parent_items_active_padding_right
        )
        this._horizontal_menu_parent_items_active_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_padding_top,
            this._horizontal_menu_parent_items_active_padding_top
        )
        this._horizontal_menu_parent_items_active_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_padding_bottom,
            this._horizontal_menu_parent_items_active_padding_bottom
        )
        this._horizontal_menu_parent_items_active_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_margin,
            this._horizontal_menu_parent_items_active_margin
        )
        this._horizontal_menu_parent_items_active_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_margin_left,
            this._horizontal_menu_parent_items_active_margin_left
        )
        this._horizontal_menu_parent_items_active_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_margin_right,
            this._horizontal_menu_parent_items_active_margin_right
        )
        this._horizontal_menu_parent_items_active_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_margin_top,
            this._horizontal_menu_parent_items_active_margin_top
        )
        this._horizontal_menu_parent_items_active_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_margin_bottom,
            this._horizontal_menu_parent_items_active_margin_bottom
        )
        this._horizontal_menu_parent_items_active_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_text_color,
            this._horizontal_menu_parent_items_active_text_color
        )
        this._horizontal_menu_parent_items_active_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_text_size,
            this._horizontal_menu_parent_items_active_text_size
        )
        this._horizontal_menu_parent_items_active_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_text_is_bold,
            this._horizontal_menu_parent_items_active_text_is_bold
        )
        this._horizontal_menu_parent_items_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_width,
            this._horizontal_menu_parent_items_icon_width
        )
        this._horizontal_menu_parent_items_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_height,
            this._horizontal_menu_parent_items_icon_height
        )
        this._horizontal_menu_parent_items_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_padding,
            this._horizontal_menu_parent_items_icon_padding
        )
        this._horizontal_menu_parent_items_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_padding_left,
            this._horizontal_menu_parent_items_icon_padding_left
        )
        this._horizontal_menu_parent_items_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_padding_right,
            this._horizontal_menu_parent_items_icon_padding_right
        )
        this._horizontal_menu_parent_items_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_padding_top,
            this._horizontal_menu_parent_items_icon_padding_top
        )
        this._horizontal_menu_parent_items_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_padding_bottom,
            this._horizontal_menu_parent_items_icon_padding_bottom
        )
        this._horizontal_menu_parent_items_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_margin,
            this._horizontal_menu_parent_items_icon_margin
        )
        this._horizontal_menu_parent_items_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_margin_left,
            this._horizontal_menu_parent_items_icon_margin_left
        )
        this._horizontal_menu_parent_items_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_margin_right,
            this._horizontal_menu_parent_items_icon_margin_right
        )
        this._horizontal_menu_parent_items_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_margin_top,
            this._horizontal_menu_parent_items_icon_margin_top
        )
        this._horizontal_menu_parent_items_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_margin_bottom,
            this._horizontal_menu_parent_items_icon_margin_bottom
        )
        this._horizontal_menu_parent_items_icon_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_tint_color,
            this._horizontal_menu_parent_items_icon_tint_color
        )
        this._horizontal_menu_parent_items_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_is_visible,
            this._horizontal_menu_parent_items_icon_is_visible
        )
        this._horizontal_menu_parent_items_icon_active_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_icon_active_tint_color,
            this._horizontal_menu_parent_items_icon_active_tint_color
        )
        this._horizontal_menu_parent_items_badge_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_width,
            this._horizontal_menu_parent_items_badge_width
        )
        this._horizontal_menu_parent_items_badge_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_height,
            this._horizontal_menu_parent_items_badge_height
        )
        this._horizontal_menu_parent_items_badge_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_padding,
            this._horizontal_menu_parent_items_badge_padding
        )
        this._horizontal_menu_parent_items_badge_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_padding_left,
            this._horizontal_menu_parent_items_badge_padding_left
        )
        this._horizontal_menu_parent_items_badge_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_padding_right,
            this._horizontal_menu_parent_items_badge_padding_right
        )
        this._horizontal_menu_parent_items_badge_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_padding_top,
            this._horizontal_menu_parent_items_badge_padding_top
        )
        this._horizontal_menu_parent_items_badge_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_padding_bottom,
            this._horizontal_menu_parent_items_badge_padding_bottom
        )
        this._horizontal_menu_parent_items_badge_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_margin,
            this._horizontal_menu_parent_items_badge_margin
        )
        this._horizontal_menu_parent_items_badge_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_margin_left,
            this._horizontal_menu_parent_items_badge_margin_left
        )
        this._horizontal_menu_parent_items_badge_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_margin_right,
            this._horizontal_menu_parent_items_badge_margin_right
        )
        this._horizontal_menu_parent_items_badge_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_margin_top,
            this._horizontal_menu_parent_items_badge_margin_top
        )
        this._horizontal_menu_parent_items_badge_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_margin_bottom,
            this._horizontal_menu_parent_items_badge_margin_bottom
        )
        this._horizontal_menu_parent_items_badge_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_background_color,
            this._horizontal_menu_parent_items_badge_background_color
        )
        this._horizontal_menu_parent_items_badge_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_border_color,
            this._horizontal_menu_parent_items_badge_border_color
        )
        this._horizontal_menu_parent_items_badge_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_border_thickness,
            this._horizontal_menu_parent_items_badge_border_thickness
        )
        this._horizontal_menu_parent_items_badge_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_text_color,
            this._horizontal_menu_parent_items_badge_text_color
        )
        this._horizontal_menu_parent_items_badge_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_text_size,
            this._horizontal_menu_parent_items_badge_text_size
        )
        this._horizontal_menu_parent_items_badge_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_text_is_bold,
            this._horizontal_menu_parent_items_badge_text_is_bold
        )
        this._horizontal_menu_parent_items_badge_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_text_is_visible,
            this._horizontal_menu_parent_items_badge_text_is_visible
        )
        this._horizontal_menu_parent_items_badge_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_is_visible,
            this._horizontal_menu_parent_items_badge_is_visible
        )
        this._horizontal_menu_parent_items_badge_is_cleared_on_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_badge_is_cleared_on_active,
            this._horizontal_menu_parent_items_badge_is_cleared_on_active
        )

        this._horizontal_menu_parent_items_navigation_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_width,
            this._horizontal_menu_parent_items_navigation_icon_width
        )
        this._horizontal_menu_parent_items_navigation_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_height,
            this._horizontal_menu_parent_items_navigation_icon_height
        )
        this._horizontal_menu_parent_items_navigation_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_padding,
            this._horizontal_menu_parent_items_navigation_icon_padding
        )
        this._horizontal_menu_parent_items_navigation_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_padding_left,
            this._horizontal_menu_parent_items_navigation_icon_padding_left
        )
        this._horizontal_menu_parent_items_navigation_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_padding_right,
            this._horizontal_menu_parent_items_navigation_icon_padding_right
        )
        this._horizontal_menu_parent_items_navigation_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_padding_top,
            this._horizontal_menu_parent_items_navigation_icon_padding_top
        )
        this._horizontal_menu_parent_items_navigation_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_padding_bottom,
            this._horizontal_menu_parent_items_navigation_icon_padding_bottom
        )
        this._horizontal_menu_parent_items_navigation_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_margin,
            this._horizontal_menu_parent_items_navigation_icon_margin
        )
        this._horizontal_menu_parent_items_navigation_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_margin_left,
            this._horizontal_menu_parent_items_navigation_icon_margin_left
        )
        this._horizontal_menu_parent_items_navigation_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_margin_right,
            this._horizontal_menu_parent_items_navigation_icon_margin_right
        )
        this._horizontal_menu_parent_items_navigation_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_margin_top,
            this._horizontal_menu_parent_items_navigation_icon_margin_top
        )
        this._horizontal_menu_parent_items_navigation_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_margin_bottom,
            this._horizontal_menu_parent_items_navigation_icon_margin_bottom
        )
        this._horizontal_menu_parent_items_navigation_icon_color = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_color,
            this._horizontal_menu_parent_items_navigation_icon_color
        )
        this._horizontal_menu_parent_items_navigation_icon_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_drawable
        )
        this._horizontal_menu_parent_items_navigation_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_is_visible,
            this._horizontal_menu_parent_items_navigation_icon_is_visible
        )
        this._horizontal_menu_parent_items_navigation_icon_is_hidden_when_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_navigation_icon_is_hidden_when_active,
            this._horizontal_menu_parent_items_navigation_icon_is_hidden_when_active
        )
        this._horizontal_menu_parent_items_active_navigation_icon_color = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_parent_items_active_navigation_icon_color,
            this._horizontal_menu_parent_items_active_navigation_icon_color
        )

        //endregion get/set horizontal_menu_parent values

        //region get/set horizontal_menu_title values
        this._horizontal_menu_title_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_width,
            this._horizontal_menu_title_items_width
        )
        this._horizontal_menu_title_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_height,
            this._horizontal_menu_title_items_height
        )
        this._horizontal_menu_title_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_padding,
            this._horizontal_menu_title_items_padding
        )
        this._horizontal_menu_title_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_padding_left,
            this._horizontal_menu_title_items_padding_left
        )
        this._horizontal_menu_title_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_padding_right,
            this._horizontal_menu_title_items_padding_right
        )
        this._horizontal_menu_title_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_padding_top,
            this._horizontal_menu_title_items_padding_top
        )
        this._horizontal_menu_title_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_padding_bottom,
            this._horizontal_menu_title_items_padding_bottom
        )
        this._horizontal_menu_title_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_margin,
            this._horizontal_menu_title_items_margin
        )
        this._horizontal_menu_title_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_margin_left,
            this._horizontal_menu_title_items_margin_left
        )
        this._horizontal_menu_title_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_margin_right,
            this._horizontal_menu_title_items_margin_right
        )
        this._horizontal_menu_title_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_margin_top,
            this._horizontal_menu_title_items_margin_top
        )
        this._horizontal_menu_title_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_margin_bottom,
            this._horizontal_menu_title_items_margin_bottom
        )
        this._horizontal_menu_title_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_background_color,
            this._horizontal_menu_title_items_background_color
        )
        this._horizontal_menu_title_items_background_drawable = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_background_drawable,
            this._horizontal_menu_title_items_background_drawable
        )
        this._horizontal_menu_title_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_text_color,
            this._horizontal_menu_title_items_text_color
        )
        this._horizontal_menu_title_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_text_size,
            this._horizontal_menu_title_items_text_size
        )
        this._horizontal_menu_title_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_text_is_capitalized,
            this._horizontal_menu_title_items_text_is_capitalized
        )
        this._horizontal_menu_title_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_text_is_bold,
            this._horizontal_menu_title_items_text_is_bold
        )
        this._horizontal_menu_title_items_divider_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_divider_visible,
            this._horizontal_menu_title_items_divider_visible
        )
        this._horizontal_menu_title_items_divider_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_divider_thickness,
            this._horizontal_menu_title_items_divider_thickness
        )
        this._horizontal_menu_title_items_divider_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_title_items_divider_color,
            this._horizontal_menu_title_items_divider_color
        )
        //endregion get/set horizontal_menu_title values

        //region get/set horizontal_menu_child values
        this._horizontal_menu_child_items_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_width,
            this._horizontal_menu_child_items_width
        )
        this._horizontal_menu_child_items_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_height,
            this._horizontal_menu_child_items_height
        )
        this._horizontal_menu_child_items_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_padding,
            this._horizontal_menu_child_items_padding
        )
        this._horizontal_menu_child_items_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_padding_left,
            this._horizontal_menu_child_items_padding_left
        )
        this._horizontal_menu_child_items_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_padding_right,
            this._horizontal_menu_child_items_padding_right
        )
        this._horizontal_menu_child_items_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_padding_top,
            this._horizontal_menu_child_items_padding_top
        )
        this._horizontal_menu_child_items_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_padding_bottom,
            this._horizontal_menu_child_items_padding_bottom
        )
        this._horizontal_menu_child_items_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_margin,
            this._horizontal_menu_child_items_margin
        )
        this._horizontal_menu_child_items_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_margin_left,
            this._horizontal_menu_child_items_margin_left
        )
        this._horizontal_menu_child_items_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_margin_right,
            this._horizontal_menu_child_items_margin_right
        )
        this._horizontal_menu_child_items_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_margin_top,
            this._horizontal_menu_child_items_margin_top
        )
        this._horizontal_menu_child_items_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_margin_bottom,
            this._horizontal_menu_child_items_margin_bottom
        )
        this._horizontal_menu_child_items_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_background_color,
            this._horizontal_menu_child_items_background_color
        )
        this._horizontal_menu_child_items_active_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_background_color,
            this._horizontal_menu_child_items_active_background_color
        )
        this._horizontal_menu_child_items_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_text_color,
            this._horizontal_menu_child_items_text_color
        )
        this._horizontal_menu_child_items_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_text_size,
            this._horizontal_menu_child_items_text_size
        )
        this._horizontal_menu_child_items_text_is_capitalized = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_text_is_capitalized,
            this._horizontal_menu_child_items_text_is_capitalized
        )
        this._horizontal_menu_child_items_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_text_is_bold,
            this._horizontal_menu_child_items_text_is_bold
        )
        this._horizontal_menu_child_items_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_text_is_visible,
            this._horizontal_menu_child_items_text_is_visible
        )
        this._horizontal_menu_child_items_indicator_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_indicator_is_visible,
            this._horizontal_menu_child_items_indicator_is_visible
        )
        this._horizontal_menu_child_items_indicator_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_indicator_thickness,
            this._horizontal_menu_child_items_indicator_thickness
        )
        this._horizontal_menu_child_items_indicator_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_indicator_color,
            this._horizontal_menu_child_items_indicator_color
        )
        this._horizontal_menu_child_items_indicator_position = typedArray.getInt(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_indicator_position,
            this._horizontal_menu_child_items_indicator_position
        )
        this._horizontal_menu_child_items_active_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_padding,
            this._horizontal_menu_child_items_active_padding
        )
        this._horizontal_menu_child_items_active_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_padding_left,
            this._horizontal_menu_child_items_active_padding_left
        )
        this._horizontal_menu_child_items_active_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_padding_right,
            this._horizontal_menu_child_items_active_padding_right
        )
        this._horizontal_menu_child_items_active_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_padding_top,
            this._horizontal_menu_child_items_active_padding_top
        )
        this._horizontal_menu_child_items_active_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_padding_bottom,
            this._horizontal_menu_child_items_active_padding_bottom
        )
        this._horizontal_menu_child_items_active_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_margin,
            this._horizontal_menu_child_items_active_margin
        )
        this._horizontal_menu_child_items_active_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_margin_left,
            this._horizontal_menu_child_items_active_margin_left
        )
        this._horizontal_menu_child_items_active_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_margin_right,
            this._horizontal_menu_child_items_active_margin_right
        )
        this._horizontal_menu_child_items_active_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_margin_top,
            this._horizontal_menu_child_items_active_margin_top
        )
        this._horizontal_menu_child_items_active_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_margin_bottom,
            this._horizontal_menu_child_items_active_margin_bottom
        )
        this._horizontal_menu_child_items_active_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_text_color,
            this._horizontal_menu_child_items_active_text_color
        )
        this._horizontal_menu_child_items_active_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_text_size,
            this._horizontal_menu_child_items_active_text_size
        )
        this._horizontal_menu_child_items_active_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_text_is_bold,
            this._horizontal_menu_child_items_active_text_is_bold
        )
        this._horizontal_menu_child_items_icon_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_width,
            this._horizontal_menu_child_items_icon_width
        )
        this._horizontal_menu_child_items_icon_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_height,
            this._horizontal_menu_child_items_icon_height
        )
        this._horizontal_menu_child_items_icon_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_padding,
            this._horizontal_menu_child_items_icon_padding
        )
        this._horizontal_menu_child_items_icon_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_padding_left,
            this._horizontal_menu_child_items_icon_padding_left
        )
        this._horizontal_menu_child_items_icon_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_padding_right,
            this._horizontal_menu_child_items_icon_padding_right
        )
        this._horizontal_menu_child_items_icon_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_padding_top,
            this._horizontal_menu_child_items_icon_padding_top
        )
        this._horizontal_menu_child_items_icon_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_padding_bottom,
            this._horizontal_menu_child_items_icon_padding_bottom
        )
        this._horizontal_menu_child_items_icon_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_margin,
            this._horizontal_menu_child_items_icon_margin
        )
        this._horizontal_menu_child_items_icon_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_margin_left,
            this._horizontal_menu_child_items_icon_margin_left
        )
        this._horizontal_menu_child_items_icon_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_margin_right,
            this._horizontal_menu_child_items_icon_margin_right
        )
        this._horizontal_menu_child_items_icon_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_margin_top,
            this._horizontal_menu_child_items_icon_margin_top
        )
        this._horizontal_menu_child_items_icon_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_margin_bottom,
            this._horizontal_menu_child_items_icon_margin_bottom
        )
        this._horizontal_menu_child_items_icon_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_tint_color,
            this._horizontal_menu_child_items_icon_tint_color
        )
        this._horizontal_menu_child_items_icon_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_is_visible,
            this._horizontal_menu_child_items_icon_is_visible
        )
        this._horizontal_menu_child_items_icon_active_tint_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_icon_active_tint_color,
            this._horizontal_menu_child_items_icon_active_tint_color
        )
        this._horizontal_menu_child_items_badge_width = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_width,
            this._horizontal_menu_child_items_badge_width
        )
        this._horizontal_menu_child_items_badge_height = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_height,
            this._horizontal_menu_child_items_badge_height
        )
        this._horizontal_menu_child_items_badge_padding = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_padding,
            this._horizontal_menu_child_items_badge_padding
        )
        this._horizontal_menu_child_items_badge_padding_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_padding_left,
            this._horizontal_menu_child_items_badge_padding_left
        )
        this._horizontal_menu_child_items_badge_padding_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_padding_right,
            this._horizontal_menu_child_items_badge_padding_right
        )
        this._horizontal_menu_child_items_badge_padding_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_padding_top,
            this._horizontal_menu_child_items_badge_padding_top
        )
        this._horizontal_menu_child_items_badge_padding_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_padding_bottom,
            this._horizontal_menu_child_items_badge_padding_bottom
        )
        this._horizontal_menu_child_items_badge_margin = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_margin,
            this._horizontal_menu_child_items_badge_margin
        )
        this._horizontal_menu_child_items_badge_margin_left = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_margin_left,
            this._horizontal_menu_child_items_badge_margin_left
        )
        this._horizontal_menu_child_items_badge_margin_right = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_margin_right,
            this._horizontal_menu_child_items_badge_margin_right
        )
        this._horizontal_menu_child_items_badge_margin_top = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_margin_top,
            this._horizontal_menu_child_items_badge_margin_top
        )
        this._horizontal_menu_child_items_badge_margin_bottom = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_margin_bottom,
            this._horizontal_menu_child_items_badge_margin_bottom
        )
        this._horizontal_menu_child_items_badge_background_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_background_color,
            this._horizontal_menu_child_items_badge_background_color
        )
        this._horizontal_menu_child_items_badge_border_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_border_color,
            this._horizontal_menu_child_items_badge_border_color
        )
        this._horizontal_menu_child_items_badge_border_thickness = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_border_thickness,
            this._horizontal_menu_child_items_badge_border_thickness
        )
        this._horizontal_menu_child_items_badge_text_color = typedArray.getColor(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_text_color,
            this._horizontal_menu_child_items_badge_text_color
        )
        this._horizontal_menu_child_items_badge_text_size = typedArray.getDimensionPixelSize(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_text_size,
            this._horizontal_menu_child_items_badge_text_size
        )
        this._horizontal_menu_child_items_badge_text_is_bold = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_text_is_bold,
            this._horizontal_menu_child_items_badge_text_is_bold
        )
        this._horizontal_menu_child_items_badge_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_is_visible,
            this._horizontal_menu_child_items_badge_is_visible
        )
        this._horizontal_menu_child_items_badge_is_cleared_on_active = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_is_cleared_on_active,
            this._horizontal_menu_child_items_badge_is_cleared_on_active
        )
        this._horizontal_menu_child_items_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_background_drawable
        )
        this._horizontal_menu_child_items_active_background_drawable = typedArray.getDrawable(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_active_background_drawable
        )
        this._horizontal_menu_child_items_badge_text_is_visible = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_badge_text_is_visible,
            this._horizontal_menu_child_items_badge_text_is_visible
        )
        this._horizontal_menu_child_items_when_active_show_parent_active_state = typedArray.getBoolean(
            R.styleable.BHAppsMenus_Menu_Declarables_Horizontal_HorizontalMenu_horizontal_menu_child_items_when_active_show_parent_active_state,
            this._horizontal_menu_child_items_when_active_show_parent_active_state
        )
        //endregion get/set horizontal_menu_child values

    }
    //endregion setTypeArray
    
    public fun refresh() {
        initializeHorizontalMenu()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        post {
        }
    }

    fun setHorizontalMenuItemsUISettings() {

        horizontalMenuItemsUISettings["horizontal_menu_is_responsive_menu"] = currentScreenOrientationIsPortrait

        if(this@HorizontalMenu.horizontal_menu_height > 0){
            if(this@HorizontalMenu.horizontal_menu_border_thickness > 0 && this@HorizontalMenu.horizontal_menu_border_is_visible){
                horizontalMenuItemsUISettings["horizontal_menu_height"] = this@HorizontalMenu.horizontal_menu_height + this@HorizontalMenu.horizontal_menu_border_thickness
            }else{
                horizontalMenuItemsUISettings["horizontal_menu_height"] = this@HorizontalMenu.horizontal_menu_height
            }
        }else{

            if(this@HorizontalMenu.horizontal_menu_border_thickness > 0 && this@HorizontalMenu.horizontal_menu_border_is_visible){
                if(this@HorizontalMenu.horizontal_menu_show_icons_only) {
                    horizontalMenuItemsUISettings["horizontal_menu_height"] =
                        getIntToDp(context, DEFAULT_HEIGHT) + this@HorizontalMenu.horizontal_menu_border_thickness
                }else{
                    horizontalMenuItemsUISettings["horizontal_menu_height"] =
                        getIntToDp(context, DEFAULT_HEIGHT) + this@HorizontalMenu.horizontal_menu_border_thickness
                }
            }else{
                if(this@HorizontalMenu.horizontal_menu_show_icons_only) {
                    horizontalMenuItemsUISettings["horizontal_menu_height"] = getIntToDp(context, DEFAULT_HEIGHT)
                }else{
                    horizontalMenuItemsUISettings["horizontal_menu_height"] = getIntToDp(context, DEFAULT_HEIGHT) + this@HorizontalMenu.horizontal_menu_border_thickness
                }
            }
        }

        horizontalMenuItemsUISettings["horizontal_menu_active_state_on_left"] = this@HorizontalMenu.horizontal_menu_active_state_on_left
        horizontalMenuItemsUISettings["horizontal_menu_show_icons"] = this@HorizontalMenu.horizontal_menu_show_icons
        horizontalMenuItemsUISettings["horizontal_menu_show_parent_icons"] = this@HorizontalMenu.horizontal_menu_show_parent_icons
        horizontalMenuItemsUISettings["horizontal_menu_show_child_icons"] = this@HorizontalMenu.horizontal_menu_show_child_icons
        horizontalMenuItemsUISettings["horizontal_menu_show_parent_divider"] = this@HorizontalMenu.horizontal_menu_show_parent_divider
        horizontalMenuItemsUISettings["horizontal_menu_show_icons_only"] = this@HorizontalMenu.horizontal_menu_show_icons_only

        horizontalMenuItemsUISettings["horizontal_menu_divider_color"] = this@HorizontalMenu.horizontal_menu_divider_color
        horizontalMenuItemsUISettings["horizontal_menu_divider_width"] = this@HorizontalMenu.horizontal_menu_divider_width
        horizontalMenuItemsUISettings["horizontal_menu_divider_height"] = this@HorizontalMenu.horizontal_menu_divider_height
        horizontalMenuItemsUISettings["horizontal_menu_divider_padding"] = this@HorizontalMenu.horizontal_menu_divider_padding
        horizontalMenuItemsUISettings["horizontal_menu_divider_padding_left"] = this@HorizontalMenu.horizontal_menu_divider_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_divider_padding_right"] = this@HorizontalMenu.horizontal_menu_divider_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_divider_padding_top"] = this@HorizontalMenu.horizontal_menu_divider_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_divider_padding_bottom"] = this@HorizontalMenu.horizontal_menu_divider_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_divider_margin"] = this@HorizontalMenu.horizontal_menu_divider_margin
        horizontalMenuItemsUISettings["horizontal_menu_divider_margin_left"] = this@HorizontalMenu.horizontal_menu_divider_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_divider_margin_right"] = this@HorizontalMenu.horizontal_menu_divider_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_divider_margin_top"] = this@HorizontalMenu.horizontal_menu_divider_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_divider_margin_bottom"] = this@HorizontalMenu.horizontal_menu_divider_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_divider_is_visible"] = this@HorizontalMenu.horizontal_menu_divider_is_visible

        horizontalMenuItemsUISettings["horizontal_menu_logo_width"] = this@HorizontalMenu.horizontal_menu_logo_width
        horizontalMenuItemsUISettings["horizontal_menu_logo_height"] = this@HorizontalMenu.horizontal_menu_logo_height
        horizontalMenuItemsUISettings["horizontal_menu_logo_padding"] = this@HorizontalMenu.horizontal_menu_logo_padding
        horizontalMenuItemsUISettings["horizontal_menu_logo_padding_left"] = this@HorizontalMenu.horizontal_menu_logo_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_logo_padding_right"] = this@HorizontalMenu.horizontal_menu_logo_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_logo_padding_top"] = this@HorizontalMenu.horizontal_menu_logo_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_logo_padding_bottom"] = this@HorizontalMenu.horizontal_menu_logo_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_logo_margin"] = this@HorizontalMenu.horizontal_menu_logo_margin
        horizontalMenuItemsUISettings["horizontal_menu_logo_margin_left"] = this@HorizontalMenu.horizontal_menu_logo_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_logo_margin_right"] = this@HorizontalMenu.horizontal_menu_logo_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_logo_margin_top"] = this@HorizontalMenu.horizontal_menu_logo_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_logo_margin_bottom"] = this@HorizontalMenu.horizontal_menu_logo_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_logo_drawable"] = this@HorizontalMenu.horizontal_menu_logo_drawable
        horizontalMenuItemsUISettings["horizontal_menu_logo_is_visible"] = this@HorizontalMenu.horizontal_menu_logo_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_logo_position"] = this@HorizontalMenu.horizontal_menu_logo_position
        
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_width"] = this@HorizontalMenu.horizontal_menu_parent_items_width
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_height"] = this@HorizontalMenu.horizontal_menu_parent_items_height
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding"] = this@HorizontalMenu.horizontal_menu_parent_items_padding
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_left"] = this@HorizontalMenu.horizontal_menu_parent_items_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_right"] = this@HorizontalMenu.horizontal_menu_parent_items_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_top"] = this@HorizontalMenu.horizontal_menu_parent_items_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_padding_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin"] = this@HorizontalMenu.horizontal_menu_parent_items_margin
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_left"] = this@HorizontalMenu.horizontal_menu_parent_items_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_right"] = this@HorizontalMenu.horizontal_menu_parent_items_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_top"] = this@HorizontalMenu.horizontal_menu_parent_items_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_margin_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_color"] = this@HorizontalMenu.horizontal_menu_parent_items_background_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_color"] = this@HorizontalMenu.horizontal_menu_parent_items_active_background_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_background_drawable"] = this@HorizontalMenu.horizontal_menu_parent_items_background_drawable
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_background_drawable"] = this@HorizontalMenu.horizontal_menu_parent_items_active_background_drawable
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_color"] = this@HorizontalMenu.horizontal_menu_parent_items_text_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_size"] = this@HorizontalMenu.horizontal_menu_parent_items_text_size
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_capitalized"] = this@HorizontalMenu.horizontal_menu_parent_items_text_is_capitalized
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_bold"] = this@HorizontalMenu.horizontal_menu_parent_items_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_text_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_text_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_indicator_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_thickness"] = this@HorizontalMenu.horizontal_menu_parent_items_indicator_thickness
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_color"] = this@HorizontalMenu.horizontal_menu_parent_items_indicator_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_indicator_position"] = this@HorizontalMenu.horizontal_menu_parent_items_indicator_position
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_padding"] = this@HorizontalMenu.horizontal_menu_parent_items_active_padding
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_padding_left"] = this@HorizontalMenu.horizontal_menu_parent_items_active_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_padding_right"] = this@HorizontalMenu.horizontal_menu_parent_items_active_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_padding_top"] = this@HorizontalMenu.horizontal_menu_parent_items_active_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_padding_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_active_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_margin"] = this@HorizontalMenu.horizontal_menu_parent_items_active_margin
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_margin_left"] = this@HorizontalMenu.horizontal_menu_parent_items_active_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_margin_right"] = this@HorizontalMenu.horizontal_menu_parent_items_active_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_margin_top"] = this@HorizontalMenu.horizontal_menu_parent_items_active_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_margin_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_active_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_color"] = this@HorizontalMenu.horizontal_menu_parent_items_active_text_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_size"] = this@HorizontalMenu.horizontal_menu_parent_items_active_text_size
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_text_is_bold"] = this@HorizontalMenu.horizontal_menu_parent_items_active_text_is_bold

        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_width"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_width
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_height"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_height
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_padding
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_left"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_right"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_top"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_padding_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_margin
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_left"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_right"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_top"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_margin_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_tint_color"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_tint_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_icon_active_tint_color"] = this@HorizontalMenu.horizontal_menu_parent_items_icon_active_tint_color
        
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_width"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_width
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_height"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_height
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_padding
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_left"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_right"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_top"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_padding_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_margin
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_left"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_right"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_top"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_margin_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_background_color"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_background_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_color"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_border_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_border_thickness"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_border_thickness
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_color"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_text_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_size"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_text_size
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_bold"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_text_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_text_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_badge_is_cleared_on_active"] = this@HorizontalMenu.horizontal_menu_parent_items_badge_is_cleared_on_active

        horizontalMenuItemsUISettings["horizontal_menu_collapse_type"] = this@HorizontalMenu.horizontal_menu_collapse_type
        horizontalMenuItemsUISettings["horizontal_menu_collapse_others"] = this@HorizontalMenu.horizontal_menu_collapse_others
        horizontalMenuItemsUISettings["horizontal_menu_collapse_expand_delay"] = this@HorizontalMenu.horizontal_menu_collapse_expand_delay
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_rotate"] = this@HorizontalMenu.horizontal_menu_collapse_icon_rotate
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_rotate_duration"] = this@HorizontalMenu.horizontal_menu_collapse_icon_rotate_duration
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_width"] = this@HorizontalMenu.horizontal_menu_collapse_icon_width
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_height"] = this@HorizontalMenu.horizontal_menu_collapse_icon_height
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding"] = this@HorizontalMenu.horizontal_menu_collapse_icon_padding
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_left"] = this@HorizontalMenu.horizontal_menu_collapse_icon_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_right"] = this@HorizontalMenu.horizontal_menu_collapse_icon_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_top"] = this@HorizontalMenu.horizontal_menu_collapse_icon_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_padding_bottom"] = this@HorizontalMenu.horizontal_menu_collapse_icon_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin"] = this@HorizontalMenu.horizontal_menu_collapse_icon_margin
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_left"] = this@HorizontalMenu.horizontal_menu_collapse_icon_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_right"] = this@HorizontalMenu.horizontal_menu_collapse_icon_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_top"] = this@HorizontalMenu.horizontal_menu_collapse_icon_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_margin_bottom"] = this@HorizontalMenu.horizontal_menu_collapse_icon_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_color"] = this@HorizontalMenu.horizontal_menu_collapse_icon_color
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_drawable"] = this@HorizontalMenu.horizontal_menu_collapse_icon_drawable
        horizontalMenuItemsUISettings["horizontal_menu_collapse_icon_is_visible"] = this@HorizontalMenu.horizontal_menu_collapse_icon_is_visible

        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_width"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_width
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_height"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_height
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_padding
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_left"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_right"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_top"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_padding_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_margin
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_left"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_right"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_top"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_margin_bottom"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_color"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_color
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_drawable"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_drawable
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_visible"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_navigation_icon_is_hidden_when_active"] = this@HorizontalMenu.horizontal_menu_parent_items_navigation_icon_is_hidden_when_active
        horizontalMenuItemsUISettings["horizontal_menu_parent_items_active_navigation_icon_color"] = this@HorizontalMenu.horizontal_menu_parent_items_active_navigation_icon_color

        horizontalMenuItemsUISettings["horizontal_menu_title_items_width"] = this@HorizontalMenu.horizontal_menu_title_items_width
        horizontalMenuItemsUISettings["horizontal_menu_title_items_height"] = this@HorizontalMenu.horizontal_menu_title_items_height
        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding"] = this@HorizontalMenu.horizontal_menu_title_items_padding
        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_left"] = this@HorizontalMenu.horizontal_menu_title_items_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_right"] = this@HorizontalMenu.horizontal_menu_title_items_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_top"] = this@HorizontalMenu.horizontal_menu_title_items_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_title_items_padding_bottom"] = this@HorizontalMenu.horizontal_menu_title_items_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin"] = this@HorizontalMenu.horizontal_menu_title_items_margin
        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_left"] = this@HorizontalMenu.horizontal_menu_title_items_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_right"] = this@HorizontalMenu.horizontal_menu_title_items_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_top"] = this@HorizontalMenu.horizontal_menu_title_items_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_title_items_margin_bottom"] = this@HorizontalMenu.horizontal_menu_title_items_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_title_items_background_color"] = this@HorizontalMenu.horizontal_menu_title_items_background_color
        horizontalMenuItemsUISettings["horizontal_menu_title_items_background_drawable"] = this@HorizontalMenu.horizontal_menu_title_items_background_drawable
        horizontalMenuItemsUISettings["horizontal_menu_title_items_text_color"] = this@HorizontalMenu.horizontal_menu_title_items_text_color
        horizontalMenuItemsUISettings["horizontal_menu_title_items_text_size"] = this@HorizontalMenu.horizontal_menu_title_items_text_size
        horizontalMenuItemsUISettings["horizontal_menu_title_items_text_is_capitalized"] = this@HorizontalMenu.horizontal_menu_title_items_text_is_capitalized
        horizontalMenuItemsUISettings["horizontal_menu_title_items_text_is_bold"] = this@HorizontalMenu.horizontal_menu_title_items_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_visible"] = this@HorizontalMenu.horizontal_menu_title_items_divider_visible
        horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_thickness"] = this@HorizontalMenu.horizontal_menu_title_items_divider_thickness
        horizontalMenuItemsUISettings["horizontal_menu_title_items_divider_color"] = this@HorizontalMenu.horizontal_menu_title_items_divider_color

        horizontalMenuItemsUISettings["horizontal_menu_child_items_width"] = this@HorizontalMenu.horizontal_menu_child_items_width
        horizontalMenuItemsUISettings["horizontal_menu_child_items_height"] = this@HorizontalMenu.horizontal_menu_child_items_height
        horizontalMenuItemsUISettings["horizontal_menu_child_items_padding"] = this@HorizontalMenu.horizontal_menu_child_items_padding
        horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_left"] = this@HorizontalMenu.horizontal_menu_child_items_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_right"] = this@HorizontalMenu.horizontal_menu_child_items_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_top"] = this@HorizontalMenu.horizontal_menu_child_items_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_padding_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_margin"] = this@HorizontalMenu.horizontal_menu_child_items_margin
        horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_left"] = this@HorizontalMenu.horizontal_menu_child_items_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_right"] = this@HorizontalMenu.horizontal_menu_child_items_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_top"] = this@HorizontalMenu.horizontal_menu_child_items_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_margin_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_background_color"] = this@HorizontalMenu.horizontal_menu_child_items_background_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_color"] = this@HorizontalMenu.horizontal_menu_child_items_active_background_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_color"] = this@HorizontalMenu.horizontal_menu_child_items_text_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_size"] = this@HorizontalMenu.horizontal_menu_child_items_text_size
        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_capitalized"] = this@HorizontalMenu.horizontal_menu_child_items_text_is_capitalized
        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_bold"] = this@HorizontalMenu.horizontal_menu_child_items_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_child_items_text_is_visible"] = this@HorizontalMenu.horizontal_menu_child_items_text_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_is_visible"] = this@HorizontalMenu.horizontal_menu_child_items_indicator_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_thickness"] = this@HorizontalMenu.horizontal_menu_child_items_indicator_thickness
        horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_color"] = this@HorizontalMenu.horizontal_menu_child_items_indicator_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_indicator_position"] = this@HorizontalMenu.horizontal_menu_child_items_indicator_position
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_padding"] = this@HorizontalMenu.horizontal_menu_child_items_active_padding
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_padding_left"] = this@HorizontalMenu.horizontal_menu_child_items_active_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_padding_right"] = this@HorizontalMenu.horizontal_menu_child_items_active_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_padding_top"] = this@HorizontalMenu.horizontal_menu_child_items_active_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_padding_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_active_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_margin"] = this@HorizontalMenu.horizontal_menu_child_items_active_margin
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_margin_left"] = this@HorizontalMenu.horizontal_menu_child_items_active_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_margin_right"] = this@HorizontalMenu.horizontal_menu_child_items_active_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_margin_top"] = this@HorizontalMenu.horizontal_menu_child_items_active_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_margin_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_active_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_color"] = this@HorizontalMenu.horizontal_menu_child_items_active_text_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_size"] = this@HorizontalMenu.horizontal_menu_child_items_active_text_size
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_text_is_bold"] = this@HorizontalMenu.horizontal_menu_child_items_active_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_width"] = this@HorizontalMenu.horizontal_menu_child_items_icon_width
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_height"] = this@HorizontalMenu.horizontal_menu_child_items_icon_height
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding"] = this@HorizontalMenu.horizontal_menu_child_items_icon_padding
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_left"] = this@HorizontalMenu.horizontal_menu_child_items_icon_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_right"] = this@HorizontalMenu.horizontal_menu_child_items_icon_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_top"] = this@HorizontalMenu.horizontal_menu_child_items_icon_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_padding_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_icon_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin"] = this@HorizontalMenu.horizontal_menu_child_items_icon_margin
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_left"] = this@HorizontalMenu.horizontal_menu_child_items_icon_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_right"] = this@HorizontalMenu.horizontal_menu_child_items_icon_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_top"] = this@HorizontalMenu.horizontal_menu_child_items_icon_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_margin_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_icon_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_tint_color"] = this@HorizontalMenu.horizontal_menu_child_items_icon_tint_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_is_visible"] = this@HorizontalMenu.horizontal_menu_child_items_icon_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_child_items_icon_active_tint_color"] = this@HorizontalMenu.horizontal_menu_child_items_icon_active_tint_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_width"] = this@HorizontalMenu.horizontal_menu_child_items_badge_width
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_height"] = this@HorizontalMenu.horizontal_menu_child_items_badge_height
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding"] = this@HorizontalMenu.horizontal_menu_child_items_badge_padding
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_left"] = this@HorizontalMenu.horizontal_menu_child_items_badge_padding_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_right"] = this@HorizontalMenu.horizontal_menu_child_items_badge_padding_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_top"] = this@HorizontalMenu.horizontal_menu_child_items_badge_padding_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_padding_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_badge_padding_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin"] = this@HorizontalMenu.horizontal_menu_child_items_badge_margin
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_left"] = this@HorizontalMenu.horizontal_menu_child_items_badge_margin_left
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_right"] = this@HorizontalMenu.horizontal_menu_child_items_badge_margin_right
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_top"] = this@HorizontalMenu.horizontal_menu_child_items_badge_margin_top
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_margin_bottom"] = this@HorizontalMenu.horizontal_menu_child_items_badge_margin_bottom
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_background_color"] = this@HorizontalMenu.horizontal_menu_child_items_badge_background_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_border_color"] = this@HorizontalMenu.horizontal_menu_child_items_badge_border_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_border_thickness"] = this@HorizontalMenu.horizontal_menu_child_items_badge_border_thickness
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_color"] = this@HorizontalMenu.horizontal_menu_child_items_badge_text_color
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_size"] = this@HorizontalMenu.horizontal_menu_child_items_badge_text_size
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_bold"] = this@HorizontalMenu.horizontal_menu_child_items_badge_text_is_bold
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_visible"] = this@HorizontalMenu.horizontal_menu_child_items_badge_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_is_cleared_on_active"] = this@HorizontalMenu.horizontal_menu_child_items_badge_is_cleared_on_active
        horizontalMenuItemsUISettings["horizontal_menu_child_items_background_drawable"] = this@HorizontalMenu.horizontal_menu_child_items_background_drawable
        horizontalMenuItemsUISettings["horizontal_menu_child_items_active_background_drawable"] = this@HorizontalMenu.horizontal_menu_child_items_active_background_drawable
        horizontalMenuItemsUISettings["horizontal_menu_child_items_badge_text_is_visible"] = this@HorizontalMenu.horizontal_menu_child_items_badge_text_is_visible
        horizontalMenuItemsUISettings["horizontal_menu_child_items_when_active_show_parent_active_state"] = this@HorizontalMenu.horizontal_menu_child_items_when_active_show_parent_active_state

    }

    fun buildHorizontalMenu(_horizontalMenuAdapter: HorizontalMenuAdapter) {
        horizontalMenuAdapter = _horizontalMenuAdapter
        initializeHorizontalMenu()
    }

    private fun initializeHorizontalMenu() {

        /*
        this@HorizontalMenu.setScreenOrientationListener(object : ScreenOrientationListener {
            override fun onChange() {
                //Log.e("HorizontalMenu", "HorizontalMenu.ScreenOrientationListener.OnChange: " + currentScreenOrientation)
            }
        })
        */

        if(this@HorizontalMenu.horizontal_menu_is_responsive_menu) {
            currentScreenOrientation = this@HorizontalMenu.resources.configuration.orientation

            if (this@HorizontalMenu.resources.configuration.orientation == 1) {
                currentScreenOrientationIsPortrait = true
            } else {
                currentScreenOrientationIsPortrait = false
            }

        }

        var setHeight = DEFAULT_HEIGHT
        var setResponsiveHeight = DEFAULT_HEIGHT
        var setBorderThckiness = 0
        if(this@HorizontalMenu.horizontal_menu_height > 0){

            if(this@HorizontalMenu.horizontal_menu_border_thickness > 0 && this@HorizontalMenu.horizontal_menu_border_is_visible){
                setBorderThckiness = this@HorizontalMenu.horizontal_menu_border_thickness
                bhappsMenusMenuHorizontalMenuLayout.layoutParams.height = this@HorizontalMenu.horizontal_menu_height + this@HorizontalMenu.horizontal_menu_border_thickness
                setHeight = this@HorizontalMenu.horizontal_menu_height + this@HorizontalMenu.horizontal_menu_border_thickness
            }else{
                bhappsMenusMenuHorizontalMenuLayout.layoutParams.height = this@HorizontalMenu.horizontal_menu_height
                setHeight = this@HorizontalMenu.horizontal_menu_height
            }

            bhappsMenusMenuHorizontalMenuWidthHolder.layoutParams.height = this@HorizontalMenu.horizontal_menu_height
            bhappsMenusMenuHorizontalMenuRecycleView.layoutParams.height = this@HorizontalMenu.horizontal_menu_height
            bhappsMenusMenuHorizontalMenuShimmerLayout.layoutParams.height = this@HorizontalMenu.horizontal_menu_height
        }else{

            var getIntToDp = getIntToDp(context, DEFAULT_HEIGHT)
            if(this@HorizontalMenu.horizontal_menu_show_icons_only) {
                getIntToDp = getIntToDp(context, DEFAULT_HEIGHT)
            }
            if(this@HorizontalMenu.horizontal_menu_border_thickness > 0 && this@HorizontalMenu.horizontal_menu_border_is_visible){
                setBorderThckiness = this@HorizontalMenu.horizontal_menu_border_thickness
                bhappsMenusMenuHorizontalMenuLayout.layoutParams.height = getIntToDp + this@HorizontalMenu.horizontal_menu_border_thickness
                setHeight = getIntToDp + this@HorizontalMenu.horizontal_menu_border_thickness

            }else{
                bhappsMenusMenuHorizontalMenuLayout.layoutParams.height = getIntToDp
                setHeight = getIntToDp
            }

            bhappsMenusMenuHorizontalMenuWidthHolder.layoutParams.height = getIntToDp
            bhappsMenusMenuHorizontalMenuRecycleView.layoutParams.height = getIntToDp
            bhappsMenusMenuHorizontalMenuShimmerLayout.layoutParams.height = getIntToDp

        }

        if(this@HorizontalMenu.horizontal_menu_padding > 0){
            //setPadding(int left, int top, int right, int bottom)
            bhappsMenusMenuHorizontalMenuLayout.setPadding(
                this@HorizontalMenu.horizontal_menu_padding,
                this@HorizontalMenu.horizontal_menu_padding,
                this@HorizontalMenu.horizontal_menu_padding,
                this@HorizontalMenu.horizontal_menu_padding
            )
        }else{
            //setPadding(int left, int top, int right, int bottom)
            bhappsMenusMenuHorizontalMenuLayout.setPadding(
                this@HorizontalMenu.horizontal_menu_padding_left,
                this@HorizontalMenu.horizontal_menu_padding_top,
                this@HorizontalMenu.horizontal_menu_padding_right,
                this@HorizontalMenu.horizontal_menu_padding_bottom
            )
        }

        if(this@HorizontalMenu.horizontal_menu_margin > 0){
            //setMargins(int left, int top, int right, int bottom)
            var layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(
                this@HorizontalMenu.horizontal_menu_margin,
                this@HorizontalMenu.horizontal_menu_margin,
                this@HorizontalMenu.horizontal_menu_margin,
                this@HorizontalMenu.horizontal_menu_margin
            )
            bhappsMenusMenuHorizontalMenuLayout.layoutParams = layoutParams
        }else{
            //setMargins(int left, int top, int right, int bottom)
            var layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(
                this@HorizontalMenu.horizontal_menu_margin_left,
                this@HorizontalMenu.horizontal_menu_margin_top,
                this@HorizontalMenu.horizontal_menu_margin_right,
                this@HorizontalMenu.horizontal_menu_margin_bottom
            )
            bhappsMenusMenuHorizontalMenuLayout.layoutParams = layoutParams
        }

        if(currentScreenOrientationIsPortrait){
            bhappsMenusMenuHorizontalMenuLayout.layoutParams.height = setResponsiveHeight + setBorderThckiness
            bhappsMenusMenuHorizontalMenuWidthHolder.layoutParams.height = setResponsiveHeight
            bhappsMenusMenuHorizontalMenuRecycleView.layoutParams.height = setResponsiveHeight
            bhappsMenusMenuHorizontalMenuShimmerLayout.layoutParams.height = setResponsiveHeight
        }

        if(this@HorizontalMenu.horizontal_menu_alignment_position !=null) {
            var layoutParamsForRecycleView = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                setHeight
            )
            if (this@HorizontalMenu.horizontal_menu_alignment_position == 1) {
                //left align
                layoutParamsForRecycleView.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.startToEnd = bhappsMenusMenuHorizontalMenuLogo.id
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                bhappsMenusMenuHorizontalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            } else if (this@HorizontalMenu.horizontal_menu_alignment_position == 2) {
                //center align
                layoutParamsForRecycleView.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.startToEnd = bhappsMenusMenuHorizontalMenuLogo.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuHorizontalMenuLayout.id
                bhappsMenusMenuHorizontalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            } else if (this@HorizontalMenu.horizontal_menu_alignment_position == 3) {
                //right align
                layoutParamsForRecycleView.startToEnd = bhappsMenusMenuHorizontalMenuLogo.id
                layoutParamsForRecycleView.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.endToEnd = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                bhappsMenusMenuHorizontalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            }else{
                //default align (left)
                layoutParamsForRecycleView.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                layoutParamsForRecycleView.startToEnd = bhappsMenusMenuHorizontalMenuLogo.id
                layoutParamsForRecycleView.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                bhappsMenusMenuHorizontalMenuRecycleView.layoutParams = layoutParamsForRecycleView
            }
        }

        if(this@HorizontalMenu.horizontal_menu_show_shimmer_animation){
            bhappsMenusMenuHorizontalMenuShimmerLayout.visibility = View.VISIBLE
            bhappsMenusMenuHorizontalMenuShimmerLayout.startShimmer()
            if(this@HorizontalMenu.horizontal_menu_show_shimmer_animation_duration != 0){
                Handler().postDelayed({
                    bhappsMenusMenuHorizontalMenuShimmerLayout.visibility = View.GONE
                    bhappsMenusMenuHorizontalMenuShimmerLayout.stopShimmer()
                    bhappsMenusMenuHorizontalMenuRecycleView.visibility = View.VISIBLE
                }, this@HorizontalMenu.horizontal_menu_show_shimmer_animation_duration.toLong())
            }else{
                Handler().postDelayed({
                    bhappsMenusMenuHorizontalMenuShimmerLayout.visibility = View.GONE
                    bhappsMenusMenuHorizontalMenuShimmerLayout.stopShimmer()
                    bhappsMenusMenuHorizontalMenuRecycleView.visibility = View.VISIBLE
                }, 1200)
            }
        }else{
            bhappsMenusMenuHorizontalMenuShimmerLayout.visibility = View.GONE
            bhappsMenusMenuHorizontalMenuShimmerLayout.stopShimmer()
            bhappsMenusMenuHorizontalMenuRecycleView.visibility = View.VISIBLE
        }
        
        if(!this@HorizontalMenu.horizontal_menu_logo_is_visible){
            bhappsMenusMenuHorizontalMenuLogo.visibility = View.GONE
        }else{
            if(this@HorizontalMenu.horizontal_menu_logo_drawable !=null){
                bhappsMenusMenuHorizontalMenuLogo.visibility = View.VISIBLE
                bhappsMenusMenuHorizontalMenuLogo.setImageDrawable(this@HorizontalMenu.horizontal_menu_logo_drawable)
                if(this@HorizontalMenu.horizontal_menu_logo_padding > 0){
                    //setPadding(int left, int top, int right, int bottom)
                    bhappsMenusMenuHorizontalMenuLogo.setPadding(
                        this@HorizontalMenu.horizontal_menu_logo_padding,
                        this@HorizontalMenu.horizontal_menu_logo_padding,
                        this@HorizontalMenu.horizontal_menu_logo_padding,
                        this@HorizontalMenu.horizontal_menu_logo_padding
                    )
                }else{
                    //setPadding(int left, int top, int right, int bottom)
                    bhappsMenusMenuHorizontalMenuLogo.setPadding(
                        this@HorizontalMenu.horizontal_menu_logo_padding_left,
                        this@HorizontalMenu.horizontal_menu_logo_padding_top,
                        this@HorizontalMenu.horizontal_menu_logo_padding_right,
                        this@HorizontalMenu.horizontal_menu_logo_padding_bottom
                    )
                }

                var bhappsMenusMenuHorizontalMenuLogoLayoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                if(this@HorizontalMenu.horizontal_menu_logo_margin > 0){
                    bhappsMenusMenuHorizontalMenuLogoLayoutParams.setMargins(
                        this@HorizontalMenu.horizontal_menu_logo_margin,
                        this@HorizontalMenu.horizontal_menu_logo_margin,
                        this@HorizontalMenu.horizontal_menu_logo_margin,
                        this@HorizontalMenu.horizontal_menu_logo_margin
                    )
                }else{
                    bhappsMenusMenuHorizontalMenuLogoLayoutParams.setMargins(
                        this@HorizontalMenu.horizontal_menu_logo_margin_left,
                        this@HorizontalMenu.horizontal_menu_logo_margin_top,
                        this@HorizontalMenu.horizontal_menu_logo_margin_right,
                        this@HorizontalMenu.horizontal_menu_logo_margin_bottom
                    )
                }

                if(this@HorizontalMenu.horizontal_menu_logo_position !=null){
                    if(this@HorizontalMenu.horizontal_menu_logo_position == 1){
                        //left align
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.startToStart = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                    }else if(this@HorizontalMenu.horizontal_menu_logo_position == 2){
                        //right align
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.endToEnd = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id

                    }else if(this@HorizontalMenu.horizontal_menu_logo_position == 3){
                        //center align
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.startToStart = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.endToEnd = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                    }else {
                        //default (left) align
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.startToStart = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.topToTop = bhappsMenusMenuHorizontalMenuLayout.id
                        bhappsMenusMenuHorizontalMenuLogoLayoutParams.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id
                    }
                }

                if(this@HorizontalMenu.horizontal_menu_logo_width !=null){
                    bhappsMenusMenuHorizontalMenuLogoLayoutParams.width = this@HorizontalMenu.horizontal_menu_logo_width
                }

                if(this@HorizontalMenu.horizontal_menu_logo_height !=null) {
                    bhappsMenusMenuHorizontalMenuLogoLayoutParams.height = this@HorizontalMenu.horizontal_menu_logo_height
                }

                bhappsMenusMenuHorizontalMenuLogo.layoutParams = bhappsMenusMenuHorizontalMenuLogoLayoutParams
                
            }else{
                bhappsMenusMenuHorizontalMenuLogo.visibility = View.GONE
            }
        }

        if(!this@HorizontalMenu.horizontal_menu_border_is_visible){
            bhappsMenusMenuHorizontalMenuBorder.visibility = View.GONE
        }else{
            if(this@HorizontalMenu.horizontal_menu_border_color != 0){
                bhappsMenusMenuHorizontalMenuBorder.setBackgroundColor(this@HorizontalMenu.horizontal_menu_border_color)
            }

            if(this@HorizontalMenu.horizontal_menu_border_thickness > 0){
                bhappsMenusMenuHorizontalMenuBorder.layoutParams.height = this@HorizontalMenu.horizontal_menu_border_thickness
                if(this@HorizontalMenu.horizontal_menu_border_position !=null) {
                    if(this@HorizontalMenu.horizontal_menu_border_position == 2) {

                        var layoutParamsForBorderPosition = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            this@HorizontalMenu.horizontal_menu_border_thickness
                        )

                        layoutParamsForBorderPosition.startToStart = bhappsMenusMenuHorizontalMenuLayout.id
                        layoutParamsForBorderPosition.endToEnd = bhappsMenusMenuHorizontalMenuLayout.id
                        layoutParamsForBorderPosition.bottomToBottom = bhappsMenusMenuHorizontalMenuLayout.id

                        bhappsMenusMenuHorizontalMenuBorder.layoutParams = layoutParamsForBorderPosition

                    }
                }
            }
        }

        bhappsMenusMenuHorizontalMenuRecycleView.adapter = null

        if(this@HorizontalMenu.horizontal_menu_collapse_type == 1){
            horizontalMenuAdapter!!.mode = 1
        }else{
            horizontalMenuAdapter!!.mode = 0
        }

        setHorizontalMenuItemsUISettings()
        horizontalMenuAdapter!!.horizontalMenuItemsUISettings = horizontalMenuItemsUISettings

        bhappsMenusMenuHorizontalMenuRecycleView!!.isHorizontalScrollBarEnabled = this@HorizontalMenu.horizontal_menu_show_scroll_bar
        bhappsMenusMenuHorizontalMenuRecycleView!!.isNestedScrollingEnabled = this@HorizontalMenu.horizontal_menu_enable_nested_scrolling
        bhappsMenusMenuHorizontalMenuRecycleView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        bhappsMenusMenuHorizontalMenuRecycleView!!.adapter = horizontalMenuAdapter

    }

}