package pl.wasat.smarthma.utils.text;

import org.apache.commons.lang3.SystemUtils;

public class MetadataCleaner {

	public MetadataCleaner() {
		super();
	}

	public String getCleanValue(String value) {
		String cleanValue;

		cleanValue = removeUnessesary(value);
		cleanValue = changeUperCase(cleanValue);

		return cleanValue;
	}

	public String removeUnessesary(String value) {
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

	public String changeUperCase(String inputString) {
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
