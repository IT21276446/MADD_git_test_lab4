package com.example.tutorial4.model

class FormDat(private var studentID:String,
              private var semester:String,
              private var year:String,
              private var agree:Boolean) {

    //Student id validation
    fun validateStudentID():ValidationResult{
        return if(studentID.isEmpty()){
            ValidationResult.Empty("Student ID cannot be empty!")
        }
        else if(!studentID.startsWith("IT")){
            ValidationResult.Invalid("Student ID should start with IT")
        }
        else if (studentID.length > 10){
            ValidationResult.Invalid("Student ID cannot have more than 10 characters")
        }
        else if (studentID.length < 10){
            ValidationResult.Invalid("Student ID cannot have less than 10 characters")
        }
        else{
            ValidationResult.Valid
        }

    }

    //Semester validation
    fun validateSemester():ValidationResult{
        return if (semester.isEmpty()){
            ValidationResult.Empty("Semester cannot be empty!")
        }
        else{
            ValidationResult.Valid
        }

    }

    //Year validation
    fun validateYear():ValidationResult{
        return if (year.isEmpty()){
            ValidationResult.Empty("Year cannot be empty!")
        }
        else{
            ValidationResult.Valid
        }

    }

    //Check agreement
    fun validateAgreement():ValidationResult{
        return if(!agree){
            ValidationResult.Invalid("Please agree to the terms and conditions!")
        }
        else{
            ValidationResult.Valid
        }

    }
}