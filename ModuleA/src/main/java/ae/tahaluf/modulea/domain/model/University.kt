package ae.tahaluf.modulea.domain.model

import com.google.gson.annotations.SerializedName

data class University(
    @SerializedName("state-province") var stateProvince : String?,
    @SerializedName("domains") var domains : List<String>,
    @SerializedName("web_pages") var webPages: List<String>,
    @SerializedName("name") var name: String,
    @SerializedName("alpha_two_code") var alphaTwoCode: String?,
    @SerializedName("country") var country: String
)