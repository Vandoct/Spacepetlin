package id.antenaislam.spacepetlin.activity.main

import id.antenaislam.spacepetlin.model.Planet
import org.json.JSONObject

interface MainContract {
    interface Model {
        fun setListener(listener: ModelListener)
        fun getPlanets()
        fun prepareData(response: JSONObject)

        interface ModelListener {
            fun showPlanets(list: MutableList<Planet>)
        }
    }

    interface View {
        fun showPlanets(list: MutableList<Planet>)
    }

    interface Presenter {
        fun getPlanets()
    }
}