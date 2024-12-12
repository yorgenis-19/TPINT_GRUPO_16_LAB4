package negocioImpl;

import java.math.BigDecimal;
import java.util.List;

import daoImpl.CuentaDaoImpl;
import daoImpl.CuotasDaoImpl;
import entidad.Cuenta;
import negocio.CuotasNegocio;

public class CuotasNegocioImpl implements CuotasNegocio {
    private final CuotasDaoImpl cuotasDao = new CuotasDaoImpl();

    @Override
    public boolean PagarCuota(int NroCuenta, int idCuota, BigDecimal importe, String detalle) {
        // Crear una instancia del DAO de cuentas
        CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

        // Obtener la lista de cuentas asociadas al cliente
        List<Cuenta> cuentas = cuentaDao.ObtenerCuentasxClienteID(NroCuenta);

        // Verificar si la lista está vacía
        if (cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas para el cliente con ID: " + NroCuenta);
            return false;
        }

        // Usar la primera cuenta de la lista
        Cuenta cuenta = cuentas.get(0);

        // Verificar si el saldo es suficiente para realizar el pago
        BigDecimal saldoActual = BigDecimal.valueOf(cuenta.getMonto());
        if (saldoActual.compareTo(importe) < 0) {
            System.out.println("Saldo insuficiente. Saldo actual: " + saldoActual + ", Importe a pagar: " + importe);
            return false;
        }

        // Realizar el pago llamando al método del DAO de cuotas
        boolean pagoExitoso = cuotasDao.pagarCuota(
            NroCuenta,
            idCuota,
            saldoActual.subtract(importe), // Nuevo saldo después del pago
            "Pago cuota - ID" + idCuota   // Descripción del pago
        );

        return pagoExitoso;
    }
}