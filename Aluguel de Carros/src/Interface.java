
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.*;

/**
 * Classe responsavel por fazer a interface entre maquina e cliente.
 * @author Igor
 */
public class Interface {
    
    private boolean aux = true;
    DecimalFormat df = new DecimalFormat("#.00");
    
    private final locadora carroSouth = new southCar();
    private final locadora carroWest = new westCar();
    private final locadora carroNorth = new northCar();
    
    /**
     * Construtor da classe Interface.
     */
    public Interface(){
        System.out.println ("\tBem vindo ao aluguel de veiculos!");
    }
    
    /**
     * Metodo para ler numeros inteiros digitados no terminal.
     * @return O numero inteiro digitado no terminal.
     */
    public int inputInt(){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Digite a sua opção: ");
        try {
            return input.nextInt();
        }
        catch (java.util.InputMismatchException e) {
            System.out.println ("Opçao invalida. Escolha uma das opçoes validas.");
            input.nextLine();
        }
        }
    }
    
    /**
     * Metodo que invoca o menu principal do programa.
     */
    public void menuInicial(){
      
      System.out.println ("\nOpções validas:\n");
      System.out.println ("1 - Alugar veiculo");
      System.out.println ("2 - Ler arquivo .txt");
      System.out.println ("3 - Sair do sistema");
      while(aux == true){
      int lido = inputInt();
      switch(lido){
          case 1:
              menuAluguel();
              aux = false;
              break;
          case 2:
              confirmarLeitura();
              aux = false;
              break;
          case 3:
              System.out.println ("Saindo do sistema.");
              System.exit(0);
               aux = false;
              break;
          default:
              System.out.println ("Opçao invalida. Escolha uma das opçoes validas.\n");
              menuInicial();
        }
      }
      aux = true;
   }
    
    /**
     * Metodo que invoca o menu especifico para alugueis de carro, sendo
     * possivel escolher entre veiculos comuns, em que qualquer carro pode
     * ser selecionado, ou veiculos premium, que apenas veiculos do tipo 
     * esportivo ou SUV's serão levados em consideração para calcular o preço.
     */
    public void menuAluguel(){
      System.out.println ("\n\tMenu de Aluguel de Veiculo\n");
      System.out.println ("1 - Alugar veiculo comum");
      System.out.println ("2 - Alugar veiculo premium");
      System.out.println ("3 - Voltar ao menu anterior");
      System.out.println ("4 - Sair do sistema");
      
      while(aux == true){
      int lido2 = inputInt();
      switch(lido2){
          case 1:
              menuCarroComum();
              aux = false;
              break;
          case 2:
              menuCarroPremium();
               aux = false;
              break;
          case 3:
              menuInicial();
              aux = false;
              break;
          case 4:
              System.out.println ("Saindo do sistema.");
              System.exit(0);
               aux = false;
              break;
          default:
              System.out.println ("Opçao invalida. Escolha uma das opçoes validas.\n");
              menuAluguel();
      }
      } 
    }
    
    /**
     * Metodo do menu especifico de veiculos comuns. Nesse metodo todos os
     * carros são levados em consideração na hora de calcular o menor preço
     * dentre os veiculos disponiveis.
     */
    public void menuCarroComum(){
      int fide = clienteFidelidade();
      int pessoas = quatidadePessoas();
      int quantidadeDias = quantidadeDias();
      ArrayList<String> datas = pegarDatas(quantidadeDias);
      double menorPreço = 1000000;
      String nome = "";
      String locadora = "";
      if(pessoas <= carroSouth.getLimitePessoas()){
          double preço = calcularPreço(fide,pessoas,carroSouth,datas);
          menorPreço = preço;
          nome = carroSouth.getNome();
          locadora = carroSouth.getLocadora();
      }
      if(pessoas <= carroNorth.getLimitePessoas()){
          double preço3 = calcularPreço(fide,pessoas,carroNorth,datas);
          if(preço3 <= menorPreço){
              menorPreço = preço3;
              nome = carroNorth.getNome();
              locadora = carroNorth.getLocadora();
          }  
      }
      if(pessoas <= carroWest.getLimitePessoas()){
          double preço2 = calcularPreço(fide,pessoas,carroWest,datas);
          if(preço2 <= menorPreço){
              menorPreço = preço2;
              nome = carroWest.getNome();
              locadora = carroWest.getLocadora();
          }
      }
      String dx = df.format(menorPreço);
      System.out.println ("O preço total por pessoa é R$" + dx + ", para um carro " + nome + " para " + quantidadeDias + " dia(s) na locadora " + locadora + ".");
    }
    
    /**
     * Metodo do menu especifico de veiculos premiuns. Neste metodo serão
     * levados em consideração apenas os carros do tipo esportivo e SUV's na
     * momento de decidir o preço do veiculo mais barato.
     */
    public void menuCarroPremium(){
      int fide = clienteFidelidade();
      int pessoas = quatidadePessoas();
      int quantidadeDias = quantidadeDias();
      ArrayList<String> datas = pegarDatas(quantidadeDias);
      double menorPreço = 1000000;
      String nome = "";
      if(pessoas <= carroNorth.getLimitePessoas()){
          double preço3 = calcularPreço(fide,pessoas,carroNorth,datas);
          menorPreço = preço3;
          nome = carroNorth.getNome();    
      }
      if(pessoas <= carroWest.getLimitePessoas()){
          double preço2 = calcularPreço(fide,pessoas,carroWest,datas);
          if(preço2 <= menorPreço){
              menorPreço = preço2;
              nome = carroWest.getNome();
          }
      }
      
      String dx = df.format(menorPreço);
      System.out.println ("O preço total por pessoa é R$" + dx + ", para um carro " + nome + " para " + quantidadeDias + " dia(s).");
    }
    
    /**
     * Metodo para calcular o preço total por pessoas. Portanto leva em
     * consideração o numero de pessoas que estarão no carro, se a pessoa
     * alugando é um cliente Fidelidade ou não e se os preços são para dias
     * da semana ou para fim de semana, uma vez que estes tem diferentes taxas.
     * @param fide Parametro que define se o cliente é um membro do programa de
     * Fidelidade ou não.
     * @param pessoas Parametro que contem o numero de pessoas que vão estar 
     * no carro.
     * @param carro1 Este parametro é um objeto, sendo ele de um dos tres filhos
     * do objeto locadora (southCar,westCar ou northCar), invocando o metodo
     * especifico do objeto filho para calcular qual o preço total por pessoa no
     * carro.
     * @param datas Este parametro é um ArrayList contendo todos os dias da 
     * semana que se deseja alugar um carro.
     * @return Retorna um double com o preço total por pessoas no carro.
     */
    public double calcularPreço(int fide,int pessoas, locadora carro1,ArrayList<String> datas){
        double preço = 0;
        
        for(int i=0;i<datas.size();i++){
          if(fide == 2){
          double preçoTemp = carro1.calcularPreçoNormal(datas.get(i), pessoas);
          preço = preço + preçoTemp;
          }
          if(fide == 1){
          double preçoTemp = carro1.calcularPreçoFidelidade(datas.get(i), pessoas);
          preço = preço + preçoTemp;
          }
      }
        return preço;
    }
    
    /**
     * Metodo para pegar as datas que o cliente deseja alugar o carro, e definir
     * qual dia da semana corresponde a esta data.
     * @param quantidadeDias Parametro com a quantidade de dias que se deseja
     * alugar o carro.
     * @return Retorna um ArrayList contendo todos os dias da semana que este
     * cliente deseja alugar o veiculo.
     */
    public ArrayList<String> pegarDatas(int quantidadeDias){
        ArrayList<String> datas = new ArrayList();
        for(int i = 1; i<=quantidadeDias; i++){
          System.out.println ("\nDigite o dia da " + i + " data: ");
          int dia = getDia();
          System.out.println ("Digite o mes da " + i + " data: ");
          int mes = getMes();
          System.out.println ("Digite o ano da " + i + " data: ");
          int ano = getAno();
          GregorianCalendar gc = new GregorianCalendar(ano,mes,dia);
          int dds = gc.get(GregorianCalendar.DAY_OF_WEEK); //um inteiro que representa o dia da semana (de 1 a 7, sendo domingo 1 e sábado 7)
          String diaSemana = getDiaSemana(dds);
          datas.add(diaSemana);
    }
        return datas;
    }
    
    /**
     * Metodo para ler o dia da data em que o cliente deseja alugar o veiculo.
     * @return Retorna um numero inteiro, contendo o dia digitado pelo cliente.
     */
    public int getDia(){
        int dia = inputInt();
        if(dia <= 0 || dia >= 32){
            System.out.println ("Valor invalido para um dia.");
            dia = inputInt();
        }
        return dia;
    }
    
    /**
     * Metodo para ler o mes da data em que o cliente deseja alugar o veiculo.
     * @return Retorna um numero inteiro, contendo o mes digitado pelo cliente.
     */
    public int getMes(){
        int mes = inputInt();
        if(mes <= -1 || mes >= 12){
            System.out.println ("Valor invalido para um mes.");
            mes = inputInt();
        }
        return (mes - 1);
    }
    
    /**
     * Metodo para ler o ano da data em que o cliente deseja alugar o veiculo.
     * @return Retorna um numero inteiro, contendo o ano digitado pelo cliente.
     */
    public int getAno(){
        int ano = inputInt();
        if(ano <= 1999 || ano >= 2031){
            System.out.println ("Valor invalido para um ano. Digite um ano entre 2000 e 2030");
            ano = inputInt();
        }
        return ano;
    }
    
    /**
     * Metodo que contem um menu para questionar ao cliente se participa do
     * programa de Fidelidade ou não.
     * @return Retorna um inteiro contendo 1 caso seja cliente Fidelidade ou 
     * contendo 2, caso não seja.
     */
    public int clienteFidelidade(){
        
        System.out.println ("Cliente fidelidade?");
        System.out.println ("1 - Sim");
        System.out.println ("2 - Não");
        int fide = inputInt();
        if(fide ==1 || fide == 2){
            return fide;
        }
        else{
            System.out.println ("Opçao digitada não é valida! Escolha uma das opçoes validas.");
            fide = clienteFidelidade();
        }
        return fide;
    }
    
    /**
     * Metodo para ler a quantidade de pessoas que se deseja colocar no carro
     * atraves do valor digitado pelo cliente.
     * @return Um numero inteiro contendo a quantidade de pessoas que vão
     * utilizar o veiculo.
     */
    public int quatidadePessoas(){
        
        System.out.println ("Digite a quantidade de pessoas.");
        int pessoas = inputInt();
        if(pessoas == 0 ){
            System.out.println ("Necessario pelo menos uma pessoa.\n");
            pessoas = quatidadePessoas();
            return pessoas;
        }
        if(pessoas >= 8 ){
            System.out.println ("O numero maximo de pessoas por carro é 7.\n");
            pessoas = quatidadePessoas();
            return pessoas;
        }
        else{
            return pessoas;
        }
    }
    
    /**
     * Metodo para ler a quantidade de dias que se deseja alugar o veiculo
     * atraves do valor digitado pelo cliente.
     * @return Um numero inteiro contendo a quantidade de dias que se deseja
     * alugar o veiculo.
     */
    public int quantidadeDias(){
        System.out.println ("Digite a quantidade de dias a reservar.");
        int dias = inputInt();
        if(dias == 0 ){
            System.out.println ("Necessario pelo menos um dia.\n");
            dias = quantidadeDias();
            return dias;
        }
        else{
            return dias;
        }
    }
    
    /**
     * Metodo para tratar numeros gerados pela classe GregorianCalendar,
     * transformando um numero inteiro em uma String com o dia da semana 
     * correspondente.
     * @param i Este parametro é um numero inteiro que a classe GregorianCalendar
     * gera após calcular o dia da semana de uma determinada data.
     * @return Retorna uma String contendo o dia da semana correspondente ao
     * numero inteiro passado por parametro.
     */
    public String getDiaSemana(int i){
        if(i == 1){
            return "DOM";
        }
        if(i == 2){
            return "SEG";
        }
        if(i == 3){
            return "TER";
        }
        if(i == 4){
            return "QUA";
        }
        if(i == 5){
            return "QUI";
        }
        if(i == 6){
            return "SEX";
        }
        if(i == 7){
            return "SAB";
        }
        return "";
    }
    
    /**
     * Metodo para pegar os dias da semana das datas escolhidas no arquivo
     * entrada.txt .
     * @param quantidadeDias Parametro inteiro com a quantidade de dias total.
     * @param valor Parametro do tipo ArrayList de Strings, que contem os valores
     * lidos do arquivo entrada.txt .
     * @return Retorna um ArrayList de String com os dias da semana de cada data
     * escolhida.
     */
    public ArrayList<String> pegarDiasSemana(int quantidadeDias,ArrayList<String> valor){
        ArrayList<String> datas = new ArrayList();
        int auxiliar = 1;
        for(int i = 1; i<=quantidadeDias; i++){
            int dia = Integer.parseInt(valor.get(auxiliar+3));
            int mes = Integer.parseInt(valor.get(auxiliar+4));
            int ano = Integer.parseInt(valor.get(auxiliar+5));
            GregorianCalendar gc = new GregorianCalendar(ano,mes,dia);
            int dds = gc.get(GregorianCalendar.DAY_OF_WEEK); //um inteiro que representa o dia da semana (de 1 a 7, sendo domingo 1 e sábado 7)
            String diaSemana = getDiaSemana(dds);
            datas.add(diaSemana);
            auxiliar = auxiliar + 3;
        }
        return datas;
    }
    
    /**
     * Metodo que faz a leitura do arquivo entrada.txt e salva os valores a serem
     * interpretados em uma ArrayList. Para que o arquivo consiga ser lido corretamente
     * é necessario que a ordem presente no arquivo seja mantida, e alterado
     * apenas os valores do lado direito do arquivo.
     * Ordem correta:
     * || normal - valor 1 para nao discriminar carro ou valor 2 para apenas carros premium
     * || fidelidade - valor 1 para cliente fidelidade ou valor 2 para clientes nao sejam do programa de fidelidade
     * || quantidadePessoas - numero inteiro de pessoas que vão utilizar o carro, não podendo ser maior que 7
     * || quantidadeDias - numero inteiro de dias que se deseja alugar o veiculo
     * || dia - numero inteiro e valido para um dia
     * || mes - numero inteiro e valido para um mes
     * || ano - numero inteiro e valido para um ano ||
     * Caso o numero de quantidadeDias for maior do que 1, deve-se repetir o
     * padrao acima, colocando dia, depois mes e por fim ano, de forma a ter a
     * mesma quantidade de datas no arquivo quanto o valor de quantidadeDias.
     * @return Retorna um ArrayList de String, contendo os valores a serem
     * interpretados.
     */
    public ArrayList<String> lerArquivo(){
        // The name of the file to open.
        String fileName = "entrada.txt";
        // This will reference one line at a time
        String line = null;
        ArrayList<String> valor = new ArrayList();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            String[] splited = line.split("\\s+",2);
            valor.add(splited[1]);
            }  
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
        }
        return valor;
    }
    
    /**
     * Metodo que le arquivo entrada.txt e interpreta os valores do seu conteudo.
     */
    public void interpretaArquivo(){
        ArrayList<String> valor;
        ArrayList<String> datas;
        valor = lerArquivo();
        int normal = Integer.parseInt(valor.get(0));
        int fide = Integer.parseInt(valor.get(1));
        int pessoas = Integer.parseInt(valor.get(2));
        int quantidadeDias = Integer.parseInt(valor.get(3));
        datas = pegarDiasSemana(quantidadeDias,valor);
        String nome = "";
        String locadora = "";
        double menorPreço = 100000;
        if(normal == 1){
            if(pessoas <= carroSouth.getLimitePessoas()){
            double preço1 = calcularPreço(fide,pessoas,carroSouth,datas);
            if(preço1 <= menorPreço){
                menorPreço = preço1;
                nome = carroSouth.getNome();
                locadora = carroSouth.getLocadora();
            }
        }
        }
        if(pessoas <= carroNorth.getLimitePessoas()){
        double preço3 = calcularPreço(fide,pessoas,carroNorth,datas);
        if(preço3 <= menorPreço){
            menorPreço = preço3;
            nome = carroNorth.getNome();
            locadora = carroNorth.getLocadora();
        }
        }
        if(pessoas <= carroWest.getLimitePessoas()){
        double preço2 = calcularPreço(fide,pessoas,carroWest,datas);
        if(preço2 <= menorPreço){
            menorPreço = preço2;
            nome = carroWest.getNome();
            locadora = carroWest.getLocadora();
        }
        }
        String dx = df.format(menorPreço);
        System.out.println ("Procurando menor preço de veiculo para " + pessoas + " pessoa(s),por " + quantidadeDias + " dia(s).");
        System.out.println ("O preço total por pessoa é R$" + dx + ", para um carro " + nome + " para " + quantidadeDias + " dia(s) na locadora " + locadora + ".");       
    }
    
    /**
     * Metodo para confirmar que o arquivo que se deseja ler esta de forma legivel
     * para o sistema.
     */
    public void confirmarLeitura(){
        System.out.println ("O arquivo .txt deve estar na mesma pasta do projeto");
        System.out.println ("O nome do arquivo deve ser entrada.txt");
        System.out.println ("Verificar documentação da classe lerArquivo para saber como preencher o conteudo do arquivo.");
        System.out.println ("Deseja continuar com tentativa de leitura do arquivo?");
        System.out.println ("1 - Sim");
        System.out.println ("2 - Não");
        int fide = inputInt();
        if(fide ==1 ){
            interpretaArquivo();
        }
        else{
            System.out.println ("Voltando pro menu inicial");
            menuInicial();
        }
    }
}
