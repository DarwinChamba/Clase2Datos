package app.aplicacion.masterdetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.masterdetail.databinding.ItemPersonasBinding
import app.aplicacion.masterdetail.model.Persona
import com.bumptech.glide.Glide

class PersonaViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val binding=ItemPersonasBinding.bind(view)

    fun render(persona:Persona,listener:((Persona)->Unit)?){
        binding.nombre.text = persona.nombre
        binding.apellido.text = persona.apellido
        binding.edad.text = persona.edad.toString()
        binding.hora.text = persona.hora
        binding.fecha.text = persona.fecha
        Glide.with(binding.nombre).load(persona.imagen).into(binding.imageView)

        itemView.setOnClickListener {
            listener?.invoke(persona)
        }

    }
}