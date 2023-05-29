package builder.chain.application.service

import arrow.core.Either
import arrow.core.flatMap
import builder.chain.model.Duck
import builder.chain.model.DuckNotFound
import builder.chain.model.ports.BirdBuilder

data class CreateDuckService(
    val duckBuilder: BirdBuilder
) {
    operator fun invoke(): Either<DuckNotFound, Duck> = duckBuilder
        .build(emptyMap())
        .flatMap { Duck.with(it) }
}