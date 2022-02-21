package study.my.calculatorosago

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
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
        res.enqueue(object : Callback<ResultList> {
            override fun onResponse(call: Call<ResultList>, response: Response<ResultList>) {
                val result = response.body()!!.factors
                setCoefficients(result[0].headerValue!!, result[1].headerValue!!,
                    result[2].headerValue!!, result[3].headerValue!!, result[4].headerValue!!,
                    result[5].headerValue!!)
                setExpandableCoefficients(result[0].value!!, result[1].value!!, result[2].value!!,
                    result[3].value!!, result[4].value!!, result[5].value!!)
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
        setExpandableCoefficients("2 754 - 4 432 ₽", "0,6 - 1,6", "0,64 - 1,99",
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

    fun setCoefficients(
        base: String, power: String, local: String, damage: String,
        limit: String, age: String,
    ) {
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
        this.baseCoefficient.text = base
        this.powerCoefficient.text = power
        this.localCoefficient.text = local
        this.damageCoefficient.text = damage
        this.ageCoefficient.text = limit
        this.limitCoefficient.text = age
    }


    fun createCityDialog(view: View) {
        val dialogCity = layoutInflater.inflate(R.layout.dialog_city, null)
        val editTextCity = dialogCity.findViewById<EditText>(R.id.enter)
        val dialogBuilder = BottomSheetDialog(this)
        val formCity = findViewById<TextView>(R.id.city)
        val buttonDialog = dialogCity.findViewById<ImageView>(R.id.buttonDialog)
        editTextCity.inputType = InputType.TYPE_CLASS_TEXT
        setDialogView(dialogBuilder, dialogCity)
        buttonDialog.setOnClickListener {
            dialogBuilder.dismiss()
            createPowerDialog(view)
            formCity.text = editTextCity.text
            formCity.typeface=Typeface.DEFAULT_BOLD
            formCity.setTextColor(getColor(R.color.black))
        }
    }

    fun createPowerDialog(view: View) {
        val dialogPower = layoutInflater.inflate(R.layout.dialog_power, null)
        val editTextPower = dialogPower.findViewById<EditText>(R.id.enter_power)
        val buttonPower = dialogPower.findViewById<ImageButton>(R.id.power_button_dialog)
        val backButtonPower = dialogPower.findViewById<RelativeLayout>(R.id.back_button_power)
        val dialogBuilder = BottomSheetDialog(this)
        val formPower = findViewById<TextView>(R.id.carHP)
        editTextPower.inputType = InputType.TYPE_CLASS_NUMBER
        setDialogView(dialogBuilder, dialogPower)
        buttonPower.setOnClickListener {
            dialogBuilder.dismiss()
            createDriversDialog(view)
            formPower.text = editTextPower.text
            formPower.typeface=Typeface.DEFAULT_BOLD
            formPower.setTextColor(getColor(R.color.black))
        }
        backButtonPower.setOnClickListener {
            dialogBuilder.dismiss()
            createCityDialog(view)
        }
    }

    fun createDriversDialog(view: View) {
        val dialogDrivers = layoutInflater.inflate(R.layout.dialog_drivers, null, false)
        val editTextDrivers = dialogDrivers.findViewById<EditText>(R.id.enter_drivers)
        val buttonDrivers = dialogDrivers.findViewById<ImageButton>(R.id.drivers_button_dialog)
        val backButtonDrivers = dialogDrivers.findViewById<RelativeLayout>(R.id.back_button_drivers)
        val dialogBuilder = BottomSheetDialog(this)
        val formDrivers = findViewById<TextView>(R.id.drivers)
        editTextDrivers.inputType = InputType.TYPE_CLASS_NUMBER
        setDialogView(dialogBuilder, dialogDrivers)
        buttonDrivers.setOnClickListener {
            dialogBuilder.dismiss()
            createYoungestDriverDialog(view)
            formDrivers.text = editTextDrivers.text.toString() + " водителей"
            formDrivers.typeface=Typeface.DEFAULT_BOLD
            formDrivers.setTextColor(getColor(R.color.black))
        }
        backButtonDrivers.setOnClickListener {
            dialogBuilder.dismiss()
            createPowerDialog(view)
        }
    }

    fun createYoungestDriverDialog(view: View) {
        val dialogYoungestDriver = layoutInflater.inflate(R.layout.dialog_youngest_driver, null, false)
        val editTextYoungestDriver = dialogYoungestDriver.findViewById<EditText>(R.id.enter_youngest_driver)
        val buttonYoungestDriver = dialogYoungestDriver.findViewById<ImageButton>(R.id.youngest_driver_button_dialog)
        val backButtonYoungestDriver = dialogYoungestDriver.findViewById<RelativeLayout>(R.id.back_button_youngest_driver)
        val dialogBuilder = BottomSheetDialog(this)
        val formYoungestDriver = findViewById<TextView>(R.id.youngestDriver)
        editTextYoungestDriver.inputType = InputType.TYPE_CLASS_NUMBER
        setDialogView(dialogBuilder, dialogYoungestDriver)
        buttonYoungestDriver.setOnClickListener {
            dialogBuilder.dismiss()
            createMinExperienceDialog(view)
            formYoungestDriver.text = editTextYoungestDriver.text.toString() + " лет"
            formYoungestDriver.typeface=Typeface.DEFAULT_BOLD
            formYoungestDriver.setTextColor(getColor(R.color.black))
        }
        backButtonYoungestDriver.setOnClickListener {
            dialogBuilder.dismiss()
            createDriversDialog(view)
        }
    }

    fun createMinExperienceDialog(view: View) {
        val dialogMinExperience = layoutInflater.inflate(R.layout.dialog_min_experience, null, false)
        val editTextMinExperience = dialogMinExperience.findViewById<EditText>(R.id.enter_min_experience)
        val buttonMinExperience = dialogMinExperience.findViewById<ImageButton>(R.id.min_experience_button_dialog)
        val backButtonMinExperience = dialogMinExperience.findViewById<RelativeLayout>(R.id.back_button_min_experience)
        val dialogBuilder = BottomSheetDialog(this)
        val formMinExperience = findViewById<TextView>(R.id.minExperience)
        editTextMinExperience.inputType = InputType.TYPE_CLASS_NUMBER
        setDialogView(dialogBuilder, dialogMinExperience)
        buttonMinExperience.setOnClickListener {
            dialogBuilder.dismiss()
            createYearsNoAccidentsDialog(view)
            formMinExperience.text = editTextMinExperience.text.toString() + " лет"
            formMinExperience.typeface=Typeface.DEFAULT_BOLD
            formMinExperience.setTextColor(getColor(R.color.black))
        }
        backButtonMinExperience.setOnClickListener {
            dialogBuilder.dismiss()
            createYoungestDriverDialog(view)
        }
    }

    fun createYearsNoAccidentsDialog(view: View) {
        val dialogYearsNoAccidents = layoutInflater.inflate(R.layout.dialog_years_no_accidents, null, false)
        val editTextYearsNoAccidents = dialogYearsNoAccidents.findViewById<EditText>(R.id.enter_years_no_accidents)
        val buttonYearsNoAccidents = dialogYearsNoAccidents.findViewById<ImageButton>(R.id.years_no_accidents_button_dialog)
        val backButtonYearsNoAccidents = dialogYearsNoAccidents.findViewById<RelativeLayout>(R.id.back_button_years_no_accidents)
        val dialogBuilder = BottomSheetDialog(this)
        val formYearsNoAccidents = findViewById<TextView>(R.id.yearsWithoutAccident)
        val generalButton = findViewById<Button>(R.id.button)
        editTextYearsNoAccidents.inputType = InputType.TYPE_CLASS_NUMBER
        setDialogView(dialogBuilder, dialogYearsNoAccidents)
        buttonYearsNoAccidents.setOnClickListener {
            dialogBuilder.dismiss()
            formYearsNoAccidents.text = editTextYearsNoAccidents.text.toString() + " лет"
            formYearsNoAccidents.typeface=Typeface.DEFAULT_BOLD
            formYearsNoAccidents.setTextColor(getColor(R.color.black))
            getAllFactorList()
            generalButton.setHintTextColor(getColor(R.color.white))
            generalButton.setBackgroundResource(R.drawable.button_main_shape_changed)
            generalButton.isEnabled = true
        }
        backButtonYearsNoAccidents.setOnClickListener {
            dialogBuilder.dismiss()
            createMinExperienceDialog(view)
        }
    }

    fun setDialogView(dialogBuilder: BottomSheetDialog, dialog: View) {
        dialogBuilder.setContentView(dialog)
        dialogBuilder.show()
        dialogBuilder.setOnDismissListener {
            getAllFactorList()
        }
    }
}
