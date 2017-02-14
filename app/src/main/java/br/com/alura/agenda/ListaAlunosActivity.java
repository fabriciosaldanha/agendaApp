package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        carregarListaAlunos();
        novoAlunoClickListener();
        registerForContextMenu( listaAlunos );
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listaAlunos, View item, int position, long id) {
                Aluno alunoSelecionado = (Aluno)listaAlunos.getItemAtPosition( position );
                Intent vaiParaFormulario = new Intent( ListaAlunosActivity.this, FormularioActivity.class );
                vaiParaFormulario.putExtra( "aluno", alunoSelecionado );
                startActivity(vaiParaFormulario);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo)  {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition( info.position );

                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                alunoDAO.deleta( aluno.getId() );
                alunoDAO.close();

                carregarListaAlunos();
                return false;
            }
        });
    }

    private void novoAlunoClickListener() {
        Button botaoNovoAluno = (Button)findViewById(R.id.novo_aluno);

        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormulario = new Intent( ListaAlunosActivity.this, FormularioActivity.class );
                startActivity(vaiParaFormulario);
            }
        });
    }

    private void carregarListaAlunos() {
        AlunoDAO alunoDAO = new AlunoDAO( this );
        List<Aluno> alunos = alunoDAO.buscaAlunos();

        ArrayAdapter adapter = new ArrayAdapter<Aluno>( this, android.R.layout.simple_list_item_1, alunos );

        listaAlunos.setAdapter( adapter );
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaAlunos();
    }
}
