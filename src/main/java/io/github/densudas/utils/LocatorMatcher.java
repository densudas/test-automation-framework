package io.github.densudas.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocatorMatcher {

  private static final String LOCATOR_REGEX = "^By\\.(\\w+): (.*)$";

  private final How locatorType;
  private String locator;

  public LocatorMatcher(String locatorType, String locator) {
    this.locatorType = How.valueOf(locatorType.toUpperCase());
    this.locator = locator;
  }

  public LocatorMatcher(How locatorType, String locator) {
    this.locatorType = locatorType;
    this.locator = locator;
  }

  public LocatorMatcher(By by) {
    List<String> matchLocatorRegex = matchLocatorRegex(by);

    if (matchLocatorRegex.isEmpty() || matchLocatorRegex.size() < 2) {
      throw new IllegalArgumentException(
          "Locator By '" + by + "' can not be matched by regex '" + LOCATOR_REGEX + "'");
    }

    this.locatorType = getLocatorType(matchLocatorRegex.get(1));
    this.locator = matchLocatorRegex.get(2);
  }

  private How getLocatorType(String locatorTypeString) {
    return switch (locatorTypeString.toUpperCase()) {
      case "LINKTEXT" -> How.LINK_TEXT;
      case "PARTIALLINKTEXT" -> How.PARTIAL_LINK_TEXT;
      case "TAGNAME" -> How.TAG_NAME;
      case "CLASSNAME" -> How.CLASS_NAME;
      case "CSSSELECTOR" -> How.CSS;
      default -> How.valueOf(locatorTypeString.toUpperCase());
    };
  }

  private List<String> matchLocatorRegex(By locator) {
    if (locator == null) {
      throw new IllegalArgumentException("Instance of class " + By.class + " is null.");
    }

    List<String> groups = new ArrayList<>();
    Matcher matcher = Pattern.compile(LOCATOR_REGEX).matcher(locator.toString());
    if (matcher.find()) {
      for (int i = 0; i <= matcher.groupCount(); i++) {
        groups.add(matcher.group(i));
      }
    }
    return groups;
  }

  public String getLocator() {
    return locator;
  }

  public How getLocatorType() {
    return locatorType;
  }

  public By formatWithName(String name) {
    locator = String.format(this.locator, name);
    return buildBy();
  }

  public By buildBy() {
    return locatorType.buildBy(locator);
  }
}