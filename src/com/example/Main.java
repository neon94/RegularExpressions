package com.example;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "I am a sring. Yes I am.";
        System.out.println(string);
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

//        . <- matches for every char
        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".", "Y"));

//        ^ <- matches only from start of string
        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));

        String secondString = "abcDeeeF12GhhabcDeeeiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDeee", "YYY"));

        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("^abcDeee"));
//        entire string matches
        System.out.println(alphanumeric.matches("abcDeeeF12Ghhiiiijkl99z"));

        System.out.println(alphanumeric.replaceAll("ijkl99z$", " THE END"));
//        Each letter from [] will be replaced
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        System.out.println(alphanumeric.replaceAll("[aei]", "-Replaced-"));
//        Replace a, e, or i by X when these letters are followed by F or j
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
//        matches all letters expect e or j
        System.out.println(newAlphanumeric.replaceAll("[^ej]", "X"));
//        replace all letters in brackets, respecting size of letter (uppercase lowercase)
        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X"));
//        the same as before but - means range between letters in alphabetic order, the same with numbers
        System.out.println(newAlphanumeric.replaceAll("[a-f3-8]", "X"));
//        with specified size of letter
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X"));
//        key sensitivity - no metter that letter is uppercased or not
        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X"));
//        replace all digits
        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X"));
//        the same result like above
        System.out.println(newAlphanumeric.replaceAll("\\d", "X"));
//        replace all NON digits
        System.out.println(newAlphanumeric.replaceAll("\\D", "X"));

        String hasWhitespace = "I have blanks and \ta tab, and also a new line\n";
        System.out.println(hasWhitespace);
//        replace all white spaces
        System.out.println(hasWhitespace.replaceAll("\\s", ""));
//        replace tab \t
        System.out.println(hasWhitespace.replaceAll("\t", "X"));
//        replace all non white space characters (only tab, spaces, new line characters remain)
        System.out.println(hasWhitespace.replaceAll("\\S", ""));
//        replace all instead whitespaces (all  characters the same af [a-zA-Z0-9])
        System.out.println(newAlphanumeric.replaceAll("\\w", "X"));
        System.out.println(hasWhitespace.replaceAll("\\w", "X"));
//        surround all words with replacement word/char
        System.out.println(hasWhitespace.replaceAll("\\b", "X"));

        System.out.println("=====================");
        String thirdAlphanumericString = "abcDeeeF12Ghhiiiijkl99z";
//        At this example, must occur at least 3 preceding 'e'
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{3}", "YYY"));
//        At least one 'e' must occur, but replace one or more
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe+", "YYY"));
//        abcD must NOT be followed by e, to make it works
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe*", "YYY"));
//        We are looking for from 2 to 5 repeats of 'e'
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{2,5}", "YYY"));
//        replace all 'h' followed by any number of 'i' and at least one repeat of 'j'
        System.out.println(thirdAlphanumericString.replaceAll("h+i*j", "Y"));

        System.out.println("====================");

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

//        .* makes there can be anything before and after <h2>
//        String h2Pattern = ".*<h2>.*";
        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }

//        We want opening h2, closing h2 and everything between
//        .* greedy quantifier (as much text as possible)
//        .? lazy quantifier (zero or one)
//        .*? makes greedy quantifier a lazy quantifier
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find()) {
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while(h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2));
        }

//        "abc" "a" and "b" and "c"
//        [Hh]arry
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));

//        [^abc]
        String tvTest = "tstvtkt";
//        String tNotVRegExp = "t[^v]";
        String tNotVRegExp = "t(?!v)";
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;
        while(tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }
//        t(?=v)
//        Sample US phone number (800) 123-4567
//        ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$
        String phone1 = "1234567890"; // Shoundn't match
        String phone2 = "(123) 456-7890"; // Match
        String phone3 = "123 456-7890"; //Shouldn't match
        String phone4 = "(123)456-7890"; //Shouldn't match

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone1 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone1 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone1 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        // ^4[0-9]{12}([0-9]{3})?$
        String visa1 = "4444444444";
        String visa2 = "5444444444";
        String visa3 = "4444444444444444";
        String visa4 = "4444";

        System.out.println("visa1 " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));




    }
}
