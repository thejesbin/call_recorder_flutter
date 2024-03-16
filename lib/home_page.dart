
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomePage extends StatelessWidget {
  static const platform = MethodChannel('call_recorder');

  const HomePage({super.key});

  Future<void> startRecording() async {
    try {
      await platform.invokeMethod('startRecording');
    } on PlatformException catch (e) {
      print("Failed to start recording: '${e}'.");
    }
  }

  Future<void> stopRecording() async {
    try {
      await platform.invokeMethod('stopRecording');
    } on PlatformException catch (e) {
      print("Failed to stop recording: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Call Recorder'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: startRecording,
              child: const Text('Start Recording'),
            ),
            ElevatedButton(
              onPressed: stopRecording,
              child: const Text('Stop Recording'),
            ),
          ],
        ),
      ),
    );
  }
}
