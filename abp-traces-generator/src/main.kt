
fun main() {
    sender()
    println()
    receiver()
}

private fun sender() {
    fun regular(bit: Int) =
        "in=req[0]; out=send[0]; in=req[0]; out=p${bit}[0]; in=a${bit}[0]; out=done[0];"

    fun lostPremature(bit: Int) =
        "in=req[0]; out=send[0]; in=req[0]; out=p${bit}[0]; in=timeout[0]; out=p${bit}[0]; in=a${bit}[0]; out=done[0];"

    val trace = arrayOfNulls<String>(3)

    fun gen(depth: Int) {
        if (depth == 3) {
            println(trace.joinToString(" "))
            return
        }
        trace[depth] = regular(depth % 2)
        gen(depth + 1)
        trace[depth] = lostPremature(depth % 2)
        gen(depth + 1)
    }

    gen(0)
}

private fun receiver() {
    fun regular(bit: Int) =
        "in=p${bit}[0]; in=a${bit}[0];"

    val trace = arrayOfNulls<String>(3)

    fun gen(depth: Int) {
        if (depth == 3) {
            println(trace.joinToString(" "))
            return
        }
        trace[depth] = regular(depth % 2)
        gen(depth + 1)
    }

    gen(0)
}