
package teste_mão_na_massa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Main2 {
    static class Pessoa{
        String nome;
        LocalDate dataNascimento;
        
        public Pessoa(String nome, LocalDate dataNascimento){
            this.nome = nome;
            this.dataNascimento = dataNascimento;
        };
    };
    
    static class Funcionario extends Pessoa{
        BigDecimal salario;
        String funcao;
        
        public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
           super(nome, dataNascimento);
           this.funcao = funcao;
           this.salario = salario;
        };
    };
    public static void main(String[] args){
        System.out.println("=== GESTAO DE FUNCIONARIOS ===");
        
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat formatoNumero = new DecimalFormat("#,##0.00");
        
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        System.out.println("Funcionarios cadastrados: " + funcionarios.size());
        
        for(int i=0; i < funcionarios.size(); i++){
            if (funcionarios.get(i).nome.equals("Joao")){
                funcionarios.remove(i);
                System.out.println("João foi removido da lista.");
                break;
            }
            
        }
        
         System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        for (Funcionario func : funcionarios) {
            String dataFormatada = func.dataNascimento.format(formatoData);
            String salarioFormatado = formatoNumero.format(func.salario);
            System.out.println("Nome: " + func.nome + 
                             " | Nascimento: " + dataFormatada + 
                             " | Salário: R$ " + salarioFormatado + 
                             " | Função: " + func.funcao);
        }
        
        System.out.println("\n=== APOS AUMENTO DE 10% ===");
        for(Funcionario func : funcionarios){
            BigDecimal aumento = func.salario.multiply(new BigDecimal("0.10"));
            func.salario.add(aumento);
            
            String dataFormatada = func.dataNascimento.format(formatoData);
            String salarioFormatado = formatoNumero.format(func.salario);
            System.out.println("Nome: " + func.nome + 
                             " | Nascimento: " + dataFormatada + 
                             " | Salário: R$ " + salarioFormatado + 
                             " | Função: " + func.funcao);
        }
        
        
        System.out.println("\n=== ANIVERSARIANTES MES 10 E 12 ===");
        for(Funcionario func : funcionarios){
            int mes = func.dataNascimento.getMonthValue();
            if (mes == 10 || mes == 12){
                String dataFormatada = func.dataNascimento.format(formatoData);
                System.out.println("Nome: "+ func.nome +"Nascimento "+ dataFormatada);
            }
        }
        
        System.out.println("\n=== FUNCIONARIO COM MAIOR IDADE ===");
        Funcionario maisVelho = funcionarios.get(0);
        for(Funcionario func : funcionarios){
            if (func.dataNascimento.isBefore(maisVelho.dataNascimento)){
                maisVelho = func;
            }
       
        }
        int idade = Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
        System.out.println("Nome: "+ maisVelho.nome + " idade "+ idade + " anos");
        
         System.out.println("\n=== FUNCIONÁRIOS EM ORDEM ALFABÉTICA ===");
        
        List<String> nomes = new ArrayList<>();
        for (Funcionario func : funcionarios) {
            nomes.add(func.nome);
        }
        
        Collections.sort(nomes);
        for (String nome : nomes) {
            System.out.println(nome);
        }
        
        System.out.println("\n=== SALARIO EM SALARIOS MINIMOS ===");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        
        for(Funcionario func : funcionarios){
            BigDecimal qntSalarioMinimos = func.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(func.nome+": "+ formatoNumero.format(qntSalarioMinimos)+ " salarios minimos");
            
        }
    };
}
