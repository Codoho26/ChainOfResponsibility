package builder.original.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import builder.original.model.DuckNotFound
import builder.original.model.DuckSongBuilderError
import builder.original.model.DuckType

object DuckTypeBuilder {
    operator fun invoke(type: String? = "Mallard duck"): Either<DuckNotFound, DuckType> =
        type?.let {
            DuckType(it).right()
        } ?: DuckSongBuilderError.left()
}