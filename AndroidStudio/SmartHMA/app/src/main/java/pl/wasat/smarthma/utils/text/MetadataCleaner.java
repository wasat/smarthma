package pl.wasat.smarthma.utils.text;

import org.apache.commons.lang3.SystemUtils;

public class MetadataCleaner {

    public MetadataCleaner() {
        super();
    }

    public static String getCleanOMValue(String value) {
        String cleanValue;
        cleanValue = removeUnnecessaryOMValues(value);
        cleanValue = changeUpperCase(cleanValue);
        return cleanValue;
    }

    public static String getCleanISOValue(String value) {
        String cleanValue;
        cleanValue = removeUnnecessaryISOValues(value);
        cleanValue = changeUpperCase(cleanValue);
        return cleanValue;
    }

    public static String getCleanDCValue(String value) {
        String cleanValue;
        cleanValue = removeUnnecessaryDCValues(value);
        cleanValue = changeUpperCase(cleanValue);
        return cleanValue;
    }

    private static String removeUnnecessaryOMValues(String value) {
        String cleanedValue = value;

        String newLine = SystemUtils.LINE_SEPARATOR;
        String doubleNewLine = SystemUtils.LINE_SEPARATOR
                + SystemUtils.LINE_SEPARATOR;
        String spaceTwoNewLine = SystemUtils.LINE_SEPARATOR + " "
                + SystemUtils.LINE_SEPARATOR + " ";

        cleanedValue = cleanedValue.replaceAll(
                "\\[processingInformation - samplingRate - \\[\\]", "");
        cleanedValue = cleanedValue.replaceAll("posString - pointsString - ",
                "");

        cleanedValue = cleanedValue.replaceAll(
                "acquisition - illuminationAzimuthAngle",
                "illuminationAzimuthAngle");
        cleanedValue = cleanedValue.replaceAll(
                "browseInformation - fileName - serviceReference - ",
                "serviceReference - ");
        cleanedValue = cleanedValue.replaceAll(
                "exterior - linearRing - posList - \\[value - ", "Point List: "
                        + newLine);
        cleanedValue = cleanedValue.replaceAll("_xmlns - xmlns", "");
        cleanedValue = cleanedValue.replaceAll(newLine + "unit -", "");

        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.trim().replaceAll(" +", " ");
        cleanedValue = cleanedValue.replaceAll("\\[", "");
        cleanedValue = cleanedValue.replaceAll("\\]", "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.replaceAll(" _", "");
        cleanedValue = cleanedValue.replaceAll("_", "");
        cleanedValue = cleanedValue.replaceAll(newLine + ", ", "," + newLine);
        cleanedValue = cleanedValue.replaceAll(spaceTwoNewLine, "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);

        cleanedValue = cleanedValue.replaceAll(newLine + "serviceReference - ",
                doubleNewLine + "serviceReference - ");
        cleanedValue = cleanedValue.replaceAll(" - value - ", " -> ");
        cleanedValue = cleanedValue.replaceAll("value - ", "");
        return cleanedValue;

    }


    private static String removeUnnecessaryISOValues(String value) {
        String cleanedValue = value;

        String newLine = SystemUtils.LINE_SEPARATOR;
        String doubleNewLine = SystemUtils.LINE_SEPARATOR
                + SystemUtils.LINE_SEPARATOR;
        String spaceTwoNewLine = SystemUtils.LINE_SEPARATOR + " "
                + SystemUtils.LINE_SEPARATOR + " ";

        cleanedValue = cleanedValue.replaceAll("CharacterString - Text - ", "");
        cleanedValue = cleanedValue.replaceAll("CIDate - dateInCIDate - dateGco - Text - ", "");
        cleanedValue = cleanedValue.replaceAll("Decimal - Text - ", "");
        cleanedValue = cleanedValue.replaceAll("Text - ", "");
        cleanedValue = cleanedValue.replaceAll("_abstract", "abstract");
        cleanedValue = cleanedValue.replaceAll("CharacterString", "name");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.trim().replaceAll(" +", " ");
        cleanedValue = cleanedValue.replaceAll("\\[", "");
        cleanedValue = cleanedValue.replaceAll("\\]", "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.replaceAll(" _", "");
        cleanedValue = cleanedValue.replaceAll("_", "");
        cleanedValue = cleanedValue.replaceAll(newLine + ", ", "," + newLine);
        cleanedValue = cleanedValue.replaceAll(spaceTwoNewLine, "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = changeUpperCase(cleanedValue);
        return cleanedValue;
    }

    private static String removeUnnecessaryDCValues(String value) {
        String cleanedValue = value;

        String newLine = SystemUtils.LINE_SEPARATOR;
        String doubleNewLine = SystemUtils.LINE_SEPARATOR
                + SystemUtils.LINE_SEPARATOR;
        String spaceTwoNewLine = SystemUtils.LINE_SEPARATOR + " "
                + SystemUtils.LINE_SEPARATOR + " ";

        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.replaceAll(spaceTwoNewLine, "");
        cleanedValue = cleanedValue.replaceAll("Text - ", "");

        return cleanedValue;

    }

    private static String changeUpperCase(String inputString) {
        if (inputString.isEmpty()) {
            return inputString;
        }
        String outputString = String.valueOf(inputString.charAt(0));

        for (int i = 1; i < inputString.length(); i++) {
            char cThis = inputString.charAt(i);
            char cPrev = inputString.charAt(i - 1);
            if (Character.isUpperCase(cThis) && Character.isLowerCase(cPrev)) {
                outputString = outputString + " "
                        + Character.toLowerCase(cThis);
            } else {
                outputString = outputString + cThis;
            }
        }
        return outputString;
    }

}
