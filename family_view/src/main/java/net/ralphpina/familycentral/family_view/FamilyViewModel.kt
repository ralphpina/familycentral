package net.ralphpina.familycentral.family_view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.Disposable
import net.ralphpina.familycentral.family.Family
import net.ralphpina.familycentral.family.FamilyRepository
import javax.inject.Inject

class FamilyViewModel(familyRepository: FamilyRepository) : ViewModel() {
    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val familyRepository: FamilyRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            FamilyViewModel(familyRepository) as T
    }

    val family: MutableLiveData<Family> by lazy {
        MutableLiveData<Family>()
    }

    private val disposable: Disposable

    init {
        disposable = familyRepository.observe()
            .doOnNext { family.value = it }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
