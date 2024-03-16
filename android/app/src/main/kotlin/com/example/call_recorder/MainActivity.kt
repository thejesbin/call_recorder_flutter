import android.os.Bundle
import androidx.annotation.NonNull
import com.aykuttasil.callrecord.CallRecord
import com.aykuttasil.callrecord.helper.CallRecordStorageHelper
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import java.io.File

class MainActivity : FlutterActivity() {

    private val CHANNEL = "call_recorder"
    private var callRecord: CallRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channel = MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, CHANNEL)
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                "startRecording" -> {
                    startRecording()
                    result.success(null)
                }
                "stopRecording" -> {
                    stopRecording()
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }

        // Initialize CallRecord
        callRecord = CallRecord.Builder(this)
            .setLogEnable(true)
            .setRecordFileName("CallRecord")
            .setRecordDirName("MyCallRecords")
            .build()
    }

    private fun startRecording() {
        if (callRecord?.isRunning == false) {
            callRecord?.startCallReceiver()
        }
    }

    private fun stopRecording() {
        if (callRecord?.isRunning == true) {
            callRecord?.stopCallReceiver()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
