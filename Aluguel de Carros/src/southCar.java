

/**
 * Classe que herda da classe locadora e faz sobreescrita de todos os seus metodos.
 * Esta classe representa uma locadora que aluga veiculos compactos.
 * @author Igor
 */
public class southCar extends locadora {
    
    private final double diariaNormal = 210;
    private final double diariaNormalFidelidade = 150;
    private final double diariaFinalSemana = 200;
    private final double diariaFinalSemanaFidelidade = 90;
    private final int limitePessoas = 4;
    private final String locadora = "SouthCar";
    private String nome;
    private final String nomePadrao = "Fiesta";
    
    /**
     * Construtor da classe southCar em que um nome diferente do padrão é passado
     * por parametro.
     * @param nome Parametro do tipo String que contem o nome do veiculo a se
     * criar.
     */
    public southCar(String nome){
        this.nome = nome;
    }
    
    /**
     * Construtor da classe southCar em que o veiculo utiliza o nome padrão.
     */
    public southCar(){
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
