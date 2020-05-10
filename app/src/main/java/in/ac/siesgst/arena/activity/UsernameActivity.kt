package `in`.ac.siesgst.arena.activity

import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.model.User
import `in`.ac.siesgst.arena.util.RetrofitClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_username.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsernameActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
        progressBar = findViewById(R.id.enterUsernameProgress)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        buttonNext.setOnClickListener {
            val username = enterUsername.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(applicationContext, "Username Empty!!", Toast.LENGTH_SHORT).show()
            }
            else {
                progressBar.visibility = VISIBLE
                RetrofitClient.instance.user(username)
                    .enqueue(object: Callback<User> {
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            progressBar.visibility = GONE
                            Toast.makeText(applicationContext, "Error finding User!!", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            progressBar.visibility = GONE
                            if (response.isSuccessful) {
                                editor.putString("username", username)
                                editor.apply()
                                editor.commit()
                                Intent(applicationContext, BottomNavigationActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }
                            else {
                                Toast.makeText(applicationContext, "Wrong Username Entered!!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
            }
        }
    }
}
