

/**
 * Classe que herda da classe locadora e faz sobreescrita de todos os seus metodos.
 * Esta classe representa uma locadora que aluga veiculos esportivos.
 * @author Igor
 */

public class westCar extends locadora {
    
    private final int diariaNormal = 530;
    private final int diariaNormalFidelidade = 150;
    private final int diariaFinalSemana = 200;
    private final int diariaFinalSemanaFidelidade = 90;
    private final int limitePessoas = 2;
    private final String locadora = "WestCar";
    private String nome;
    private final String nomePadrao = "Ferrari";
    
    /**
     * Construtor da classe westCar em que um nome diferente do padrão é passado
     * por parametro.
     * @param nome Parametro do tipo String que contem o nome do veiculo a se
     * criar.
     */
    public westCar(String nome){
        this.nome = nome;
    }
    
    /**
     * Construtor da classe westCar em que o veiculo utiliza o nome padrão.
     */
    public westCar(){
        nome = nomePadrao;
    }
    
    @Override
    public String getLocadora(){
        return locadora;
    }
    
    @Override
    public String getNome(){
        return nome;
    }

    @Override
    public double getDiariaNormal(){
        return diariaNormal;
    }
    
    @Override
    public double getDiariaNormalFidelidade(){
        return diariaNormalFidelidade;
    }
    
    @Override
    public double getDiariaFinalSemana(){
        return diariaFinalSemana;
    }
    
    @Override
    public double getDiariaFinalSemanaFidelidade(){
        return diariaFinalSemanaFidelidade;
    }
    
    @Override
    public int getLimitePessoas(){
        return limitePessoas;
    }
    
   
    @Override
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    
    @Override
    public double calcularPreçoNormal(String dia, int pessoas){
        double preço = 0;
        if(dia.equals("DOM")){
            preço = diariaFinalSemana / pessoas;
        }
        if(dia.equals("SEG")){
            preço = diariaNormal / pessoas;
        }
        if(dia.equals("TER")){
            preço = diariaNormal / pessoas;
        }
        if(dia.equals("QUA")){
            preço = diariaNormal / pessoas;
        }
        if(dia.equals("QUI")){
            preço = diariaNormal / pessoas;
        }
        if(dia.equals("SEX")){
            preço = diariaNormal / pessoas;
        }
        if(dia.equals("SAB")){
            preço = diariaFinalSemana / pessoas;
        }
        return preço;
    }
    
    
    @Override
    public double calcularPreçoFidelidade(String dia, int pessoas){
        double preço = 0;
        if(dia.equals("DOM")){
            preço = diariaFinalSemanaFidelidade / pessoas;
        }
        if(dia.equals("SEG")){
            preço = diariaNormalFidelidade / pessoas;
        }
        if(dia.equals("TER")){
            preço = diariaNormalFidelidade / pessoas;
        }
        if(dia.equals("QUA")){
            preço = diariaNormalFidelidade / pessoas;
        }
        if(dia.equals("QUI")){
            preço = diariaNormalFidelidade / pessoas;
        }
        if(dia.equals("SEX")){
            preço = diariaNormalFidelidade / pessoas;
        }
        if(dia.equals("SAB")){
            preço = diariaFinalSemanaFidelidade / pessoas;
        }
        return preço;
    }
}
