package study.my.calculatorosago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import study.my.calculatorosago.coefficientsRecyclerView.CoefficientsItem
import study.my.calculatorosago.coefficientsRecyclerView.RvAdapter
import study.my.calculatorosago.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var coefficientList = ArrayList<CoefficientsItem>()
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun createRecyclerView() {
        binding.rvList.layoutManager = LinearLayoutManager(this@MainActivity)
        rvAdapter = RvAdapter(coefficientList)
        binding.rvList.adapter = rvAdapter
        val baseRV = "БТ"
        var powerRV = "КМ"
        var localRV = "КТ"
        var demageRV = "КБМ"
        var limitRV = "КО"
        var ageRV = "КВС"
        var base = "2754 - 4 432"
        var power = "0,6 - 1,6"
        var local = "0,64 - 1,99"
        var demage = "0,5 - 2,45"
        var age = "0,90 - 1,93"
        var limit = "1 или 1,99"

        val coefficient1 = CoefficientsItem(
            baseRV,
            powerRV,
            localRV,
            demageRV,
            limitRV,
            ageRV,
            base,
            power,
            local,
            demage,
            age,
            limit,
            false
        )
        coefficientList.add(coefficient1)
        rvAdapter.notifyItemChanged(0)
    }

}