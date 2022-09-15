# Hyperskill_Kotlin_Sorting Tool

### Stage 4/6: Everything Counts

Changed argument `-sortIntegers` to `-sortingType` which accepts either `natural` or `byCount`. Default is `natural`
If `natural`, program will display input data sorted naturally.
If `byCount`, program will display input data sorted by # of occurrences. Items with the same # of occurrences are sorted naturally.

### Stage 3/6: Sorting it out

Added argument `-sortIntegers` which, if passed, will change the output to display the number of integers input, and then a sorted list of said integers.

### Stage 2/6: Not just numbers

Tool now takes the `-dataType` argument and collects/reports data differently depending on the argument given.
- `line` records each line
- `long` records input as numbers
- `word` records input as strings

`-dataType` defaults to `word`

### Stage 1/6: Numbers only

Tool now takes input until EOF and prints metadata including:
- number of items input (separated by one or more space)
- greatest number among inputs
- occurrences of the greatest number among inputs.