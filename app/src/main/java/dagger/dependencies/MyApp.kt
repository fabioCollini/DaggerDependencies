package dagger.dependencies

import android.app.Application

class MyApp : Application() {
    val component : MyComponent by lazy {
        DaggerMyComponent.factory().create(this)
    }
}