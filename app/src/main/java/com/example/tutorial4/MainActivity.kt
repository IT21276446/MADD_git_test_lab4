package com.example.tutorial4

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.tutorial4.model.FormDat
import com.example.tutorial4.model.FormData
import com.example.tutorial4.model.ValidationResult

class MainActivity : AppCompatActivity() {


    lateinit var edtStudentId:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var btnConfirm:Button
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentId = findViewById(R.id.edtStudentId)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)
        btnConfirm = findViewById(R.id.btnConfirm)
    }

    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") {
                dialog, which ->
        }
        val dialog = builder.create()
        dialog.show()
    }



    override fun onResume() {
        super.onResume()

        btnConfirm.setOnClickListener(View.OnClickListener {

            count = 0
            val myForm = FormDat(edtStudentId.text.toString(),
                                spnYear.selectedItem.toString(),
                                spnSemester.selectedItem.toString(),
                                cbAgree.isChecked
            )

            val studentIdVal = myForm.validateStudentID()
            val yearVal = myForm.validateYear()
            val semesterVal = myForm.validateSemester()
            val agreeVal = myForm.validateAgreement()

            when(studentIdVal){

                is ValidationResult.Valid ->{
                    count++
                }
                is ValidationResult.Invalid ->{
                    edtStudentId.error = studentIdVal.errorMessage
                }
                is ValidationResult.Empty ->{
                    edtStudentId.error = studentIdVal.errorMessage
                }

            }

            when(yearVal){

                is ValidationResult.Valid ->{
                    count++
                }
                is ValidationResult.Invalid ->{

                    val tv:TextView = spnYear.selectedItem as TextView
                    tv.error = ""
                    tv.text = yearVal.errorMessage
                }
                is ValidationResult.Empty ->{

                    val tv:TextView = spnYear.selectedItem as TextView
                    tv.error = ""
                    tv.text = yearVal.errorMessage

                }

            }

            when(semesterVal){

                is ValidationResult.Valid ->{
                    count++
                }
                is ValidationResult.Invalid ->{

                    val tv:TextView = spnSemester.selectedItem as TextView
                    tv.error = ""
                    tv.text = semesterVal.errorMessage
                }
                is ValidationResult.Empty ->{

                    val tv:TextView = spnSemester.selectedItem as TextView
                    tv.error = ""
                    tv.text = semesterVal.errorMessage

                }
            }

            when(agreeVal){

                is ValidationResult.Valid ->{
                    count++
                }

                is ValidationResult.Invalid ->{
                    displayAlert("Error", agreeVal.errorMessage)
                }

                is ValidationResult.Empty ->{

                }

            }

            if(count == 4){
                displayAlert("Success.", "You have successfully registered")

                FormData(edtStudentId.text.toString(),
                    Integer.parseInt(spnYear.selectedItem.toString()),
                    spnSemester.selectedItem.toString(),
                    cbAgree.isChecked
                )
            }





        })
    }
}