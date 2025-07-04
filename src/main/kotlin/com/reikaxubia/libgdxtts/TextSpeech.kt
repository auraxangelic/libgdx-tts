package com.reikaxubia.libgdxtts

import java.util.*
import javax.speech.Central
import javax.speech.synthesis.Synthesizer
import javax.speech.synthesis.Synthesizer.QUEUE_EMPTY
import javax.speech.synthesis.SynthesizerModeDesc

object TextSpeech {
    private var synthesizer: Synthesizer

    init {
        // Set property as Kevin Dictionary
        com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory()
        System.setProperty(
            "freetts.voices",
            "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory"
        )


        // Register Engine
        com.sun.speech.freetts.jsapi.FreeTTSEngineCentral()
        Central.registerEngineCentral(
            ("com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral")
        )

        // Create a Synthesizer
        synthesizer = Central.createSynthesizer(
            SynthesizerModeDesc(Locale.US)
        )

        // Allocate synthesizer
        synthesizer.allocate()

        // Resume Synthesizer
        synthesizer.resume()
    }

    /** Speaks the given text. */
    fun speak(text: String) {
        synthesizer.speakPlainText(
            text, null
        )
    }

    /** Blocks thread until synthesizer queue is empty. */
    fun waitUntilFinished() {
        synthesizer.waitEngineState(QUEUE_EMPTY)
    }

    /** Frees resources allocated by the synthesizer. */
    fun deallocate() {
        synthesizer.deallocate()
    }

    /** Cancels any currently speaking and queued text. */
    fun cancelSpeech() {
        synthesizer.cancelAll()
    }
}
