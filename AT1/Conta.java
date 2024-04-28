package projeto;

class ContaBancaria {
    private double saldoCorrente;
    private double saldoTotal;

    public ContaBancaria(double saldoInicial) {
        this.saldoCorrente = saldoInicial;
    }

    public double getSaldoCorrente() {
        return saldoCorrente;
    }

    public double getSaldoTotal(){
        return saldoTotal;
    }

    public synchronized void creditar(double valor) {
        saldoCorrente += valor;
    }

    public synchronized void debitar(double valor) {
        if (valor <= saldoCorrente) {
            saldoCorrente -= valor;
        } else {
            System.out.println("Saldo insuficiente para realizar a operação.");
        }
    }

    public String getTitular() {
        return ""; // Adicione um método para retornar o titular da conta se necessário
    }
}
