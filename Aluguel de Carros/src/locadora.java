
/**
 * Classe abstrata que tem como objetivo definir metodos de assinatura para
 * as suas classes filhas.
 * @author Igor
 */
public abstract class locadora {
    
    /**
     * Metodo para retornar valor da diaria em dias uteis da semana.
     * @return Retorna valor double contendo o valor de uma diaria em um dia
     * util.
     */
    public abstract double getDiariaNormal();

    /**
     * Metodo para retornar valor da diaria em dias uteis da semana para cliente
     * membro do programa de Fidelidade.
     * @return Retorna valor double contendo o valor de uma diaria em um dia
     * util para cliente membro do programa de Fidelidade.
     */
    public abstract double getDiariaNormalFidelidade();

    /**
     * Metodo para retornar valor da diaria em finais de semana.
     * @return Retorna valor double contendo o valor de uma diaria em um dia
     * do final da semana.
     */
    public abstract double getDiariaFinalSemana();

    /**
     * Metodo para retornar valor da diaria em finais de semana para cliente
     * membro do programa de Fidelidade.
     * @return Retorna valor double contendo o valor de uma diaria em um dia
     * do final da semana para cliente membro do programa de Fidelidade.
     */
    public abstract double getDiariaFinalSemanaFidelidade();

    /**
     * Metodo para pegar o limite de pessoas por veiculo.
     * @return Retorna um valor inteiro com o numero maximo de pessoas
     * que podem estar dentro do veiculo.
     */
    public abstract int getLimitePessoas();

    /**
     * Metodo que retorna uma String contendo o nome da locadora em que o veiculo
     * se encontra
     * @return Retorna uma String contendo o nome da locadora.
     */
    public abstract String getLocadora();

    /**
     * Metodo para mudar o nome de um veiculo.
     * @param novoNome String contendo o novo nome do veiculo.
     */
    public abstract void setNome(String novoNome);

    /**
     * Metodo que calcula o valor do aluguel do carro para cliente normais
     * pelo numero de pessoas que vao usar o carro.
     * @param dia Parametro do tipo String contendo o dia da semana que se deseja
     * alugar o veiculo.
     * @param pessoas Parametro do tipo inteiro que contem o numero de pessoas
     * que vão utilizar o veiculo.
     * @return Retorna um double contendo o preço total por pessoas para cliente
     * normal.
     */
    public abstract double calcularPreçoNormal(String dia, int pessoas);

    /**
     * Metodo que calcula o valor do aluguel do carro para cliente Fidelidade
     * pelo numero de pessoas que vao usar o carro.
     * @param dia Parametro do tipo String contendo o dia da semana que se deseja
     * alugar o veiculo.
     * @param pessoas Parametro do tipo inteiro que contem o numero de pessoas
     * que vão utilizar o veiculo.
     * @return Retorna um double contendo o preço total por pessoas para cliente
     * Fidelidade.
     */
    public abstract double calcularPreçoFidelidade(String dia, int pessoas);

    /**
     * Metodo para retorna o nome do veiculo. 
     * @return Retorna uma String contendo o nome do veiculo.
     */
    public abstract String getNome();
}
