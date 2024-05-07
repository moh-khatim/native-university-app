package ae.tahaluf.moduleb.presentation.ui

import ae.tahaluf.moduleb.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val universityName = findViewById<TextView>(R.id.universityName)
        val universityCountry =    findViewById<TextView>(R.id.universityCountry)
        val universityState = findViewById<TextView>(R.id.universityState)
        val countryCode = findViewById<TextView>(R.id.CountryCode)
        val webPage = findViewById<TextView>(R.id.webPage)
        val backButton = findViewById<Button>(R.id.BackButton)

        val name = intent.getStringExtra("university_name")
        val country = intent.getStringExtra("university_country")
        val state = intent.getStringExtra("university_state")
        val code = intent.getStringExtra("country_code")
        val firstWebPage = intent.getStringExtra("web_pages") // nullable
        universityName.text = name
        universityCountry.text = country
        universityState.text = state
        countryCode.text = code
        if(firstWebPage != null)
            webPage.text= firstWebPage

        backButton.setOnClickListener {
            // Navigate back to the listing activity
            finish() // Close the current activity
        }
    }
}
