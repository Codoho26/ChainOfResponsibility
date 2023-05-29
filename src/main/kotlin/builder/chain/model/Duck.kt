package builder.chain.model

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import builder.chain.model.DuckComponent.Food
import builder.chain.model.DuckComponent.Habitat
import builder.chain.model.DuckComponent.Song
import builder.chain.model.DuckComponent.Type
import builder.chain.model.DuckComponentName.FOOD
import builder.chain.model.DuckComponentName.HABITAT
import builder.chain.model.DuckComponentName.SONG
import builder.chain.model.DuckComponentName.TYPE

data class Duck(
    val song: Song, val type: Type? = null, val food: Food? = null, val habitat: Habitat? = null
) {
    companion object {
        fun with(
            components: Map<DuckComponentName, DuckComponent>,
        ): Either<DuckNotFound, Duck> = components.verifyAll()
            .flatMap {
                Duck(
                    song = it.getValue(SONG) as Song,
                    type = it.getValue(TYPE) as Type,
                    food = it.getValue(FOOD) as Food,
                    habitat = it.getValue(HABITAT) as Habitat,
                ).right()
            }
    }
}
