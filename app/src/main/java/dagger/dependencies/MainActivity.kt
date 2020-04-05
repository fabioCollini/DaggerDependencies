package dagger.dependencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.dependencies.utils.viewModels
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<MyViewModel>

    private val viewModel by viewModels { viewModelProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.text).text = viewModel.loadData()
    }
}