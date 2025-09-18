import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Main processes.csv");
            return;
        }

        Scheduler scheduler = new Scheduler();
        try (BufferedReader br = new BufferedReader(new FileReader("src/" + args[0]))){
            String line;
            br.readLine(); // Ignora o cabeçalho
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String nome = parts[1].trim();
                int prioridade = Integer.parseInt(parts[2].trim());
                int ciclos = Integer.parseInt(parts[3].trim());
                String recurso = parts[4].trim().isEmpty() ? null : parts[4].trim();
                Process p = new Process(id, nome, prioridade, ciclos, recurso);
                scheduler.addProcess(p);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Executar ciclos
        while (!scheduler.isAllDone()) { // Método a ser adicionado em Scheduler
            scheduler.executarCicloDeCPU();
        }
        System.out.println("Simulação completa. Todos os processos terminaram.");
    }
}