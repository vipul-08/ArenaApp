package `in`.ac.siesgst.arena.activity

import `in`.ac.siesgst.arena.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_username.*

class UsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        buttonNext.setOnClickListener {
            editor.putString("username", enterUsername.text.toString())
            editor.apply()
            editor.commit()
            Intent(applicationContext, BottomNavigationActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}
