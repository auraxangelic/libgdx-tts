[![](https://jitpack.io/v/auraxangelic/libgdx-tts.svg)](https://jitpack.io/#auraxangelic/libgdx-tts)

# libgdx-tts
Library for a text-to-speech synthesizer for LibGDX. Tested working for desktop, android, and iOS apps.

Use this library for implementing accessibility for visually impaired or blind players of your game, or use it in a fun and creative way as part of your gameplay!

Created using FreeTTS source: https://freetts.sourceforge.io/ and using the LibGDX audio player `AudioDevice`.

For iOS, please make sure you're using libGDX version 1.13.0 or later.

## Gradle Configuration

If you don't have already, add Jitpack to your repositories in your root `build.gradle` file:
```
maven { url "https://jitpack.io" }
```

Add the following line to your core module dependencies in `build.gradle`:
```
api "com.github.auraxangelic:libgdx-tts:1.0.25"
```

## Usage

1. At any point after your Game class has been initialized, you can use the `TextSpeech` class to read any string. It looks like this in Java:
```
import com.reikaxubia.libgdxtts.TextSpeech
...
    TextSpeech.INSTANCE.speak("testing 1 2 3");
```
2. The text-to-speech runs asynchronously. If you want to block until it's finished speaking, make sure not to block a render function which would freeze your game. To do so, you can run:
```
TextSpeech.INSTANCE.waitUntilFinished();
```
3. To stop speech while it is in the middle of speakinga previous `speak` call:
```
TextSpeech.INSTANCE.cancelSpeech();
```
4. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate the thread when your game exits by running:
```
TextSpeech.INSTANCE.deallocate();
```
or run `System.exit(0);`.

## Known Issues
On desktop builds only, when a new `speak` call is made, a small bit of the end of the previous speak audio will be played.
