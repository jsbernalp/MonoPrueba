package com.jsb.monoprueba.ui.home.fragments.notificaciones

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jsb.monoprueba.R
import com.jsb.monoprueba.data.db.AppDatabase
import com.jsb.monoprueba.databinding.ItemCiudadBinding
import com.jsb.monoprueba.model.Ciudad

class NotificacionesAdapter(val context: Context): RecyclerView.Adapter<NotificacionesAdapter.CiudadViewHolder>(){


    var ciudades = ArrayList<Ciudad>()
    var ciudadesFiltered = ArrayList<Ciudad>()
    lateinit var db: AppDatabase

    init {
        this.ciudadesFiltered = ciudades
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
        val binding: ItemCiudadBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ciudad, parent,false)
        db = context?.let { AppDatabase(it) }!!
        return CiudadViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return ciudadesFiltered.size

    }

    override fun onBindViewHolder(viewHolder: CiudadViewHolder, position: Int) {
        viewHolder.bind(ciudadesFiltered[position])


    }

    fun updateData(ciudades: ArrayList<Ciudad>){
        this.ciudadesFiltered.clear()
        this.ciudadesFiltered.addAll(ciudades)
        notifyDataSetChanged()

    }

    inner class CiudadViewHolder(val binding: ItemCiudadBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ciudades: Ciudad) {
            binding.txtId.text = ciudades.Id
            binding.txtCode.text = ciudades.Code
            binding.txtNombre.text = ciudades.Nombre


            if (ciudades.State.equals("Activo")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.txtNombre.setBackgroundColor(context.getColor(R.color.grey))
                    binding.txtCode.setBackgroundColor(context.getColor(R.color.white))
                    binding.txtId.setBackgroundColor(context.getColor(R.color.grey))
                }

            }

            binding.lyCity.setOnClickListener { view->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.txtNombre.setBackgroundColor(context.getColor(R.color.grey))
                    binding.txtCode.setBackgroundColor(context.getColor(R.color.white))
                    binding.txtId.setBackgroundColor(context.getColor(R.color.grey))
                }
                db.updateSql(ciudades.Code)
                notifyDataSetChanged()
            }
        }

    }

}