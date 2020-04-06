package dagger.dependencies

import android.app.Application
import dagger.*
import javax.inject.Inject
import javax.inject.Provider
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
        fun provideApi(): Api = createApiImplementationUsingRetrofit()
    }
}

@Module
object MyModule2 {
    @Singleton
    @Provides
    fun provideApi(): Api = createApiImplementationUsingRetrofit()

    @Singleton
    @Provides
    fun provideCache(
        databaseCache: Provider<DatabaseCache>,
        inMemoryCache: Provider<InMemoryCache>
    ): Cache {
        return if (useInMemoryCache())
            inMemoryCache.get()
        else
            databaseCache.get()
    }
}

@Singleton
class CacheFactory @Inject constructor(
    private val databaseCache: Provider<DatabaseCache>,
    private val inMemoryCache: Provider<InMemoryCache>
) {
    fun create() : Cache {
        return if (useInMemoryCache())
            inMemoryCache.get()
        else
            databaseCache.get()
    }
}

@Module
object MyModule3 {
    @Singleton
    @Provides
    fun provideApi(): Api = createApiImplementationUsingRetrofit()

    @Singleton
    @Provides
    fun provideCache(factory: CacheFactory): Cache = factory.create()
}

fun useInMemoryCache(): Boolean {
    return true
}

private fun createApiImplementationUsingRetrofit(): Api {
    return object : Api {
        override fun load(): String = "Hello world!"
    }
}