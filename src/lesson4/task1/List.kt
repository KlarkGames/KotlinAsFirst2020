@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.*


// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else (list.sum() / list.size)


/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val center = mean(list)
    for (i in 0 until list.size) list[i] -= center
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = a.zip(b) { a, b -> a * b }.sum()


/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    var i = 0
    for (element in p) {
        result += element * x.toDouble().pow(i).toInt()
        i++
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var tempN = n
    val result = mutableListOf<Int>()
    var i = 2

    while (tempN != 1) {
        if (tempN % i == 0) {
            tempN /= i
            result.add(i)
            i = 2
        } else i++
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var tempN = n
    val result = mutableListOf<Int>()

    if (n == 0) return listOf(0)

    while (tempN != 0) {
        result.add(tempN % base)
        tempN /= base
    }
    return result.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val alphabet = mapOf<Int, String>(10 to "a",
    11 to "b", 12 to "c", 13 to "d", 14 to "e", 15 to "f", 16 to "g", 17 to "h", 18 to "i", 19 to "j",
    20 to "k", 21 to "l", 22 to "m", 23 to "n", 24 to "o", 25 to "p", 26 to "q",
    27 to "r", 28 to "s", 29 to "t", 30 to "u", 31 to "v", 32 to "w", 33 to "x",
    34 to "y", 35 to "z")

    var tempN = n
    var result = ""
    var symbol = 0

    while (tempN != 0) {
        symbol = tempN % base
        if (symbol < 10) result += "$symbol"
        else result += alphabet[symbol]
        tempN /= base
    }
    return result.reversed()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    var normalN = n
    while (normalN != 0) {
        when {
            normalN >= 1000 -> { normalN -= 1000; result += "M" }
            normalN >= 900 -> { normalN -= 900; result += "CM" }
            normalN >= 500 -> { normalN -= 500; result += "D" }
            normalN >= 400 -> { normalN -= 400; result += "CD" }
            normalN >= 100 -> { normalN -= 100; result += "C" }
            normalN >= 90 -> { normalN -= 90; result += "XC" }
            normalN >= 50 -> { normalN -= 50; result += "L" }
            normalN >= 40 -> { normalN -= 40; result += "XL" }
            normalN >= 10 -> { normalN -= 10; result += "X" }
            normalN >= 9 -> { normalN -= 9; result += "IX" }
            normalN >= 5 -> { normalN -= 5; result += "V" }
            normalN >= 4 -> { normalN -= 4; result += "IV" }
            normalN >= 1 -> { normalN -= 1; result += "I" }
        }
    }
    return result
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val firstThree = n / 1000
    val secondThree = n % 1000
    var result = ""

    when {
        firstThree / 100 == 0 -> result += ""
        firstThree / 100 == 1 -> result += " сто"
        firstThree / 100 == 2 -> result += " двести"
        firstThree / 100 == 3 -> result += " триста"
        firstThree / 100 == 4 -> result += " четыреста"
        firstThree / 100 == 5 -> result += " пятьсот"
        firstThree / 100 == 6 -> result += " шестьсот"
        firstThree / 100 == 7 -> result += " семьсот"
        firstThree / 100 == 8 -> result += " восемьсот"
        firstThree / 100 == 9 -> result += " девятьсот"
    }

    when {
        (firstThree % 100 < 20 && firstThree % 100 > 10) -> {
            when {
                firstThree % 100 == 11 -> result += " одинадцать тысяч"
                firstThree % 100 == 12 -> result += " двенадцать тысяч"
                firstThree % 100 == 13 -> result += " тринадцать тысяч"
                firstThree % 100 == 14 -> result += " четырнадцать тысяч"
                firstThree % 100 == 15 -> result += " пятнадцать тысяч"
                firstThree % 100 == 16 -> result += " шестнадцать тысяч"
                firstThree % 100 == 17 -> result += " семнадцать тысяч"
                firstThree % 100 == 18 -> result += " восемнадцать тысяч"
                firstThree % 100 == 19 -> result += " девятнадцать тысяч"
            }
        }
        !(firstThree % 100 < 20 && firstThree % 100 > 10) -> {
            when {
                firstThree / 10 % 10 == 0 -> result += ""
                firstThree / 10 % 10 == 1 -> result += " десять"
                firstThree / 10 % 10 == 2 -> result += " двадцать"
                firstThree / 10 % 10 == 3 -> result += " тридцать"
                firstThree / 10 % 10 == 4 -> result += " сорок"
                firstThree / 10 % 10 == 5 -> result += " пятьдесят"
                firstThree / 10 % 10 == 6 -> result += " шестьдесят"
                firstThree / 10 % 10 == 7 -> result += " семьдесят"
                firstThree / 10 % 10 == 8 -> result += " восемьдесят"
                firstThree / 10 % 10 == 9 -> result += " девяносто"
            }
            when {
                (firstThree % 10 == 0 && firstThree / 10 == 0) -> result += ""
                (firstThree % 10 == 0 && firstThree / 10 != 0) -> result += " тысяч"
                firstThree % 10 == 1 -> result += " одна тысяча"
                firstThree % 10 == 2 -> result += " две тысячи"
                firstThree % 10 == 3 -> result += " три тысячи"
                firstThree % 10 == 4 -> result += " четыре тысячи"
                firstThree % 10 == 5 -> result += " пять тысяч"
                firstThree % 10 == 6 -> result += " шесть тысяч"
                firstThree % 10 == 7 -> result += " семь тысяч"
                firstThree % 10 == 8 -> result += " восемь тысяч"
                firstThree % 10 == 9 -> result += " девять тысяч"
            }
        }
    }
    when {
        secondThree / 100 == 0 -> result += ""
        secondThree / 100 == 1 -> result += " сто"
        secondThree / 100 == 2 -> result += " двести"
        secondThree / 100 == 3 -> result += " триста"
        secondThree / 100 == 4 -> result += " четыреста"
        secondThree / 100 == 5 -> result += " пятьсот"
        secondThree / 100 == 6 -> result += " шестьсот"
        secondThree / 100 == 7 -> result += " семьсот"
        secondThree / 100 == 8 -> result += " восемьсот"
        secondThree / 100 == 9 -> result += " девятьсот"
    }
    when {
        (secondThree % 100 < 20 && secondThree % 100 > 10) -> {
            when {
                secondThree % 100 == 11 -> result += " одинадцать"
                secondThree % 100 == 12 -> result += " двенадцать"
                secondThree % 100 == 13 -> result += " тринадцать"
                secondThree % 100 == 14 -> result += " четырнадцать"
                secondThree % 100 == 15 -> result += " пятнадцать"
                secondThree % 100 == 16 -> result += " шестнадцать"
                secondThree % 100 == 17 -> result += " семнадцать"
                secondThree % 100 == 18 -> result += " восемнадцать"
                secondThree % 100 == 19 -> result += " девятнадцать"
            }
        }
        !(secondThree % 100 < 20 && secondThree % 100 > 10) -> {
            when {
                secondThree / 10 % 10 == 0 -> result += ""
                secondThree / 10 % 10 == 1 -> result += " десять"
                secondThree / 10 % 10 == 2 -> result += " двадцать"
                secondThree / 10 % 10 == 3 -> result += " тридцать"
                secondThree / 10 % 10 == 4 -> result += " сорок"
                secondThree / 10 % 10 == 5 -> result += " пятьдесят"
                secondThree / 10 % 10 == 6 -> result += " шестьдесят"
                secondThree / 10 % 10 == 7 -> result += " семьдесят"
                secondThree / 10 % 10 == 8 -> result += " восемьдесят"
                secondThree / 10 % 10 == 9 -> result += " девяносто"
            }
            when {
                (secondThree % 10 == 0 && secondThree / 10 == 0) -> result += ""
                (secondThree % 10 == 0 && secondThree / 10 != 0) -> result += ""
                secondThree % 10 == 1 -> result += " один"
                secondThree % 10 == 2 -> result += " два"
                secondThree % 10 == 3 -> result += " три"
                secondThree % 10 == 4 -> result += " четыре"
                secondThree % 10 == 5 -> result += " пять"
                secondThree % 10 == 6 -> result += " шесть"
                secondThree % 10 == 7 -> result += " семь"
                secondThree % 10 == 8 -> result += " восемь"
                secondThree % 10 == 9 -> result += " девять"
            }
        }
    }
    result = result.trim()
    return result
}