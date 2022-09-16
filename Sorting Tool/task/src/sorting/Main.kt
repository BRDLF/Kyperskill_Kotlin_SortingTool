package sorting

import kotlin.math.roundToInt

object Sorter {
    private val inputList = mutableListOf<String>()
    private val argumentsList = mutableListOf<String>()
    private var dataType: DataType = DataType.WORD
    private var sortType: SortType = SortType.NATURAL

    private val validArguments = listOf<String>("-sortingType", "-dataType")


    enum class DataType(val argName: String, val print: String) {
        LONG("long", "number"),
        LINE("line", "line"),
        WORD("word", "word");

        companion object {
            fun byArgVal(argName: String): DataType {
                for (value in DataType.values()) {
                    if (value.argName == argName) return value
                }
                return WORD
            }
        }

    }
    enum class SortType(val argName: String) {
        NATURAL("list"),
        COUNT("byCount");

        companion object {
            fun byArgVal(argName: String): SortType {
                for (value in SortType.values()) {
                    if (value.argName == argName) return value
                }
                return NATURAL
            }
        }
    }
    private fun updateArgumentsList(args: Array<String>) {
        argumentsList.clear()
        argumentsList.addAll(args)
    }

    private fun parseArgs() {
        for (arg in argumentsList.filter { it.startsWith("-") }) {
            if (!validArguments.contains(arg)) {
                println("\"${arg}\" is not a valid parameter. It will be skipped")
                continue
            }
            when (arg) {
                "-dataType" -> {
                    val argVal = argumentValueOf(arg)?: throw IllegalArgumentException("No data type defined!")
                    dataType = DataType.byArgVal(argVal)
                }
                "-sortingType" -> {
                    val argVal = argumentValueOf(arg)?: throw IllegalArgumentException("No sort type defined!")
                    sortType = SortType.byArgVal(argVal)
                }
            }
        }
    }

    private fun argumentValueOf(arg: String): String? {
        val argAddress = argumentsList.indexOf(arg)
        if (argAddress == - 1) throw IllegalArgumentException("validateArg passed for $arg which is not in argsList")
        if (argAddress == argumentsList.lastIndex) return null
        if (argumentsList[argAddress + 1].startsWith("-")) return null
        return argumentsList[argAddress + 1]
    }

    fun run(args: Array<String>) {
        updateArgumentsList(args)
        try {
            parseArgs()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }


        fillList()

        printList(sortList())
    }

    private fun fillList() {
        when (dataType) {
            DataType.LINE -> do {
                val input = readlnOrNull()?: break
                if (input.isNotBlank()) inputList.add(input)
            } while (true)
            DataType.LONG -> do {
                val input = readlnOrNull()?: break
                input.trim().split(" +".toRegex())
                    .filter { it.isNotBlank() }
                    .forEach {
                        it.toIntOrNull()?: println("$it is not a valid parameter. It will be skipped.")
                        inputList.add(it)
                    }
            } while (true)
            DataType.WORD -> do {
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
