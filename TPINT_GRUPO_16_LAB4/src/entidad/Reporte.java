package entidad;

public class Reporte {
	private int totalMovimientos;
    private double totalPrestamos;
    private double saldoTotal;

    // Getters y Setters
    public int getTotalMovimientos() {
        return totalMovimientos;
    }

    public void setTotalMovimientos(int totalMovimientos) {
        this.totalMovimientos = totalMovimientos;
    }

    public double getTotalPrestamos() {
        return totalPrestamos;
    }

    public void setTotalPrestamos(double totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }
}
