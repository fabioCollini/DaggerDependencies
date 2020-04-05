package dagger.dependencies

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton

interface Cache {
    fun load(): String?
}

@Singleton
class Database @Inject constructor(private val app: Application) {
    fun executeQuery(): String? = null
}

@Singleton
class DatabaseCache @Inject constructor(
    private val database: Database
) : Cache {
    override fun load(): String? = database.executeQuery()
}