package com.auraxangelic.libgdxtts

object Bitshift {
    fun bytesToShorts(bytes: ByteArray): ShortArray {
        val size = bytes.size
        val shortArray = ShortArray(size / 2)

        var index = 0
        while (index < size) {
            shortArray[index / 2] = (bytes[index].toInt() shl 8 and bytes[index + 1].toInt()).toShort()
            index += 2
        }
        return shortArray
    }
}
