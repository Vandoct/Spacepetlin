package id.antenaislam.spacepetlin.activity.main

import id.antenaislam.spacepetlin.model.Planet

class MainPresenter(_view: MainContract.View, _model: MainModel) : MainContract.Presenter,
    MainContract.Model.ModelListener {
    private val view: MainContract.View = _view
    private val model: MainContract.Model = _model

    init {
        model.setListener(this)
    }

    override fun getPlanets() {
        model.getPlanets()
    }

    override fun showPlanets(list: MutableList<Planet>) {
        view.showPlanets(list)
    }
}