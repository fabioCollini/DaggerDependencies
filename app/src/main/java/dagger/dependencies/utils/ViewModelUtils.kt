package dagger.dependencies.utils

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider


inline fun <reified VM : ViewModel> AppCompatActivity.viewModels(crossinline producer: () -> Provider<VM>): Lazy<VM> =
    viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(c: Class<T>) = producer().get() as T
        }
    }

//fun <VM : ViewModel> Provider<VM>.factory(): ViewModelProvider.Factory {
//    return object : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(aClass: Class<T>) = get() as T
//    }
//}