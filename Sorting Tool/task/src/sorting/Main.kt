package sorting

import kotlin.math.roundToInt

object Sorter {
    private val inputList = mutableListOf<String>()
    private val argumentsList = mutableListOf<String>()
    private var dataType: DataType = DataType.WORD
    private var sortType: SortType = SortType.NATURAL
    enum class DataType(val arg: String, val print: String) {
        LONG("long", "number"),
        LINE("line", "line"),
        WORD("word", "word");
    }
    enum class SortType(val arg: String) {
        NATURAL("list"),
        COUNT("byCount");
    }
    private fun updateArgs(args: Array<String>) {
        argumentsList.clear()
        argumentsList.addAll(args)
    }
    private fun determineData() {
        val argAddress = argumentsList.indexOf("-dataType")
        if (argAddress != -1 && argAddress != argumentsList.size) {
            val caughtDataType = argumentsList[argAddress + 1]
            for(targetType in DataType.values()) {
                if (caughtDataType == targetType.arg) dataType = targetType
            }
        }
        else dataType = DataType.WORD
    }
    private fun determineSort() {
        val argAddress = argumentsList.indexOf("-sortingType")
        if (argAddress != -1 && argAddress != argumentsList.size) {
            val caughtSortType = argumentsList[argAddress + 1]
            for(targetType in SortType.values()) {
                if (caughtSortType == targetType.arg) sortType = targetType
            }
        }
        else sortType = SortType.NATURAL
    }

    fun run(args: Array<String>) {
        updateArgs(args)
        determineData()
        determineSort()

        fillList()

        printList(sortList())
    }

    private fun fillList() {
        when (dataType) {
            DataType.LINE -> do {
                val input = readlnOrNull()?: break
                if (input.isNotBlank()) inputList.add(input)
            } while (true)
            else -> do {
                val input = readlnOrNull()?: break
                input.trim().split(" +".toRegex())
                    .filter { it.isNotBlank()}
                    .forEach { inputList.add(it) }
            } while (true)
        }
    }
    private fun sortList(): List<String> {
        val sortedStep = when (dataType) {
            DataType.LONG -> inputList.map { it.toInt() }.sorted().map { it.toString() }
            else -> inputList.sorted()
        }
        return when (sortType) {
            SortType.COUNT -> {
                sortByCount(sortedStep)
            }
            SortType.NATURAL -> sortedStep
        }
    }
    private fun sortByCount(passedList: List<String>): List<String> {
        val countList = mutableMapOf<String, Int>()
        for (item in passedList) {
            if (countList.containsKey(item)) continue
            countList[item] = passedList.count { it == item }
        }
        return countList.keys.toMutableList().sortedBy { countList[it] }
    }

    private fun printList(sortedList: List<String> = inputList) {
        println("Total ${dataType.print}s: ${inputList.size}.")
        when (sortType) {
            SortType.NATURAL -> {
                print("Sorted data: ")
                for (item in sortedList) {
                    print(item)
                    if (dataType.name == "LINE") println() else print(" ")
                }
                if (dataType.name != "LINE") println()
            }
            SortType.COUNT -> {
                for (item in sortedList) {
                    val itemCount = inputList.count { it == item }
                    val itemPercent = ((itemCount.toDouble() / inputList.size) * 100).roundToInt()
                    println("${item}: $itemCount time(s), ${itemPercent}%")
                }
            }
        }
    }

}

fun main(args: Array<String>) {
    Sorter.run(args)
}
