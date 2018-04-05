package aprenderbrincando.Controller;

import static aprenderbrincando.Config.QUANTIDADE_BOTOES;
import aprenderbrincando.Model.Dao.ValoresDAO;
import aprenderbrincando.View.BotaoFormula;
import aprenderbrincando.Model.Vo.Valores;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vinicius Berto
 */
public class Nivel extends Observado{

    private List<BotaoFormula> listaBotoes;
    private List<Valores> listaAtual;
    private List<Integer> listaUsados;
    private int id = 1;

    
    
    public Nivel(Observador ob) {
        super(ob);
        this.listaAtual = new ArrayList<>();
        this.listaBotoes = new ArrayList<>();
        this.listaUsados = new ArrayList<>();
    }

    public Valores formulaAtual() {
        Random rn = new Random();
        boolean resp = true;
        int n;
        do {
            resp = true;
            n = rn.nextInt(listaAtual.size() - 1);
            for (int I = 0; I < listaUsados.size(); I++) {
                if (n == listaUsados.get(I)) {
                    resp = false;
                }
            }
        } while (!resp);

        listaUsados.add(n);
        return listaAtual.get(n);
    }

    public void gerarAtualizarListaAtual() {
        ValoresDAO dao = new ValoresDAO();
        List<Valores> listaCompleta = dao.listagem();

        switch (id) {
            case 2:
                QUANTIDADE_BOTOES = 12;
                break;
            case 3:
                QUANTIDADE_BOTOES = 14;
                break;
            case 4:
                QUANTIDADE_BOTOES = 16;
                break;
            case 5:
                QUANTIDADE_BOTOES = 18;
                break;
            case 6:
                QUANTIDADE_BOTOES = 20;
                break;
            case 7:
                QUANTIDADE_BOTOES = 22;
                break;
            case 8:
                QUANTIDADE_BOTOES = 24;
                break;
        }

        Random ram = new Random();
        int valor;
        listaAtual.clear();
        listaUsados.clear();

        for (int i = 0; i < QUANTIDADE_BOTOES; i++) {
            boolean teste = true;
            valor = ram.nextInt(listaCompleta.size());
            for (int j = 0; j < listaAtual.size(); j++) {
                if (listaAtual.get(j).getNome().equals(listaCompleta.get(valor).getNome())) {
                    teste = false;
                }
            }
            if (teste) {
                listaAtual.add(listaCompleta.get(valor));
            } else {
                i--;
            }
        }
    }

    public List<BotaoFormula> criarBotoes() {
        listaBotoes.clear();
        for (int i = 0; i < listaAtual.size(); i++) {
            BotaoFormula botao = new BotaoFormula(listaAtual.get(i).getBtnTexto(), listaAtual.get(i).getFormula());
            listaBotoes.add(botao);
        }

        return listaBotoes;
    }

    public List<BotaoFormula> getListaBotoes() {
        return listaBotoes;
    }

    public List<Valores> getListaAtual() {
        return listaAtual;
    }

    public int getId() {
        return id;
    }

    public void incrementaId() {
        this.id++;
    }
    
}
