package com.app.weather.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.weather.R
import com.app.weather.util.Constants
import com.app.weather.util.Tools


class InsertCity : AppCompatActivity() {
    private lateinit var edtCityName:EditText
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_city)
        edtCityName=findViewById(R.id.edtCityName)
        actionSearch()
    }

    fun lookupClicked(view: View) {
        performSearch(view)
    }

    private fun actionSearch(){
        edtCityName.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v)
                return@OnEditorActionListener true
            }
            false
        })
    }
    private fun performSearch(view:View){
        if (Tools.hasValue(edtCityName.text.toString())) {
            Tools.hideSoftKeyBoard(this@InsertCity, view)
            val intent = Intent(this@InsertCity, WeatherList::class.java)
            intent.putExtra(Constants.KEY_CITY_NAME, edtCityName.text.toString())
            startActivity(intent)        } else {
            Toast.makeText(applicationContext, "Please Enter City Name", Toast.LENGTH_SHORT).show()
        }

    }
}