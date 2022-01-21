package fr.vannsuplabs.programmationfonctionnelle.data

import android.content.Context
import android.util.Log
import java.io.File

class FileManager(private val context: Context) {

    fun listOfFile() :String {
        return try {
            "Fichier dans le répertoire : " + File(context.filesDir.toURI()).list()?.reduce{ acc,string -> "$acc, $string" } + "\n"
        }catch (ex:Exception){
            ""
        }
    }

    private fun hasFile(name: String):Boolean{
        return File(context.filesDir,"$name.txt").exists()
    }

    fun deleteFile(name: String){
        if(hasFile(name))
            File(context.filesDir,"$name.txt").delete()
    }

    fun saveToFile(texts: String, name: String){
        try {
            val file = File(context.filesDir,"$name.txt")
            Log.i("Save as $file", texts)
            file.printWriter().use { out -> out.println(texts) }
        }catch (ex:Exception){
            Log.e("File Manager Failed","Your texts \'$texts\' is Not save at file", ex)
        }
    }

    fun readFile(name: String): String{
        return if(hasFile(name))
            File(context.filesDir,"$name.txt").readText()
        else
            ""
    }
}