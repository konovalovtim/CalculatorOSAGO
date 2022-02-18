package study.my.calculatorosago

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import study.my.calculatorosago.retrofit.ResultList
import study.my.calculatorosago.retrofit.RetrofitClient


class MainActivity : AppCompatActivity() {

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
    }

    private fun getAllFactorList() {
        val res: Call<ResultList> = RetrofitClient.service.getFactorList()
        res?.enqueue(object : Callback<ResultList> {
            override fun onResponse(call: Call<ResultList>, response: Response<ResultList>, ) {
                val result = response.body()!!.factors
                setCoefficients(result.get(0).headerValue!!, result.get(1).headerValue!!,
                    result.get(2).headerValue!!, result.get(3).headerValue!!,
                    result.get(4).headerValue!!, result.get(5).headerValue!!)
                setExpandableCoefficients(result.get(0).value!!, result.get(1).value!!,
                    result.get(2).value!!, result.get(3).value!!, result.get(4).value!!,
                    result.get(5).value!!)
            }
            override fun onFailure(call: Call<ResultList>, t: Throwable) {
            }
        })
    }

    fun createExpandableView() {
        base = findViewById(R.id.base)
        power = findViewById(R.id.power)
        local = findViewById(R.id.local)
        damage = findViewById(R.id.demage)
        limit = findViewById(R.id.limit)
        age = findViewById(R.id.age)
        baseCoefficient = findViewById(R.id.base_coefficient)
        powerCoefficient = findViewById(R.id.power_coefficient)
        localCoefficient = findViewById(R.id.local_coefficient)
        damageCoefficient = findViewById(R.id.demage_coefficient)
        ageCoefficient = findViewById(R.id.age_coefficient)
        limitCoefficient = findViewById(R.id.limit_coefficient)
        expandableView=findViewById(R.id.expandable_view)
        dropImage=findViewById(R.id.drop_image)
        cardView=findViewById(R.id.card_view)

        setCoefficients("БТ", "КМ", "КТ","КБМ", "КО", "КВС")
        setExpandableCoefficients("2 754 - 4 432", "0,6 - 1,6", "0,64 - 1,99",
            "0,5 - 2,45", "0,90 - 1,93", "1 или 1,99")

        dropImage?.setOnClickListener {
            val transition: Transition= AutoTransition().setDuration(100)
            if(expandableView?.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView, transition)
                expandableView?.visibility = View.VISIBLE
                dropImage?.setBackgroundResource(R.drawable.drop_reverse)
            } else {
                expandableView?.visibility = View.GONE
                dropImage?.setBackgroundResource(R.drawable.drop)
            }
        }
    }

    fun setCoefficients(base: String, power: String, local: String, damage: String,
                        limit: String, age: String) {
        this.base.text = base
        this.power.text = power
        this.local.text = local
        this.damage.text = damage
        this.limit.text = limit
        this.age.text = age
    }

    fun setExpandableCoefficients(base: String, power: String, local: String, damage: String,
                                  limit: String, age: String) {
        this.baseCoefficient.text = base
        this.powerCoefficient.text = power
        this.localCoefficient.text = local
        this.damageCoefficient.text = damage
        this.ageCoefficient.text = limit
        this.limitCoefficient.text = age
    }


    fun createCityDialog(view: View) {
        val dialogCity = layoutInflater.inflate(R.layout.dialog_city, null, false)
        val editTextCity = dialogCity.findViewById<EditText>(R.id.enter)
        val buttonCity = findViewById<ImageButton>(R.id.button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        val formCity = findViewById<TextView>(R.id.city)
        formCity.text = "Город регистрации\nсобственника"
//        var newFormCity = editTextCity.text.toString()
        setDialogView(dialogBuilder, dialogCity)
    }

    fun createPowerDialog(view: android.view.View) {
        val dialogPower = layoutInflater.inflate(R.layout.dialog_power, null, false)
        val editTextPower = dialogPower.findViewById<EditText>(R.id.enter_power)
        val buttonPower = findViewById<ImageButton>(R.id.power_button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        setDialogView(dialogBuilder, dialogPower)
    }

    fun createDriversDialog(view: View) {
        val dialogDrivers = layoutInflater.inflate(R.layout.dialog_drivers, null, false)
        val editTextDrivers = dialogDrivers.findViewById<EditText>(R.id.enter_drivers)
        val buttonDrivers = findViewById<ImageButton>(R.id.drivers_button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        setDialogView(dialogBuilder, dialogDrivers)
    }

    fun createYoungestDriverDialog(view: View) {
        val dialogYoungestDriver = layoutInflater.inflate(R.layout.dialog_youngest_driver, null, false)
        val editTextYoungestDriver = dialogYoungestDriver.findViewById<EditText>(R.id.enter_youngest_driver)
        val buttonYoungestDriver = findViewById<ImageButton>(R.id.youngest_driver_button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        setDialogView(dialogBuilder, dialogYoungestDriver)
    }

    fun createMinExperienceDialog(view: View) {
        val dialogMinExperience = layoutInflater.inflate(R.layout.dialog_min_experience, null, false)
        val editTextMinExperience = dialogMinExperience.findViewById<EditText>(R.id.enter_min_experience)
        val buttonMinExperience = findViewById<ImageButton>(R.id.min_experience_button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        setDialogView(dialogBuilder, dialogMinExperience)
    }

    fun createYearsNoAccidentsDialog(view: View) {
        val dialogYearsNoAccidents = layoutInflater.inflate(R.layout.dialog_years_no_accidents, null, false)
        val editTextYearsNoAccidents = dialogYearsNoAccidents.findViewById<EditText>(R.id.enter_years_no_accidents)
        val buttonYearsNoAccidents = findViewById<ImageButton>(R.id.years_no_accidents_button_dialog)
        val dialogBuilder = BottomSheetDialog(this)
        setDialogView(dialogBuilder, dialogYearsNoAccidents)
        getAllFactorList()
    }

    fun setDialogView(dialogBuilder: BottomSheetDialog, dialog: View) {
        dialogBuilder.setContentView(dialog)
        dialogBuilder.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        dialogBuilder.behavior.peekHeight = 3000
        dialogBuilder.show()
    }
}
