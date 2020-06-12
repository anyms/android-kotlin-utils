package app.spidy.kotlinutils

import kotlin.math.sqrt

class Krypton {
    fun generate(): HashMap<String, Int> {
        val primes = ArrayList<Int>()
        for (i in 47 until 2307) {
            if (isPrime(i)) primes.add(i)
        }

        val p = primes.random()
        val q = primes.random()
        var x = 50

        val eq = (p - 1) * (q - 1) + 1
        var y = 50
        var xy = x + y

        while (xy != eq) {
            x += 1
            y = eq / x
            xy = x * y
        }

        return hashMapOf("pb" to x, "pt" to y, "pq" to p * q)
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2) return true
        if (n % 2 == 0 || n <= 1) return false
        val sqr = sqrt(n.toDouble()).toInt() + 1
        for (divisor in 3 until sqr step 2) {
            if (n % divisor == 0) return false
        }
        return true
    }
}