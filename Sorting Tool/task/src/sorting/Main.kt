package sorting

object Sorter {
    val inputList = mutableListOf<String>()

    fun sort() {
        do {
            val input = readlnOrNull()?: break
            input.trim().split(" +".toRegex()).filter { it.isNotBlank()}
                .forEach { inputList.add(it) }
        } while (true)
        listInfo()
    }

    private fun listInfo() {
        var greatestNum = Integer.MIN_VALUE
        for (item in inputList) {
            greatestNum = if ((item.toIntOrNull() ?: Integer.MIN_VALUE) > greatestNum) item.toInt() else greatestNum
        }
        val greatestCount = inputList.count { it.toInt() == greatestNum }
        println("Total numbers: ${inputList.count()}")
        println("The greatest number: $greatestNum ($greatestCount time(s)).")
    }
}

fun main() {
    Sorter.sort()
}
