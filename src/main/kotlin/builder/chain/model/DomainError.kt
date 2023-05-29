package builder.chain.model

interface DuckNotFound

object InitialDuckBuildingError : DuckNotFound

object DuckTypeBuilderError : DuckNotFound

object DuckFoodBuilderError : DuckNotFound

object DuckHabitatBuilderError : DuckNotFound

object MissingBirdComponent : DuckNotFound
