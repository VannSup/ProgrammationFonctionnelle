package fr.vannsuplabs.programmationfonctionnelle.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import fr.vannsuplabs.programmationfonctionnelle.R
import fr.vannsuplabs.programmationfonctionnelle.data.FileManager
import fr.vannsuplabs.programmationfonctionnelle.utils.StringTools

class MainActivity : AppCompatActivity() {

    lateinit var fileManager: FileManager
    lateinit var stringTools: StringTools
    lateinit var title:     TextInputEditText
    lateinit var textInput: TextInputEditText
    lateinit var saveButton: Button
    lateinit var readButton: Button
    lateinit var textResume: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        fileManager = FileManager(this.baseContext)
        stringTools = StringTools()
        initSimpleView()
    }

    fun initSimpleView(){
        title = findViewById(R.id.titre)
        textInput = findViewById(R.id.text)
        textResume = findViewById(R.id.text_info)
        textResume.text = fileManager.listOfFile()
        saveButton = findViewById(R.id.save)
        readButton= findViewById(R.id.read)

        textInput.doOnTextChanged { charSequence, _, _, _ ->
            if(title.text.toString().isNotEmpty())
                textResume.text = fileManager.listOfFile() + stringTools.showDetailAboutText(charSequence.toString(),title.text.toString())
        }

        saveButton.setOnClickListener {
            fileManager.saveToFile(textInput.text.toString(), "${title.text}")
        }

        readButton.setOnClickListener {
            val fileText = fileManager.readFile("${title.text}")
            textInput.setText(fileText)
        }
    }
}