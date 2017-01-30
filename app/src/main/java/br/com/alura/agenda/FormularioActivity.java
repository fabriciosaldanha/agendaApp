package br.com.alura.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.alura.agenda.modelo.Aluno;
import br.com.alura.agenda.dao.AlunoDao;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);
        helper = new FormularioHelper( this );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.menu_formulario, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();
                AlunoDao dao = new AlunoDao( this );
                dao.insere( aluno );
                dao.close();

                Toast.makeText( FormularioActivity.this, "Aluno " + aluno.getNome() + " Salvo!", Toast.LENGTH_SHORT ).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getEditTextContent( int id ){
        EditText campoNome = (EditText) findViewById( id );
        return campoNome.getText().toString();
    }

    private float getRatingBarContent( int id ){
        RatingBar rating = (RatingBar) findViewById( id );
        return rating.getRating();
    }
}
