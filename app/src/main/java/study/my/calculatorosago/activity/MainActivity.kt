package study.my.calculatorosago.activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import study.my.calculatorosago.retrofit.ResultList
import study.my.calculatorosago.retrofit.RetrofitClient
import android.widget.TextView
import android.content.Intent
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import androidx.cardview.widget.CardView
import study.my.calculatorosago.activity.OffersActivity
import study.my.calculatorosago.R


class MainActivity : AppCompatActivity() {

    lateinit var dialog: ConstraintLayout
    lateinit var dialogBuilder: BottomSheetDialog
    lateinit var button: ImageButton
    lateinit var editText: EditText
    lateinit var title: TextView
    lateinit var enter: EditText
    lateinit var backButton: ImageButton

    lateinit var newFormCity: TextView
    var newFormPower: TextView? = null
    var newFormDrivers: TextView? = null
    var newFormYoungestDriver: TextView? = null
    var newFormMinExperience: TextView? = null
    var newFormYearsWithoutAccident: TextView? = null
    var formCity: TextView? = null
    var formPower: TextView? = null
    var formDrivers: TextView? = null
    var formYoungestDriver: TextView? = null
    var formMinExperience: TextView? = null
    var formYearsWithoutAccident: TextView? = null
    var generalButton: Button?=null

    var expandableView: ConstraintLayout?=null
    var dropImage: ImageView?=null
    var cardView: CardView?=null
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
        setContentView(R.layout.activity_main)
        createExpandableView()
        createDialog()

        generalButton=findViewById(R.id.button)
        generalButton?.setOnClickListener {
            var intent = Intent(this@MainActivity, OffersActivity::class.java)
            intent.putExtra("base", base.text)
            intent.putExtra("power", power.text)
            intent.putExtra("local", local.text)
            intent.putExtra("damage", damage.text)
            intent.putExtra("limit", limit.text)
            intent.putExtra("age", age.text)
            intent.putExtra("baseCoefficient", baseCoefficient.text)
            intent.putExtra("powerCoefficient", powerCoefficient.text)
            intent.putExtra("localCoefficient", localCoefficient.text)
            intent.putExtra("damageCoefficient", damageCoefficient.text)
            intent.putExtra("limitCoefficient", limitCoefficient.text)
            intent.putExtra("ageCoefficient", ageCoefficient.text)
            startActivity(intent)
        }
    }

    private fun getAllFactorList() {
        val res: Call<ResultList> = RetrofitClient.service.getFactorList()
        res.enqueue(object : Callback<ResultList> {
            override fun onResponse(call: Call<ResultList>, response: Response<ResultList>) {
                val result = response.body()!!.factors
                setCoefficients(
                    result[0].headerValue!!, result[1].headerValue!!,
                    result[2].headerValue!!, result[3].headerValue!!, result[4].headerValue!!,
                    result[5].headerValue!!
                )
                setExpandableCoefficients(
                    result[0].value!!, result[1].value!!, result[2].value!!,
                    result[3].value!!, result[4].value!!, result[5].value!!
                )
            }

            override fun onFailure(call: Call<ResultList>, t: Throwable) {
            }
        })
    }

    fun setCoefficients(base: String, power: String, local: String, damage: String, limit: String,
                        age: String, ) {
        this.base.text = base
        this.power.text = power
        this.local.text = local
        this.damage.text = damage
        this.limit.text = limit
        this.age.text = age
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

    fun createExpandableView() {

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

        setCoefficients("БТ", "КМ", "КТ", "КБМ",
            "КО", "КВС")
        setExpandableCoefficients(
            "2 754 - 4 432 ₽", "0,6 - 1,6", "0,64 - 1,99",
            "0,5 - 2,45", "0,90 - 1,93", "1 или 1,99"
        )

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

    fun createDialog() {
        val dialogs = layoutInflater.inflate(R.layout.dialogs, null)
        dialog = dialogs.findViewById(R.id.dialog)
        newFormCity = findViewById(R.id.newFormCity)
        newFormPower = findViewById(R.id.newFormPower)
        newFormDrivers = findViewById(R.id.newFormDrivers)
        newFormYoungestDriver = findViewById(R.id.newFormYoungestDriver)
        newFormMinExperience = findViewById(R.id.newFormMinExperience)
        newFormYearsWithoutAccident = findViewById(R.id.newFormYearsWithoutAccident)
        formCity = findViewById(R.id.cityForm)
        formPower = findViewById(R.id.powerForm)
        formDrivers = findViewById(R.id.driversForm)
        formYoungestDriver = findViewById(R.id.youngestDriverForm)
        formMinExperience = findViewById(R.id.minExperienceForm)
        formYearsWithoutAccident = findViewById(R.id.yearsWithoutAccidentForm)
        backButton = dialog.findViewById(R.id.backButton)
        title = dialog.findViewById(R.id.titleDialog)
        enter = dialog.findViewById(R.id.enter)
        button = dialogs.findViewById(R.id.buttonDialog)
        editText=dialogs.findViewById(R.id.enter)
        dialogBuilder = BottomSheetDialog(this)
        dialogBuilder.setContentView(dialog)
    }

    fun showCityDialog(view: View) {
        title.setText(R.string.city)
        enter.setHint(R.string.add_city)
        backButton.visibility = View.GONE
        dialogBuilder.show()
        button.setOnClickListener {
            showPowerDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formCity?.setBackgroundResource(R.drawable.shape_changed)
            newFormCity.visibility = View.VISIBLE
            newFormCity.text = enter.text
            getAllFactorList()
        }
    }

    fun showPowerDialog(view: View) {
        title.setText(R.string.carHp)
        enter.setHint(R.string.add_power)
        backButton.visibility = View.VISIBLE
        dialogBuilder.show()
        button.setOnClickListener {
            showDriversDialog(view)
        }
        backButton.setOnClickListener {
            showCityDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formPower?.setBackgroundResource(R.drawable.shape_changed)
            newFormPower?.visibility = View.VISIBLE
            newFormPower?.text = enter.text
            getAllFactorList()
        }
    }

    fun showDriversDialog(view: View) {
        title.setText(R.string.drivers)
        enter.setHint(R.string.add_drivers)
        backButton.visibility = View.VISIBLE
        dialogBuilder.show()
        button.setOnClickListener {
            showYoungestDriverDialog(view)
        }
        backButton.setOnClickListener {
            showPowerDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formDrivers?.setBackgroundResource(R.drawable.shape_changed)
            newFormDrivers?.visibility = View.VISIBLE
            newFormDrivers?.text = enter.text
            getAllFactorList()
        }
    }

    fun showYoungestDriverDialog(view: View) {
        title.setText(R.string.youngestDriver)
        enter.setHint(R.string.add_youngest_driver)
        backButton.visibility = View.VISIBLE
        dialogBuilder.show()
        button.setOnClickListener {
            showMinExperienceDialog(view)
        }
        backButton.setOnClickListener {
            showDriversDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formYoungestDriver?.setBackgroundResource(R.drawable.shape_changed)
            newFormYoungestDriver?.visibility = View.VISIBLE
            newFormYoungestDriver?.text = enter.text
            getAllFactorList()
        }
    }

    fun showMinExperienceDialog(view: View) {
        title.setText(R.string.minExperience)
        enter.setHint(R.string.add_min_experience)
        backButton.visibility = View.VISIBLE
        dialogBuilder.show()
        button.setOnClickListener {
            showYearsWithoutAccidentDialog(view)
        }
        backButton.setOnClickListener {
            showYoungestDriverDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formMinExperience?.setBackgroundResource(R.drawable.shape_changed)
            newFormMinExperience?.visibility = View.VISIBLE
            newFormMinExperience?.text = enter.text
            getAllFactorList()
        }
    }

    fun showYearsWithoutAccidentDialog(view: View) {
        title.setText(R.string.yearsNoAccident)
        enter.setHint(R.string.add_years_no_accidents)
        backButton.visibility = View.VISIBLE
        dialogBuilder.show()
        button.setOnClickListener {
            generalButton?.setHintTextColor(getColor(R.color.white))
            generalButton?.setBackgroundResource(R.drawable.button_main_shape_changed)
            generalButton?.isEnabled=true
            dialogBuilder.dismiss()
        }
        backButton.setOnClickListener {
            showMinExperienceDialog(view)
        }
        dialogBuilder.setOnDismissListener {
            formYearsWithoutAccident?.setBackgroundResource(R.drawable.shape_changed)
            newFormYearsWithoutAccident?.visibility = View.VISIBLE
            newFormYearsWithoutAccident?.text = enter.text
            getAllFactorList()
        }
    }
}
