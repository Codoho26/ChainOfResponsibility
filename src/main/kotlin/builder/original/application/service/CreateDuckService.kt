package builder.original.application.service

import arrow.core.Either
import arrow.core.Tuple4
import arrow.core.flatMap
import builder.original.infrastructure.adapters.output.DuckFoodBuilder
import builder.original.infrastructure.adapters.output.DuckHabitatBuilder
import builder.original.infrastructure.adapters.output.DuckSongBuilder
import builder.original.infrastructure.adapters.output.DuckTypeBuilder
import builder.original.model.Duck
import builder.original.model.DuckNotFound

data class CreateDuckService(
    val duckSongBuilder: DuckSongBuilder,
    val duckTypeBuilder: DuckTypeBuilder,
    val duckFoodBuilder: DuckFoodBuilder,
    val duckHabitatBuilder: DuckHabitatBuilder,

) {
    fun invoke(
        song: String?,
        type: String?,
        food: String?,
        habitat: String?,
    ): Either<DuckNotFound, Duck> =
        duckSongBuilder(song)
            .flatMap { duckSong -> duckTypeBuilder(type).map { Pair(duckSong, it) } }
            .flatMap { (duckSong, duckType) -> duckFoodBuilder(food).map { Triple(duckSong, duckType, it) } }
            .flatMap { (duckSong, duckType, duckFood) -> duckHabitatBuilder(habitat).map { Tuple4(duckSong, duckType, duckFood, it) } }
            .map { (duckSong, duckType, duckFood, duckHabitat) ->
                Duck(
                    song = duckSong,
                    type = duckType,
                    food = duckFood,
                    habitat = duckHabitat
                )
            }
}