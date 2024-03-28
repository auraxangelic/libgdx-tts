package com.auraxangelic.libgdxtts

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.AudioDevice

fun audioDeviceGet(): AudioDevice {
    return Gdx.audio.newAudioDevice(8000, true)
}
