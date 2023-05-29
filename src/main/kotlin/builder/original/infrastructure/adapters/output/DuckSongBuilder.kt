package builder.original.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import builder.original.model.DuckNotFound
import builder.original.model.DuckSongBuilderError
import builder.original.model.Song

object DuckSongBuilder {
    operator fun invoke(song: String? = "quack quack"): Either<DuckNotFound, Song> =
        song?.let {
            Song(it).right()
        } ?: DuckSongBuilderError.left()
}