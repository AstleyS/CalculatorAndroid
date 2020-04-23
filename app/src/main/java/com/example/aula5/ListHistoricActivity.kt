package com.example.aula5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_historic.*
import kotlinx.android.synthetic.main.activity_list_historic.list_historic

class ListHistoricActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_historic)

        val listaOperacoes = intent.getStringArrayListExtra(EXTRA_NAME)
        list_historic?.adapter = HistoryAdapter(this, R.layout.item_expression, listaOperacoes)

        button_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.apply {}
            startActivity(intent)
            finish()
        }
    }
}
