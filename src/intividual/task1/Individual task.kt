package intividual.task1

import java.io.File

/**
 * Есть файл, в котором схематично изображено поле для игры в крестики-нолики на доске 15х15, а именно:
 * - 15 строк
 * - в каждой строке строго 15 символов
 * - пустая клетка обозначается -, крестик х, нолик о
 *
 * Функция, которую нужно написать, принимает как параметры имя этого файла и очередность хода (крестики или нолики).
 * Необходимо определить, существует ли ход, приводящий эту сторону к победе
 * (для этого нужно составить 5 своих знаков в ряд по вертикали, горизонтали или диагонали).
 * Если он не существует, следует вернуть null, если существует — координаты клетки, куда нужно сходить.
 *
 * Необходимо написать функцию и тесты к ней.
 *
 * Примечание: Координаты поля считаются с левого верхнего улга и начинаются с 1 для y (По вертекали) и x (По горизонтали)
 * Считается, что входное поле содержит только 15 строк по 15 символов, включающих только "x", "o", "-"
 * (Символы "x", "o" напечатаны на английской раскладке)
 **/

fun checkLine(list: List<Triple<Int, Int, String>>, turn: String): Pair<Int, Int>? {
    val opponent = if (turn == "x") "o" else "x"
    var gaps = 0
    var result: Pair<Int, Int>? = null

    for ((x, y, value) in list) {
        if (value == opponent) return null
        else if (value == "-") {
            gaps++
            result = x + 1 to y + 1
        }
    }
    if (gaps == 1) return result
    return null
}

fun createLine(matrix: Array<Array<String>>, point: Triple<Int, Int, String>, turn: String): Pair<Int, Int>? {
    var tempValue: Pair<Int, Int>?
    val listOfPoints = mutableListOf<Triple<Int, Int, String>>()

    if (point.first in 4..14) {
        for (i in 0..4) listOfPoints += Triple(
            point.first - i,
            point.second,
            matrix[point.second][point.first - i]
        )
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != null) return tempValue
        listOfPoints.clear()
    }
    if (point.second in 4..14) {
        for (i in 0..4) listOfPoints += Triple(
            point.first,
            point.second - i,
            matrix[point.second - i][point.first]
        )
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != null) return tempValue
        listOfPoints.clear()
    }
    if (point.first in 4..14 && point.second in 4..14) {
        for (i in 0..4) listOfPoints += Triple(
            point.first - i,
            point.second - i,
            matrix[point.second - i][point.first - i]
        )
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != null) return tempValue
        listOfPoints.clear()
    }
    if (point.first in 0..10 && point.second in 4..14) {
        for (i in 0..4) listOfPoints += Triple(
            point.first + i,
            point.second - i,
            matrix[point.second - i][point.first + i]
        )
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != null) return tempValue
        listOfPoints.clear()
    }
    return null
}

fun ticTacToe(inputFileName: String, turn: String): Pair<Int, Int>? {
    var matrix = arrayOf<Array<String>>()
    var i = 0
    var j: Int
    var row: Array<String>
    var tempValue: Pair<Int, Int>?

    for (line in File(inputFileName).readLines()) {
        i++
        j = 0
        row = arrayOf()
        matrix += row
        for (symbol in line) {
            j++
            row += symbol.toString()
            matrix[i - 1] = row
            tempValue = createLine(matrix, Triple(j - 1, i - 1, matrix[i - 1][j - 1]), turn)
            if (tempValue != null) return tempValue.first to tempValue.second
        }
    }
    return null
}
