package study.my.calculatorosago.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import study.my.calculatorosago.R
import study.my.calculatorosago.model.Branding
import study.my.calculatorosago.retrofit.ResultListOffers
import study.my.calculatorosago.retrofit.RetrofitClient
import java.net.URL

class OffersActivity : AppCompatActivity() {

    private var backToMain: ConstraintLayout? = null
    var expandableView: ConstraintLayout?=null
    var dropImage: ImageView?=null
    var cardView: CardView?=null

    lateinit var logos: MutableList<ImageView>
    lateinit var names: MutableList<TextView>
    lateinit var ratings: MutableList<TextView>
    lateinit var prices: MutableList<TextView>

    lateinit var base: TextView
    lateinit var power: TextView
    lateinit var local: TextView
    lateinit var damage: TextView
    lateinit var limit: TextView
    lateinit var age: TextView
    lateinit var baseCoefficient: TextView
    lateinit var powerCoefficient: TextView
    lateinit var localCoefficient: TextView
    lateinit var damageCoefficient: TextView
    lateinit var ageCoefficient: TextView
    lateinit var limitCoefficient: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)
        backToMain = findViewById(R.id.back_to_main)
        backToMain?.setOnClickListener {
            val back = Intent(this@OffersActivity, MainActivity::class.java)
            startActivity(back)
        }
        base=findViewById(R.id.base)
        power=findViewById(R.id.power)
        local=findViewById(R.id.local)
        damage=findViewById(R.id.demage)
        limit=findViewById(R.id.limit)
        age=findViewById(R.id.age)
        baseCoefficient=findViewById(R.id.base_coefficient)
        powerCoefficient=findViewById(R.id.power_coefficient)
        localCoefficient=findViewById(R.id.local_coefficient)
        damageCoefficient=findViewById(R.id.demage_coefficient)
        ageCoefficient=findViewById(R.id.age_coefficient)
        limitCoefficient=findViewById(R.id.limit_coefficient)
        expandableView=findViewById(R.id.expandable_view)
        dropImage=findViewById(R.id.drop_image)
        cardView=findViewById(R.id.card_view)

        base.setText(intent.getStringExtra("base"))
        power.setText(intent.getStringExtra("power"))
        local.setText(intent.getStringExtra("local"))
        damage.setText(intent.getStringExtra("damage"))
        limit.setText(intent.getStringExtra("limit"))
        age.text = intent.getStringExtra("age")
        baseCoefficient.setText(intent.getStringExtra("baseCoefficient"))
        powerCoefficient.setText(intent.getStringExtra("powerCoefficient"))
        localCoefficient.setText(intent.getStringExtra("localCoefficient"))
        damageCoefficient.setText(intent.getStringExtra("damageCoefficient"))
        ageCoefficient.setText(intent.getStringExtra("ageCoefficient"))
        limitCoefficient.setText(intent.getStringExtra("limitCoefficient"))

        collectViews()
        createExpandableView()
        getAllOffersList()
    }

    private fun getAllOffersList() {
        val res: Call<ResultListOffers> = RetrofitClient.service.getOffersList()
        res.enqueue(object : Callback<ResultListOffers> {

            override fun onResponse(call: Call<ResultListOffers>, response: Response<ResultListOffers>) {
                val offers = response.body()!!.offers
                var i = 0
                while (i in offers.indices) {
                    Glide
                        .with(this@OffersActivity)
                        .load(offers[i].branding?.bankLogoUrlSVG)
                        .into(logos[i])


                    names[i].setText(offers[i].name.toString())
                    ratings[i].setText(offers[i].rating.toString())
                    prices[i].setText(offers[i].price.toString())

                    i++
                }
            }

            override fun onFailure(call: Call<ResultListOffers>, t: Throwable) {
                Toast.makeText(applicationContext, "text", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun collectViews() {
        logos = arrayListOf(
            findViewById(R.id.logo1), findViewById(R.id.logo2), findViewById(R.id.logo3),
            findViewById(R.id.logo4), findViewById(R.id.logo5), findViewById(R.id.logo6))
        names = arrayListOf(
            findViewById(R.id.name1), findViewById(R.id.name2), findViewById(R.id.name3),
            findViewById(R.id.name4), findViewById(R.id.name5), findViewById(R.id.name6))
        ratings = arrayListOf(
            findViewById(R.id.rating1), findViewById(R.id.rating2), findViewById(R.id.rating3),
            findViewById(R.id.rating4), findViewById(R.id.rating5), findViewById(R.id.rating6))
        prices = arrayListOf(
            findViewById(R.id.price1), findViewById(R.id.price2), findViewById(R.id.price3),
            findViewById(R.id.price4), findViewById(R.id.price5), findViewById(R.id.price6))
    }

    fun createExpandableView() {

        setCoefficients(base.text.toString(), power.text.toString(), local.text.toString(),
            damage.text.toString(), limit.text.toString(), age.text.toString())
        setExpandableCoefficients(
            baseCoefficient.text.toString(), powerCoefficient.text.toString(), localCoefficient.text.toString(),
            damageCoefficient.text.toString(), ageCoefficient.text.toString(), limitCoefficient.text.toString())

        dropImage?.setOnClickListener {
            val transition: Transition=AutoTransition().setDuration(100)
            if(expandableView?.visibility==View.GONE) {
                TransitionManager.beginDelayedTransition(cardView, transition)
                expandableView?.visibility=View.VISIBLE
                dropImage?.setBackgroundResource(R.drawable.drop_reverse)
            } else {
                expandableView?.visibility=View.GONE
                dropImage?.setBackgroundResource(R.drawable.drop)
            }
        }
    }

    fun setCoefficients(
        base: String, power: String, local: String, damage: String,
        limit: String, age: String,
    ) {
        this.base.text=base
        this.power.text=power
        this.local.text=local
        this.damage.text=damage
        this.limit.text=limit
        this.age.text=age
    }

    fun setExpandableCoefficients(
        base: String, power: String, local: String, damage: String,
        limit: String, age: String,
    ) {
        this.baseCoefficient.text=base
        this.powerCoefficient.text=power
        this.localCoefficient.text=local
        this.damageCoefficient.text=damage
        this.ageCoefficient.text=limit
        this.limitCoefficient.text=age
    }


}