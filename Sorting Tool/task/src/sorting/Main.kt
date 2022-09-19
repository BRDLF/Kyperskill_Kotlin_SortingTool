package sorting

import java.io.File
import kotlin.math.roundToInt

object Sorter {
    private val validArguments = listOf<String>("-sortingType", "-dataType", "-inputFile", "-outputFile")
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

    fun run(args: Array<String>) {
        val inputList = mutableListOf<String>()
        val argumentsList = args.toMutableList()
        var dataType: DataType = DataType.WORD
        var sortType: SortType = SortType.NATURAL
        var inputFileName: String? = null
        var outputFileName: String? = null

        fun argumentValueOf(arg: String): String? {
            val argAddress = argumentsList.indexOf(arg)
            if (argAddress == - 1) throw IllegalArgumentException("validateArg passed for $arg which is not in argsList")
            if (argAddress == argumentsList.lastIndex) return null
            if (argumentsList[argAddress + 1].startsWith("-")) return null
            return argumentsList[argAddress + 1]
        }

        fun parseArgs() {
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
                    "-inputFile" -> inputFileName = argumentValueOf(arg)?: throw IllegalArgumentException("No input file defined!")
                    "-outputFile" -> outputFileName = argumentValueOf(arg)?: throw IllegalArgumentException("No output file defined!")
                }
            }
        }

        fun fromFile() {
            val inFile = File(inputFileName!!)
            when (dataType) {
                DataType.LINE -> {
                    inFile.forEachLine {
                            line -> if(line.isNotBlank()) inputList.add(line)
                    }
                }
                DataType.LONG -> {
                    inFile.forEachLine { line ->
                        line.trim().split(" +".toRegex())
                            .filter { it.isNotBlank() }
                            .forEach {
                                it.toIntOrNull()?: println("$it is not a valid parameter. It will be skipped.")
                                inputList.add(it)
                            }
                    }
                }
                DataType.WORD -> {
                    inFile.forEachLine {line ->
                        line.trim().split(" +".toRegex())
                            .filter { it.isNotBlank() }
                            .forEach { inputList.add(it) }
                    }
                }
            }
        }
        fun fromInput() {
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
                        .filter { it.isNotBlank() }
                        .forEach { inputList.add(it) }
                } while (true)
            }
        }
        fun fillList() {
            if (inputFileName != null) fromFile()
            else fromInput()
        }

        fun sortByCount(passedList: List<String>): List<String> {
            val countList = mutableMapOf<String, Int>()
            for (item in passedList) {
                if (countList.containsKey(item)) continue
                countList[item] = passedList.count { it == item }
            }
            return countList.keys.toMutableList().sortedBy { countList[it] }
        }
        fun sortList(): List<String> {
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

        fun printList(sortedList: List<String> = inputList) {
            val outFile = if (outputFileName != null) File(outputFileName!!) else null
            fun variableWriteFunction(text: String) {
                if (outFile != null) outFile.writeText(text)
                else print(text)
            }
            outFile?.writeText("")
            variableWriteFunction("Total ${dataType.print}s: ${inputList.size}.\n")
            when (sortType) {
                SortType.NATURAL -> {
                    variableWriteFunction("Sorted data: ")
                    for (item in sortedList) {
                        variableWriteFunction(item)
                        if (dataType.name == "LINE") variableWriteFunction("\n") else variableWriteFunction(" ")
                    }
                    if (dataType.name != "LINE") variableWriteFunction("\n")
                }
                SortType.COUNT -> {
                    for (item in sortedList) {
                        val itemCount = inputList.count { it == item }
                        val itemPercent = ((itemCount.toDouble() / inputList.size) * 100).roundToInt()
                        variableWriteFunction("${item}: $itemCount time(s), ${itemPercent}%\n")
                    }
                }
            }
        }

        try {
            parseArgs()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }

        fillList()

        printList(sortList())
    }
}

fun main(args: Array<String>) {
    Sorter.run(args)
}
