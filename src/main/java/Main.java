import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import credits.CollateralCredit;
import credits.ConsumerCredit;
import services.CreditData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreditData creditData = readYaml(new File("/Users/mgerasimchuk/IdeaProjects/Herasymchuk/src/main/resources/credits_data.yml"));
        final List<ConsumerCredit> consumerCredits = new ArrayList<>();
        List<CollateralCredit> collateralLoans = new ArrayList<>();

        creditData.getCash_loan().forEach((k, v) -> consumerCredits.add(v));
        creditData.getCar_loan().forEach((k, v) -> collateralLoans.add(v));
        creditData.getMortgage().forEach((k, v) -> collateralLoans.add(v));

        consumerCredits.get(0).print();
        collateralLoans.get(0).print();

    }

    private static CreditData readYaml(File file) {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(file, CreditData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
