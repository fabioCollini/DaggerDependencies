package dagger.dependencies

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCase @Inject constructor(
    private val api: Api,
    private val cache: Cache
) {
    fun loadData() = cache.load() ?: api.load()
}