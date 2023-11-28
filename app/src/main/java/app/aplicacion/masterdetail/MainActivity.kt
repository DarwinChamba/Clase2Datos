package app.aplicacion.masterdetail

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.aplicacion.masterdetail.adapter.PersonDelete
import app.aplicacion.masterdetail.adapter.PersonaAdapter
import app.aplicacion.masterdetail.databinding.ActivityMainBinding
import app.aplicacion.masterdetail.lista.ListaEjemplo
import app.aplicacion.masterdetail.model.Persona

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var cpersonas:PersonaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        cpersonas.differ.submitList(ListaEjemplo.lista)
        getSelectedPerson()
        binding.addPerson.setOnClickListener { showDialog() }
        deletePerson()
        searchName()
    }

    private fun initRecycler() {
        cpersonas= PersonaAdapter()
        binding.rvPersonas.apply {
            adapter=cpersonas
            layoutManager=LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }
    private fun getSelectedPerson(){
        cpersonas.getItemSelected {persona->
            Toast.makeText(this,"hola soy ${persona.nombre} ${persona.apellido}",Toast.LENGTH_SHORT).show()
        }

    }
    private  fun showDialog(){
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.new_person)
        val name=dialog.findViewById<EditText>(R.id.nombreDialog)
        val lastName=dialog.findViewById<EditText>(R.id.apellidoDialog)
        val age=dialog.findViewById<EditText>(R.id.edadDialog)
        val image=dialog.findViewById<EditText>(R.id.imagenDialog)
        val save=dialog.findViewById<Button>(R.id.guardarDialog)
        save.setOnClickListener {
            val nombre=name.text.toString()
            val apellido=lastName.text.toString()
            val edad=age.text.toString()
            val imagen=image.text.toString()
            if(TextUtils.isEmpty(nombre)){
                validarCampo(name,"required name")
            }else if(TextUtils.isEmpty(apellido)){
                validarCampo(lastName,"required last name")
            }else if(TextUtils.isEmpty(edad)){
                validarCampo(age,"required age")
            }else if(TextUtils.isEmpty(imagen)){
                validarCampo(image,"required image")
            }else{
                val id=(10..345).random()
                val persona=Persona(id,nombre,apellido,
                    Integer.parseInt(edad),getTime(),getDate(),imagen)
                ListaEjemplo.lista.add(persona)
                Toast.makeText(this,"Registro ingresado con exito",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                cpersonas.notifyDataSetChanged()

            }
        }

        dialog.show()
    }
    private fun validarCampo(campo:EditText,mensaje:String){
        campo.setError(mensaje)
        campo.focusable
    }
    private fun getTime():String{
        val time= Calendar.getInstance().time
        val timeFormat=SimpleDateFormat("HH:mm")
        return timeFormat.format(time)

    }
    private fun getDate():String{
        val date= Date()
        val dateFormat=SimpleDateFormat("YYYY-MM-dd")
        return dateFormat.format(date)
    }

    private fun deletePerson(){
        val element=object : PersonDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val personaAEliminar=cpersonas.differ.currentList[position]
                ListaEjemplo.lista.remove(personaAEliminar)
                cpersonas.notifyDataSetChanged()
                Toast.makeText(this@MainActivity,"Registro eliminado con exito",Toast.LENGTH_SHORT).show()
            }

        }
        ItemTouchHelper(element).apply {
            attachToRecyclerView(binding.rvPersonas)
        }
    }
    private fun searchName(){
        binding.buscar.addTextChangedListener {

            val nombres=it.toString().toLowerCase()
            if(!TextUtils.isEmpty(nombres)){
                val newList=ListaEjemplo.lista
                    .filter { it.nombre.toLowerCase().contains(nombres) }
                cpersonas.differ.submitList(newList)
                cpersonas.notifyDataSetChanged()
            }else{
                cpersonas.differ.submitList(ListaEjemplo.lista)
                cpersonas.notifyDataSetChanged()
            }
        }
    }

}