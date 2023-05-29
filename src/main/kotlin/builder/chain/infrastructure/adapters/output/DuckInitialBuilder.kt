package builder.chain.infrastructure.adapters.output

import arrow.core.Either
import arrow.core.left
import builder.chain.model.DuckComponent
import builder.chain.model.DuckComponentName
import builder.chain.model.DuckNotFound
import builder.chain.model.InitialDuckBuildingError
import builder.chain.model.addNewComponent
import builder.chain.model.ports.BirdBuilder

data class DuckInitialBuilder(
    val birdBuilder: BirdBuilder, val song: String? = "quack quack"
) : BirdBuilder {
    override fun build(components: Map<DuckComponentName, DuckComponent>): Either<DuckNotFound, Map<DuckComponentName, DuckComponent>> = song?.let { song ->
        birdBuilder.build(components.addNewComponent(DuckComponent.Song.name, DuckComponent.Song(song)))
    } ?: InitialDuckBuildingError.left()
}