public class Process{ // Esta classe representa um processo com os atributos necessários.
    private final  int id; // Inclui getters e setters para modificação, especialmente para ciclosNecessarios e recursoNecessario.
    private final String nome;
    private final int prioridade;
    private int ciclosNecessarios;
    private String recursosNecessario;

    public Process(int id, String nome, int prioridade, int ciclosNecessarios, String recursosNecessario){
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursosNecessario = recursosNecessario;
    }
    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public int getPrioridade(){
        return prioridade;
    }
    public int getCiclosNecessarios(){
        return ciclosNecessarios;
    }
    public void setCiclosNecessarios(int ciclosNecessarios){
        this.ciclosNecessarios = ciclosNecessarios;
    }
    public String getRecursosNecessarios(){
        return recursosNecessario;
    }
    public void setRecursosNecessarios(String recursosNecessarios){
        this.recursosNecessario = recursosNecessarios;
    }
    @Override
    public String toString(){
        return "Processo[id=" + id + ", nome=" + nome + ", prioridade = " + prioridade + ", ciclosNecessarios=" + ciclosNecessarios + ", recursosNecessarios" + recursosNecessario + "]";
    }
}