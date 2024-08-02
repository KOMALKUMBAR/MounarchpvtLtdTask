package com.android1.mounachpvttask1

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android1.mounachpvttask1.FilePath.getPath
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.FFmpegSession
import com.arthenica.ffmpegkit.LogCallback
import com.arthenica.ffmpegkit.Statistics
import com.arthenica.ffmpegkit.StatisticsCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AudioProcessActivity :  AppCompatActivity() {

    private lateinit var btnSelectAudio1: Button
    private lateinit var btnSelectAudio2: Button
    private lateinit var btnMergeAudio: Button
    private lateinit var btnPlayAudio: Button
    private lateinit var mediaPlayer: MediaPlayer

    private var audioUri1: Uri? = null
    private var audioUri2: Uri? = null

    companion object {
        private const val PICK_AUDIO_1 = 1
        private const val PICK_AUDIO_2 = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_process)

        btnSelectAudio1 = findViewById(R.id.btnSelectAudio1)
        btnSelectAudio2 = findViewById(R.id.btnSelectAudio2)
        btnMergeAudio = findViewById(R.id.btnMergeAudio)
        btnPlayAudio = findViewById(R.id.btnPlayAudio)

        btnSelectAudio1.setOnClickListener {
            selectAudioFile(PICK_AUDIO_1)
        }

        btnSelectAudio2.setOnClickListener {
            selectAudioFile(PICK_AUDIO_2)
        }

        btnMergeAudio.setOnClickListener {
            mergeAudioFiles()
        }

        btnPlayAudio.setOnClickListener {
            playMergedAudio()
        }
    }

    private fun selectAudioFile(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                PICK_AUDIO_1 -> {
                    audioUri1 = data.data
                    Toast.makeText(this, "Audio 1 selected", Toast.LENGTH_SHORT).show()
                }
                PICK_AUDIO_2 -> {
                    audioUri2 = data.data
                    Toast.makeText(this, "Audio 2 selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun mergeAudioFiles() {
        if (audioUri1 == null && audioUri2 == null) {
            Toast.makeText(this, "Please select both audio files", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val inputStream1 = contentResolver.openInputStream(audioUri1!!)
            val inputStream2 = contentResolver.openInputStream(audioUri2!!)
            val mergedFile = File(cacheDir, "merged_audio.mp3")
            val outputStream = FileOutputStream(mergedFile)

            val buffer = ByteArray(1024)
            var length: Int

            inputStream1?.use { input ->
                while (input.read(buffer).also { length = it } > 0) {
                    outputStream.write(buffer, 0, length)
                }
            }

            inputStream2?.use { input ->
                while (input.read(buffer).also { length = it } > 0) {
                    outputStream.write(buffer, 0, length)
                }
            }

            outputStream.close()

            Toast.makeText(this, "Audio files merged successfully", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error merging audio files", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playMergedAudio() {
        try {
            val mergedFile = File(cacheDir, "merged_audio.mp3")
            if (!mergedFile.exists()) {
                Toast.makeText(this, "Merged audio file not found", Toast.LENGTH_SHORT).show()
                return
            }

            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(mergedFile.absolutePath)
            mediaPlayer.prepare()
            mediaPlayer.start()
            Toast.makeText(this, "Playing merged audio", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error playing merged audio", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }


    }
}