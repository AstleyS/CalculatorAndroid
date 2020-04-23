package com.example.aula5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.list_historic

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val listaOperacoes = intent.getParcelableArrayListExtra<Operation>(EXTRA_OPERATIONS)
        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, listaOperacoes)

        button_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.apply {}
            startActivity(intent)
            finish()
        }
    }
}
