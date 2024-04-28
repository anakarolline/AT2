package projeto;

class Empregado extends Thread {
    private String nomeEmpregado;
    private ContaBancaria contaSalario;
    private ContaBancaria contaInvestimento;

    public Empregado(String nome) {
        this.nomeEmpregado = nome;
        this.contaSalario = new ContaBancaria(0); // Saldo inicial da conta de salário é zero
        this.contaInvestimento = new ContaBancaria(0); // Saldo inicial da conta de investimento é zero
    }

    public static double getSalario() {
        return 1400.0;
        
    }
    public String getNome() {
        return nomeEmpregado;
    }
    public ContaBancaria getContaSalario() {
        return contaSalario;
    }

    public void setContaSalario(ContaBancaria contaSalario) {
        this.contaSalario = contaSalario;
    }

    public double getSaldoInvestimento() {
        return contaInvestimento.getSaldoCorrente();
    }
    public boolean recebeuSalario() {
        return contaSalario.getSaldoCorrente() > 0;
    }
    public double getValorConta(double valorSalario) {
        double valorInvestimento = valorSalario * 0.2; // 20% do salário para investimento
        double valorConta = valorSalario - valorInvestimento;
        return valorConta;
    }

    public void receberPagamento(double valorSalario) {
        contaSalario.creditar(valorSalario);
        double valorInvestimento = valorSalario * 0.2; // 20% do salário para investimento
        contaSalario.debitar(valorInvestimento); // Deduzindo o valor do investimento
        double valorConta = getValorConta(valorSalario);
        contaInvestimento.creditar(valorInvestimento);
        System.out.println(nomeEmpregado + " recebeu o salário e investiu R$" + valorInvestimento);
        System.out.println("Valor na conta após investir: R$" + valorConta);
    }
    
    public void pedirDemissao() {
        System.out.println( nomeEmpregado + " pediu demissão.");
    }
}
