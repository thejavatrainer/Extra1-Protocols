import org.junit.jupiter.api.Test;

class CurrencyRatesServiceTest {

    @Test
    void getEuroRonRate() {
        System.out.println(new CurrencyRatesService().getEuroRonRate());
    }

    @Test
    void getEuroRonRateWithURLConnection(){
        System.out.println(new CurrencyRatesService().getEuroRonRateWithUrlConnection());
    }
}