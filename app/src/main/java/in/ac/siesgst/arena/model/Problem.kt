package `in`.ac.siesgst.arena.model

data class Problem (
    var _id: String,
    var name: String,
    var code: String,
    var points: Int,
    var contestCode: String,
    var tags: List<String>
)