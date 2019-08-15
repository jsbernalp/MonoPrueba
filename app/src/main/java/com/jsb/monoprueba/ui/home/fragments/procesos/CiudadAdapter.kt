package com.jsb.monoprueba.ui.home.fragments.procesos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jsb.monoprueba.R
import com.jsb.monoprueba.databinding.ItemCiudadBinding
import com.jsb.monoprueba.model.Ciudad
import com.jsb.monoprueba.ui.home.fragments.notificaciones.FragmentNotificaciones

class CiudadAdapter(val context: Context): RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder>(),Filterable {


    var ciudades = ArrayList<Ciudad>()
    var ciudadesFiltered = ArrayList<Ciudad>()


    init {
        this.ciudadesFiltered = ciudades
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
        val binding: ItemCiudadBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ciudad, parent,false)
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
        }


    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charString: String = constraint.toString()
                if (charString.isEmpty()){
                    ciudadesFiltered = ciudades
                }
                else {
                    val filteredCiudad = ArrayList<Ciudad>()
                    for (c: Ciudad in ciudades) {
                        if (c.Nombre.toLowerCase().contains(charString.toLowerCase())) {
                            filteredCiudad.add(c)
                        }
                    }
                    ciudadesFiltered = filteredCiudad
                }

                var filterResults = Filter.FilterResults()
                filterResults.values = ciudadesFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ciudadesFiltered = results?.values as ArrayList<Ciudad>
                notifyDataSetChanged()
            }

        }
    }
}