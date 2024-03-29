# libgdx-tts
Text to Speech for LibGDX

## Releases

For the latest release, download the jar here:

https://github.com/auraxangelic/libgdx-tts/releases/tag/v1.0.0

## Usage

1. Download the jar from the releases.
2. Put the jar into your libGDX project.
3. Include the jar in your core module's `build.gradle` with something like the following: 
```
dependencies {
    ...
    
    api fileTree(dir: "../libs", include: ["*.jar"])
}
```
4. At any point after your Game class has been initialized, run the following:
```
import com.auraxangelic.libgdxtts.TextSpeech
...

    TextSpeech.speak("testing 1 2 3")
```
5. The text to speech will run asynchronously. If you want to block until it's finished speaking, you can run the following: `TextSpeech.waitUntilFinished()`
6. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate it when your game exits by running `TextSpeech.deallocate()`, or run `exitProcess(0)`/`System.exit(0)` to close the thread.

## Issues
This text to speech synthesizer currently creates staticky speech and sounds like an old radio. More work needs to be done to configure the audio device to play clearer sound.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ by replacing its default audio player with the libGDX one.

Tested working on desktop and android.
