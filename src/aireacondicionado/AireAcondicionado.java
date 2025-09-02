package aireacondicionado;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class AireAcondicionado {

    public static void main(String[] args) {
        String filename = "C:/Users/User/Documents/NetBeansProjects/AireAcondicionado/src/files/aire_acondicionado.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            System.err.println("No se pudo cargar el archivo: " + filename);
            return;
        }

        fis.setVariable("temperatura", 14);
        fis.setVariable("humedad", 79);
        fis.setVariable("personas", 12);

        fis.evaluate();

        Variable potencia  = fis.getVariable("potencia");

        System.out.println("Potencia del aire acondicionado: " + potencia .getValue());

        JFuzzyChart.get().chart(potencia, potencia.getDefuzzifier(), true);
    }
    
}
