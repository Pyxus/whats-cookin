package edu.towson.whatscookin.ext

// Algorithm based on this java implementation found on wikipedia
// https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Dice's_coefficient
// Returns a float 0-1 representing how similar 2 strings are
// This is an extension method!! Basically you can use it as if it was built into any and all string instances.
// example: "ABC123".similarity("123ABC") -> Output should be .8
fun String.similarity(str: String): Float{

    if (this == str){
        return 1f
    }

    if (this.length < 2 || str.length < 2){
        return 0f
    }

    val srcBigrams = this.bigrams()
    val targetBigrams = str.bigrams()

    val sum = srcBigrams.size + targetBigrams.size
    var inter = 0

    for (sb in srcBigrams){
        for (tb in targetBigrams){
            if (sb == tb){
                inter++
                break
            }
        }
    }

    return (2f * inter) / sum.toFloat()
}

// Returns an array containing bigrams (pair of consecutive letters) of this string
fun String.bigrams(): IntArray{
    val n = this.length - 1
    val pairs = IntArray(n)

    (0..n).forEach{i ->
        when(i){
            0 -> pairs[i] = this[i].code shl 16
            n -> pairs[i-1] = pairs[i-1] or this[i].code
            else -> {
                pairs[i-1] = pairs[i-1] or this[i].code
                pairs[i] = pairs[i-1] shl 16
            }
        }
    }

    pairs.sort()
    return pairs
}