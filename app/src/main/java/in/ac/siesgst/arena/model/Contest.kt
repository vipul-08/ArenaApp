package `in`.ac.siesgst.arena.model

data class Contest (
    var _id: String,
    var type: String,
    var name: String,
    var code: String,
    var description: String,
    var startsAt: String,
    var endsAt: String,
    var contestAdmin: List<User>
)