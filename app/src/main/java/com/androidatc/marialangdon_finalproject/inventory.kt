package com.androidatc.marialangdon_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_inventory.*

class inventory : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var mydatabase : FirebaseDatabase
    private lateinit var myreference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        auth = FirebaseAuth.getInstance()
        mydatabase = FirebaseDatabase.getInstance()
        myreference = mydatabase.getReference("Gifts")

        // get user input from edit texts and radio button
        val receiverName = findViewById<EditText>(R.id.edReceiver)
        val itemName = findViewById<EditText>(R.id.edItem)
        val priceAmount = findViewById<EditText>(R.id.edPrice)
        val dateAdded = findViewById<EditText>(R.id.edDate)
        val eventName = findViewById<EditText>(R.id.edEvent)

        val rdGroup = findViewById<RadioGroup>(R.id.rgStatus)

        var status = ""

        rdGroup.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == R.id.rbPayed) {
                status = "Payed"
            }
            if (checkedID == R.id.rbOrdered) {
                status = "Ordered"
            }
            if (checkedID == R.id.rbReceived) {
                status = "Received"
            }
            if (checkedID == R.id.rbWrapped) {
                status = "Wrapped"
            }
            if (checkedID == R.id.rbGiven) {
                status = "Given"
            }
        }

        btAdd.setOnClickListener {

            // send data to database
            sendData(receiverName.text.toString().trim(), itemName.text.toString().trim(),
                     priceAmount.text.toString().trim(), dateAdded.text.toString().trim(),
                     eventName.text.toString().trim(), status.trim())

            val receiver = receiverName.text.toString()
            val item = itemName.text.toString()
            val price = priceAmount.text.toString()
            val date = dateAdded.text.toString()
            val event = eventName.text.toString()

            // intent to start activity
            val intent = Intent( this@inventory, view_inventory::class.java)
            intent.putExtra("Receiver", receiver)
            intent.putExtra("Item", item)
            intent.putExtra("Price", price)
            intent.putExtra("Date", date)
            intent.putExtra("Event", event)
            intent.putExtra("Status", status)

            startActivity(intent)
        }

    }

    // send data to the Firebase database
    private fun sendData(receiver: String, item: String,price: String,
                         date: String, event: String, status: String ) {

        var model = DatabaseModel(receiver,item,price,date,event,status)
        var id = myreference.push().key

        myreference.child(id!!).setValue(model)
    }

    class DatabaseModel (var receiver:String, var item:String, var price:String,
                         var date:String, var event:String, var status:String) {}

}
