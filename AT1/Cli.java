package projeto;

class Comprador extends Thread {
    private String nomeCliente;
    private ContaBancaria contaBancaria;
    private BancoCentral bancoCentral;
    private Filial[] filiais;

    public Comprador(String nomeCliente, double saldoInicial, BancoCentral bancoCentral, Filial... filiais) {
        this.nomeCliente = nomeCliente;
        this.contaBancaria = new ContaBancaria(saldoInicial);
        this.bancoCentral = bancoCentral;
        this.filiais = filiais;
    }

    @Override
    public void run() {
        while (contaBancaria.getSaldoCorrente() > 0) {
            double valorCompra = 100;
            for (Filial filial : filiais) {
                bancoCentral.transferencia(contaBancaria, filial.getConta(), valorCompra);
                filial.receberPagamento(valorCompra);
                System.out.println("Transferência de R$" + valorCompra + " da conta do " + nomeCliente + " para a conta da " + filial.getNome());
            }
            try {
                Thread.sleep(1000); // Aguarda um segundo entre as compras
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nomeCliente + " concluiu suas compras.");
    }
}
