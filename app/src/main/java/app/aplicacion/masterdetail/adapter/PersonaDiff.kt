package app.aplicacion.masterdetail.adapter

import androidx.recyclerview.widget.DiffUtil
import app.aplicacion.masterdetail.model.Persona

class PersonaDiff:DiffUtil.ItemCallback<Persona>() {
    override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
        return oldItem == newItem
    }
}