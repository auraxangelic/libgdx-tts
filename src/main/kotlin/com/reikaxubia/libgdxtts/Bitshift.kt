package com.reikaxubia.libgdxtts

import java.nio.ByteBuffer
import java.nio.ByteOrder

object Bitshift {
    fun bytesToShorts(bytes: ByteArray): ShortArray {
        val size = bytes.size
        val shortArray = ShortArray(size / 2)

        var index = 0
        while (index < size) {
            val bb: ByteBuffer = ByteBuffer.allocate(2)
            bb.order(ByteOrder.LITTLE_ENDIAN)
            bb.put(bytes[index+1])
            bb.put(bytes[index])
            shortArray[index/2] = bb.getShort(0)
            index += 2
        }
        return shortArray
    }
}
