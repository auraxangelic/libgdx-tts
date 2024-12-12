[![](https://jitpack.io/v/auraxangelic/libgdx-tts.svg)](https://jitpack.io/#auraxangelic/libgdx-tts)

# libgdx-tts
Library for a text-to-speech synthesizer for LibGDX. Available for desktop, android, and iOS apps.

Use this library for enabling accessibility for visually impaired or blind players of your game, or use it in fun and creative way as part of your gameplay!

For iOS, please make sure you're using libGDX version 1.13.0 or later.

## Gradle Configuration

If you don't have already, add Jitpack to your repositories in your root build.gradle.kts file:

```
maven { setUrl("https://jitpack.io") }
```

Add the following line to your core module dependencies:
```
api "com.github.auraxangelic:libgdx-tts:1.0.25"
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
On desktop, when a new `speak` call is made, a small bit of the previous audio will be played. This is due to how the AudioDevice is currently implemented for desktop.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ by replacing its default audio player with the libGDX one `Gdx.audio#newAudioDevice`.
