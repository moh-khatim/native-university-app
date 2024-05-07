package ae.tahaluf.modulea.presentation.ui

import ae.tahaluf.modulea.R
import ae.tahaluf.modulea.domain.model.University
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UniversityAdapter(
    private val onClick: (University) -> Unit
) : ListAdapter<University, UniversityAdapter.UniversityViewHolder>(UniversityDiffCallback()) {

    class UniversityViewHolder(itemView: View, val onClick: (University) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val universityNameTextView: TextView = itemView.findViewById(R.id.universityName)
        private val universityCountryTextView: TextView = itemView.findViewById(R.id.universityCountry)

        private var currentUniversity: University? = null

        init {
            itemView.setOnClickListener {
                currentUniversity?.let(onClick)
            }
        }

        fun bind(university: University) {
            currentUniversity = university
            universityNameTextView.text = university.name
            universityCountryTextView.text = university.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_university, parent, false)
        return UniversityViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = getItem(position)
        holder.bind(university)
    }
}

class UniversityDiffCallback : DiffUtil.ItemCallback<University>() {
    override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
        return oldItem == newItem
    }
}