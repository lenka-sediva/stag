package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.SpecializationsList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        SpecializationsList specializations = new Gson().fromJson(json, SpecializationsList.class);

        Set<String> seenDates = new HashSet<>(); // ukládá zpracované datumy

        return specializations.actions.stream()
                .map(s -> s.eprDeadlinePrihlaska)
                .filter(d -> d != null && d.value != null)
                .map (d -> d.value)
                .filter(seenDates::add) // přidá datum pouze pokud už není v seenDates
                .sorted(Comparator.comparing((String date) -> {
                    String[] parts = date.split("\\.");
                    int day = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int yearPart = Integer.parseInt(parts[2]);
                    return yearPart * 10000 + month * 100 + day;
                }))
                .collect(Collectors.joining(","));
    }
}