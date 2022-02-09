package study.my.calculatorosago

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.TypedValue
import android.widget.ImageButton
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTextCoefficients()


    }

    fun setTextCoefficients() {
        val x = getString(R.string.x)
        val base = getString(R.string.base)
        var coefficientPower = getString(R.string.coefficientPower)
        var coefficientLocal = getString(R.string.coefficientLocal)
        var coefficientAccident = getString(R.string.coefficientAccident)
        var coefficientLimit = getString(R.string.coefficientLimit)
        var coefficientAge = getString(R.string.coefficientAge)
        var textCoefficient: TextView = findViewById(R.id.coefficient_text)
        var textColor ="<font color=#00afff>$base</font><font color=#B4BAC3>$x</font><font color=#00afff>$coefficientPower</font>" +
                "<font color=#B4BAC3>$x</font><font color=#00afff>$coefficientLocal</font><font color=#B4BAC3>$x</font>" +
                "<font color=#00afff>$coefficientAccident</font><font color=#B4BAC3>$x</font><font color=#00afff>$coefficientLimit</font>" +
                "<font color=#B4BAC3>$x</font><font color=#00afff>$coefficientAge</font>"
        textCoefficient.text=Html.fromHtml(textColor)
        textCoefficient.setTypeface(null, Typeface.BOLD)
        textCoefficient.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)

    }

    fun setCoefficients(view: android.view.View) {
        val coefficients = findViewById<ImageButton>(R.id.drop_button)
        coefficients.setBackgroundResource(R.drawable.drop_button_reverse)

    }
}