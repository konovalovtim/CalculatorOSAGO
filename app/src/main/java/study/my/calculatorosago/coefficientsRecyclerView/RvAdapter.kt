package study.my.calculatorosago.coefficientsRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.my.calculatorosago.R
import study.my.calculatorosago.databinding.SingleItemBinding

class RvAdapter(private var coefficientsItemList: List<CoefficientsItem>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(coefficientsItemList[position]){

                if (this.expand)
                    binding.dropImage.setImageResource(R.drawable.drop_reverse)
                binding.baseRv.text = this.baseRv
                binding.powerRv.text = this.powerRv
                binding.localRv.text = this.localRv
                binding.demageRv.text = this.demageRv
                binding.limitRv.text = this.limitRv
                binding.ageRv.text = this.ageRv
                binding.base.text = this.baseCoefficient
                binding.powerCoefficient.text = this.power
                binding.localCoefficient.text = this.local
                binding.demageCoefficient.text = this.demage
                binding.ageCoefficient.text = this.age
                binding.limitCoefficient.text = this.limit
                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE
                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyItemChanged(position)

                }
            }
        }
    }
    override fun getItemCount(): Int {
        return coefficientsItemList.size
    }
}
