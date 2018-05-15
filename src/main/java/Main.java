import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import credits.CollateralCredit;
import credits.ConsumerCredit;
import credits.CreditData;
import services.PrintService;
import services.SelectCreditService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreditData creditData = readYaml(new File(System.getProperty("user.dir") +  "/src/main/resources/credits_data.yml"));
        List<ConsumerCredit> consumerCredits = new ArrayList<>();
        List<CollateralCredit> collateralCredits = new ArrayList<>();

        creditData.getCash_loan().forEach((k, v) -> consumerCredits.add(v));
        creditData.getCar_loan().forEach((k, v) -> collateralCredits.add(v));
        creditData.getMortgage().forEach((k, v) -> collateralCredits.add(v));

        SelectCreditService selectCreditService = new SelectCreditService(collateralCredits, consumerCredits);
        selectCreditService.runService();

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
