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

data class Point(val coordinates: Pair<Int, Int>, val value: String)

fun checkLine(list: List<Point>, turn: String): Point {
    val opponent = if (turn == "x") "o" else "x"
    var gaps = 0
    var result = Point(0 to 0, "-")

    for (point in list) {
        if (point.value == opponent) return Point(0 to 0, "-")
        else if (point.value == "-") {
            result = point
            gaps++
        }
    }
    if (gaps == 1) return result
    return Point(0 to 0, "-")
}

fun createLine(matrix: Array<Array<Point>>, point: Point, turn: String): Point {
    var tempValue: Point
    val listOfPoints = mutableListOf<Point>()

    if (point.coordinates.first in 5..15) {
        for (i in 0..4) listOfPoints += matrix[point.coordinates.second - 1][point.coordinates.first - 1 - i]
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != Point(0 to 0, "-")) return tempValue
        listOfPoints.clear()
    }
    if (point.coordinates.second in 5..15) {
        for (i in 0..4) listOfPoints += matrix[point.coordinates.second - 1 - i][point.coordinates.first - 1]
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != Point(0 to 0, "-")) return tempValue
        listOfPoints.clear()
    }
    if (point.coordinates.first in 5..15 && point.coordinates.second in 5..15) {
        for (i in 0..4) listOfPoints += matrix[point.coordinates.second - 1 - i][point.coordinates.first - 1 - i]
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != Point(0 to 0, "-")) return tempValue
        listOfPoints.clear()
    }
    if (point.coordinates.first in 1..11 && point.coordinates.second in 5..15) {
        for (i in 0..4) listOfPoints += matrix[point.coordinates.second - 1 - i][point.coordinates.first - 1 + i]
        tempValue = checkLine(listOfPoints, turn)
        if (tempValue != Point(0 to 0, "-")) return tempValue
        listOfPoints.clear()
    }
    return Point(0 to 0, "-")
}

fun ticTacToe(inputFileName: String, turn: String): Pair<Int, Int>? {
    var matrix = arrayOf<Array<Point>>()
    var i = 0
    var j: Int
    var row: Array<Point>
    var tempValue: Point

    for (line in File(inputFileName).readLines()) {
        i++
        j = 0
        row = arrayOf()
        matrix += row
        for (symbol in line) {
            j++
            row += Point(j to i, symbol.toString())
            matrix[i - 1] = row
            tempValue = createLine(matrix, matrix[i - 1][j - 1], turn)
            if (tempValue.coordinates != 0 to 0) return tempValue.coordinates
        }
    }
    return null
}
