package ru.netology.nmedia.services

object CounterToText  {
    fun counterToTextFun (count: Long): String {
        //цифры и текст запихнуть в константы.
        val result: String
        val resultDouble: Double
        if (count < 0) {
            result = "ERROR"
        } else if (count < 1000) {
            result = count.toString()
        } else if (count < 1_100) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 1) + "K"
        } else if (count < 10_000) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 3) + "K"
        } else if (count < 100_000) {
            resultDouble = count.toDouble() / 1000
            result = resultDouble.toString().substring(0, 2) + "K"
        } else if (count < 1_000_000) {
            result = (count.toDouble() / 1000).toString().substring(0, 3) + "K"
        } else if (count < 1_100_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 1) + "M"
        } else if (count < 10_000_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 3) + "M"
        } else if (count < 100_000_000) {
            resultDouble = count.toDouble() / 1_000_000
            result = resultDouble.toString().substring(0, 2) + "M"
        } else {
            result = (count / 1_000_000).toDouble().toString().substring(0, 3) + "M"
        }

        return result
    }
}