package test2

import kotlin.math.max

fun main() {
    println(solution(2, arrayOf(1, 2, 1, 1), arrayOf(1, 4, 3, 2, 4)))
}


fun solution(k: Int, c: Array<Int>, d: Array<Int>): Int {
    val store: MutableMap<Int, Int> = mutableMapOf()
    c.forEach {
        if (it in store) {
           store[it] = store[it]!! + 1
        } else {
            store[it] = 1
        }
    }

    return when {
        k >= d.size -> { // If number of wash is greater than or equal to number of dirty socks
            getMaxPairByWashingAllDirtySocks(store, d)
        }
        k == 0 -> { //if no wash, find the current pair possible from the clean socks
            findMaxPair(store)
        }
        else -> {
            val combo = combination(d, k)
            getMaxPairOfPossibleCombinations(store, combo)
        }
    }
}

fun getMaxPairByWashingAllDirtySocks(store: MutableMap<Int, Int>, d: Array<Int>): Int {
    val storeCopy: MutableMap<Int, Int> = mutableMapOf()
    storeCopy.putAll(store)

    d.forEach { i  ->
        if (i in storeCopy) {
            storeCopy[i] = storeCopy[i]!! + 1
        } else {
            storeCopy[i] = 1
        }
    }

    return findMaxPair(storeCopy)
}

fun getMaxPairOfPossibleCombinations(store: MutableMap<Int, Int>, combo: List<List<Int>>): Int {
    val storeCopy: MutableMap<Int, Int> = mutableMapOf()
    var maxSock = 0
    combo.forEach {
        storeCopy.clear()
        storeCopy.putAll(store)
        it.forEach { i ->
            if (i in storeCopy) {
                storeCopy[i] = storeCopy[i]!! + 1
            } else {
                storeCopy[i] = 1
            }
        }
        maxSock = max(maxSock, findMaxPair(storeCopy))
    }

   return maxSock
}

fun findMaxPair(store: Map<Int, Int>): Int {
    var max = 0
    store.forEach { (key, _) ->
        max += store.getValue(key) / 2
    }
    return max
}

private fun combination(elements: Array<Int>, k: Int): List<List<Int>> {
    val result: MutableList<List<Int>> = mutableListOf()
    val size = elements.size

    if (k > size) {
        return emptyList()
    }

    val pointers = IntArray(k)
    var r = 0
    var i = 0
    while (r >= 0) {
        if (i <= size + (r - k)) {
            pointers[r] = i

            if (r == k - 1) {

                result.add(getItem(pointers, elements))
                i++
            } else {
                i = pointers[r] + 1
                r++
            }
        } else {
            r--
            if (r >= 0) i = pointers[r] + 1
        }
    }

    return result
}

private fun getItem(combination: IntArray, elements: Array<Int>): MutableList<Int> {
    val array: MutableList<Int> = mutableListOf()
    for (z in combination.indices) {
        array.add(elements[combination[z]])
    }
    return array
}
