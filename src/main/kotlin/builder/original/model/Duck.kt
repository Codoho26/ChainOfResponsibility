package builder.original.model

data class Duck(
    val song: Song,
    val type: DuckType? = null,
    val food: DuckFood? = null,
    val habitat: DuckHabitat? = null
)

data class Song(val value: String)

data class DuckType(val value: String)

data class DuckFood(val value: String)

data class DuckHabitat(val value: String)
