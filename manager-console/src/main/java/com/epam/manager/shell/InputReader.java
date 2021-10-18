package com.epam.manager.shell;

import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class InputReader {

    public static final Character DEFAULT_MASK = '*';

    @Autowired
    @Lazy
    private LineReader lineReader;
    private final Character mask;

    public InputReader() {
        this(null);
    }

    public InputReader(final Character mask) {
        this.mask = mask != null ? mask : DEFAULT_MASK;
    }

    public String prompt(final String prompt) {
        return prompt(prompt, null, true);
    }

    public String prompt(final String prompt, final String defaultValue) {
        return prompt(prompt, defaultValue, true);
    }

    public String prompt(final String prompt, final String defaultValue, final boolean echo) {
        String answer = "";
        if (echo) {
            answer = lineReader.readLine(prompt + ": " + defaultValue);
        } else {
            answer = lineReader.readLine(prompt + ": ", mask);
        }
        if (answer.isEmpty()) {
            return defaultValue;
        }
        return answer;
    }
}
