package aireacondicionado;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class AireAcondicionado {

    public static void main(String[] args) {
        try {
            String filename = "src/files/aire_acondicionado.fcl";
            FIS fis = FIS.load(filename, true);
            if (fis == null) {
                System.err.println("No se pudo cargar el archivo: " + filename);
                return;
            }

            // Si se pasan argumentos desde la terminal, los usamos
            double temperatura = (args.length > 0) ? Double.parseDouble(args[0]) : 22;
            double humedad = (args.length > 1) ? Double.parseDouble(args[1]) : 60;
            double personas = (args.length > 2) ? Double.parseDouble(args[2]) : 5;
            
            if (temperatura < 0 || temperatura > 40) {
                System.err.println("Temperatura fuera de rango (0 - 40). Se usará el valor por defecto (22).");
                temperatura = 22;
            }

            if (humedad < 0 || humedad > 100) {
                System.err.println("Humedad fuera de rango (0 - 100). Se usará el valor por defecto (60).");
                humedad = 60;
            }

            if (personas < 0 || personas > 50) {
                System.err.println("Cantidad de personas fuera de rango (0 - 50). Se usará el valor por defecto (5).");
                personas = 5;
            }
            
            fis.setVariable("temperatura", temperatura);
            fis.setVariable("humedad", humedad);
            fis.setVariable("personas", personas);

            fis.evaluate();

            Variable potencia = fis.getVariable("potencia");

            System.out.println("===== RESULTADO =====");
            System.out.println("Temperatura: " + temperatura + " °C");
            System.out.println("Humedad: " + humedad + " %");
            System.out.println("Personas: " + personas);
            System.out.println("Potencia del aire acondicionado: " + potencia.getValue());

            JFuzzyChart.get().chart(potencia, potencia.getDefuzzifier(), true);
        } catch (NumberFormatException e) {
            System.err.println("Error: Los argumentos deben ser valores numéricos.");
        } catch (Exception e) {
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
