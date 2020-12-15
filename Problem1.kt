fun main(args: Array<String>) {
    val str = "abc"
    println(convert(str,1))
}

fun convert(str: String, n: Int): String {
    var output=""
    val shift=n % 26
    for (char in str.chars()) {
        // ASCII
        var c = char + shift

        if (c > 122) {
            c -= 26
        }
        output+= c.toChar()
    }
    return output
}
