package app.aplicacion.masterdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.masterdetail.R
import app.aplicacion.masterdetail.model.Persona

class PersonaAdapter : RecyclerView.Adapter<PersonaViewHolder>() {

    val item = PersonaDiff()
    val differ = AsyncListDiffer(this, item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        return PersonaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_personas, parent, false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    var itemSelected: ((Persona) -> Unit)? = null

    fun getItemSelected(listener:((Persona)->Unit)?){
        itemSelected=listener
    }
    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.render(differ.currentList[position],itemSelected)
    }
}