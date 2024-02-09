package faturamento;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class RevenueAnalysis {
    public static void main(String[] args) {
        String pathToJson = "C:\\Users\\ruffl\\OneDrive\\Documentos\\ws-eclipse\\faturamento-diario\\dados.json";
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(pathToJson)));
            JSONArray revenueData = new JSONArray(content);

            double minRevenue = Double.MAX_VALUE;
            double maxRevenue = Double.MIN_VALUE;
            double sumRevenue = 0;
            int revenueDays = 0;
            int aboveAverageDays = 0;

            for (int i = 0; i < revenueData.length(); i++) {
                JSONObject dayRevenue = revenueData.getJSONObject(i);
                double revenue = dayRevenue.getDouble("valor");
                
                if (revenue > 0) {
                    revenueDays++;
                    sumRevenue += revenue;
                    if (revenue < minRevenue) {
                        minRevenue = revenue;
                    }
                    if (revenue > maxRevenue) {
                        maxRevenue = revenue;
                    }
                }
            }

            double averageRevenue = sumRevenue / revenueDays;

            for (int i = 0; i < revenueData.length(); i++) {
                JSONObject dayRevenue = revenueData.getJSONObject(i);
                double revenue = dayRevenue.getDouble("valor");

                if (revenue > averageRevenue) {
                    aboveAverageDays++;
                }
            }

            System.out.println("Menor valor de faturamento: " + minRevenue);
            System.out.println("Maior valor de faturamento: " + maxRevenue);
            System.out.println("Número de dias com faturamento acima da média: " + aboveAverageDays);
        } catch (IOException e) {
            System.out.println("Deu erro: " + e);
        }
    }
}

