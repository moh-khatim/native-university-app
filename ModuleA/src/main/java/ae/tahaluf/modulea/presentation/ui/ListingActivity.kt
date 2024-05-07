package ae.tahaluf.modulea.presentation.ui


import ae.tahaluf.modulea.R
import ae.tahaluf.modulea.presentation.viewmodel.ListingViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    @Inject
    lateinit var listingViewModel: ListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        val recyclerView = findViewById<RecyclerView>(R.id.universityRecyclerView)
        val refreshButton = findViewById<Button>(R.id.refreshButton)
        val adapter = UniversityAdapter { university ->
            // Navigate to details screen
            val intent = Intent(this, ae.tahaluf.moduleb.presentation.ui.DetailsActivity::class.java)
            intent.putExtra("university_name", university.name)
            intent.putExtra("university_country", university.country)
            intent.putExtra("university_state", university.stateProvince)
            intent.putExtra("country_code", university.alphaTwoCode)
            intent.putExtra("domains", university.domains.firstOrNull())
            intent.putExtra("web_pages", university.webPages.firstOrNull())
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        listingViewModel.universities.observe(this, Observer {
            adapter.submitList(it)
        })

        listingViewModel.loadUniversities()

        refreshButton.setOnClickListener {
            // Call the API again
            listingViewModel.loadUniversities()
        }

    }
}