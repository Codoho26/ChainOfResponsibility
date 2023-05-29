package builder.chain.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import builder.chain.model.DuckComponent
import builder.chain.model.DuckComponentName
import builder.chain.model.DuckNotFound
import builder.chain.model.DuckTypeBuilderError
import builder.chain.model.addNewComponent
import builder.chain.model.ports.BirdBuilder

data class DuckTypeBuilder(
    val birdBuilder: BirdBuilder, val type: String? = "Mallard duck"
) : BirdBuilder {
    override fun build(components: Map<DuckComponentName, DuckComponent>): Either<DuckNotFound, Map<DuckComponentName, DuckComponent>> = type?.let { type ->
        birdBuilder.build(components.addNewComponent(DuckComponent.Type.name, DuckComponent.Type(type)))
    } ?: DuckTypeBuilderError.left()
}