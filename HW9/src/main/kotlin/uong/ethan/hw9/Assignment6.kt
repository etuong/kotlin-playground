package uong.ethan.hw9

import java.io.File
import java.io.FileWriter

fun main() {
    val sampleFilesDirectory = File("sample-files")

    sampleFilesDirectory.exists().apply { sampleFilesDirectory.deleteRecursively() }

    val kotlinDirectory = File(sampleFilesDirectory, "kotlin")
    (1..3).forEach {
        var i = it
        var packageDirectory = File(kotlinDirectory, "package$i")
        packageDirectory.mkdirs()
        (1..2).forEach {
            var sampleFileNumber = "${i}${it}"
            var sampleFile = File(packageDirectory, "Sample${sampleFileNumber}.kt")
            FileWriter(sampleFile).use {
                it.write(
                    """
                    package package${i}
                    
                    class Sample${sampleFileNumber} {
                        fun foo() {
                            println("Hello Sample${sampleFileNumber}!!!")
                        }
                    }
                    """.trimIndent()
                )
            }
        }
    }

    var resourcesDirectory = File(sampleFilesDirectory, "resources")
    resourcesDirectory.mkdirs()
    setOf("fee", "fie", "foo").forEach {
        var filename = it
        var resourceFile = File(resourcesDirectory, "${filename}.txt")
        FileWriter(resourceFile).use { fw ->
            (1..3).forEach {
                fw.write("${filename} ".repeat(3))
                fw.write("\n")
            }
        }
    }
}
