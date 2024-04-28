package projeto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Loja extends Thread {
    private String nome;
    private Conta conta;
    private double saldoTotal;
    private Lock lock;
    private List<Funcionario> funcionarios;

    public Loja(String nome, double saldoInicial) {
        this.nome = nome;
        this.conta = new Conta(saldoInicial);
        this.saldoTotal = saldoInicial;
        this.lock = new ReentrantLock();
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public boolean atingiuSaldoMinimo() {
        return conta.getSaldo() >= 1400;
    }

    public void pagarSalarios() {
        lock.lock();
        try {
            double valorSalario = Funcionario.getSalario();
            for (Funcionario funcionario : funcionarios) {
                if (saldoTotal >= valorSalario) {
                    conta.debitar(valorSalario);
                    funcionario.receberPagamento(valorSalario);
                    System.out.println("Salário pago para o " + funcionario.getNome() + " da " + nome);
                    saldoTotal -= valorSalario;
                } else {
                    System.out.println("Saldo insuficiente na " + nome + " para pagar o salário do  " + funcionario.getNome());
                    funcionario.pedirDemissao();
                    funcionarios.remove(funcionario);
                    break; // Interrompe o loop se o saldo for insuficiente para pagar um funcionário
                }
            }
    
            // Exibir o saldo final da conta da loja
            System.out.println("Saldo final da conta da " + nome + ": R$" + saldoTotal);
        } finally {
            lock.unlock();
        }
    }
    

    public double getSaldoLoja() {
        return conta.getSaldo();
    }

    
    public synchronized void receberPagamento(double valor) {
        lock.lock();
        try {
            saldoTotal += valor;
        } finally {
            lock.unlock();
        }
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
}