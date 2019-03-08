package pt.hlbk.orders;

import java.util.Objects;
import java.util.StringJoiner;

public class CurrencyPair {

    private final Currency ccy1;
    private final Currency ccy2;

    public CurrencyPair(final Currency ccy1,
                        final Currency ccy2) {
        this.ccy1 = ccy1;
        this.ccy2 = ccy2;
    }

    public static CurrencyPair of(final String symbol) {
        Objects.requireNonNull(symbol, "Provided symbol cannot be null");
        final String[] split = symbol.split("/");
        if (split.length != 2) {
            throw new IllegalArgumentException("Provided symbol " + symbol + " is invalid");
        }
        final Currency ccy1 = Currency.valueOf(split[0]);
        final Currency ccy2 = Currency.valueOf(split[1]);
        return new CurrencyPair(ccy1, ccy2);
    }

    public String toSymbol() {
        return ccy1.name() + "/" + ccy2.name();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CurrencyPair.class.getSimpleName() + "[", "]")
                .add("ccy1=" + ccy1)
                .add("ccy2=" + ccy2)
                .toString();
    }
}
