package com.example.apriltagapp.model

data class Tag(
    /** 고유 태그 id */
    val id: Int = -1, // default value
    /** 연결된 태그 */
    val linkedTags: ArrayList<LinkedTag> = arrayListOf<LinkedTag>() // id, 가중치
) {
    /** 태그가 담고 있는 장소 */
    val spots: MutableList<Spot> = mutableListOf()

    var x: Double = 0.0
    var y: Double = 0.0
    var rot: Double = 0.0

    fun addSpot(spot: Spot) {
        spots.add(spot)
    }//

    override fun equals(other: Any?): Boolean {
        return if (other is Tag) {
            id == other.id
        } else false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

}