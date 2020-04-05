package dagger.dependencies

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MyViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    fun loadData(): String = useCase.loadData()
}