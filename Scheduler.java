public class Scheduler {
    private final ListaDeProcessos listaDeAltaPrioridade;
    private final ListaDeProcessos listaDeMediaPrioridade;
    private final ListaDeProcessos listaDeBaixaPrioridade;
    private final ListaDeProcessos listaDeBloqueados;
    private int contadorCiclosAltaPrioridade;

    public Scheduler() {
        listaDeAltaPrioridade = new ListaDeProcessos();
        listaDeMediaPrioridade = new ListaDeProcessos();
        listaDeBaixaPrioridade = new ListaDeProcessos();
        listaDeBloqueados = new ListaDeProcessos();
        contadorCiclosAltaPrioridade = 0;
    }

    public void addProcess(Process P) {
        switch (P.getPrioridade()) {
            case 1 -> listaDeAltaPrioridade.addToEnd(P);
            case 2 -> listaDeMediaPrioridade.addToEnd(P);
            case 3 -> listaDeBaixaPrioridade.addToEnd(P);
            default -> {
            }
        }
    }

    public void executarCicloDeCPU() {
        System.out.println("== Início do Ciclo ==");
        System.out.println("Alta Prioridade: " + listaDeAltaPrioridade);
        System.out.println("Média Prioridade: " + listaDeMediaPrioridade);
        System.out.println("Baixa Prioridade: " + listaDeBaixaPrioridade);
        System.out.println("Bloqueados: " + listaDeBloqueados);

        Process unblocked = listaDeBloqueados.removeFromFront();
        if (unblocked != null) {
            unblocked.setRecursosNecessarios(null);
            addProcess(unblocked);
            System.out.println("Evento: Desbloqueio de Processo " + unblocked.getId() + " (" + unblocked.getNome() + ")");
        }

        // <-- ajustado: declarar sem inicializar para evitar o hint "assigned value is never used"
        Process toRun;
        if (contadorCiclosAltaPrioridade >= 5) {
            toRun = listaDeMediaPrioridade.removeFromFront();
            if (toRun == null) {
                toRun = listaDeBaixaPrioridade.removeFromFront();
            }
            if (toRun != null) {
                contadorCiclosAltaPrioridade = 0;
            }
        } else {
            toRun = listaDeAltaPrioridade.removeFromFront();
            if (toRun == null) {
                toRun = listaDeMediaPrioridade.removeFromFront();
            }
            if (toRun == null) {
                toRun = listaDeBaixaPrioridade.removeFromFront();
            }
        }

        if (toRun == null) {
            System.out.println("Nenhum processo para executar.");
            System.out.println("== Fim do Ciclo ==");
            return;
        }

        if ("DISCO".equals(toRun.getRecursosNecessarios())) {
            listaDeBloqueados.addToEnd(toRun);
            System.out.println("Evento: Processo " + toRun.getId() + " (" + toRun.getNome() + ") Bloqueado por DISCO.");
        } else {
            toRun.setCiclosNecessarios(toRun.getCiclosNecessarios() - 1);
            System.out.println("Processo em execução: " + toRun.getId() + " (" + toRun.getNome() + ")");
            if (toRun.getCiclosNecessarios() <= 0) {
                System.out.println("Evento: Término de Processo " + toRun.getId() + " (" + toRun.getNome() + ")");
            } else {
                addProcess(toRun);
            }
            if (toRun.getPrioridade() == 1) {
                contadorCiclosAltaPrioridade++;
            } else {
                contadorCiclosAltaPrioridade = 0;
            }
        }

        System.out.println("== Fim do Ciclo ==");
    }

    public boolean allDone() {
        return listaDeAltaPrioridade.isEmpty() &&
               listaDeMediaPrioridade.isEmpty() &&
               listaDeBaixaPrioridade.isEmpty() &&
               listaDeBloqueados.isEmpty();
    }

    public boolean isAllDone() {
        return listaDeAltaPrioridade.isEmpty() &&
               listaDeMediaPrioridade.isEmpty() &&
               listaDeBaixaPrioridade.isEmpty() &&
               listaDeBloqueados.isEmpty();
    }
}
