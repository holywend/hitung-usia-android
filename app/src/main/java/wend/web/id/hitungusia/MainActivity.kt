package wend.web.id.hitungusia

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvTanggal: TextView? = null
    private var tvMenit: TextView? = null
    private var tvJam: TextView? = null
    private var tvHari: TextView? = null
    private var tvLabelTanggal: TextView? = null
    private var tvLabelMenit: TextView? = null
    private var tvLabelJam: TextView? = null
    private var tvLabelHari: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnTanggal: Button = findViewById(R.id.btnTanggal)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvMenit = findViewById(R.id.tvMenit)
        tvJam = findViewById(R.id.tvJam)
        tvHari = findViewById(R.id.tvHari)
        tvLabelTanggal = findViewById(R.id.tvLabelTanggal)
        tvLabelMenit = findViewById(R.id.tvLabelMenit)
        tvLabelJam = findViewById(R.id.tvLabelJam)
        tvLabelHari = findViewById(R.id.tvLabelHari)

        btnTanggal.setOnClickListener {
            clickBtnTanggal()
        }
    }

    private fun clickBtnTanggal() {
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this,{
                _, year, month, day ->
            val tanggal = "$day/${month+1}/$year"
            tvTanggal?.text = tanggal
            tvTanggal?.visibility=View.VISIBLE
            tvMenit?.visibility = View.VISIBLE
            tvJam?.visibility = View.VISIBLE
            tvHari?.visibility = View.VISIBLE
            tvLabelTanggal?.visibility = View.VISIBLE
            tvLabelMenit?.visibility = View.VISIBLE
            tvLabelJam?.visibility = View.VISIBLE
            tvLabelHari?.visibility = View.VISIBLE
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDate = sdf.parse(tvTanggal?.text as String)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            try {
                val minutes = (currentDate?.time ?:0) / 60000 - (selectedDate?.time?:0) / 60000
                val hours = minutes / 60
                val days = hours / 24
                tvMenit?.text = String.format(Locale.ENGLISH,"%,d",minutes)
                tvJam?.text = String.format(Locale.ENGLISH,"%,d",hours)
                tvHari?.text = String.format(Locale.ENGLISH,"%,d",days)
            }catch(e: Exception){
                print(e.message)
            }

            Toast.makeText(this,"terpilih tanggal $day bulan ${month+1} tahun $year",Toast.LENGTH_SHORT).show()
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePicker.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePicker.show()

    }
}