package study.my.calculatorosago

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {
    var expandableView: ConstraintLayout?=null
    var dropImage: ImageView?=null
    var cardView: CardView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createExpandableView()
    }

    fun createExpandableView() {
        val base = findViewById<TextView>(R.id.base)
        val power = findViewById<TextView>(R.id.power)
        val local = findViewById<TextView>(R.id.local)
        val demage = findViewById<TextView>(R.id.demage)
        val limit = findViewById<TextView>(R.id.limit)
        val age = findViewById<TextView>(R.id.age)
        val baseCoefficient = findViewById<TextView>(R.id.base_coefficient)
        val powerCoefficient = findViewById<TextView>(R.id.power_coefficient)
        val localCoefficient = findViewById<TextView>(R.id.local_coefficient)
        val demageCoefficient = findViewById<TextView>(R.id.demage_coefficient)
        val ageCoefficient = findViewById<TextView>(R.id.age_coefficient)
        val limitCoefficient = findViewById<TextView>(R.id.limit_coefficient)
        base.text = "БТ"
        power.text = "КМ"
        local.text = "КТ"
        demage.text = "КБМ"
        limit.text = "КО"
        age.text = "КВС"
        baseCoefficient.text = "2 754 - 4 432"
        powerCoefficient.text = "0,6 - 1,6"
        localCoefficient.text = "0,64 - 1,99"
        demageCoefficient.text = "0,5 - 2,45"
        ageCoefficient.text = "0,90 - 1,93"
        limitCoefficient.text = "1 или 1,99"
        expandableView=findViewById(R.id.expandable_view)
        dropImage=findViewById(R.id.drop_image)
        cardView=findViewById(R.id.card_view)
        dropImage?.setOnClickListener {
            val transition: Transition= AutoTransition().setDuration(100)
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

    fun createCityDialog(view: View) {
        val dialog = layoutInflater.inflate(R.layout.dialog_city, null, false)
        val editText = dialog.findViewById<EditText>(R.id.enter)
        val dialogBuilder = BottomSheetDialog(this)
        dialogBuilder.setContentView(dialog)
        dialogBuilder.show()
    }
}
