package resturant.business.configuration.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.util.Locale;

public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(p.getValueAsString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}



