package base;

import helpers.LogHelper;
import keyword.KeywordWeb;
import mySQL.MonGoDb;
import mySQL.PostGre;
import org.slf4j.Logger;
import utilities.ExcelReader;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class BasePage {
    protected Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    protected ExcelReader excelReader;
    public BasePage() {
        keyword = new KeywordWeb();
        excelReader = new ExcelReader();
    }
    public BasePage(KeywordWeb keyword) {
        this.keyword = keyword;
    }
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
