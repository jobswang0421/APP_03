package com.example.app_03

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applyDate = findViewById<TextView>(R.id.BIRTHDAY)
        val radGrp_Gender = findViewById<RadioGroup>(R.id.radGrpGENDER) // 确保这里使用的ID与布局文件中的ID匹配
        val chkbox1 = findViewById<CheckBox>(R.id.INTEREST1)
        val chkbox2 = findViewById<CheckBox>(R.id.INTEREST2)
        val chkbox3 = findViewById<CheckBox>(R.id.INTEREST3)
        val btn_send = findViewById<Button>(R.id.SEND)

        applyDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, year, month, day ->
                val format = setDateFormat(year, month, day)
                applyDate.text = format
            }, year, month, day).show()
        }

        btn_send.setOnClickListener {
            
            var msg = "興趣:\n"
            if (chkbox1.isChecked) msg += "${chkbox1.text}\n"
            if (chkbox2.isChecked) msg += "${chkbox2.text}\n"
            if (chkbox3.isChecked) msg += "${chkbox3.text}\n"

            val selectedGenderId = radGrp_Gender.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.MALE -> "性别: 男"
                R.id.FEMALE -> "性别: 女"
                else -> "性别: 未知"
            }
            msg += "\n$gender\n"

            val birthday = applyDate.text.toString()
            msg += "生日: $birthday"

            // 显示对话框
            AlertDialog.Builder(this).apply {
                setTitle("确认信息")
                setMessage(msg)
                setPositiveButton("确定") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }
    }

    private fun setDateFormat(year: Int, month: Int, day: Int): String {
        return "$year-${month + 1}-$day"
    }
}
