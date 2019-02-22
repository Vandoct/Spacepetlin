package id.antenaislam.spacepetlin.activity.main

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import id.antenaislam.spacepetlin.model.Planet
import org.json.JSONObject

class MainModel(private val context: Context) : MainContract.Model {
    private lateinit var listener: MainContract.Model.ModelListener

    override fun setListener(listener: MainContract.Model.ModelListener) {
        this.listener = listener
    }

    override fun getPlanets() {
        val url = "http://dsc-space.000webhostapp.com/public/id/milkyway"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                prepareData(response)
            },
            { error ->
                error.printStackTrace()
            }
        )

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonObjectRequest)
    }

    override fun prepareData(response: JSONObject) {
        val list: MutableList<Planet> = mutableListOf()
        val arr = response.getJSONArray("data")
        val length = arr.length()

        for (i in 0 until length) {
            val title = arr.getJSONObject(i).getString("name")
            val description = arr.getJSONObject(i).getString("description")
            val orbitSpeed = arr.getJSONObject(i).getString("orbit_speed")
            val radius = arr.getJSONObject(i).getString("radius")
            val volume = arr.getJSONObject(i).getString("volume")
            val mass = arr.getJSONObject(i).getString("mass")
            val image = arr.getJSONObject(i).getString("image_path").split("/")[2].replace(".svg", "")

            list.add(Planet(title, description, orbitSpeed, radius, volume, mass, image))
        }

        listener.showPlanets(list)
    }
}