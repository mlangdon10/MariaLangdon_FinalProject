package com.androidatc.marialangdon_finalproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_view_inventory.*

class view_inventory : AppCompatActivity() {

    private val data = ArrayList<String>()
    private val data1 = ArrayList<String>()
    private val data2 = ArrayList<String>()
    private val data3 = ArrayList<String>()
    private val data4 = ArrayList<String>()
    private val data5 = ArrayList<String>()

    private lateinit var auth: FirebaseAuth
    private lateinit var mydatabase: FirebaseDatabase
    private lateinit var myreference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_inventory)

        auth = FirebaseAuth.getInstance()
        mydatabase = FirebaseDatabase.getInstance()
        myreference = mydatabase.getReference("Gifts")

        // get intent object and data from intent
        val intent = getIntent()
        val receiverName = intent.getStringExtra("Receiver")
        val itemName = intent.getStringExtra("Item")
        val priceAmount = intent.getStringExtra("Price")
        val dateAdded = intent.getStringExtra("Date")
        val eventName = intent.getStringExtra("Event")
        val status = intent.getStringExtra("Status")

        data.add(receiverName.toString())
        data1.add(itemName.toString())
        data2.add(priceAmount.toString())
        data3.add(dateAdded.toString())
        data4.add(eventName.toString())
        data5.add(status.toString())

        val table = findViewById<TableLayout>(R.id.tb1)

        val row = TableRow(this)
        val t1 = TextView(this)
        val t2 = TextView(this)
        val t3 = TextView(this)
        val t4 = TextView(this)
        val t5 = TextView(this)
        val t6 = TextView(this)

        t1.setTextColor(Color.WHITE)
        t2.setTextColor(Color.WHITE)
        t3.setTextColor(Color.WHITE)
        t4.setTextColor(Color.WHITE)
        t5.setTextColor(Color.WHITE)
        t6.setTextColor(Color.WHITE)

        for (i in data.indices) {
            val rec = data[i]
            val ite = data1[i]
            val pri = data2[i]
            val dat = data3[i]
            val eve = data4[i]
            val stat = data5[i]

            t1.text = rec
            t2.text = ite
            t3.text = pri
            t4.text = dat
            t5.text = eve
            t6.text = stat
        }

        row.addView(t1)
        row.addView(t2)
        row.addView(t3)
        row.addView(t4)
        row.addView(t5)
        row.addView(t6)
        table.addView(row)

        // when user clicks on add another entry button they are taken back to that activity
        btAdd2.setOnClickListener {
            startActivity(Intent(this, inventory::class.java))
        }
    }
}

