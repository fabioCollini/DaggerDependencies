package dagger.dependencies

import dagger.Component
import javax.inject.Inject

class Dependency @Inject constructor()

class Minimal @Inject constructor(private val dependency: Dependency)

@Component
interface MinimalComponent {
    val minimal: Minimal
}

fun main() {
    println(DaggerMinimalComponent.create().minimal)
}