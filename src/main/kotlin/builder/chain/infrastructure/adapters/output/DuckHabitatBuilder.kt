package builder.chain.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import builder.chain.model.DuckComponent
import builder.chain.model.DuckComponentName
import builder.chain.model.DuckHabitatBuilderError
import builder.chain.model.DuckNotFound
import builder.chain.model.addNewComponent
import builder.chain.model.ports.BirdBuilder

data class DuckHabitatBuilder(
    val habitat: String? = "lake and land",
) : BirdBuilder {
    override fun build(components: Map<DuckComponentName, DuckComponent>): Either<DuckNotFound, Map<DuckComponentName, DuckComponent>> = habitat?.let { habitat ->
        (components.addNewComponent(DuckComponent.Habitat.name, DuckComponent.Habitat(habitat))).right()
    } ?: DuckHabitatBuilderError.left()
}