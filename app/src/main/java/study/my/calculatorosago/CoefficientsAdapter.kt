package study.my.calculatorosago

//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//
//class CoefficientsAdapter internal constructor(context: Context?, coefficientsdrop: List<CoefficientsItems>) :
//    RecyclerView.Adapter<CoefficientsAdapter.ViewHolder>() {
//    private val inflater: LayoutInflater
//    private val coefficientsdrop: List<CoefficientsItems>
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view: View=inflater.inflate(R.layout.list_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val drop: CoefficientsItems=coefficientsdrop[position]
//        holder.titleView.setText("БТ")
//    }
//
//    override fun getItemCount(): Int {
//        return coefficientsdrop.size
//    }
//
//    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
//        val titleView: TextView
//
//        init {
//            titleView=view.findViewById(R.id.title)
//        }
//    }
//
//    init {
//        this.coefficientsdrop=coefficientsdrop
//        inflater=LayoutInflater.from(context)
//    }
//}