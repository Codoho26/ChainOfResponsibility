package builder.chain.application.service

import arrow.core.left
import arrow.core.right
import builder.chain.infrastructure.adapters.output.DuckFoodBuilder
import builder.chain.infrastructure.adapters.output.DuckHabitatBuilder
import builder.chain.infrastructure.adapters.output.DuckInitialBuilder
import builder.chain.infrastructure.adapters.output.DuckTypeBuilder
import builder.chain.model.Duck
import builder.chain.model.DuckComponent
import builder.chain.model.DuckHabitatBuilderError
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateDuckServiceTest {

    private val duckHabitatBuilder = DuckHabitatBuilder(habitat = "some lake")

    private val duckFoodBuilder = DuckFoodBuilder(birdBuilder = duckHabitatBuilder, food = "some green grass or sea weed")

    private val duckTypeBuilder = DuckTypeBuilder(birdBuilder = duckFoodBuilder, type = "test Mallard duck")

    private val duckInitialBuilder = DuckInitialBuilder(birdBuilder = duckTypeBuilder, song = "quick quack quck")

    private val createDuckService = CreateDuckService(duckInitialBuilder)

    @Test
    fun `should create a new duck`() {
        val expected = Duck(
            song = DuckComponent.Song("quick quack quck"),
            type = DuckComponent.Type("test Mallard duck"),
            food = DuckComponent.Food("some green grass or sea weed"),
            habitat = DuckComponent.Habitat("some lake")
        )

        val result = createDuckService()

        assertThat(result).isEqualTo(expected.right())
    }

    @Test
    fun `should fail creating a duck for any reason`() {
        val duckHabitatBuilder = duckHabitatBuilder.copy(habitat = null)
        val duckFoodBuilder = DuckFoodBuilder(birdBuilder = duckHabitatBuilder, food = "some green grass or sea weed")
        val duckTypeBuilder = DuckTypeBuilder(birdBuilder = duckFoodBuilder, type = "test Mallard duck")
        val duckInitialBuilder = DuckInitialBuilder(birdBuilder = duckTypeBuilder, song = "quick quack quck")
        val createDuckService = CreateDuckService(duckInitialBuilder)

        val result = createDuckService()

        assertThat(result).isEqualTo(DuckHabitatBuilderError.left())
    }
}