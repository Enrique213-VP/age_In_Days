package col.android.svape.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import col.android.svape.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener { view ->
            clickDatePicker(view)
        }


    }

    private fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "DatePicker works", Toast.LENGTH_LONG).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"

                binding.tvSelectDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectDateInMinutes = theDate!!.time / 86400000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate!!.time / 86400000

                val differenceInMinutes =  currentDateInMinutes - selectDateInMinutes

                binding.tvSelectedInMinutes.text = differenceInMinutes.toString()

            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}