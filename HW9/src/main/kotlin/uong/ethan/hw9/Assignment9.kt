package uong.ethan.hw9


import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.awt.BorderLayout
import java.awt.TextArea
import java.awt.TextField
import java.io.File
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.WindowConstants


// Assignment 9
//
// Create a project like you did for HW7, but call it HW9
//
// Copy this file into your project. Make sure you change the package and put it in the appropriate
//   folders in your project. DO NOT CHANGE ANY OF THE CODE THAT CAME IN THIS FILE __UNLESS__ INSTRUCTED TO DO SO!
//
// Run your HW6 to create the sample files. You should end up with a directory that looks like
//    sample-files
//       kotlin
//          package1
//            Sample11.kt
//            Sample12.kt
//          package2
//            Sample21.kt
//            Sample22.kt
//          package3
//            Sample31.kt
//            Sample32.kt
//       resources
//          fee.txt
//          fie.txt
//          foo.txt
//
// Replace the "TODO" comments in the main with the appropriate calls to
//   perform the requested work when the button is pressed.
//   Create any functions needed
//
// Make sure you ONLY update the user interface on the main dispatcher, and process files on the IO dispatcher

data class SampleFile(
    val name: String,
    val content: List<String>,
)

// Not necessary, could just use emit() but adding this here just for kicks
suspend fun FlowCollector<SampleFile>.emitFile(sampleFile: SampleFile) {
    emit(sampleFile)
}

suspend fun walk(file: File, flowCollector: FlowCollector<SampleFile>) {
    file.listFiles().forEach {
        if (it.isFile) {
            flowCollector.emitFile(SampleFile(it.name, it.useLines { it.toList() }))
        } else if (it.isDirectory) {
            walk(it, flowCollector)
        }
    }
}

fun main() {
    var currentJob: Job? = null

    JFrame().apply {
        layout = BorderLayout()
        val fileName = TextField()
        val fileContent = TextArea()
        add(fileName, BorderLayout.NORTH)
        add(fileContent, BorderLayout.CENTER)
        add(JButton("Show File Content").apply {
            addActionListener {
                currentJob?.cancel()
                currentJob = MainScope().launch {
                    // TODO create a Flow that recurses through the directory structure
                    // TODO   the flow should return objects that contain
                    // TODO     the name of the file
                    // TODO     the file's contents
                    fun fileFlow(): Flow<SampleFile> = flow {
                        walk(File("sample-files"), this)
                    }

                    // TODO when the button is pressed
                    // TODO   display "Preparing to display file contents" in the file name field
                    // TODO       (file content should be empty)
                    // TODO       display this for 2 seconds
                    fileName.text = "Preparing to display file contents"
                    fileContent.text = ""
                    delay(2000)

                    // TODO   collect the flow
                    // TODO     ONLY process files that have names containing a '2' or end with ".txt"
                    // TODO     displaying the file name in the fileName field, and the
                    // TODO     file content in the fileContent field
                    // TODO     keep the file name/content visible for 1 second per file
                    fileFlow().filter { it.name.contains('2') || it.name.endsWith(".txt") }
                        .flowOn(Dispatchers.IO)
                        .collect {
                            withContext(Dispatchers.Main) {
                                fileContent.text = it.content.joinToString("\n")
                                fileName.text = it.name
                                delay(1000)
                            }
                        }

                    // TODO   display "We hope you have enjoyed this presentation" in the file name field
                    // TODO       (file content should be empty)
                    // TODO       display this for 2 seconds
                    // TODO       then blank out the file name
                    fileContent.text = ""
                    fileName.text = "We hope you have enjoyed this presentation"
                    delay(2000)
                    fileName.text = ""

                    // TODO   if the button is pressed multiple times, be sure that we don't end up running
                    // TODO       multiple coroutines at the same time
                    // TODO
                    // TODO NOTE: Although Swing doesn't require being on the user interface thread to set
                    //            text in a label, pretend that it did - in other words, explicitly switch
                    //            to the user interface dispatcher to update the fields
                }
            }
        }, BorderLayout.SOUTH)
        setSize(600, 400)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }
}
