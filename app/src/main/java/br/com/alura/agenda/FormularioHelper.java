package br.com.alura.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.alura.agenda.br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Fabricio on 29/01/2017.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final EditText campoEmail;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText)activity.findViewById( R.id.formulario_nome );
        campoEndereco = (EditText)activity.findViewById( R.id.formulario_endereco );
        campoTelefone = (EditText)activity.findViewById( R.id.formulario_telefone );
        campoSite = (EditText)activity.findViewById( R.id.formulario_site );
        campoEmail = (EditText)activity.findViewById( R.id.formulario_email );
        campoNota = (RatingBar)activity.findViewById( R.id.formulario_nota );
    }

    public Aluno pegaAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome( campoNome.getText().toString() );
        aluno.setEndereco( campoEndereco.getText().toString() );
        aluno.setTelefone( campoTelefone.getText().toString() );
        aluno.setSite( campoSite.getText().toString() );
        aluno.setEmail( campoEmail.getText().toString() );
        aluno.setNota( campoNota.getProgress() );
        return aluno;
    }
}
