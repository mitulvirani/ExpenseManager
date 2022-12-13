package com.example.expensemanager

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class AddInccomeExpenseActivity : AppCompatActivity() {
    lateinit var spinCategory: Spinner
    lateinit var spinPaymentmethod: Spinner
    lateinit var imgDate: ImageView
    lateinit var txtSetDate: TextView
    lateinit var edtIncomeExpense: EditText
    lateinit var edtDescription: EditText
    lateinit var txtType: TextView
    lateinit var btnAddIncomeExpense: AppCompatButton

    var mYear = 0
    var mMonth: kotlin.Int = 0
    var mDay: kotlin.Int = 0

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    var categoryName: String? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inccome_expense)
        txtType = findViewById(R.id.txtType)
        var type = intent.getStringExtra("type")

        txtType.setText(type)

        intiView()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun intiView() {
        spinCategory = findViewById(R.id.spinCategory)
        spinPaymentmethod = findViewById(R.id.spinPaymentmethod)
        imgDate = findViewById(R.id.imgDate)
        txtSetDate = findViewById(R.id.txtSetDate)
        edtDescription = findViewById(R.id.edtDescription)
        btnAddIncomeExpense = findViewById(R.id.btnAddIncomeExpense)
        edtIncomeExpense = findViewById(R.id.edtIncomeExpense)
        val categoryDB = CategoryDB(this)
        val incomeExpenseDB = IncomeExpenseDB(this)

        val paymentMethod = arrayOf("Online", "Offline", "Other")
        var paymentMethodSpinnerAdapter =
            ArrayAdapter(this, R.layout.category_list, R.id.txtCategory, paymentMethod)
        spinPaymentmethod.adapter = paymentMethodSpinnerAdapter


        var list = categoryDB.categoryDisplay()

        var categorySpinnerAdapter =
            CategorySpinnerAdapter(this, list, categoryItemClick = { category ->
                categoryName = category
            })
        spinCategory.adapter = categorySpinnerAdapter

        imgDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth -> txtSetDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
            val incomeExpense = edtIncomeExpense.text.toString()
            val category = spinCategory.toString()
            val paymentMethod = spinPaymentmethod.toString()
            val date = txtSetDate.text.toString()
            val description = edtDescription.text.toString()
            val type = txtType.text.toString()


            btnAddIncomeExpense.setOnClickListener {
                if (incomeExpense.isEmpty()) {
                    edtIncomeExpense.error = "Please Enter Amount"
                } else if (date.isEmpty()) {
                    txtSetDate.error = "Please Select Date"
                } else {
                    notification()
                    var position = spinPaymentmethod.selectedItemPosition
                    var payment = paymentMethod.get(position).toString()
                    incomeExpenseDB.insertIncomeExpense(
                        incomeExpense,
                        categoryName,
                        payment,
                        description,
                        date,
                        type
                    )
                    Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show()

                    var i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }


            }
        }

    }


        fun notification() {

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)

                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pendingIntent)

                builder = Notification.Builder(this)
                    .setContentText("Income Expense Data Updated")
                    .setContentTitle("Updated")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234, builder.build())

        }
}