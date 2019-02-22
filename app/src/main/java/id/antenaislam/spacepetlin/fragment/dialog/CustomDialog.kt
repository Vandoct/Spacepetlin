package id.antenaislam.spacepetlin.fragment.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.antenaislam.spacepetlin.R
import id.antenaislam.spacepetlin.model.Planet
import kotlinx.android.synthetic.main.dialog_custom.view.*


class CustomDialog : DialogFragment() {
    private lateinit var planet: Planet
    private lateinit var detail: Array<String>
    private val headers = arrayOf("Deskripsi", "Kecepatan Orbit", "Radius", "Volume", "Massa")
    private var counter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.dialog_custom, container, false)

        views.btn_prev.setOnClickListener {
            counter--
            setDetail(views)
        }

        views.btn_next.setOnClickListener {
            counter++
            setDetail(views)
        }

        detail = arrayOf(planet.description, planet.orbitSpeed, planet.radius, planet.volume, planet.mass)

        views.tv_title.text = planet.name

        val resId = resources.getIdentifier("ic_${planet.imagePath}", "drawable", context?.packageName)

        Glide.with(this)
            .load(resId)
            .into(views.iv_planet_dialog)

        setDetail(views)

        return views
    }

    fun setPlanet(planet: Planet) {
        this.planet = planet
    }

    private fun setDetail(view: View) {
        counter = if (counter < 0) detail.size - 1 else if (counter < detail.size) counter else 0

        view.tv_header.text = headers[counter]
        view.tv_detail.text = detail[counter]
    }
}