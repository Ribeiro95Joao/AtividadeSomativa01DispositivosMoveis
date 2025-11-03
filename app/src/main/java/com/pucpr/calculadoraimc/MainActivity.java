package com.pucpr.calculadoraimc;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText heightValue;
    EditText weightValue;
    TextView resultValue;
    TextView classificationValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heightValue = findViewById(R.id.heightValue);
        weightValue = findViewById(R.id.weightValue);
        resultValue = findViewById(R.id.resultValue);
        classificationValue = findViewById(R.id.imcClassification);

    }

    public void buttonCalculationOnClick(View v){
        String heightStr = heightValue.getText().toString();
        String weightStr = weightValue.getText().toString();

        resultValue.setText("");
        classificationValue.setText("");

        try {
            float height = Float.parseFloat(heightStr);
            float weight = Float.parseFloat(weightStr);

            if (height <= 0) {
                resultValue.setText("Erro: A altura deve ser > 0.");
                return;
            }

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                resultValue.setText("Erro: Preencha todos os campos.");
                return;
            }

            float imc = weight / (height * height);

            String imcResult = String.format(Locale.getDefault(), "%.2f", imc);
            resultValue.setText("IMC: " + imcResult);

            String classification = classifyImc(imc);
            classificationValue.setText("Classificação: " + classification);

        } catch (NumberFormatException e) {
            resultValue.setText("Erro: Digite apenas números válidos.");
        }
    }

    private String classifyImc(float imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau I";
        } else if (imc < 39.9) {
            return "Obesidade Grau II (Severa)";
        } else {
            return "Obesidade Grau III (Mórbida)";
        }
    }
}