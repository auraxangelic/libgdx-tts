[![](https://jitpack.io/v/auraxangelic/libgdx-tts.svg)](https://jitpack.io/#auraxangelic/libgdx-tts)

# libgdx-tts
Library for a text-to-speech synthesizer for LibGDX. Tested working for desktop, android, and iOS apps.

Use this library for implementing accessibility for visually impaired or blind players of your game, or use it in a fun and creative way as part of your gameplay!

For iOS, please make sure you're using libGDX version 1.13.0 or later.

## Gradle Configuration

If you don't have already, add Jitpack to your repositories in your root `build.gradle.kts` file:

```
maven { setUrl("https://jitpack.io") }
```

For `build.gradle`:
```
maven { url "https://jitpack.io" }
```

Add the following line to your core module dependencies in `build.gradle`:
```
api "com.github.auraxangelic:libgdx-tts:1.0.25"
```

## Usage

1. At any point after your Game class has been initialized, you can use the `TextSpeech` class to read any string. It looks like this in Kotlin:
```
import com.reikaxubia.libgdxtts.TextSpeech
...
    TextSpeech.speak("testing 1 2 3")
```
In Java:
```
    TextSpeech.INSTANCE.speak("testing 1 2 3");
```
2. The text-to-speech runs asynchronously. If you want to block until it's finished speaking, make sure not to block a render function which would freeze your game. To do so, you can run in Kotlin:
```
TextSpeech.waitUntilFinished()
```
In Java:
```
TextSpeech.INSTANCE.waitUntilFinished()
```
3. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate the thread when your game exits by running:
```
TextSpeech.deallocate()
```
In Java:
```
TextSpeech.INSTANCE.deallocate()
```
or run `exitProcess(0)`/`System.exit(0);`.

## Known Issues
On desktop builds only, when a new `speak` call is made, a small bit of the previous audio will be played.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ and using the LibGDX audio player `AudioDevice`.
