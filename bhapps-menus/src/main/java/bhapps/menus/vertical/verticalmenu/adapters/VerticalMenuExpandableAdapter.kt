package bhapps.menus.vertical.verticalmenu.adapters

import android.content.Context
import android.os.Handler
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_ARROW_ROTATION_DURATION
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_COLLAPSEOTHERS
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_EXPANDCOLLAPSE_DELAY
import bhapps.menus.vertical.verticalmenu.helpers.VerticalMenuConfig.VERTICALMENU_EXPANDCOLLAPSE_ROTATE
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuItem
import bhapps.menus.vertical.verticalmenu.models.VerticalMenuType
import java.util.*

abstract class VerticalMenuExpandableAdapter<T : VerticalMenuItem>(
    protected var mContext: Context
) : RecyclerView.Adapter<VerticalMenuExpandableAdapter<VerticalMenuItem>.ViewHolder>() {
    protected var allItems: MutableList<T> = mutableListOf()
    protected var visibleItems: MutableList<T>? = mutableListOf()
    private var indexList: MutableList<Int> = mutableListOf()
    private var expandMap = SparseIntArray()
    var mode = 0

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getItemCount(): Int {
        return visibleItems?.size ?: 0
    }

    var items: List<T>
        get() = allItems
        set(items) {
            allItems = items as MutableList<T>
            val visibleItems: MutableList<T> = mutableListOf()
            expandMap.clear()
            indexList.clear()
            for (i in items.indices) {
                val type = allItems[i]!!.item_type
                if (type == PARENTWITHCHILDREN || type == DIVIDER || type == PARENT || type == TITLE) {
                    indexList.add(i)
                    visibleItems.add(items[i])
                }
            }
            this.visibleItems = visibleItems
            notifyDataSetChanged()
        }

    protected fun inflate(resourceID: Int, viewGroup: ViewGroup?): View {
        return LayoutInflater.from(mContext).inflate(resourceID, viewGroup, false)
    }

    open inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!)
    open inner class HeaderViewHolder(
        view: View,
        var arrow: ImageView
    ) :
        ViewHolder(view) {
        protected fun handleClick() {
            if (toggleExpandedItems(layoutPosition, false)) {
                openArrow(
                    arrow
                )
            } else {
                closeArrow(
                    arrow
                )
            }
            // refresh item Id
            Handler().postDelayed({ notifyDataSetChanged() },
                VERTICALMENU_EXPANDCOLLAPSE_DELAY.toLong()
            )
        }

        open fun bind(position: Int) {
            arrow.rotation =
                if (isExpanded(position)) VERTICALMENU_EXPANDCOLLAPSE_ROTATE.toFloat() else 0.toFloat()
        }

        init {
            view.setOnClickListener { handleClick() }
        }
    }

    fun toggleExpandedItems(position: Int, notify: Boolean): Boolean {
        return if (isExpanded(position)) {
            collapseItems(position, notify)
            false
        } else {
            expandItems(position, notify)
            if (mode == MODE_ACCORDION) {
                collapseAllExcept(position)
            }
            true
        }
    }

    fun expandItems(position: Int, notify: Boolean) {
        var count = 0
        val index = indexList[position]
        var insert = position
        for (i in index + 1 until allItems.size) {
            val type = allItems[i]!!.item_type
            if (i < allItems.size && type != PARENTWITHCHILDREN && type != DIVIDER && type != PARENT && type != TITLE) {
                insert++
                count++
                visibleItems?.add(insert, allItems[i])
                indexList.add(insert, i)
            } else {
                break
            }
        }
        notifyItemRangeInserted(position + 1, count)
        val allItemsPosition = indexList[position]
        expandMap.put(allItemsPosition, 1)
        if (notify) {
            notifyItemChanged(position)
        }
    }

    fun collapseItems(position: Int, notify: Boolean) {
        var count = 0
        val index = indexList[position]
        for (i in index + 1 until allItems.size) {
            val type = allItems[i]!!.item_type
            if (i < allItems.size && type != PARENTWITHCHILDREN && type != DIVIDER && type != PARENT && type != TITLE) {
                count++
                visibleItems?.removeAt(position + 1)
                indexList.removeAt(position + 1)
            } else {
                break
            }
        }
        notifyItemRangeRemoved(position + 1, count)
        val allItemsPosition = indexList[position]
        expandMap.delete(allItemsPosition)
        if (notify) {
            notifyItemChanged(position)
        }
    }

    inner class StaticViewHolder(view: View?) :
        ViewHolder(view)

    inner class ItemViewHolder(view: View?) :
        ViewHolder(view)

    protected fun isExpanded(position: Int): Boolean {
        val allItemsPosition = indexList[position]
        return expandMap[allItemsPosition, -1] >= 0
    }

    override fun getItemViewType(position: Int): Int {
        return visibleItems!![position]!!.item_type
    }

    protected fun notifyItemInserted(allItemsPosition: Int, visiblePosition: Int) {
        incrementIndexList(allItemsPosition, visiblePosition, 1)
        incrementExpandMapAfter(allItemsPosition, 1)
        if (visiblePosition >= 0) {
            notifyItemInserted(visiblePosition)
        }
    }

    protected fun removeItemAt(visiblePosition: Int) {
        val allItemsPosition = indexList[visiblePosition]
        allItems.removeAt(allItemsPosition)
        visibleItems?.removeAt(visiblePosition)
        incrementIndexList(allItemsPosition, visiblePosition, -1)
        incrementExpandMapAfter(allItemsPosition, -1)
        notifyItemRemoved(visiblePosition)
    }

    private fun incrementExpandMapAfter(position: Int, direction: Int) {
        val newExpandMap = SparseIntArray()
        for (i in 0 until expandMap.size()) {
            val index = expandMap.keyAt(i)
            newExpandMap.put(if (index < position) index else index + direction, 1)
        }
        expandMap = newExpandMap
    }

    private fun incrementIndexList(
        allItemsPosition: Int,
        visiblePosition: Int,
        direction: Int
    ) {
        val newIndexList: MutableList<Int> = ArrayList()
        for (i in indexList.indices) {
            if (i == visiblePosition) {
                if (direction > 0) {
                    newIndexList.add(allItemsPosition)
                }
            }
            val `val` = indexList[i]
            newIndexList.add(if (`val` < allItemsPosition) `val` else `val` + direction)
        }
        indexList = newIndexList
    }

    fun collapseAll() {
        collapseAllExcept(-1)
    }

    fun collapseAllExcept(position: Int) {
        if (VERTICALMENU_COLLAPSEOTHERS) {
            for (i in visibleItems!!.indices.reversed()) {
                val type = getItemViewType(i)
                if (i != position && (type == PARENTWITHCHILDREN || type == DIVIDER || type == PARENT || type == TITLE)) {
                    if (isExpanded(i)) {
                        collapseItems(i, true)
                    }
                }
            }
        }
    }

    fun expandAll() {
        for (i in visibleItems!!.indices.reversed()) {
            val type = getItemViewType(i)
            if (type == PARENTWITHCHILDREN || type == DIVIDER || type == PARENT || type == TITLE) {
                if (!isExpanded(i)) {
                    expandItems(i, true)
                }
            }
        }
    }

    companion object {

        val PARENT = VerticalMenuType.PARENT.value
        val PARENTWITHCHILDREN = VerticalMenuType.PARENTWITHCHILDREN.value
        val CHILD = VerticalMenuType.CHILD.value
        val DIVIDER = VerticalMenuType.DIVIDER.value
        val TITLE = VerticalMenuType.TITLE.value

        private val ARROW_ROTATION_DURATION: Int = VERTICALMENU_ARROW_ROTATION_DURATION
        const val MODE_NORMAL = 0
        const val MODE_ACCORDION = 1
        fun openArrow(view: View) {
            view.animate()
                .setDuration(ARROW_ROTATION_DURATION.toLong())
                .rotation(180f)
        }

        fun closeArrow(view: View) {
            view.animate()
                .setDuration(ARROW_ROTATION_DURATION.toLong())
                .rotation(0f)
        }
    }

}