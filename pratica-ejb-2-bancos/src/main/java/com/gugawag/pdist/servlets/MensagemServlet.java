package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();

        switch (operacao) {
                case "1": {
                    long id = Long.parseLong(request.getParameter("id"));
                    String texto = request.getParameter("mensagem");
                    boolean mensagem = mensagemService.inserir(id, texto);

                     if (mensagem == true) {
                        resposta.println("Mensagem inserida com sucesso");
                        resposta.println("ID: " + id + " - Texto: "+ texto);
                        break;
                    } else { resposta.println("a mensagem contém   palavras ofensivas");
                    resposta.println("ID: " + id + " - Texto: "+ texto);
                    break;
                    }
                }
                case "2": {
                    for (Mensagem msg : mensagemService.listar()) {
                        resposta.println("ID: " + msg.getId() + " - Texto: " + msg.getMensagem());
                    }
                    break;
                }
                default: {
                    resposta.println("Operação inválida!");
                }
            }
    }
}