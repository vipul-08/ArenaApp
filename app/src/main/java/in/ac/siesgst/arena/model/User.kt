package `in`.ac.siesgst.arena.model

data class User (
    val _id: String,
    val name: String,
    val username: String,
    val email: String,
    val about: String,
    val ratings: Int,
    val codeforcesLink: String,
    val codechefLink: String,
    val githubLink: String
)