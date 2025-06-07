package projeto_integrado.controllers.controllers_de_login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import projeto_integrado.Infra.CurrencyAPI;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Coinvert")
public class CurrencyController {



    private CurrencyAPI api = new CurrencyAPI();

    @GetMapping
    public String paginapost() {
        return "Coinvert";  
    }

    @PostMapping
    public String obterCotacao(@RequestParam String origem, @RequestParam String destino, @RequestParam double valor, Model model) {
        String valorco = this.api.obterCotacao(origem, destino);
        double valorcotacao = Double.parseDouble(valorco);
        
        Double valorconvertido = valor * valorcotacao;
        
        model.addAttribute("cotacao", valorcotacao);
        model.addAttribute("valorConvertido", valorconvertido);
        return "Coinvert :: resultado"; 
    }

    @PostMapping("/passado")
    public String valoremadata(@RequestParam String origem, @RequestParam String destino,@RequestParam Integer ano, @RequestParam Integer mes, @RequestParam Integer dia, @RequestParam double valor, Model model) {
        String Valoremdata = this.api.valoremadata(origem, destino, ano, mes, dia);
        double valorcotacaobefore = Double.parseDouble(Valoremdata);

        double Valoremdataconvertido = valor * valorcotacaobefore;

        model.addAttribute("valorcotacaobefore", valorcotacaobefore);
        model.addAttribute("Valoremdataconvertido", Valoremdataconvertido);
        return "Coinvert :: Seeu";
    }

    @PostMapping("/simulaçao")
    @ResponseBody
    public String compararCotacao(@RequestParam String origem, @RequestParam String destino, @RequestParam double valor, @RequestParam Integer ano, @RequestParam Integer mes, @RequestParam Integer dia, Model model){
        double cotacaoAtual = Double.parseDouble(api.obterCotacao(origem, destino));
        double cotacaoPassada = Double.parseDouble(api.valoremadata(origem, destino, ano, mes, dia));

// Quantidade de moeda estrangeira que teria comprado com 'valor' reais no passado
        double quantidadeMoeda = valor / cotacaoPassada;

// Valor dessa quantidade de moeda na cotação de hoje
        double valorHoje = quantidadeMoeda * cotacaoAtual;

// Valor que foi convertido no passado (opcional, é igual ao valor original em reais)
        double valorConvertidoNoPassado = valor;

        double diferenca = valorHoje - valor;

        String resultado;
        if (diferenca > 0) {
            resultado = "Lucro de " + String.format("%.2f", diferenca);
        } else if (diferenca < 0) {
            resultado = "Prejuízo de " + String.format("%.2f", Math.abs(diferenca));
        } else {
            resultado = "Nem lucro nem prejuízo.";
        }

        model.addAttribute("cotacaoAtual", cotacaoAtual);
        model.addAttribute("cotacaoPassada", cotacaoPassada);
        model.addAttribute("valorConvertidoNoPassado", valorConvertidoNoPassado);
        model.addAttribute("valorHoje", valorHoje);
        model.addAttribute("resultado", resultado);

// Para JSON (ex: Postman)
        Map<String, Object> response = new HashMap<>();
        response.put("cotacaoAtual", cotacaoAtual);
        response.put("cotacaoPassada", cotacaoPassada);
        response.put("valorConvertidoNoPassado", valorConvertidoNoPassado);
        response.put("valorHoje", valorHoje);
        response.put("resultado", resultado);

        return response.toString();
    }

}
	
