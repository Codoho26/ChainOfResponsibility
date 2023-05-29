package builder.chain.model

import arrow.core.Either
import arrow.core.left
import arrow.core.right

sealed class DuckComponent {
    data class Song(val value: String) : DuckComponent() {
        companion object {
            val name = DuckComponentName.SONG
        }
    }

    data class Type(val value: String) : DuckComponent() {
        companion object {
            val name = DuckComponentName.TYPE
        }
    }

    data class Food(val value: String) : DuckComponent() {
        companion object {
            val name = DuckComponentName.FOOD
        }
    }

    data class Habitat(val value: String) : DuckComponent() {
        companion object {
            val name = DuckComponentName.HABITAT
        }
    }
}

fun  Map<DuckComponentName, DuckComponent>.addNewComponent(name: DuckComponentName, component: DuckComponent) = this + mapOf(name to component)

enum class DuckComponentName {
    SONG, TYPE, FOOD, HABITAT
}

fun Map<DuckComponentName, DuckComponent>.verifyAll(): Either<MissingBirdComponent, Map<DuckComponentName, DuckComponent>> =
    DuckComponentName.values()
        .filter { !this.contains(it) }
        .takeIf { it.isNotEmpty() }
        ?.let { MissingBirdComponent.left() } ?: this.right()