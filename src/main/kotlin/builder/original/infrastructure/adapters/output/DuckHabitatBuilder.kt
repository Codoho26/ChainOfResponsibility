package builder.original.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import builder.original.model.DuckHabitat
import builder.original.model.DuckHabitatBuilderError
import builder.original.model.DuckNotFound

object DuckHabitatBuilder {
    operator fun invoke(habitat: String? = "lake and land"): Either<DuckNotFound, DuckHabitat> = habitat?.let {
        DuckHabitat(it).right()
    } ?: DuckHabitatBuilderError.left()
}