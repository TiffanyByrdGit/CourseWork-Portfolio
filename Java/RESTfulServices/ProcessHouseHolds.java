//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    ProcessHouseHolds.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
//  Due Date:     7/13/2025
//
//  Instructor:   Fred Kumi
//
//  Chapter:      17
//
//  Description:  Performs queries on the array household objects
//                and displays results.  
//
//***************************************************************
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ProcessHouseHolds {
    private double averageIncome;

    //***************************************************************
    //
    //  Method:       getPovertyData
    // 
    //  Description:  Retrieves federal poverty guideline income thresholds
    //                for household sizes 1 through 15 via API.
    //
    //  Parameters:   None
    //
    //  Returns:      Map of household size to income threshold
    //
    //***************************************************************
    public Map<Integer, Double> getPovertyData() {
        Map<Integer, Double> povertyMap = new HashMap<>();
        String baseURL = "https://aspe.hhs.gov/topics/poverty-economic-mobility/poverty-guidelines/api/2025/us/";

        try {
            HttpClient client = HttpClient.newBuilder().build();

            for (int size = 1; size <= 15; size++) {
                String queryURI = baseURL + size;

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(queryURI))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new RuntimeException("HTTP request error: " + response.statusCode());
                }

                String body = response.body();
                JSONObject json = new JSONObject(body);
                JSONObject data = json.getJSONObject("data");
                double level = Double.parseDouble(data.get("income").toString());

                povertyMap.put(size, level);
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return povertyMap;
    }

    //***************************************************************
    //
    //  Method:       solutionPartA
    // 
    //  Description:  Displays all household records from file
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartA(ArrayList<HouseHolds> houseHoldsList, PrintWriter writer) {
        writer.println("Part A - Survey Results:");
        writer.println("-------------------------");

        houseHoldsList.stream()
            .forEach(home -> writer.printf(
                "ID Number: %-4d Annual Income: $%-10.2f Members: %-2d State: %-10s%n",
                home.getIdNum(),
                home.getAnnualIncome(),
                home.getHouseholdMembers(),
                home.getState()
            ));

        writer.println();
    }

    //***************************************************************
    //
    //  Method:       solutionPartB
    // 
    //  Description:  Calculates and displays the average annual income
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartB(ArrayList<HouseHolds> houseHoldsList, PrintWriter writer) {
        writer.println("Part B - Annual Income average:");
        writer.println("------------------------------");

        averageIncome = houseHoldsList.stream()
            .mapToDouble(HouseHolds::getAnnualIncome)
            .average()
            .orElse(0.0);

        writer.printf("Average Annual Income: $%,.2f%n", averageIncome);
        writer.println();
    }

    //***************************************************************
    //
    //  Method:       solutionPartC
    // 
    //  Description:  Displays households with income greater than average
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartC(ArrayList<HouseHolds> houseHoldsList, PrintWriter writer) {
        writer.println("Part C - Households With an annual income exceeding the average annual income:");
        writer.println("----------------------------------------------------------------------------");

        houseHoldsList.stream()
            .filter(home -> home.getAnnualIncome() > averageIncome)
            .forEach(home -> writer.printf("ID: %-4d Income: $%,10.2f Members: %-2d State: %-15s%n",
                    home.getIdNum(), home.getAnnualIncome(), home.getHouseholdMembers(), home.getState()));

        writer.println();
    }

    //***************************************************************
    //
    //  Method:       solutionPartD
    // 
    //  Description:  Displays households with income below poverty level
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                povertyMap - map of FPL thresholds
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartD(ArrayList<HouseHolds> houseHoldsList, Map<Integer, Double> povertyMap, PrintWriter writer) {
        writer.println("Part D - Households Below Federal Poverty Level:");
        writer.println("---------------------------------------------------");
        writer.printf("%-10s %-12s %-15s %-10s %-10s%n", 
                      "ID Number", "Annual Income", "Poverty Level", "Household Members", "State");

        for (HouseHolds home : houseHoldsList) {
            int members = home.getHouseholdMembers();
            double income = home.getAnnualIncome();
            Double povertyLevel = povertyMap.get(members);

            if (povertyLevel != null && income < povertyLevel) {
                writer.printf("%-10d $%-11.2f $%-14.2f %-10d %-10s%n", 
                              home.getIdNum(), income, povertyLevel, members, home.getState());
            }
        }

        writer.println();
    }

    //***************************************************************
    //
    //  Method:       solutionPartE
    // 
    //  Description:  Calculates percentage of households below poverty
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                povertyMap - map of FPL thresholds
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartE(ArrayList<HouseHolds> houseHoldsList, Map<Integer, Double> povertyMap, PrintWriter writer) {
        long belowPoverty = houseHoldsList.stream()
            .filter(home -> {
                Double povertyLevel = povertyMap.get(home.getHouseholdMembers());
                return povertyLevel != null && home.getAnnualIncome() < povertyLevel;
            })
            .count();

        double percentage = (belowPoverty * 100.0) / houseHoldsList.size();

        writer.println("Part E - Percentage Below Federal Poverty Level:");
        writer.println("-----------------------------------------------");
        writer.printf("Households below FPL: %d out of %d (%.2f%%)%n%n", 
                      belowPoverty, houseHoldsList.size(), percentage);
    }

    //***************************************************************
    //
    //  Method:       solutionPartF
    // 
    //  Description:  Calculates households qualifying for Medicaid
    //
    //  Parameters:   houseHoldsList - list of household objects
    //                povertyMap - map of FPL thresholds
    //                writer - file output stream
    //
    //  Returns:      None
    //
    //***************************************************************
    public void solutionPartF(ArrayList<HouseHolds> houseHoldsList, Map<Integer, Double> povertyMap, PrintWriter writer) {
        writer.println();
        writer.println("Part F - Percentage of households that qualify for Medicaid:");
        writer.println("-----------------------------------------------------------");

        long qualifyingCount = houseHoldsList.stream()
            .filter(home -> {
                int members = home.getHouseholdMembers();
                double income = home.getAnnualIncome();
                double povertyLevel = povertyMap.getOrDefault(members, 0.0);
                return income <= (povertyLevel * 1.38);
            })
            .count();

        double percentage = (double) qualifyingCount / houseHoldsList.size() * 100;

        writer.printf("Total qualifying households: %d\n", qualifyingCount);
        writer.printf("Percentage that qualifies for Medicaid: %.2f%%\n", percentage);
        writer.println();
    }

    //***************************************************************
    //
    //  Method:       readHouseHoldData
    // 
    //  Description:  Reads household data from Program3.txt file
    //
    //  Parameters:   None
    //
    //  Returns:      Populated household list
    //
    //***************************************************************
    public ArrayList<HouseHolds> readHouseHoldData() {
        ArrayList<HouseHolds> houseHoldsList = new ArrayList<>();
        Program3 program3Obj = new Program3();

        program3Obj.openFile();
        program3Obj.readRecords(houseHoldsList);

        return houseHoldsList;
    }
}
