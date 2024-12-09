[![](https://jitpack.io/v/auraxangelic/libgdx-tts.svg)](https://jitpack.io/#auraxangelic/libgdx-tts)

# libgdx-tts
Library for a text-to-speech synthesizer for LibGDX. Available for desktop, android, and iOS apps.

## Gradle Configuration

Add the following line to your core module dependencies:
```
api "com.github.auraxangelic:libgdx-tts:v1.0.9"
```

## Usage

1. At any point after your Game class has been initialized, you my use the `TextSpeech` class to read any string.
```
import com.reikaxubia.libgdxtts.TextSpeech
...

    TextSpeech.speak("testing 1 2 3")
    // TextSpeech.INSTANCE.speak("testing 1 2 3") // in java
```
2. The text to speech will run asynchronously. If you want to block until it's finished speaking, you can run: `TextSpeech.waitUntilFinished()`/`TextSpeech.INSTANCE.waitUntilFinished()`
3. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate the thread when your game exits by running `TextSpeech.deallocate()`/`TextSpeech.INSTANCE.deallocate()`, or run `exitProcess(0)`/`System.exit(0)`.

## Known Issues
On desktop, when a new `speak` call is made, the audio will play a tiny bit of the end of the previous `speak` call. This is due to how the AudioDevice is currently implemented for desktop.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ by replacing its default audio player with the libGDX one `Gdx.audio#newAudioDevice`.
