package sorting

object Sorter {
    private val inputList = mutableListOf<String>()
    enum class dataType(val arg: String) {
        LONG("long"),
        LINE("line"),
        WORD("word")
    }
    enum class behavior(val arg: String) {
        LIST("list"),
        SORT("sort")
    }

    fun run(args: Array<String>) {
        if (args.contains("-sortIntegers")) {
            sort(dataType.LONG, behavior.SORT)
        }
        else {
            sort(passedType = determineType(args))
        }
    }

    private fun sort(passedType: dataType = dataType.WORD, passedBehavior: behavior = behavior.LIST) {

        fillList(passedType.arg)

        when (passedBehavior.name) {
            "LIST" -> listInfo(passedType.arg)
            "SORT" -> listSorted(passedType.arg)
        }

    }

    private fun determineType(args: Array<String>): dataType {
        return when (val dataTypeAddress = args.indexOf("-dataType")) {
            -1 -> dataType.WORD //-dataType not passed
            args.size -> dataType.WORD //-dataType passed with no type following
            else -> {
                val caughtDataType = args[dataTypeAddress + 1]
                for(targetType in dataType.values()) {
                    if (caughtDataType == targetType.arg) return targetType
                }
                dataType.WORD
            }
        }
    }

    private fun fillList(type: String) {
        when (type) {
            "line" -> do {
                val input = readlnOrNull()?: break
                if (input.isNotBlank()) inputList.add(input)
            } while (true)
            else -> do {
                val input = readlnOrNull()?: break
                input.trim().split(" +".toRegex()).filter { it.isNotBlank()}
                    .forEach { inputList.add(it) }
            } while (true)
        }

        do {
            val input = readlnOrNull()?: break
            input.trim().split(" +".toRegex()).filter { it.isNotBlank()}
                .forEach { inputList.add(it) }
        } while (true)
    }

    private fun listInfo(type: String) {
        var greatestIndex = 0
        var greatestFrequency = 0
        when (type) {
            "long" -> {
                for (index in inputList.indices) {
                    greatestIndex = if ((inputList[index].toIntOrNull()
                            ?: Integer.MIN_VALUE) > (inputList[greatestIndex].toIntOrNull() ?: Integer.MIN_VALUE)
                    ) index else greatestIndex
                }
                greatestFrequency = inputList.count { it == inputList[greatestIndex] }
            }
            "line", "word"-> {
                for (index in inputList.indices) {
                    greatestIndex = if (inputList[index].length > inputList[greatestIndex].length) index else greatestIndex
                }
                greatestFrequency = inputList.count { it == inputList[greatestIndex] }
            }
        }
        infoPrint(greatestIndex, greatestFrequency, type)


    }

    private fun listSorted(type: String) {
        val sortedList = when (type) {
            "long" -> inputList.map { it.toInt() }.sorted()
            else -> inputList.sorted()
        }
        println("Total numbers: ${sortedList.size}.")
        println("Sorted data: ${sortedList.joinToString(" ")}")
    }

    private fun infoPrint(greatestIndex: Int, freq: Int, type: String) {
        val percent = ((freq.toDouble() / inputList.size) * 100).toInt()
        val printType = if (type == "long") "number" else type
        val printMeasure = if (type == "long") "great" else "long"
        println("Total ${printType}s: ${inputList.count()}.")
        print("The ${printMeasure}est ${printType}: ")
        if (type == "line") println()
        print(inputList[greatestIndex])
        if (type == "line") println()
        println(" ($freq time(s), ${percent}%).")
    }


}

fun main(args: Array<String>) {
    Sorter.run(args)
}
