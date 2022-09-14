package sorting

object Sorter {
    private val inputList = mutableListOf<String>()

    fun sort(args: Array<String>) {
        val type = getMeasure(args)

        fillList(type)
        listInfo(type)
    }

    private fun getMeasure(args: Array<String>): String {
        return when (val dataTypeAddress = args.indexOf("-dataType")) {
            -1 -> "word"
            args.size -> "word"
            else -> args[dataTypeAddress + 1]
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
        printout(greatestIndex, greatestFrequency, type)


    }

    private fun printout(greatestIndex: Int, freq: Int, type: String = "word") {
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
    Sorter.sort(args)
}
