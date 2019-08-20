@file:Suppress("UNREACHABLE_CODE")

package com.jsb.monoprueba.ui.home.fragments.procesos

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsb.monoprueba.R
import com.jsb.monoprueba.data.db.AppDatabase
import com.jsb.monoprueba.databinding.ProcesosFragmentBinding
import com.jsb.monoprueba.di.procesos.DaggerProcesosComponent

import com.jsb.monoprueba.factory.*
import com.jsb.monoprueba.model.Ciudad
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.procesos_fragment.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentProcesos : Fragment(), ProcesosListener {


    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private var adapter: CiudadAdapter? = null
    lateinit var binding:ProcesosFragmentBinding
    lateinit var searchView: SearchView
    lateinit var  db: AppDatabase
    var contador:Int = 0
    var lstCities:ArrayList<Ciudad> = ArrayList<Ciudad>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        DaggerProcesosComponent.create().inject(this)

        binding = DataBindingUtil.inflate(inflater,R.layout.procesos_fragment,container,false)
        binding.rvData.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(ProcesosViewModel::class.java)
        binding.viewmodel1 = viewModel
        viewModel.procesosListener = this

        return binding.root

    }


    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
        db = activity?.let { AppDatabase(it) }!!
        adapter = context?.let { CiudadAdapter(it) }
        rvData.adapter = adapter
        binding.viewmodel1?.LoadData()

           showToast()

    }

    override fun onSuccess(resultResponse: LiveData<ArrayList<Ciudad>>) {
        lstCities = db.allCities
        if (lstCities.isEmpty()){
            resultResponse.observe(this, Observer {
                adapter?.updateData(it)
                var estado:String = "Inactivo"
                db.addCity(it,estado)
            })
        }else{
            adapter?.updateData(lstCities)
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        toolbar.setTitle("MonoLegal")
        activity?.menuInflater?.inflate(R.menu.menu_item,menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search1)
        searchView = MenuItemCompat
            .getActionView(item) as SearchView
        MenuItemCompat.setOnActionExpandListener(item,object : MenuItemCompat.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(Color.WHITE)
                (searchView.findViewById(R.id.search_src_text) as EditText?)?.setHintTextColor(Color.BLACK)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                searchView.setQuery("",false)
                return true
            }
        })
        searchView.maxWidth = Int.MAX_VALUE
        searchName(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchName(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return true
            }
        })

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_search1){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showToast() {
        if (contador >= 120) {
        contadortxt.text = "TERMINO"
        } else {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    activity!!.runOnUiThread(Runnable {
                        contadortxt.text = "Contador: $contador"
                        contador++
                        if (contador == 10001) {
                            this.cancel()
                        }

                    })
                }
            }, 2000, 300)

        }

    }
}


