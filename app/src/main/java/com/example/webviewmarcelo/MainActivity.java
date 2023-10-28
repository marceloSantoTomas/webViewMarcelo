package com.example.webviewmarcelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la referencia del id definido en el XML
        webView = findViewById(R.id.webView);

        // Obtiene las configuraciones del WebView para modificar sus propiedades
        WebSettings webSettings = webView.getSettings();

        // Habilita JavaScript
        webSettings.setJavaScriptEnabled(true);
        // Agrega una interfaz de JavaScript, permite comunicar el código de la aplicación
        webView.addJavascriptInterface(new JavaScriptInterface(), "appInterface");

        // El método loadURL se llama cuando el usuario hace clic en el botón
    }

    public void loadURL(View view) {
        // Obtiene referencias del EditText definido en el XML
        EditText urlEditText = findViewById(R.id.txtLink);

        // Obtiene el texto ingresado como cadena
        String url = urlEditText.getText().toString();

        // Comprueba si la cadena no está vacía antes de cargar la URL
        if (!url.isEmpty()) {
            webView.loadUrl(url);
        }
    }

    // Define una clase interna que se utiliza para comunicar entre JavaScript y Java
    private class JavaScriptInterface {
        // Clase interna que contiene el método llamado showToast
        @JavascriptInterface // Permite a JavaScript llamarlo desde una página
        public void showToast(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
