package com.caroline.appagua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.caroline.appagua.model.CalcularIngestaoDiaria
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var edit_peso:EditText
    private lateinit var edit_idade:EditText
    private lateinit var bt_calcular: Button
    private lateinit var txt_resultado:TextView
    private lateinit var ic_redefinir_dados:ImageView

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hiding action bar
        supportActionBar!!.hide();
        IniciarComponentes();
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

        bt_calcular.setOnClickListener{
            if(edit_idade.text.toString().isEmpty()){
                Toast.makeText(this,R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
            }else if(edit_peso.text.toString().isEmpty()){
                Toast.makeText(this,R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()
            }else{
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
                resultadoMl = calcularIngestaoDiaria.ResultadoLitros()
                val formatar = NumberFormat.getNumberInstance(Locale("pt","BR"))
                formatar.isGroupingUsed = false
                txt_resultado.text = formatar.format(resultadoMl)+" "+ "Litros"
            }

        }

        ic_redefinir_dados.setOnClickListener{
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_descricao)
                .setPositiveButton("OK",{dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    txt_resultado.text=""
                })
            alertDialog.setNegativeButton("Cancelar",{dialogInterface, i ->  })
            val dialog = alertDialog.create()
            dialog.show()

        }
    }

    private fun IniciarComponentes(){
        edit_peso = findViewById(R.id.et_peso);
        edit_idade = findViewById(R.id.et_idade);
        bt_calcular = findViewById(R.id.bt_calcular);
        txt_resultado = findViewById(R.id.tv_resultado);
        ic_redefinir_dados = findViewById(R.id.ic_redefinir);

    }
}