package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }

    public boolean inserir(long id, String mensagem) {
        if (temPalavrao(mensagem)) {
            /* throw new RuntimeException("Mensagem ofensiva bloqueada."); */
            return false;
        }
        else {
            Mensagem novaMensagem = new Mensagem(id, mensagem);
            mensagemDao.inserir(novaMensagem);
            return true;
        }
    }

    private boolean temPalavrao(String texto) {
        String[] palavroes = {"palavra1", "palavra2"};

        String lowerCase = texto.toLowerCase();

        for (String palavrao : palavroes) {
            if (lowerCase.contains(palavrao.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}