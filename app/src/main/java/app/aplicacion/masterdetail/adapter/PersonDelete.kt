package app.aplicacion.masterdetail.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class PersonDelete :ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val direccion=ItemTouchHelper.LEFT or ItemTouchHelper.DOWN or
                ItemTouchHelper.RIGHT or ItemTouchHelper.UP
        return makeMovementFlags(0,direccion)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return  false
    }
}