package builder.chain.model.ports

import arrow.core.Either
import builder.chain.model.DuckComponent
import builder.chain.model.DuckComponentName
import builder.chain.model.DuckNotFound

interface BirdBuilder {
    fun build(components: Map<DuckComponentName, DuckComponent>): Either<DuckNotFound, Map<DuckComponentName, DuckComponent>>
}