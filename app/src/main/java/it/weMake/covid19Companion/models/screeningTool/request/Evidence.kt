package it.weMake.covid19Companion.models.screeningTool.request

data class Evidence(
    val id: String,
    val choiceId: String
){
    override fun equals(other: Any?): Boolean {
        if (other is Evidence){
            return other.id == id && other.choiceId == choiceId
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + choiceId.hashCode()
        return result
    }
}