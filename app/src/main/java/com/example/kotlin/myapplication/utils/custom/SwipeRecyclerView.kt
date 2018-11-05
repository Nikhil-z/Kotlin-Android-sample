package com.example.kotlin.myapplication.utils.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.AttributeSet
import com.example.kotlin.myapplication.utils.RecyclerItemTouchHelper

open class SwipeRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun setSwipeListener(recyclerItemTouchHelperListener: RecyclerItemTouchHelper.RecyclerItemTouchHelperListener) {

        val itemTouchHelperCallback = RecyclerItemTouchHelper(
            0,
            ItemTouchHelper.LEFT,
            recyclerItemTouchHelperListener
        )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)

        val itemTouchHelperCallback1 = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

        }
        // attaching the touch helper to recycler view
        ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(this)
    }


}