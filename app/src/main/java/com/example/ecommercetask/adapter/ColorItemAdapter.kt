import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercetask.R

class ColorItemAdapter(
    private val colors: List<Int>,
    private val onColorSelected: (Int) -> Unit
) : RecyclerView.Adapter<ColorItemAdapter.ColorViewHolder>() {

    private var selectedPosition = 0

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageEyeColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color_circle, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.imageView.setImageResource(colors[position])

        holder.imageView.setBackgroundResource(
            if (selectedPosition == position) R.drawable.bg_circle_color
            else R.drawable.bg_color_drawable
        )

        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onColorSelected(colors[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = colors.size
}
