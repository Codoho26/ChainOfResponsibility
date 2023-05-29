package builder.chain.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import builder.chain.model.DuckComponent
import builder.chain.model.DuckComponentName
import builder.chain.model.DuckFoodBuilderError
import builder.chain.model.DuckNotFound
import builder.chain.model.addNewComponent
import builder.chain.model.ports.BirdBuilder

data class DuckFoodBuilder(
    val birdBuilder: BirdBuilder,
    val food: String? = "green grass",
) : BirdBuilder {
    override fun build(components: Map<DuckComponentName, DuckComponent>): Either<DuckNotFound, Map<DuckComponentName, DuckComponent>> = food?.let { food ->
        birdBuilder.build(components.addNewComponent(DuckComponent.Food.name, DuckComponent.Food(food)))
    } ?: DuckFoodBuilderError.left()
}