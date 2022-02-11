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
/*
создание recyclerview
 */
    private fun createRecyclerView() {
        binding.rvList.layoutManager = LinearLayoutManager(this@MainActivity)
        rvAdapter = RvAdapter(coefficientList)
        binding.rvList.adapter = rvAdapter
        val x = "×"
        val base = "БТ"
        var coefficientPower = "КМ"
        var coefficientLocal = "КТ"
        var coefficientAccident = "КБМ"
        var coefficientLimit = "КО"
        var coefficientAge = "КВС"

        val coefficient1 = CoefficientsItem(
            "2754 - 4 432",
            "0,6 - 1,6",
            "0,64 - 1,99",
            "0,5 - 2,45",
            "0,90 - 1,93",
            "1 или 1,99",
            base + x + coefficientPower + x + coefficientLocal + x +
                    coefficientAccident + x + coefficientLimit + x + coefficientAge,
            false
        )
        coefficientList.add(coefficient1)
        rvAdapter.notifyItemChanged(0)
    }

}