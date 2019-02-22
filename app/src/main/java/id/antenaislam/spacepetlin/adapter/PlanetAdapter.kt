package id.antenaislam.spacepetlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import com.bumptech.glide.Glide
import id.antenaislam.spacepetlin.R
import id.antenaislam.spacepetlin.model.Planet
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.planet_item_left.*
import kotlinx.android.synthetic.main.planet_item_left.view.*


class PlanetAdapter(
    private val context: Context,
    private val list: MutableList<Planet>,
    private val listener: (Planet) -> Unit
) :
    RecyclerView.Adapter<PlanetAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        if (p1 == 0) {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.planet_item_right, p0, false))
        }

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.planet_item_left, p0, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) = p0.bindItem(list[p1], listener)

    override fun getItemViewType(position: Int) = (position % 2)

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        holder.iv_planet.animation = animatePlanet()
    }

    fun animatePlanet(): TranslateAnimation {
        val animation = TranslateAnimation(
            TranslateAnimation.ABSOLUTE, 0f,
            TranslateAnimation.ABSOLUTE, 0f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0.1f
        )

        animation.duration = 2000
        animation.repeatCount = -1
        animation.repeatMode = Animation.REVERSE
        animation.interpolator = LinearInterpolator()

        return animation
    }

    inner class MyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: Planet, listener: (Planet) -> Unit) {
            val resId = containerView.context.resources.getIdentifier(
                "ic_${item.imagePath}",
                "drawable",
                containerView.context.packageName
            )

            Glide.with(containerView.context)
                .load(resId)
                .into(itemView.iv_planet)

            itemView.iv_planet.animation = animatePlanet()
            itemView.iv_planet.setOnClickListener { listener(item) }
        }
    }
}