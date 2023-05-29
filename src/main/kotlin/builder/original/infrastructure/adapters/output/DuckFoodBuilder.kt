package builder.original.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import builder.original.model.DuckFood
import builder.original.model.DuckFoodBuilderError
import builder.original.model.DuckNotFound

object DuckFoodBuilder {
    operator fun invoke(food: String? = "green grass"): Either<DuckNotFound, DuckFood> = food?.let {
        DuckFood(it).right()
    } ?: DuckFoodBuilderError.left()
}