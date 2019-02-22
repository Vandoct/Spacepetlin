package id.antenaislam.spacepetlin.activity.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import id.antenaislam.spacepetlin.R
import id.antenaislam.spacepetlin.adapter.PlanetAdapter
import id.antenaislam.spacepetlin.fragment.dialog.CustomDialog
import id.antenaislam.spacepetlin.model.Planet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var list: MutableList<Planet> = mutableListOf()
    private lateinit var adapter: PlanetAdapter
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTransparent()

        adapter = PlanetAdapter(this, list) {
            val customDialog = CustomDialog()
            customDialog.setPlanet(it)
            customDialog.show(supportFragmentManager, "Custom Dialog")
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        presenter = MainPresenter(this, MainModel(this))
        presenter.getPlanets()
    }

    override fun showPlanets(list: MutableList<Planet>) {
        this.list.clear()
        this.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun setTransparent() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}
