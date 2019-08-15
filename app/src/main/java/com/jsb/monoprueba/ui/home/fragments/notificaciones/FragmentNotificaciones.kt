package com.jsb.monoprueba.ui.home.fragments.notificaciones


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsb.monoprueba.R
import com.jsb.monoprueba.data.db.AppDatabase
import com.jsb.monoprueba.databinding.NotificacionesFragmentBinding
import com.jsb.monoprueba.model.Ciudad
import kotlinx.android.synthetic.main.notificaciones_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentNotificaciones : Fragment(), NotificacionesListener {

    private var adapter: NotificacionesAdapter? = null
    lateinit var binding: NotificacionesFragmentBinding
    lateinit var db: AppDatabase
    var lstNotifi:ArrayList<Ciudad> = ArrayList<Ciudad>()
    var lstNotifi1:ArrayList<Ciudad> = ArrayList<Ciudad>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.notificaciones_fragment,container,false)
        binding.rvData1.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)

        val viewModel = ViewModelProviders.of(this).get(NotificacionesViewModel::class.java)
        binding.viewmodelnotifica = viewModel

        viewModel.notificacionesListener = this

        db = activity?.let { AppDatabase(it) }!!

        return binding.root
    }

            override fun onStart() {
                super.onStart()
                adapter = context?.let { NotificacionesAdapter(it) }
                rvData1.adapter = adapter
                binding.viewmodelnotifica?.LoadCities()
                db = activity?.let { AppDatabase(it) }!!

            }

    override fun onStarted() {
        Toast.makeText(activity,"INICIO",Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(resultNotifica: LiveData<ArrayList<Ciudad>>) {
        lstNotifi = db.allCities
        if (lstNotifi.isEmpty()){
            resultNotifica.observe(this, Observer {
                for (c:Ciudad in it){
                    if (c.Nombre.equals("Bogotá")||c.Nombre.equals("Medellin")||c.Nombre.equals("Ibague")||c.Nombre.equals("Tunja")){
                        lstNotifi.add(c)
                    }
                }
                adapter?.updateData(lstNotifi)
            })
        }else{
            for(c:Ciudad in lstNotifi){
                if (c.Nombre.equals("Bogotá")||c.Nombre.equals("Medellin")||c.Nombre.equals("Ibague")||c.Nombre.equals("Tunja")){
                    lstNotifi1.add(c)
                }
            }
            adapter?.updateData(lstNotifi1)
        }
        lstNotifi.clear()
        lstNotifi1.clear()
    }

    override fun onFailure(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }
}