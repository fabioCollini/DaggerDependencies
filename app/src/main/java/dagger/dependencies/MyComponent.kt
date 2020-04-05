package dagger.dependencies

import android.app.Application
import dagger.*
import javax.inject.Singleton

@Component(modules = [MyModule::class])
@Singleton
interface MyComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyComponent
    }
}

@Module
interface MyModule {
    @Binds
    fun bindsCache(impl: DatabaseCache): Cache

    companion object {
        @Singleton
        @Provides
        fun provideApi(): Api {
            return createApiImplementationUsingRetrofit()
        }
    }
}

private fun createApiImplementationUsingRetrofit(): Api {
    return object : Api {
        override fun load(): String = "Hello world!"
    }
}