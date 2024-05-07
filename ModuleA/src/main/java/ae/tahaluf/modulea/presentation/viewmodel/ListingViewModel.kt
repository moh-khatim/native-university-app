package ae.tahaluf.modulea.presentation.viewmodel



import ae.tahaluf.modulea.data.repository.UniversityRepository
import ae.tahaluf.modulea.domain.model.University
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListingViewModel @Inject constructor(
    private val universityRepository: UniversityRepository
) : ViewModel() {

private val _universities = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>>
        get() = _universities

    fun loadUniversities() {
        viewModelScope.launch {
            val universities = universityRepository.fetchUniversities()
            _universities.value = universities
        }
    }
}
